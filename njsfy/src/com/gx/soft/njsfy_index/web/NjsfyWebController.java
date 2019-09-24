package com.gx.soft.njsfy_index.web;

import com.gx.core.hibernate.PropertyFilter;
import com.gx.soft.medicineTree.persistence.domain.MedicineInstance;
import com.gx.soft.medicineTree.persistence.domain.MedicineType;
import com.gx.soft.medicineTree.persistence.manager.MedicineInstanceManager;
import com.gx.soft.medicineTree.persistence.manager.MedicineTypeManager;
import com.gx.soft.sys.vo.ZtreeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by adminstrator on 2019/9/23.
 */
@Controller
@RequestMapping("njsfy-index")
public class NjsfyWebController {
    @Autowired
    private MedicineTypeManager medicineTypeManager;
    @Autowired
    private MedicineInstanceManager medicineInstanceManager;
    @RequestMapping("home")
    public String home(@RequestParam Map<String, Object> parameterMap,String searchMedicine, String searchYl, String searchSyz, Model model){
        List<MedicineInstance>medicineInstanceList=new ArrayList<>();
        if(searchMedicine!=null && searchMedicine.length()>0){
            String hql="from MedicineInstance where medicineName like ?";
            String t1="'%"+String.valueOf(searchMedicine)+"%'";
            medicineInstanceList=medicineInstanceManager.find(hql,t1);

        }else if(searchYl !=null && searchYl.length()>0){
            String hql="from MedicineInstance where yfyl like ?";
            medicineInstanceList=medicineInstanceManager.find(hql,"%"+searchYl+"%");

        }else if(searchSyz !=null && searchSyz.length()>0){
            String hql="from MedicineInstance where syz like ?";
            medicineInstanceList=medicineInstanceManager.find(hql,"%"+searchSyz+"%");
        }
        if(medicineInstanceList.size()>0){
            model.addAttribute("medicineInstanceList",medicineInstanceList);
        }


        return "njsfy_index/detailed";

    }
    @RequestMapping(value = "medicineTree-tree", produces = "application/json")
    public
    @ResponseBody
    List<ZtreeData> visTree(@RequestParam Map<String, Object> parameterMap, Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);
        List<MedicineType> visList = medicineTypeManager.find("createTime", true, propertyFilters);
        List<ZtreeData> ztreeData = new ArrayList<ZtreeData>();
        for (MedicineType vis : visList) {
            ZtreeData zData = new ZtreeData(vis.getRowId(), vis.getMedicineParentTypeId(), vis.getMedicineTypeName(), vis.getMedicineTypeName());
            String hql="from MedicineInstance where medicineType=?";
            List<MedicineInstance>medicineInstanceList=new ArrayList<>();
            medicineInstanceList=medicineInstanceManager.find(hql,vis.getRowId());
            if(medicineInstanceList.size()>0){
                for(MedicineInstance medicineInstance:medicineInstanceList){
                    ZtreeData zData1 = new ZtreeData(medicineInstance.getRowId(), vis.getRowId(), medicineInstance.getMedicineName(), medicineInstance.getMedicineName());
                    ztreeData.add(zData1);
                }
            }
            ztreeData.add(zData);
        }
        return ztreeData;
    }
}
