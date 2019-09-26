<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<script src="<%=request.getContextPath()%>/gx/BJUI/js/jquery-1.7.2.min.js"></script>

<link href="<%=request.getContextPath()%>/gx/njsfy_index/images/css.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/gx/njsfy_index/images/demo.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/gx/njsfy_index/images/zTreeStyle.css" type="text/css">
<script src="<%=request.getContextPath()%>/gx/njsfy_index/js/jquery.ztree.core.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/gx/njsfy_index/js/jquery.lightbox.css"
      type="text/css"></link>
<script type="text/javascript" src="<%=request.getContextPath()%>/gx/njsfy_index/js/jquery.lightbox.min.js"></script>
<style>
    ul.ztree {
        margin-left: 0px;
        margin-top: -2px;
        border: 1px solid #617775;
        background: #f0f6e4;
        width: 270px;
        height: 500px;
        overflow-y: scroll;
        overflow-x: auto;
    }
</style>
<script>
    //图片放大预览
    jQuery(document).ready(function ($) {
        $('.lightbox').lightbox();
    });
</script>
<script>
    var rowIdList = new Array();
    function allClick() {
        var selectedCheckList = $(".selectedCheck");
        for (var i = 0; i < selectedCheckList.length; i++) {
            if ($("#allselected").is(':checked')) {
                $(selectedCheckList[i]).attr("checked", true);
                rowIdList.push($(selectedCheckList[i]).val());
            } else {
                $(selectedCheckList[i]).attr("checked", false);
                rowIdList.length = 0;
            }
        }
    }
    function selectedOne(rowId) {

        if ($("#" + rowId).is(':checked')) {
            $("#" + rowId).attr("checked", true);
            rowIdList.push($("#" + rowId).val());
        } else {
            $("#" + rowId).attr("checked", false);
            var index = rowIdList.indexOf($("#" + rowId).val());
            if (index > -1) {
                rowIdList.splice(index, 1);
            }

        }
    }
    function downloadAttach() {
        if (rowIdList.length > 0) {
            location.href = "<%=basePath%>njsfy-index/fileDownload-attach.do?rowIdList=" + rowIdList;
        }

    }
    /* function lookAttach(rowId) {
     location.href="<%=basePath%>njsfy-index/fileDownload-attach.do?rowIdList="+rowId;

     }*/
</script>

<!-- for doc end -->
<div class="detail">
    <div class="drugxx">
        <div class="drugname">
            <div class="drug"><%=request.getParameter("medicineName") == null ? "" : request.getParameter("medicineName")%>
            </div>
            <div class="drugfirm">
                厂商：<%=request.getParameter("changShang") == null ? "" : request.getParameter("changShang")%>
            </div>
        </div>
        <div class="drugprice">

            <div class="stock">
                <div class="detail_img"><img src="<%=request.getContextPath()%>/gx/njsfy_index/images/img_stock.png"
                                             width="52" height="52"/></div>
                <div class="detail_word">
                    <div><span class="font18">156</span> 盒
                    </div>
                    <div>库存量</div>
                </div>
            </div>
            <div class="price">
                <div class="detail_img"><img src="<%=request.getContextPath()%>/gx/njsfy_index/images/img_price.png"
                                             width="52" height="52"/></div>
                <div class="detail_word">
                    <div><span
                            class="font18"><%=request.getParameter("price") == null ? "" : request.getParameter("price")%></span>
                        元
                    </div>
                    <div>价格</div>
                </div>
            </div>
        </div>
        <div class="category">
            <span class="lactation_z pregnancy">孕期安全等级：<%=request.getParameter("brqAqdj") == null ? "" : request.getParameter("brqAqdj")%></span>
            <span class="lactation_z lactation">哺乳期安全等级：<%=request.getParameter("yqAqdj") == null ? "" : request.getParameter("yqAqdj")%></span>
            <% if (request.getParameter("isJy") != null && request.getParameter("isJy").equals("是")) {%>
            <span class="lactation_z medicine">基药</span>
            <%
                }else{
            %>
            <%
                }
            %>
            <% if (request.getParameter("isGwy") != null && request.getParameter("isGwy").equals("是")) {%>
            <span class="lactation_z risk">高危药品</span>
            <%
            }else{
            %>
            <%
                }
            %>


            <span class="lactation_z expense">医保类型：<%=request.getParameter("ybType") == null ? "" : request.getParameter("ybType")%></span>
        </div>
    </div>
</div>





