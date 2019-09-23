package com.gx.soft.njsfy_index.web;

import com.gx.soft.medicineTree.persistence.domain.MedicineInstance;
import com.gx.soft.medicineTree.persistence.manager.MedicineInstanceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adminstrator on 2019/9/23.
 */
@Controller
@RequestMapping("njsfy-index")
public class NjsfyWebController {
    @Autowired
    private MedicineInstanceManager medicineInstanceManager;
    @RequestMapping("home")
    public String home(String searchMedicine,String searchYl,String searchSyz,Model model){
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
}
