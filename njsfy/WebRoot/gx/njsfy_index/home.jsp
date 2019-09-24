<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>南京市妇幼</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/gx/njsfy_index/js/jquery-1.7.2.min.js"></script>
    <link href="<%=request.getContextPath()%>/gx/njsfy_index/images/css.css" rel="stylesheet">
    <script type="text/javascript">
        $(function(){
            $(".bodys p").not(":first").hide();
            $(".searchbox ul li").mouseover(function(){
                var index = $(this).index();
                if(index==0){
                    $(this).find("a").addClass("style1");
                    $("li").eq(1).find("a").removeClass("style2");
                    $("li").eq(2).find("a").removeClass("style3");
                }
                if(index==1){
                    $(this).find("a").addClass("style2");
                    $("li").eq(0).find("a").removeClass("style1");
                    $("li").eq(2).find("a").removeClass("style3");
                }
                if(index==2){
                    $(this).find("a").addClass("style3");
                    $("li").eq(0).find("a").removeClass("style1");
                    $("li").eq(1).find("a").removeClass("style2");
                }
                var index=$(this).index();
                $(".bodys p").eq(index).show().siblings().hide();
            });
        });
    </script>
</head>
<body>
<div class="searchbg">
    <div class="searchbox_bg">
        <!--查询-->
        <div class="searchbox">
            <ul class="border1">
                <li><a href="#" class="style1">药品类别</a></li>
                <li><a href="#">用量</a></li>
                <li><a href="#">适应症</a></li>
            </ul>
            <div class="bodys">
                <form action="<%=basePath%>njsfy-index/home.do" method="get">
                    <p>
                        <input type="text" name="name1" value="" id="1" class="one" placeholder="如：阿司匹林" />
                        <button type="submit" class="one1"><img src="images/img_search.png" align="absmiddle" width="38" height="38" />  查询</button>
                    </p>
                    <p>
                        <input type="text" name="name2" value="" id="2" class="two" placeholder="如：一日一次" />
                        <button type="submit" class="two2"><img src="images/img_search.png" align="absmiddle" width="38" height="38" />  查询</button>
                    </p>
                    <p>
                        <input type="text" name="name3" value="" id="3" class="three" placeholder="如：感冒" />
                        <button type="submit" class="three3 "><img src="images/img_search.png" align="absmiddle" width="38" height="38" />  查询</button>
                    </p>

                </form>
            </div>
        </div>
        <!--查询-->
    </div>
</div>
<div class="content">
    <div class="contentrow">
        <div class="contentdiv">
            <div class="content_pic"><img src="images/img_menu1.png" width="64" height="64" /></div>
            <div class="content_words">
                <p class="title">药物类别索引
                </p>
                <p class="titleC">处方药、非处方药（甲类非处方药、乙类非处方药）
                </p>
            </div>
        </div>
    </div>
    <div class="contentrow">
        <div class="contentdiv">
            <div class="content_pic"><img src="images/img_menu2.png" width="64" height="64" /></div>
            <div class="content_words">
                <p class="title">药物类别索引
                </p>
                <p class="titleC">处方药、非处方药（甲类非处方药、乙类非处方药）
                </p>
            </div>
        </div>
    </div>
    <div class="contentrow">
        <div class="contentdiv">
            <div class="content_pic"><img src="images/img_menu3.png" width="64" height="64" /></div>
            <div class="content_words">
                <p class="title">药物类别索引
                </p>
                <p class="titleC">处方药、非处方药（甲类非处方药、乙类非处方药）
                </p>
            </div>
        </div>
    </div>
    <!--第二行-->
    <div class="contentrow">
        <div class="contentdiv">
            <div class="content_pic"><img src="images/img_menu4.png" width="64" height="64" /></div>
            <div class="content_words">
                <p class="title">药物类别索引
                </p>
                <p class="titleC">处方药、非处方药（甲类非处方药、乙类非处方药）
                </p>
            </div>
        </div>
    </div>
    <div class="contentrow">
        <div class="contentdiv">
            <div class="content_pic"><img src="images/img_menu5.png" width="64" height="64" /></div>
            <div class="content_words">
                <p class="title">药物类别索引
                </p>
                <p class="titleC">处方药、非处方药（甲类非处方药、乙类非处方药）
                </p>
            </div>
        </div>
    </div>
    <div class="contentrow">
        <div class="contentdiv">
            <div class="content_pic"><img src="images/img_menu6.png" width="64" height="64" /></div>
            <div class="content_words">
                <p class="title">药物类别索引
                </p>
                <p class="titleC">处方药、非处方药（甲类非处方药、乙类非处方药）
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
