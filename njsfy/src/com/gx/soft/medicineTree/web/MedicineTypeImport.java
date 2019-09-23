package com.gx.soft.medicineTree.web;

import com.gx.core.hibernate.PropertyFilter;
import com.gx.core.mapper.BeanMapper;
import com.gx.core.page.Page;
import com.gx.soft.common.util.DateUtil;
import com.gx.soft.medicineTree.persistence.domain.MedicineInstance;
import com.gx.soft.medicineTree.persistence.domain.MedicineType;
import com.gx.soft.medicineTree.persistence.manager.MedicineInstanceManager;
import com.gx.soft.medicineTree.persistence.manager.MedicineTypeManager;
import com.gx.soft.sys.persistence.domain.VUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adminstrator on 2019/9/11.
 */
@Controller
@RequestMapping("njsfy-medicineImport")
public class MedicineTypeImport {
    private BeanMapper beanMapper = new BeanMapper();
    @Autowired
    private MedicineTypeManager medicineTypeManager;
    @Autowired
    private MedicineInstanceManager medicineInstanceManager;

    @RequestMapping(value = "medicineImport-list")
    public String showColumnArticleManagerList(@ModelAttribute Page page,
                                               @RequestParam Map<String, Object> parameterMap,
                                               Model model, @ModelAttribute("user_session") VUser user) {
        page.addOrder("createTime", "desc");
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);

        page = medicineTypeManager.pagedQuery(page, propertyFilters);

        model.addAttribute("page", page);
        return "medicineTree/medicineImport/medicineImport-list";
    }

    @RequestMapping("medicineImport-input")
    public String input( String bean, Model model) {
        MedicineType medicineType = new MedicineType();
        if (bean != null) {
            medicineType = medicineTypeManager.get(bean);
        }
        model.addAttribute("medicineType", medicineType);
        return "medicineTree/medicineImport/medicineImport-input";
    }
    @RequestMapping("medicineImport-type-list")
    public String orgLookupList(@ModelAttribute Page page,
                                @RequestParam Map<String, Object> parameterMap, Model model) {

        List<PropertyFilter> propertyFilters = PropertyFilter
                .buildFromMap(parameterMap);
        page = medicineTypeManager.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);

        return "medicineTree/medicineImport/medicineImport-type-lookup";

    }
    @RequestMapping(value = "medicineImport-save", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> save(MedicineType medicineType, @ModelAttribute("user_session") VUser user) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String statusCode = "200", message = "操作成功";
        Timestamp ts = DateUtil.getDate();
        try {
            MedicineType dest ;
            String id = medicineType.getRowId();
            if (id != null && id.length() > 0) {
                dest = medicineTypeManager.get(id);
                beanMapper.copy(medicineType, dest);
                dest.setUploadTime(ts);
            } else {
                medicineType.setRowId(null);
                dest = medicineType;
                dest.setCreateTime(ts);

            }
            dest.setMedicineTypeName(dest.getMedicineTypeName().trim());
            if(dest.getMedicineParentTypeName()!=null){
                dest.setMedicineParentTypeName(dest.getMedicineParentTypeName().trim());
            }
            medicineTypeManager.save(dest);
            dest.setMedicineTypeId(dest.getRowId());
            medicineTypeManager.save(dest);
        } catch (Exception e) {
            statusCode = "300";
            message = "操作失败";
            e.printStackTrace();
        }
        resMap.put("statusCode", statusCode);
        resMap.put("message", message);
        resMap.put("closeCurrent", true);
        return resMap;
    }
    @RequestMapping("remove")
    @ResponseBody
    public Map<String, Object> viewRemove(@RequestParam("delides") String delides) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String statusCode = "200", message = "删除成功";
        try {
            if (delides != null && delides.length() > 0) {
                String[] ids = delides.split(",");
                for (String rowId : ids) {
                    if (rowId != null && rowId.length() > 0) {
                        MedicineType medicineType=medicineTypeManager.get(rowId);
                        if(medicineType.getMedicineParentTypeId()==null || medicineType.getMedicineParentTypeId().equals("root")){
                           List<MedicineType>medicineTypeList= medicineTypeManager.findBy("medicineParentTypeId",rowId);
                           if(medicineTypeList.size()>0){
                               resMap.put("statusCode", "300");
                               resMap.put("message", "删除失败,请把本药品类别下所有药品类别删光!");
                               return resMap;

                           }
                        }else{
                            List<MedicineInstance>medicineInstanceList=medicineInstanceManager.findBy("medicineType",rowId);
                            if(medicineInstanceList.size()>0){
                                resMap.put("statusCode", "300");
                                resMap.put("message", "删除失败,请把本药品类别下所有药品删光!");
                                return resMap;
                            }
                        }

                        medicineTypeManager.removeById(rowId);
                    }
                }
            }
        } catch (Exception e) {
            statusCode = "300";
            message = "删除失败";
        }
        resMap.put("statusCode", statusCode);
        resMap.put("message", message);
        resMap.put("divid", "article-manager-view-list");
        return resMap;
    }
}
