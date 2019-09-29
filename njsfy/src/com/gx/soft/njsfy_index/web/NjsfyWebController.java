package com.gx.soft.njsfy_index.web;

import com.alibaba.fastjson.JSONObject;
import com.gx.core.hibernate.PropertyFilter;
import com.gx.core.util.ZipUtils;
import com.gx.soft.common.util.FileUtil;
import com.gx.soft.medicineTree.persistence.domain.Attachment;
import com.gx.soft.medicineTree.persistence.domain.MedicineInstance;
import com.gx.soft.medicineTree.persistence.domain.MedicineType;
import com.gx.soft.medicineTree.persistence.domain.VChangshang;
import com.gx.soft.medicineTree.persistence.manager.AttachMentManager;
import com.gx.soft.medicineTree.persistence.manager.MedicineInstanceManager;
import com.gx.soft.medicineTree.persistence.manager.MedicineTypeManager;
import com.gx.soft.medicineTree.persistence.manager.VChangshangManager;
import com.gx.soft.sys.vo.ZtreeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * Created by adminstrator on 2019/9/23.
 */
@Controller
@RequestMapping("njsfy-index")
public class NjsfyWebController {


    private List<MedicineInstance>medicineInstanceList1=new ArrayList<>();
    private List<VChangshang>changShangList1=new ArrayList<>();
    @Autowired
    private MedicineTypeManager medicineTypeManager;
    @Autowired
    private AttachMentManager attachMentManager;
    @Autowired
    private VChangshangManager vChangshangManager;
    private String name=null;
    private int pageNumber=0;
    private int numberSize=0;
    @Autowired
    private MedicineInstanceManager medicineInstanceManager;
    @RequestMapping("home")
    public String home(@RequestParam Map<String, Object> parameterMap,String searchCS,String searchMedicine, String searchYl, String searchSyz,String bolS,String changShangName, String bolC,Model model){
        medicineInstanceList1=new ArrayList<>();
        changShangList1=new ArrayList<>();
        this.name=null;
        this.pageNumber=0;
        this.numberSize=0;
        List<VChangshang>changShangList=new ArrayList<>();
        List<MedicineInstance>medicineInstanceList=new ArrayList<>();
        if(searchMedicine!=null && searchMedicine.length()>0){
            String hql="from MedicineInstance where medicineName like ?";
            String t1="%"+String.valueOf(searchMedicine)+"%";
            model.addAttribute("serachName",searchMedicine);
            this.name=searchMedicine;
            medicineInstanceList=medicineInstanceManager.find(hql,t1);

        }else if(searchCS!=null && searchCS.length()>0){
            String hql="from MedicineInstance where changShang like ?";
            model.addAttribute("serachName",searchCS);
            this.name=searchCS;
            medicineInstanceList=medicineInstanceManager.find(hql,"%"+searchCS+"%");

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
        }else if(changShangName !=null && changShangName.length()>0){
            medicineInstanceList=  medicineInstanceManager.find("from MedicineInstance where changShang=?",changShangName);
            model.addAttribute("serachName",changShangName);
            this.name=searchSyz;
        }else if(bolS!=null && bolS.equals("bol")){
            medicineInstanceList=medicineInstanceManager.getAll();
        }else if(bolC!=null && bolC.equals("bol")){
            changShangList=vChangshangManager.getAll();
            model.addAttribute("bolC","bol");
        }

        if(changShangList.size()>0){
            if(changShangList.size()>0){
                if(changShangList.size()>10) {
                    changShangList1=changShangList;
                    model.addAttribute("medicineInstanceList", changShangList.subList(0, 10));
                }else{
                    model.addAttribute("medicineInstanceList", changShangList);
                    changShangList1=changShangList;
                }
                model.addAttribute("count",changShangList.size());
                this.numberSize=changShangList.size();
                int pageAllCount=0;
                if(changShangList.size()%10==0){
                    pageAllCount=changShangList.size()/10;
                    this.pageNumber=pageAllCount;
                }else{
                    pageAllCount=changShangList.size()/10+1;
                    this.pageNumber=pageAllCount;
                }
                model.addAttribute("pageAllCount",pageAllCount);
            }else{
                model.addAttribute("count",0);
                this.numberSize=0;
            }
        }else{
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
        }
        model.addAttribute("curNumber",1);
        model.addAttribute("nextNumber",2);
        return "njsfy_index/search";

    }
    @RequestMapping(value = "chage-number", produces = "application/json")

    public String chageCurentNumber(int number,String bolNum,String bolL,String bolR,String bolRR,Model model){
        Boolean bolNext=true;
        if(medicineInstanceList1.size()>0){
            if(number!=pageNumber && medicineInstanceList1.size()<10){
                model.addAttribute("medicineInstanceList",medicineInstanceList1);

            }else if(number!=pageNumber && medicineInstanceList1.size()>0){
                model.addAttribute("medicineInstanceList",medicineInstanceList1.subList((number-1)*10,number*10));
            }else{
                model.addAttribute("medicineInstanceList",medicineInstanceList1.subList((number-1)*10,this.numberSize));

            }

        }else if(changShangList1.size()>0){
            model.addAttribute("bolC","bol");
            if(bolL!=null && bolL.equals("bolL") && number>=2){
                bolNext=false;
                model.addAttribute("medicineInstanceList",changShangList1.subList((number-2)*10,(number-1)*10));

            }else if(number!=pageNumber && changShangList1.size()>0){
                model.addAttribute("medicineInstanceList",changShangList1.subList((number-1)*10,number*10));
            }else{
                model.addAttribute("medicineInstanceList",changShangList1.subList((number-1)*10,this.numberSize));
            }


        }
        model.addAttribute("pageAllCount",this.pageNumber);
        model.addAttribute("count", this.numberSize);
        if(medicineInstanceList1.size()>0) {
            model.addAttribute("serachName", this.name);
        }
        if(bolNum!=null && bolNum.equals("bol")){
            model.addAttribute("curNumber",number);
        }else if(bolL!=null&&bolL.equals("bolL")&&number!=1){
            model.addAttribute("curNumber",number-1);
        }else if(bolRR!=null&&bolRR.equals("bolRR") && number==pageNumber){
            model.addAttribute("curNumber",number);

        } else if(bolR!=null&&bolR.equals("bolR")){
            model.addAttribute("curNumber",number);
        }else {
            model.addAttribute("curNumber",1);
        }

        if(number==pageNumber){
            model.addAttribute("nextNumber",number);
        }else if(bolNext){
            model.addAttribute("nextNumber",number+1);
        }else{
            model.addAttribute("nextNumber",number);
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
            ZtreeData zData=null;
            if(vis.getMedicineParentTypeId()!=null && vis.getMedicineParentTypeId().equals("root")){
                 zData = new ZtreeData(vis.getRowId(), vis.getMedicineParentTypeId(), vis.getMedicineTypeName(), vis.getMedicineTypeName(),false,true);

            }else{
                 zData = new ZtreeData(vis.getRowId(), vis.getMedicineParentTypeId(), vis.getMedicineTypeName(), vis.getMedicineTypeName(),false,false);

            }
            String hql="from MedicineInstance where medicineType=?";
            List<MedicineInstance>medicineInstanceList=new ArrayList<>();
            medicineInstanceList=medicineInstanceManager.find(hql,vis.getRowId());
            if(medicineInstanceList.size()>0){
                for(MedicineInstance medicineInstance:medicineInstanceList){
                    ZtreeData zData1 = new ZtreeData(medicineInstance.getRowId(), vis.getRowId(), medicineInstance.getMedicineName(), medicineInstance.getMedicineName(),true,true);
                    ztreeData.add(zData1);
                }
            }
            ztreeData.add(zData);
        }
        return ztreeData;
    }
    @RequestMapping("medicine-instance")
    public String getMedicineInstance(String rowId,Model model){
        MedicineInstance medicineInstance=null;
        medicineInstance=medicineInstanceManager.get(rowId);
        List<Attachment>attachmentList=new ArrayList<>();
        attachmentList=attachMentManager.findBy("relationId",rowId);
        model.addAttribute("medicineInstance",medicineInstance);
        model.addAttribute("attachmentList",attachmentList);
        return  "njsfy_index/detailed";

    }
    @RequestMapping("medicine-changshang-instance")
    public String getChangshangMedicine(String changShangName,Model model){
        List<MedicineInstance>medicineInstanceList=new ArrayList<>();
        medicineInstanceList=  medicineInstanceManager.find("from MedicineInstance where changShang=?",changShangName);
        model.addAttribute("medicineInstanceList",medicineInstanceList);
        return  "njsfy_index/search";

    }
    @RequestMapping("medicine-instance2")
    public String getMedicineInstance2(String rowId,Model model){
        MedicineInstance medicineInstance=null;
        medicineInstance=medicineInstanceManager.get(rowId);
        List<Attachment>attachmentList=new ArrayList<>();
        attachmentList=attachMentManager.findBy("relationId",rowId);
        model.addAttribute("medicineInstance",medicineInstance);
        model.addAttribute("attachmentList",attachmentList);
        return  "njsfy_index/right";

    }
    @RequestMapping("medicine-instance3")
    public String getMedicineInstance3(String rowId,Model model){
        MedicineInstance medicineInstance=null;
        medicineInstance=medicineInstanceManager.get(rowId);
        List<Attachment>attachmentList=new ArrayList<>();
        attachmentList=attachMentManager.findBy("relationId",rowId);
        model.addAttribute("medicineInstance",medicineInstance);
        model.addAttribute("attachmentList",attachmentList);
        return  "njsfy_index/top";

    }
    @RequestMapping("medicine-instance1")
    @ResponseBody
    public JSONObject getMedicineInstance1(String rowId,Model model){
        MedicineInstance medicineInstance=null;
        medicineInstance=medicineInstanceManager.get(rowId);
        List<Attachment>attachmentList=new ArrayList<>();
        attachmentList=attachMentManager.findBy("relationId",rowId);
        model.addAttribute("medicineInstance",medicineInstance);
        model.addAttribute("attachmentList",attachmentList);
        HashMap<String,Object>map=new HashMap<>();
        map.put("medicineInstance",medicineInstance);
        map.put("attachmentList",attachmentList);

        return (JSONObject) JSONObject.toJSON(map);

    }
    /**
     * 批量下载
     * @param request 请求
     * @param response 返回
     */
    @RequestMapping(value = "fileDownload-attach")
    public String  batchDownloadFiles(String []rowIdList, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(new Date().getTime());

        //读取前端传来json字段
        String[] ids = rowIdList;
    /*    String jsonString = request.getParameter("paperInfo");*/

        //获取web项目根目录
        String fileSaveRootPath = request.getSession().getServletContext().getRealPath("/");

        //创建zip文件并返回zip文件路径
        List<String>filaPathList=new ArrayList<>();
        List<String>fileNameList=new ArrayList<>();
        for(String id:ids){
            Attachment fileRecord = attachMentManager.get(id);
            if(fileRecord!=null){
                filaPathList.add(fileRecord.getFilePath());
                fileNameList.add(fileRecord.getFileName());
            }
        }
        if(filaPathList.size()==1){
            FileUtil fileHelper = new FileUtil();
            fileHelper.downloadFile(filaPathList.get(0), request, response, fileNameList.get(0));
        }else{
            String zipPath = new ZipUtils().createZipAndReturnPath(JSONObject.toJSONString(filaPathList), JSONObject.toJSONString(fileNameList),fileSaveRootPath);
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
        return  "njsfy_index/detailed";
    }
    @RequestMapping("medicineType-ShuoYing")
    public String medicineTypeShuoYing(Model model){
        return "njsfy_index/detailed";
    }

    @RequestMapping("changShang")
    public String changShang(Model model){
        List<VChangshang>changShangList=vChangshangManager.getAll();
        model.addAttribute("changShangList",changShangList);
        return "njsfy_index/changShang";
    }



}
