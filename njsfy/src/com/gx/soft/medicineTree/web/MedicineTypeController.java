package com.gx.soft.medicineTree.web;

import com.gx.core.hibernate.PropertyFilter;
import com.gx.core.page.Page;
import com.gx.core.util.StringUtils;
import com.gx.soft.common.util.FileUtil;
import com.gx.soft.medicineTree.persistence.domain.Attachment;
import com.gx.soft.medicineTree.persistence.domain.MedicineType;
import com.gx.soft.medicineTree.persistence.manager.AttachMentManager;
import com.gx.soft.medicineTree.persistence.manager.MedicineInstanceManager;
import com.gx.soft.medicineTree.persistence.manager.MedicineTypeManager;
import com.gx.soft.sys.persistence.domain.GxSysUser;
import com.gx.soft.sys.persistence.domain.GxSysUserInOrgCopy;
import com.gx.soft.sys.persistence.domain.VUser;
import com.gx.soft.sys.vo.ZtreeData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by adminstrator on 2019/9/11.
 */
@Controller
@RequestMapping("njsfy-medicineTree")
public class MedicineTypeController {
    @Autowired
    private MedicineTypeManager medicineTypeManager;
    @Autowired
    private AttachMentManager attachMentManager;
    @Autowired
    private MedicineInstanceManager medicineInstanceManager;

    @RequestMapping("medicineTree-manager")
    public String kshjdManagerPage(Model model) {
        return "medicineTree/medicineTree-manager";
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
            ztreeData.add(zData);
        }
        return ztreeData;
    }

    @RequestMapping(value = "medicineTree-list")
    public String showColumnArticleManagerList(@ModelAttribute Page page,
                                               String columnId, @RequestParam Map<String, Object> parameterMap,
                                               Model model, @ModelAttribute("user_session") VUser user) {
        page.addOrder("createTime", "desc");
        StringUtils.setDefualtStringIfNull(columnId);
        parameterMap.put("filter_EQS_medicineType", columnId);
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);

        page = medicineInstanceManager.pagedQuery(page, propertyFilters);

        model.addAttribute("page", page);
        model.addAttribute("columnId", columnId);
        return "medicineTree/medicineTree-list";
    }

    @RequestMapping(value = "medicineTree-import")
    public String medicineTreeImport() {
        return "medicineTree/medicineTree-import";
    }



    @RequestMapping("fileupload")
    @ResponseBody
    public Map<String, Object> fileUpload(@RequestParam MultipartFile file,
                                          Model model, HttpSession session
    ) throws IOException {
        Map<String, Object> resMap = new HashMap<String, Object>();
        VUser user = (VUser) session.getAttribute("user_session");
        resMap.put("user", user);
        model.addAttribute("message", "File '" + file.getOriginalFilename());
//        String bol="yes";
        Attachment attachment = new Attachment();
        String fileOriginalName = file.getOriginalFilename();
        String statusCode = "200", message = "上传成功";
        try {
            if (!StringUtils.isEmpty(fileOriginalName)) {
                FileUtil fileHelper = new FileUtil();
                String decodeFileName = fileHelper.getDecodeFileName(fileOriginalName);// 文件名编码
                String mFilePath = session.getServletContext().getRealPath("") + decodeFileName; // 取得服务器路径
                mFilePath = mFilePath.substring(0, 2) + "\\njsfy" + "\\moban\\" + decodeFileName;
                fileHelper.createFile(mFilePath, file.getBytes());
                attachment.setFilePath(mFilePath);
                attachment.setUploadUserName(user.getUserId());
                attachment.setUploadUserId(user.getUserName());
                attachment.setFileName(fileOriginalName);
                Timestamp time = new Timestamp(System.currentTimeMillis());
                attachment.setUploadTime(time);
                attachment.setFileType(file.getContentType());
                attachment.setFileIdentifyName(decodeFileName);
                attachMentManager.save(attachment);
                message = "请选择上传文件";
                statusCode = "300";
            }

        } catch (Exception e) {
            statusCode = "300";
            message = "上传失败";
//            bol="no";
            e.printStackTrace();
        }
        resMap.put("AttachEntity", attachment);
//        if(fileRecord.getRowId()!=null) {
//            rowIdList.add(fileRecord.getRowId());
//        }
        return resMap;
    }

    @RequestMapping("import-save")
    public
    @ResponseBody
    Map<String, Object> UserSaveImport(String fileUrl, HttpSession session, String orgId) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String statusCode = "200", message = "操作成功";

        Workbook wb0 = null;
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            InputStream in = new FileInputStream(fileUrl);

            wb0 = new XSSFWorkbook(in);


            Sheet sheet = wb0.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getCell(1) == null) {
                    break;
                }

            }
        } catch (Exception e) {
            statusCode = "300";
            message = "操作失败";
            e.printStackTrace();
        }
        resMap.put("statusCode", statusCode);
        resMap.put("message", message);
        resMap.put("closeCurrent", true);
        resMap.put("divid", "user-manager-user-list1");

        return resMap;

    }

}
