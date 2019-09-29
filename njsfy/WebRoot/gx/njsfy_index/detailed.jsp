<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="<%=request.getContextPath()%>/gx/njsfy_index/images/css.css" rel="stylesheet">
    <%--<base href="<%=request.getContextPath()%>/gx/"/>
    --%><meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>南京市妇幼保健院医药管理系统软件开发</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="Keywords" content=""/>
    <meta name="Description" content=""/>
    <!-- bootstrap - css -->
    <link href="<%=request.getContextPath()%>/gx/BJUI/themes/css/bootstrap.css" rel="stylesheet">
    <!-- core - css -->
    <link href="<%=request.getContextPath()%>/gx/BJUI/themes/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/gx/BJUI/themes/purple/core.css" id="bjui-link-theme" rel="stylesheet">
    <!-- plug - css -->
    <link href="<%=request.getContextPath()%>/gx/BJUI/plugins/kindeditor_4.1.10/themes/default/default.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/gx/BJUI/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/gx/BJUI/plugins/niceValidator/jquery.validator.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/gx/BJUI/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/gx/BJUI/themes/css/FA/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/gx/BJUI/themes/css/style-my.css" rel="stylesheet">
    <!--[if lte IE 7]>
    <link href="/gx/BJUI/themes/css/ie7.css" rel="stylesheet">


    <![endif]-->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lte IE 9]>
    <script src="/gx/BJUI/other/html5shiv.min.js"></script>
    <script src="/gx/BJUI/other/respond.min.js"></script>

    <![endif]-->
    <script src="<%=request.getContextPath()%>/gx/BJUI/other/html5shiv.min.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/other/respond.min.js"></script>

  <%--  <script src="/gx/js/jquery-weui.min.js"></script>
    <script src="/gx/js/jquery-weui.min.css"></script>
    <script src="/gx/js/weui.min.css"></script>--%>
    <!-- jquery -->
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/jquery-1.7.2.min.js"></script>
    <%--<script src="<%=request.getContextPath()%>/gx/js/jquery-1.11.2.js"></script>--%>

    <script src="<%=request.getContextPath()%>/gx/BJUI/js/jquery.cookie.js"></script>
    <!--[if lte IE 9]>
    <script src="BJUI/other/jquery.iframe-transport.js"></script>
    <![endif]-->
    <!-- BJUI.all 分模块压缩版 -->
    <!--<script src="BJUI/js/bjui-all.js"></script>-->
    <!-- 以下是B-JUI的分模块未压缩版，建议开发调试阶段使用下面的版本 -->

    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-core.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-regional.zh-CN.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-frag.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-extends.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-basedrag.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-slidebar.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-contextmenu.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-navtab.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-dialog.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-taskbar.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-ajax.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-alertmsg.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-pagination.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-util.date.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-datepicker.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-ajaxtab.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-datagrid.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-tablefixed.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-tabledit.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-spinner.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-lookup.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-tags.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-upload.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-theme.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-initui.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-plugins.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/js/bjui-zmy.js"></script>

    <!-- plugins -->
    <!-- swfupload for uploadify && kindeditor -->
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/swfupload/swfupload.js"></script>
    <!-- kindeditor -->
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/kindeditor_4.1.10/kindeditor-all.min.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/kindeditor_4.1.10/lang/zh_CN.js"></script>
    <!-- colorpicker -->
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/colorpicker/js/bootstrap-colorpicker.min.js"></script>
    <!-- ztree -->
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/ztree/jquery.ztree.all-3.5.js"></script>
    <!-- nice validate -->
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/niceValidator/jquery.validator.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/niceValidator/jquery.validator.themes.js"></script>
    <!-- bootstrap plugins -->
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/bootstrapSelect/defaults-zh_CN.min.js"></script>
    <!-- icheck -->
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/icheck/icheck.min.js"></script>
    <!-- dragsort -->
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/dragsort/jquery.dragsort-0.5.1.min.js"></script>
    <!-- HighCharts -->
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/highcharts/highcharts.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/highcharts/highcharts-3d.js"></script>
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/highcharts/themes/gray.js"></script>
    <!-- other plugins -->
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/other/jquery.autosize.js"></script>
    <link href="<%=request.getContextPath()%>/gx/BJUI/plugins/uploadify/css/uploadify.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/uploadify/scripts/jquery.uploadify.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/gx/js/plupload.full.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/gx/js/i18n/zh_CN.js"></script>
    <!-- 配置文件 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/widgets/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/widgets/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/widgets/ueditor/ueditor.parse.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/widgets/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- 日历（日程）控件 -->

    <script src='<%=request.getContextPath()%>/widgets/fullCalendar2.4.0/lib/moment.min.js'></script>
    <script  type="text/javascript" src='<%=request.getContextPath()%>/widgets/fullCalendar2.4.0/fullcalendar.js'></script>
    <script  type="text/javascript" src='<%=request.getContextPath()%>/widgets/fullCalendar2.4.0/lang/zh-cn.js'></script>

    <!-- ECharts -->
    <script src="<%=request.getContextPath()%>/gx/BJUI/plugins/echarts/echarts.js"></script>
    <!-- init -->
    <script type="text/javascript">
        _BASE_PATH = '<%=basePath%>';
        $(function() {
            BJUI.init({
                JSPATH       : '../gx/BJUI/',         //[可选]框架路径
                PLUGINPATH   : '../gx/BJUI/plugins/', //[可选]插件路径
                loginInfo    : {url:'login_timeout.html', title:'登录', width:400, height:200}, // 会话超时后弹出登录对话框
                statusCode   : {ok:200, error:300, timeout:301}, //[可选]
                ajaxTimeout  : 500000, //[可选]全局Ajax请求超时时间(毫秒)
                pageInfo     : {total:'total', pageCurrent:'pageCurrent', pageSize:'pageSize', orderField:'orderField', orderDirection:'orderDirection'}, //[可选]分页参数
                alertMsg     : {displayPosition:'topcenter', displayMode:'slide', alertTimeout:3000}, //[可选]信息提示的显示位置，显隐方式，及[info/correct]方式时自动关闭延时(毫秒)
                keys         : {statusCode:'statusCode', message:'message'}, //[可选]
                ui           : {
                    windowWidth      : 0,    //框架可视宽度，0=100%宽，> 600为则居中显示
                    showSlidebar     : true, //[可选]左侧导航栏锁定/隐藏
                    clientPaging     : true, //[可选]是否在客户端响应分页及排序参数
                    overwriteHomeTab : false //[可选]当打开一个未定义id的navtab时，是否可以覆盖主navtab(我的主页)
                },
                debug        : false,    // [可选]调试模式 [true|false，默认false]
                theme        : 'sky' // 若有Cookie['bjui_theme'],优先选择Cookie['bjui_theme']。皮肤[五种皮肤:default, orange, purple, blue, red, green]
            });
            var funId='${defaultFun.rowId}';
            if(funId.length>0){
                funId="I"+funId;
                $(this).navtab({id:funId, url:'<%=basePath %>${defaultFun.functionEnName}', title:'${defaultFun.functionName}',isdefault:true});
            }




            // main - menu
            $('#bjui-accordionmenu')
                .collapse()
                .on('hidden.bs.collapse', function(e) {
                    $(this).find('> .panel > .panel-heading').each(function() {
                        var $heading = $(this), $a = $heading.find('> h4 > a')

                        if ($a.hasClass('collapsed')) $heading.removeClass('active')
                    })
                })
                .on('shown.bs.collapse', function (e) {
                    $(this).find('> .panel > .panel-heading').each(function() {
                        var $heading = $(this), $a = $heading.find('> h4 > a')

                        if (!$a.hasClass('collapsed')) $heading.addClass('active')
                    })
                });

            $(document).on('click', 'ul.menu-items li > a', function(e) {
                var $a = $(this), $li = $a.parent(), options = $a.data('options').toObj(), $children = $li.find('> .menu-items-children');
                var onClose = function() {
                    $li.removeClass('active');
                }
                var onSwitch = function() {
                    $('#bjui-accordionmenu').find('ul.menu-items li').removeClass('switch');
                    $li.addClass('switch');
                }

                $li.addClass('active');
                if (options) {
                    options.url      = $a.attr('href');
                    options.onClose  = onClose;
                    options.onSwitch = onSwitch;
                    if (!options.title) options.title = $a.text()

                    if (!options.target)
                        $a.navtab(options);
                    else
                        $a.dialog(options);
                }
                if ($children.length) {
                    $li.toggleClass('open');
                }

                e.preventDefault();
            });

            //时钟
            var today = new Date(), time = today.getTime();
            $('#bjui-date').html(today.formatDate('yyyy/MM/dd'));
            setInterval(function() {
                today = new Date(today.setSeconds(today.getSeconds() + 1));
                $('#bjui-clock').html(today.formatDate('HH:mm:ss'));
            }, 1000);
            $("#bjui-hnav-navbar li:first").addClass("active");



        });

        //菜单-事件
        function MainMenuClick(event, treeId, treeNode) {
            event.preventDefault();

            if (treeNode.isParent) {
                var zTree = $.fn.zTree.getZTreeObj(treeId);

                zTree.expandNode(treeNode, !treeNode.open, false, true, true);
                return;
            }

            if (treeNode.target && treeNode.target == 'dialog'){
                $(event.target).dialog({id:treeNode.tabid, url:treeNode.url, title:treeNode.name});
            } else{
                $(event.target).navtab({id:treeNode.tabid, url:treeNode.url, title:treeNode.name, fresh:treeNode.fresh, external:treeNode.external})

            }
        }
    </script>
    <!-- for doc begin -->
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/gx/js/syntaxhighlighter-2.1.382/styles/shCore.css"/>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/gx/js/syntaxhighlighter-2.1.382/styles/shThemeEclipse.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/gx/js/syntaxhighlighter-2.1.382/scripts/brush.js"></script>
    <script type="text/javascript">
        $(function(){
            SyntaxHighlighter.config.clipboardSwf = '/js/syntaxhighlighter-2.1.382/scripts/clipboard.swf'
            $(document).on(BJUI.eventType.initUI, function(e) {
                SyntaxHighlighter.highlight();
            })
        })
    </script>
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
            <div style="float:left; width:250px; height:620px; margin-bottom:0px; overflow:auto; margin-top:26px; margin-left:0px; border-right:1px #c3ced5 solid; border-bottom:1px #c3ced5 solid;border-left:1px #c3ced5 solid;position: absolute;">
                <ul id="view-manager-1-tree" class="ztree" data-toggle="ztree" data-options="{nodes:'returnTreeJSON', expandAll: true ,onClick:'showVis'}">
                </ul>
            </div>
        <div style="margin-left:255px; height:100%;border:1px #c3ced5 solid;"  id="article-manager-view-list"  ></div>
        <%--<img src="<%=request.getContextPath()%>/gx/njsfy_index/images/tree.jpg" width="252" height="956" />--%>
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
<script type="text/javascript">

        function returnTreeJSON() {
            var jsont=null;
            $.ajax({
                url:'<%=basePath%>njsfy-medicineTree/medicineTree-tree.do',
                type:'get',
                async:false,
                cache:false,
                dataType:'json',
                success:function(json){
                    jsont = json;
                    console.log(jsont.length)
                },
                fail:function () {
                    console.log("fail")

                }
            });
            console.log(jsont);

            return jsont;
        }
</script>

