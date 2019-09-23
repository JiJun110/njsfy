package com.gx.soft.sjdjPz.web;

import com.gx.core.hibernate.PropertyFilter;
import com.gx.core.mapper.BeanMapper;
import com.gx.core.page.Page;
import com.gx.soft.common.util.DateUtil;
import com.gx.soft.sjdjPz.persistence.domain.SjdjPz;
import com.gx.soft.sjdjPz.persistence.manager.SjdjPzManager;
import com.gx.soft.sys.persistence.domain.VUser;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adminstrator on 2019/9/17.
 */
@Controller
@RequestMapping("njsfy-sjdjpz")
public class SjdjPzController {
    private BeanMapper beanMapper = new BeanMapper();
    @Autowired
    private SjdjPzManager sjdjPzManager;
    @RequestMapping(value = "sjdjpz-list")
    public String showColumnArticleManagerList(@ModelAttribute Page page,
                                               @RequestParam Map<String, Object> parameterMap,
                                               Model model, @ModelAttribute("user_session") VUser user) {
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);

        page = sjdjPzManager.pagedQuery(page, propertyFilters);

        model.addAttribute("page", page);
        return "sjdjPz/sjdjPz-list";
    }
    @RequestMapping("sjdjpz-edit")
    public String medicineInstanceInput(String bean, Model model) {
        SjdjPz sjdjPz=null;
        sjdjPz=sjdjPzManager.get(bean);
        model.addAttribute("sjdjPz", sjdjPz);
        return "sjdjPz/sjdjPz-edit";
    }
    @RequestMapping(value = "sjdjpz-save", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> save(SjdjPz sjdjPz, @ModelAttribute("user_session") VUser user) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String statusCode = "200", message = "操作成功";
        Timestamp ts = DateUtil.getDate();
        try {
            SjdjPz dest;
            String id = sjdjPz.getRowId();
            if (id != null && id.length() > 0) {
                dest = sjdjPzManager.get(id);
                beanMapper.copy(sjdjPz, dest);
                dest.setUploadTime(ts);
            }else {
                sjdjPz.setRowId(null);
                dest = sjdjPz;
                dest.setCreateTime(ts);

            }
            sjdjPzManager.save(dest);
            updateConfig(dest.getUrl(),dest.getUserName(),dest.getUserPwd());

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
    public void updateConfig(String url,String userName,String pwd) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("//application.properties");

        config.setProperty("db.default.username", userName);
        config.setProperty("db.default.password", pwd);
        System.out.println(config.getString("db.default.username"));
        System.out.println(config.getString("db.default.password"));

        config.save();
        System.out.println("配置文件更新成功");

    }
}
