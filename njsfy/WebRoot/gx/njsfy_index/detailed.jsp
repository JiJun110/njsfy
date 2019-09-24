<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script src="<%=request.getContextPath()%>/gx/BJUI/js/jquery-1.7.2.min.js"></script>

<link href="<%=request.getContextPath()%>/gx/njsfy_index/images/css.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/gx/njsfy_index/images/demo.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/gx/njsfy_index/images/zTreeStyle.css" type="text/css">
<script src="<%=request.getContextPath()%>/gx/njsfy_index/js/jquery.ztree.core.js"></script>




    <!-- for doc end -->
<div class="detail">
    <div class="drugxx">
        <div class="drugname">
            <div class="drug">中枢神经兴奋药(1ml:3mg/支)</div>
            <div class="drugfirm">厂商：湖北午时药业股份有限公司</div>
        </div>
        <div class="drugprice">

            <div class="stock">
                <div class="detail_img"><img src="<%=request.getContextPath()%>/gx/njsfy_index/images/img_stock.png" width="52" height="52" /></div>
                <div class="detail_word">
                    <div><span class="font18">156</span> 元
                    </div>
                    <div>库存量</div>
                </div>
            </div>
            <div class="price">
                <div class="detail_img"><img src="<%=request.getContextPath()%>/gx/njsfy_index/images/img_price.png" width="52" height="52" /></div>
                <div class="detail_word">
                    <div><span class="font18">126</span> 元</div>
                    <div>价格</div>
                </div>
            </div>
        </div>
        <div class="category">
            <span class="lactation_z lactation">哺乳期</span>
            <span class="lactation_z pregnancy">孕期</span>
            <span class="lactation_z medicine">基药</span>
            <span class="lactation_z risk">高危药品</span>
            <span class="lactation_z expense">医保类型：自费</span>
        </div>
    </div>
</div>
<div class="content">
    <div class="detail_left">
        <div class="content_wrap">
            <div class="zTreeDemoBackground left">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
            <%--<div class="right">
                <ul class="info">
                    <li class="title"><h2>1、beforeClick / onClick 事件回调函数控制</h2>
                        <ul class="list">
                            <li>利用 click 事件回调函数 可以进行各种其他的附加操作，这里简单演示如何监控此事件</li>
                            <li><p><span class="highlight_red">请尝试按下 <b>Ctrl</b> 或 <b>Cmd</b> 键进行 多节点选择 和 取消选择</span><br/>
                                click log:<br/>
                                <ul id="log" class="log"></ul></p>
                            </li>
                        </ul>
                    </li>
                    <li class="title"><h2>2、setting 配置信息说明</h2>
                        <ul class="list">
                            <li class="highlight_red">需要设置 setting.callback.beforeClick 和  setting.callback.onClick 属性, 详细请参见 API 文档中的相关内容</li>
                        </ul>
                    </li>
                    <li class="title"><h2>3、treeNode 节点数据说明</h2>
                        <ul class="list">
                            <li>对 节点数据 没有特殊要求，用户可以根据自己的需求添加自定义属性</li>
                        </ul>
                    </li>
                </ul>
            </div>--%>
        </div>

    </div>
    <div class="detail_right">
        <div class="detail_contain">
            <div class="detail_title">
                <span class="line"></span>黑框警示
                <p class="explain">消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛。</p>
            </div>
            <div class="detail_title">
                <span class="line"></span>适应症
                <p class="explain">消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛。</p>
            </div>
            <div class="detail_title">
                <span class="line"></span>用法用量
                <p class="explain">消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛。</p>
            </div>
            <div class="detail_title">
                <span class="line"></span>特殊存储条件
                <p class="explain">消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛。</p>
            </div>
            <div class="detail_title">
                <span class="line"></span>超说明书使用
                <p class="explain">消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛；妇科痉挛性疼痛。消化系统和胆道功能障碍引起的急性痉挛性疼痛；急性痉挛性尿道、膀胱、肾绞痛。</p>
            </div>
        </div>
        <div class="detail_contain" style="margin-top:20px;">
            <div class="detail_title">
                <span class="line"></span>附件
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="fjtable" align="center">
                    <tr class="fjtitle">
                        <td>序号</td>
                        <td>附件名称</td>
                        <td>上传时间</td>
                        <td>操作</td>
                    </tr>
                    <tr>
                        <td align="center">1</td>
                        <td>扫描说明书1.pdf</td>
                        <td align="center">2019-08-14 10:30</td>
                        <td align="center"><input name="" type="button" value="点击查看" class="button" /></td>
                    </tr>
                    <tr>
                        <td align="center">2</td>
                        <td>扫描说明书2.pdf</td>
                        <td align="center">2019-08-14 10:30</td>
                        <td align="center"><input name="" type="button" value="点击查看" class="button" /></td>
                    </tr>
                    <tr>
                        <td align="center">3</td>
                        <td>扫描说明书3.pdf</td>
                        <td align="center">2019-08-14 10:30</td>
                        <td align="center"><input name="" type="button" value="点击查看" class="button" /></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>

<script>
    var jsont=null;
    $(function(){
        console.log("treetree")

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
        alert("tthaoy")
        showLog("[ "+getTime()+" onClick ]&nbsp;&nbsp;clickFlag = " + clickFlag + " (" + (clickFlag===1 ? "普通选中": (clickFlag===0 ? "<b>取消选中</b>" : "<b>追加选中</b>")) + ")");
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

