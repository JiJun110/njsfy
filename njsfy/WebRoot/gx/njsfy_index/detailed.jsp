<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<title>南京市妇幼</title>

<script src="<%=request.getContextPath()%>/gx/BJUI/js/jquery-1.7.2.min.js"></script>

<link href="<%=request.getContextPath()%>/gx/njsfy_index/images/css.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/gx/njsfy_index/images/demo.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/gx/njsfy_index/images/zTreeStyle.css" type="text/css">
<script src="<%=request.getContextPath()%>/gx/njsfy_index/js/jquery.ztree.core.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/gx/njsfy_index/js/jquery.lightbox.css" type="text/css"></link>
<script type="text/javascript" src="<%=request.getContextPath()%>/gx/njsfy_index/js/jquery.lightbox.min.js"></script>
<style>
    ul.ztree {
        margin-left: 0px;
        margin-top: 12px;
        border: 0px solid #617775;
     /*   background: #f0f6e4;*/
        width: 270px;
        /*height: 500px;*/
/*
        overflow-y: scroll;
        overflow-x: auto;*/
    }
</style>
<script>
    //图片放大预览
    jQuery(document).ready(function($){
        $('.lightbox').lightbox();
    });
</script>
<script>
    var rowIdList=new Array();
    function allClick() {
        var selectedCheckList=$(".selectedCheck");
        for(var i=0;i<selectedCheckList.length;i++){
            if($("#allselected").is(':checked')){
                $(selectedCheckList[i]).attr("checked",true);
                rowIdList.push($(selectedCheckList[i]).val());
            }else{
                $(selectedCheckList[i]).attr("checked",false);
                rowIdList.length=0;
            }
        }
    }
    function selectedOne(rowId) {

        if($("#"+rowId).is(':checked')){
            $("#"+rowId).attr("checked",true);
            rowIdList.push($("#"+rowId).val());
        }else{
            $("#"+rowId).attr("checked",false);
            var index = rowIdList.indexOf($("#"+rowId).val());
            if (index > -1) {
                rowIdList.splice(index, 1);
            }

        }
    }
    function downloadAttach() {
        if(rowIdList.length>0){
            location.href="<%=basePath%>njsfy-index/fileDownload-attach.do?rowIdList="+rowIdList;
        }

    }
   /* function lookAttach(rowId) {
        location.href="<%=basePath%>njsfy-index/fileDownload-attach.do?rowIdList="+rowId;

    }*/
</script>


<div >
<iframe id="ifrmname1" width="100%" height="218px" frameborder=0 scrolling=no src="<%=request.getContextPath()%>/gx/njsfy_index/top.jsp"></iframe>
</div>
<div class="content">
<div class="detail_left">

    <table cellpadding="0" cellspacing="0" width="100%" style="min-height: 600px">
        <tr>
            <td style="background:#FFF;box-shadow:0  2px 10px rgba(0,0,0,0.15); "><div class="content_wrap">
                <div class="zTreeDemoBackground left">
                    <ul id="treeDemo" class="ztree" ></ul>
                </div>
            </div></td>
        </tr>
    </table>

</div>
<div class="detail_right"><iframe id="ifrmname2" width="100%" height="1100px"  frameborder=0 scrolling=no src="<%=request.getContextPath()%>/gx/njsfy_index/right.jsp"></iframe></div>
</div>
<input type="hidden" id="getId" >
    <!-- for doc end -->
<script>


    var jsont=null;
    $(function(){
        if('${medicineInstance.rowId}'!=null && '${medicineInstance.rowId}'.length>0){
            document.getElementById('ifrmname2').contentWindow.location.replace("<%=basePath%>njsfy-index/medicine-instance2.do?rowId="+'${medicineInstance.rowId}')

            document.getElementById('ifrmname1').contentWindow.location.replace("<%=basePath%>njsfy-index/medicine-instance3.do?rowId="+'${medicineInstance.rowId}')

        }

        $.ajax({
            url:'<%=basePath%>njsfy-index/medicineTree-tree.do',
            type:'get',
            async:false,
            cache:false,
            dataType:'json',
            success:function(json){
                jsont = json;
                console.log(jsont.length)
            }
        });
    });
</script>
<SCRIPT type="text/javascript">
    <!--
    var setting = {
        data: {
            key: {
                title:"title"
            },
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: beforeClick,
            onClick: onClick
        }
    };

  /*  var zNodes =[
        { id:1, pId:0, name:"普通的父节点", t:"我很普通，随便点我吧", open:true},
        { id:11, pId:1, name:"叶子节点 - 1", t:"我很普通，随便点我吧"},
        { id:12, pId:1, name:"叶子节点 - 2", t:"我很普通，随便点我吧"},
        { id:13, pId:1, name:"叶子节点 - 3", t:"我很普通，随便点我吧"},
        { id:2, pId:0, name:"NB的父节点", t:"点我可以，但是不能点我的子节点，有本事点一个你试试看？", open:true},
        { id:21, pId:2, name:"叶子节点2 - 1", t:"你哪个单位的？敢随便点我？小心点儿..", click:false},
        { id:22, pId:2, name:"叶子节点2 - 2", t:"我有老爸罩着呢，点击我的小心点儿..", click:false},
        { id:23, pId:2, name:"叶子节点2 - 3", t:"好歹我也是个领导，别普通群众就来点击我..", click:false},
        { id:3, pId:0, name:"郁闷的父节点", t:"别点我，我好害怕...我的子节点随便点吧...", open:true, click:false },
        { id:31, pId:3, name:"叶子节点3 - 1", t:"唉，随便点我吧"},
        { id:32, pId:3, name:"叶子节点3 - 2", t:"唉，随便点我吧"},
        { id:33, pId:3, name:"叶子节点3 - 3", t:"唉，随便点我吧"}
    ];*/

    var log, className = "dark";
    function beforeClick(treeId, treeNode, clickFlag) {
        className = (className === "dark" ? "":"dark");
        showLog("[ "+getTime()+" beforeClick ]&nbsp;&nbsp;" + treeNode.name );
        return (treeNode.click != false);
    }
    function onClick(event, treeId, treeNode, clickFlag) {
        $.ajax({
            url:'<%=basePath%>njsfy-index/medicine-instance1.do?rowId='+treeNode.id,
            type:'get',
            async:false,
            cache:false,
            dataType:'json',
            success:function(json){

                document.getElementById('ifrmname1').contentWindow.location.replace("<%=basePath%>njsfy-index/medicine-instance3.do?rowId="+json.medicineInstance.rowId)

                /*document.getElementById('ifrmname2').contentWindow.location.replace("<%=request.getContextPath()%>/gx/njsfy_index/right.jsp?warn="+(json.medicineInstance.warn==null?"":json.medicineInstance.warn)+"&syz="+(json.medicineInstance.syz==null?"":json.medicineInstance.syz)+"&yfyl="+(json.medicineInstance.yfyl==null?"":json.medicineInstance.yfyl)+"&tsCcTj="+(json.medicineInstance.tsCcTj==null?"":json.medicineInstance.tsCcTj)+"&cSmSy="+(json.medicineInstance.cSmSy==null?"":json.medicineInstance.cSmSy)+"&attachmentList="+(json.attachmentList));*/
                document.getElementById('ifrmname2').contentWindow.location.replace("<%=basePath%>njsfy-index/medicine-instance2.do?rowId="+json.medicineInstance.rowId)
            }
        });



    }
    function showLog(str) {
        if (!log) log = $("#log");
        log.append("<li class='"+className+"'>"+str+"</li>");
        if(log.children("li").length > 8) {
            log.get(0).removeChild(log.children("li")[0]);
        }
    }
    function getTime() {
        var now= new Date(),
            h=now.getHours(),
            m=now.getMinutes(),
            s=now.getSeconds();
        return (h+":"+m+":"+s);
    }

    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, jsont);
    });

</SCRIPT>

