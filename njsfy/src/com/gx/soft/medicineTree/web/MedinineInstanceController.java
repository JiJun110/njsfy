package com.gx.soft.medicineTree.web;

import com.alibaba.fastjson.JSONObject;
import com.gx.core.hibernate.PropertyFilter;
import com.gx.core.mapper.BeanMapper;
import com.gx.core.page.Page;
import com.gx.core.util.StringUtils;
import com.gx.core.util.ZipUtils;
import com.gx.soft.common.util.DateUtil;
import com.gx.soft.common.util.FileUtil;
import com.gx.soft.medicineTree.persistence.domain.Attachment;
import com.gx.soft.medicineTree.persistence.domain.MedicineInstance;
import com.gx.soft.medicineTree.persistence.domain.MedicineType;
import com.gx.soft.medicineTree.persistence.manager.AttachMentManager;
import com.gx.soft.medicineTree.persistence.manager.MedicineInstanceManager;
import com.gx.soft.medicineTree.persistence.manager.MedicineTypeManager;
import com.gx.soft.sys.persistence.domain.VUser;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by adminstrator on 2019/9/11.
 */
@Controller
@SessionAttributes("user_session")
@RequestMapping("njsfy-medicineInstance")
public class MedinineInstanceController {
    private BeanMapper beanMapper = new BeanMapper();
    @Autowired
    private MedicineInstanceManager medicineInstanceManager;
    @Autowired
    private MedicineTypeManager medicineTypeManager;
    @Autowired
    private AttachMentManager attachMentManager;

    private String bol_a = null;
    private String bol_c = null;
    private List<String> rowIdList = new ArrayList<String>();

    @RequestMapping(value = "medicineInstance-list")
    public String showColumnArticleManagerList(@ModelAttribute Page page,
                                               @RequestParam Map<String, Object> parameterMap,
                                               Model model, @ModelAttribute("user_session") VUser user) {
        page.addOrder("createTime", "desc");
        List<PropertyFilter> propertyFilters = PropertyFilter.buildFromMap(parameterMap);

        page = medicineInstanceManager.pagedQuery(page, propertyFilters);

        model.addAttribute("page", page);
        return "medicineTree/medicineInstance/medicineInstance-list";
    }

    @RequestMapping("medicineInstance-input")
    public String medicineInstanceInput(String bol, Model model) {
        this.bol_a = bol;
        MedicineInstance medicineInstance = new MedicineInstance();
        model.addAttribute("bol_a", bol_a);
        model.addAttribute("medicineInstance", medicineInstance);
        return "medicineTree/medicineInstance/medicineInstance-input";
    }

    @RequestMapping("lookup-medicineType-list")
    public String orgLookupList(@ModelAttribute Page page,
                                @RequestParam Map<String, Object> parameterMap, Model model) {

        List<PropertyFilter> propertyFilters = PropertyFilter
                .buildFromMap(parameterMap);
        page = medicineTypeManager.pagedQuery(page, propertyFilters);
        model.addAttribute("page", page);

        return "medicineTree/medicineInstance/lookup-medicineType-list";

    }

    @RequestMapping(value = "remove")
    @ResponseBody
    public Map<String, Object> reMove(String bol_b, String delids) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String statusCode = "200", message = "删除成功";
        try {
            if (delids != null && delids.length() > 0) {
                String[] ids = delids.split(",");
                for (String rowId : ids) {
                    if (rowId != null && rowId.length() > 0) {
                        medicineInstanceManager.removeById(rowId);
                    }
                }
            }
        } catch (Exception e) {
            statusCode = "300";
            message = "删除失败";
        }
        resMap.put("statusCode", statusCode);
        resMap.put("message", message);
        if (bol_b != null && bol_b.equals("b")) {
            resMap.put("divid", "article-manager-view-list");
        } else {
            resMap.put("reload", true);
        }
        return resMap;
    }

    @RequestMapping(value = "fileDownload")
    @ResponseBody
    public Map<String, Object> fileDownLoad(String rowId, HttpServletRequest request,
                                            HttpServletResponse response) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        FileUtil fileHelper = new FileUtil();
        fileHelper.downloadFile("D:\\apache-tomcat-njsfy\\webapps\\file-moban\\sj.xlsx", request, response, "sj.xlsx");
        resMap.put("statusCode", "200");
        resMap.put("message", "下载成功");
        return resMap;
    }
    
    /**
     * 批量下载
     *
     * @param request  请求
     * @param response 返回
     */
    @RequestMapping(value = "fileDownload-attach")
    @ResponseBody
    public Map<String, Object> batchDownloadFiles(String delids, HttpServletRequest request, HttpServletResponse response) {

        //读取前端传来json字段
        String[] ids = delids.split(",");
    /*    String jsonString = request.getParameter("paperInfo");*/

        //获取web项目根目录
        String fileSaveRootPath = request.getSession().getServletContext().getRealPath("/");

        //创建zip文件并返回zip文件路径
        List<String> filaPathList = new ArrayList<>();
        List<String> fileNameList = new ArrayList<>();
        for (String id : ids) {
            Attachment fileRecord = attachMentManager.get(id);
            if (fileRecord != null) {
                filaPathList.add(fileRecord.getFilePath());
                fileNameList.add(fileRecord.getFileName());
            }
        }
        if (filaPathList.size() == 1) {
            FileUtil fileHelper = new FileUtil();
            fileHelper.downloadFile(filaPathList.get(0), request, response, fileNameList.get(0));
        } else {
            String zipPath = new ZipUtils().createZipAndReturnPath(JSONObject.toJSONString(filaPathList), JSONObject.toJSONString(fileNameList), fileSaveRootPath);
            try {
                response.reset();
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/zip;charset=utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=Papers.zip");
                System.out.println(response.getHeader("Content-Disposition"));

                //开始下载
                BufferedInputStream is = new BufferedInputStream(new FileInputStream(new File(zipPath)));
                BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());

                byte[] buff = new byte[1024];
                int len = 0;
                while ((len = is.read(buff, 0, buff.length)) != -1) {
                    out.write(buff, 0, len);
                }
                out.close();
                out.flush();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("statusCode", "200");
        resMap.put("message", "下载成功");
        return resMap;
    }

    @RequestMapping(value = "fileDownload-attach123")
    @ResponseBody
    public JSONObject fileDownLoadAttach(String delids, HttpServletRequest request,
                                         HttpServletResponse response) throws IOException {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String[] ids = delids.split(",");
        resMap.put("ids", ids);

        for (String id : ids) {
            Attachment fileRecord = attachMentManager.get(id);
            if (fileRecord != null) {
                String filePath = fileRecord.getFilePath();
                String fileName = fileRecord.getFileName();
                System.out.println(fileName);
                FileUtil fileHelper = new FileUtil();

                fileHelper.downloadFile(filePath, request, response, fileName);
            } else {
                resMap.put("statusCode", "300");
                resMap.put("message", "下载失败");
            }
        }
        resMap.put("statusCode", "200");
        resMap.put("message", "下载成功");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mapRecordList", "qweweqweq");
        return jsonObject;

    }


    @RequestMapping("delete-attach")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam String delids) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<String> row_ides = new ArrayList<>();
        String statusCode = "200", message = "删除成功";

        try {
            if (delids != null && delids.length() > 0) {
                String[] ids = delids.split(",");
                for (String rowId : ids) {
                    if (rowId != null && rowId.length() > 0 && !rowId.equals("on")) {
                        row_ides.add(rowId);
                        String filePath = attachMentManager.get(rowId).getFilePath();
                        FileUtil fileHelper = new FileUtil();
                        boolean isDelete = true;
                        try {
                            fileHelper.deleteFile(filePath);
                            if (!isDelete) {
                                statusCode = "300";
                                message = "删除失败";
                            }
                        } catch (Exception e) {
                            statusCode = "300";
                            e.printStackTrace();
                        }
                        attachMentManager.removeById(rowId);
                        rowIdList.remove(rowId);
                        resMap.put("nowRowId", rowId);
                    }
                }
            }
        } catch (Exception e) {
            statusCode = "300";
            message = "删除失败";
        }
        resMap.put("statusCode", statusCode);
        resMap.put("message", message);
        resMap.put("row_ides", row_ides);
        return resMap;
    }

    @RequestMapping(value = "medicineInstance-import")
    public String medicineTreeImport() {
        return "medicineTree/medicineInstance/medicineInstance-import";
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
    @RequestMapping("fileupload-attach1")
    public void c(){

    }
    @RequestMapping("fileupload-attach")
    @ResponseBody
    public Map<String, Object> fileUploadAttach(@RequestParam MultipartFile file,
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
                if (!decodeFileName.contains(".jpg")) {
                    if (!decodeFileName.contains(".png")) {
                        decodeFileName += "jpg";
                    }

                }
               /* String decodeFileName = fileHelper.getDecodeFileName(fileOriginalName);*/// 文件名编码
                String mFilePath = session.getServletContext().getRealPath("") + decodeFileName; // 取得服务器路径
                mFilePath = mFilePath.substring(0, 2) + "\\njsfy" + "\\moban\\" + decodeFileName;
                String uuid = UUID.randomUUID().toString();
                String pathName = File.separator + uuid + "."
                        + FilenameUtils.getExtension(file.getOriginalFilename());
                String oldPath = "D:\\img\\" + pathName;
          /*      fileHelper.createFile(oldPath, file.getBytes());
                PicUtil.commpressPicForScaleSize(oldPath,
                        mFilePath, 1000, 0.25);*/

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
        if (attachment.getRowId() != null) {
            rowIdList.add(attachment.getRowId());
        }
        return resMap;
    }

    @RequestMapping(value = "medicineInstance-save", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> save(MedicineInstance medicineInstance, @ModelAttribute("user_session") VUser user) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String statusCode = "200", message = "操作成功";
        Timestamp ts = DateUtil.getDate();
        try {
            MedicineInstance dest;
            String id = medicineInstance.getRowId();
            if (id != null && id.length() > 0) {
                dest = medicineInstanceManager.get(id);
                String rowID = id;
                dest.setUploadTime(ts);
                dest.setUploadUser(user.getUserName());
                for (int i = 0; i < rowIdList.size(); i++) {
                    Attachment attachment = attachMentManager.get(rowIdList.get(i));
                    attachment.setRelationId(rowID);
                    attachMentManager.save(attachment);
                }
                beanMapper.copy(medicineInstance, dest);
            } else {
                medicineInstance.setRowId(null);
                dest = medicineInstance;
                dest.setCreateTime(ts);
                dest.setCreateUser(user.getUserName().trim());
            }
            dest.setWarn(dest.getWarn().trim());
            dest.setSyz(dest.getSyz().trim());
            dest.setYfyl(dest.getYfyl().trim());
            dest.setTsCcTj(dest.getTsCcTj().trim());
            dest.setcSmSy(dest.getcSmSy().trim());
            medicineInstanceManager.save(dest);
            String rowID = dest.getRowId();
            if (rowID != null) {
                for (int i = 0; i < rowIdList.size(); i++) {
                    Attachment attachment = attachMentManager.get(rowIdList.get(i));
                    attachment.setRelationId(rowID);
                    attachMentManager.save(attachment);
                }
                rowIdList.clear();
            }
        } catch (Exception e) {
            statusCode = "300";
            message = "操作失败";
            e.printStackTrace();
        }
        resMap.put("statusCode", statusCode);
        resMap.put("message", message);

        if (bol_a != null && bol_a.equals("a")) {
            resMap.put("divid", "article-manager-view-list");
        } else if (bol_c != null && bol_c.equals("c")) {
            resMap.put("divid", "article-manager-view-list");
        } else {
            resMap.put("reload", true);
        }
        resMap.put("closeCurrent", true);
        this.bol_a = null;
        this.bol_c = null;
        return resMap;
    }

    @RequestMapping("edit")
    public String edit(@RequestParam(value = "rowId", required = false) String rowId, String bol, Model model) {
        MedicineInstance medicineInstance;
        medicineInstance = medicineInstanceManager.get(rowId);
        String hql = "from Attachment where relationId=?";
        ArrayList<Attachment> fileRecordList = (ArrayList<Attachment>) attachMentManager.find(hql, rowId);
        model.addAttribute("medicineInstance", medicineInstance);
        model.addAttribute("fileRecordList", fileRecordList);
        bol_c = bol;
        if (bol == null) {
            bol = "A";
        }
        model.addAttribute("bol", bol);
        return "medicineTree/medicineInstance/medicineInstance-edit";
    }

    @RequestMapping("look")
    public String look(@RequestParam(value = "rowId", required = false) String rowId, Model model) {
        MedicineInstance medicineInstance;
        medicineInstance = medicineInstanceManager.get(rowId);
        String hql = "from Attachment where relationId=?";
        ArrayList<Attachment> fileRecordList = (ArrayList<Attachment>) attachMentManager.find(hql, rowId);
        model.addAttribute("medicineInstance", medicineInstance);
        model.addAttribute("fileRecordList", fileRecordList);
        return "medicineTree/medicineInstance/medicineInstance-look";
    }

    @RequestMapping("import-save")
    public
    @ResponseBody
    Map<String, Object> UserSaveImport(String fileUrl, HttpSession session, String orgId) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String statusCode = "200", message = "操作成功";

        Workbook wb0 = null;
        Timestamp ts = DateUtil.getDate();
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            InputStream in = new FileInputStream(fileUrl);
            wb0 = WorkbookFactory.create(in);
         /*   Sheet hssfSheet = workbook.getSheetAt(0);
            wb0 = new XSSFWorkbook(in);*/


            Sheet sheet = wb0.getSheetAt(0);
            int rowCount = 0;
            for (Row row : sheet) {
                /*if (row.getCell(1) == null) {
                    break;
                }*/
                if (rowCount == 0) {
                    Boolean bol = true;
                    if (row.getCell(0) != null) {
                        row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(0).getStringCellValue().trim().equals("章节")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (row.getCell(1) != null) {
                        row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(1).getStringCellValue().trim().equals("药品名称")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (row.getCell(2) != null) {
                        row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(2).getStringCellValue().trim().equals("药品规格")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (row.getCell(3) != null) {
                        row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(3).getStringCellValue().trim().equals("价格")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (row.getCell(4) != null) {
                        row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(4).getStringCellValue().trim().equals("厂商")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (row.getCell(5) != null) {
                        row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(5).getStringCellValue().trim().equals("警示")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (row.getCell(6) != null) {
                        row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(6).getStringCellValue().trim().equals("适应症")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (row.getCell(7) != null) {
                        row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(7).getStringCellValue().trim().equals("用法用量")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (row.getCell(8) != null) {
                        row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(8).getStringCellValue().trim().equals("哺乳期安全等级")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (row.getCell(9) != null) {
                        row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(9).getStringCellValue().trim().equals("孕期安全等级")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (row.getCell(10) != null) {
                        row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(10).getStringCellValue().trim().equals("是否基药")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (row.getCell(11) != null) {
                        row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(11).getStringCellValue().trim().equals("是否高危药")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (row.getCell(12) != null) {
                        row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(12).getStringCellValue().trim().equals("医保类别")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (row.getCell(13) != null) {
                        row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(13).getStringCellValue().trim().equals("药品所属目录")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (row.getCell(14) != null) {
                        row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(14).getStringCellValue().trim().equals("特殊存储条件")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (row.getCell(15) != null) {
                        row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(15).getStringCellValue().trim().equals("超说明使用")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (row.getCell(16) != null) {
                        row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
                        if (!row.getCell(16).getStringCellValue().trim().equals("是否皮试")) {
                            bol = false;
                        }
                    } else {
                        bol = false;
                    }
                    if (!bol) {
                        statusCode = "300";
                        message = "操作失败,请使用我们提供的模板操作";
                        resMap.put("statusCode", statusCode);
                        resMap.put("message", message);
                        resMap.put("reload", true);
                        //  resMap.put("divid", "user-manager-user-list1");
                        return resMap;
                    }
                }
                if (rowCount != 0) {
                    int cBol = 0;
                    String medicineTypeName = null;
                    if (row.getCell(0) != null) {
                        row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                        medicineTypeName = row.getCell(0).getStringCellValue();
                    }
                    if (medicineTypeName == null || medicineTypeName.length() == 0) {
                        cBol++;
                    }
                    String medicineName = null;
                    if (row.getCell(1) != null) {
                        row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                        medicineName = row.getCell(1).getStringCellValue();
                    }
                    if (medicineName == null || medicineName.length() == 0) {
                        cBol++;
                    }
                    String yaoPingGuiGe = null;
                    if (row.getCell(2) != null) {
                        row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                        yaoPingGuiGe = row.getCell(2).getStringCellValue();
                    }
                    if (yaoPingGuiGe == null || yaoPingGuiGe.length() == 0) {
                        cBol++;
                    }
                    String price = null;
                    if (row.getCell(3) != null) {
                        row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                        price = row.getCell(3).getStringCellValue();
                    }
                    if (price == null || price.length() == 0) {
                        cBol++;
                    }
                    String changShang = null;
                    if (row.getCell(4) != null) {
                        row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                        changShang = row.getCell(4).getStringCellValue();
                    }
                    if (changShang == null || changShang.length() == 0) {
                        cBol++;
                    }
                    String warn = null;
                    if (row.getCell(5) != null) {
                        row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                        warn = row.getCell(5).getStringCellValue();
                    }
                    if (warn == null || warn.length() == 0) {
                        cBol++;
                    }
                    String shiYingZheng = null;
                    if (row.getCell(6) != null) {
                        row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                        shiYingZheng = row.getCell(6).getStringCellValue();
                    }
                    if (shiYingZheng == null || shiYingZheng.length() == 0) {
                        cBol++;
                    }
                    String yongFaYongLiang = null;
                    if (row.getCell(7) != null) {
                        row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                        yongFaYongLiang = row.getCell(7).getStringCellValue();
                    }
                    if (yongFaYongLiang == null || yongFaYongLiang.length() == 0) {
                        cBol++;
                    }
                    String buRuiQiAnQuanDengJi = null;
                    if (row.getCell(8) != null) {
                        row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                        buRuiQiAnQuanDengJi = row.getCell(8).getStringCellValue();
                    }
                    if (buRuiQiAnQuanDengJi == null || buRuiQiAnQuanDengJi.length() == 0) {
                        cBol++;
                    }
                    String yunQiAnQuanDengJi = null;
                    if (row.getCell(9) != null) {
                        row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                        yunQiAnQuanDengJi = row.getCell(9).getStringCellValue();
                    }
                    String shiFuJiYao = null;
                    if (row.getCell(10) != null) {
                        row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                        shiFuJiYao = row.getCell(10).getStringCellValue();

                    }
                    String sfGaoWeiYao = null;
                    if (row.getCell(11) != null) {
                        row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                        sfGaoWeiYao = row.getCell(11).getStringCellValue();
                    }
                    String yiBaoLeiBie = null;
                    if (row.getCell(12) != null) {
                        row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                        yiBaoLeiBie = row.getCell(12).getStringCellValue();
                    }
                    String yiPingShusuMulu = null;
                    if (row.getCell(13) != null) {
                        row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                        yiPingShusuMulu = row.getCell(13).getStringCellValue();

                    }
                    String teShuTiaoJian = null;
                    if (row.getCell(14) != null) {
                        row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                        teShuTiaoJian = row.getCell(14).getStringCellValue();
                    }
                    String chaoShuoMingShiYong = null;
                    if (row.getCell(15) != null) {
                        row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                        chaoShuoMingShiYong = row.getCell(15).getStringCellValue();
                    }
                    String sfPs = null;
                    if (row.getCell(16) != null) {
                        row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
                        sfPs = row.getCell(16).getStringCellValue();
                    }
                    if (cBol >= 4) {
                        resMap.put("statusCode", statusCode);
                        resMap.put("message", message);
                        resMap.put("reload", true);
                        resMap.put("closeCurrent", true);
                        // resMap.put("co")
                        //resMap.put("divid", "user-manager-user-list1");

                        return resMap;
                    }

                    MedicineInstance medicineInstance = new MedicineInstance();
                    MedicineType medicineType = null;
                    medicineType = medicineTypeManager.findUniqueBy("medicineTypeName", medicineTypeName.trim());
                    if (medicineType == null) {
                        System.out.println("*******************************");
                        System.out.println(medicineTypeName);
                        System.out.println(cBol);
                        System.out.println("*******************************");
                        statusCode = "300";
                        message = "操作失败,请检查类别："+medicineTypeName;
                        resMap.put("statusCode", statusCode);
                        resMap.put("message", message);
                        resMap.put("reload", true);
                        resMap.put("closeCurrent", true);
                        //resMap.put("divid", "user-manager-user-list1");
                        return resMap;
                    } else {
                        medicineInstance.setMedicineType(medicineType.getMedicineTypeId());
                        medicineInstance.setMedicineTypeName(medicineTypeName.trim());
                        if (medicineName != null && medicineName.length() > 0) {
                            medicineInstance.setMedicineName(medicineName);
                        }
                        if (yaoPingGuiGe != null && yaoPingGuiGe.length() > 0) {
                            medicineInstance.setMedicineGuige(yaoPingGuiGe);
                        }
                        if (price != null && price.length() > 0) {
                            medicineInstance.setPrice(Double.parseDouble(price));
                        }
                        if (changShang != null && changShang.length() > 0) {
                            medicineInstance.setChangShang(changShang);
                        }
                        if (warn != null && warn.length() > 0) {
                            medicineInstance.setWarn(warn);
                        }
                        if (shiYingZheng != null && shiYingZheng.length() > 0) {
                            medicineInstance.setSyz(shiYingZheng);
                        }
                        if (yongFaYongLiang != null && yongFaYongLiang.length() > 0) {
                            medicineInstance.setYfyl(yongFaYongLiang);
                        }
                        if (buRuiQiAnQuanDengJi != null && buRuiQiAnQuanDengJi.length() > 0) {
                            medicineInstance.setBrqAqdj(buRuiQiAnQuanDengJi);
                        }
                        if (yunQiAnQuanDengJi != null && yunQiAnQuanDengJi.length() > 0) {
                            medicineInstance.setYqAqdj(yunQiAnQuanDengJi);
                        }
                        if (shiFuJiYao != null && shiFuJiYao.length() > 0) {
                            medicineInstance.setIsJy(shiFuJiYao);
                        }
                        if (sfGaoWeiYao != null && sfGaoWeiYao.length() > 0) {
                            medicineInstance.setIsGwy(sfGaoWeiYao);
                        }
                        if (yiBaoLeiBie != null && yiBaoLeiBie.length() > 0) {
                            medicineInstance.setYbType(yiBaoLeiBie);
                        }
                        if (yiPingShusuMulu != null && yiPingShusuMulu.length() > 0) {
                            medicineInstance.setYpSsMl(yiPingShusuMulu);
                        }
                        if (teShuTiaoJian != null && teShuTiaoJian.length() > 0) {
                            medicineInstance.setTsCcTj(teShuTiaoJian);
                        }
                        if (chaoShuoMingShiYong != null && chaoShuoMingShiYong.length() > 0) {
                            medicineInstance.setcSmSy(chaoShuoMingShiYong);
                        }
                        if (sfPs != null && sfPs.length() > 0) {
                            medicineInstance.setIsPs(sfPs);
                        }
                        medicineInstance.setCreateTime(ts);
                        medicineInstance.setRowId(null);

                        medicineInstanceManager.save(medicineInstance);
                    }
                }
                rowCount++;

            }
        } catch (Exception e) {
            statusCode = "300";
            message = "操作失败";
            e.printStackTrace();
        }
        resMap.put("statusCode", statusCode);
        resMap.put("message", message);
        resMap.put("reload", true);
        resMap.put("closeCurrent", true);
        //resMap.put("divid", "user-manager-user-list1");

        return resMap;

    }

}
