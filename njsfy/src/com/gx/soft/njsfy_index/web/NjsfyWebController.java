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


    private List<MedicineInstance>medicineInstanceList1=new ArrayList<>();
    @Autowired
    private MedicineTypeManager medicineTypeManager;
    private String name=null;
    private int pageNumber=0;
    private int numberSize=0;
    @Autowired
    private MedicineInstanceManager medicineInstanceManager;
    @RequestMapping("home")
    public String home(@RequestParam Map<String, Object> parameterMap,String searchMedicine, String searchYl, String searchSyz, Model model){
        medicineInstanceList1=new ArrayList<>();
        this.name=null;
        this.pageNumber=0;
        this.numberSize=0;
        List<MedicineInstance>medicineInstanceList=new ArrayList<>();
        if(searchMedicine!=null && searchMedicine.length()>0){
            String hql="from MedicineInstance where medicineName like ?";
            String t1="%"+String.valueOf(searchMedicine)+"%";
            model.addAttribute("serachName",searchMedicine);
            this.name=searchMedicine;
            medicineInstanceList=medicineInstanceManager.find(hql,t1);

        }else if(searchYl !=null && searchYl.length()>0){
            String hql="from MedicineInstance where yfyl like ?";
            model.addAttribute("serachName",searchYl);
            this.name=searchYl;
            medicineInstanceList=medicineInstanceManager.find(hql,"%"+searchYl+"%");

        }else if(searchSyz !=null && searchSyz.length()>0){
            String hql="from MedicineInstance where syz like ?";
            model.addAttribute("serachName",searchSyz);
            this.name=searchSyz;
            medicineInstanceList=medicineInstanceManager.find(hql,"%"+searchSyz+"%");
        }
        if(medicineInstanceList.size()>0){
            if(medicineInstanceList.size()>10) {
                medicineInstanceList1=medicineInstanceList;
                model.addAttribute("medicineInstanceList", medicineInstanceList.subList(0, 10));
            }else{
                model.addAttribute("medicineInstanceList", medicineInstanceList);
                medicineInstanceList1=medicineInstanceList;
            }
            model.addAttribute("count",medicineInstanceList.size());
            this.numberSize=medicineInstanceList.size();
            int pageAllCount=0;
            if(medicineInstanceList.size()%10==0){
                pageAllCount=medicineInstanceList.size()/10;
                this.pageNumber=pageAllCount;
            }else{
                pageAllCount=medicineInstanceList.size()/10+1;
                this.pageNumber=pageAllCount;
            }
            model.addAttribute("pageAllCount",pageAllCount);
        }else{
            model.addAttribute("count",0);
            this.numberSize=0;

        }
        model.addAttribute("curNumber",1);
        model.addAttribute("nextNumber",2);
        return "njsfy_index/search";

    }
    @RequestMapping(value = "chage-number", produces = "application/json")

    public String chageCurentNumber(int number,Model model){
        if(number!=pageNumber && medicineInstanceList1.size()>0){
            model.addAttribute("medicineInstanceList",medicineInstanceList1.subList((number-1)*10,number*10));
        }else{
            model.addAttribute("medicineInstanceList",medicineInstanceList1.subList((number-1)*10,this.numberSize));

        }
        model.addAttribute("pageAllCount",this.pageNumber);
        model.addAttribute("count", this.numberSize);
        model.addAttribute("serachName",this.name);
        if(number!=1){
            model.addAttribute("curNumber",number-1);
        }else{
            model.addAttribute("curNumber",number);
        }
        if(number==pageNumber){
            model.addAttribute("nextNumber",number);
        }else{
            model.addAttribute("nextNumber",number+1);
        }
        return  "njsfy_index/search";
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
