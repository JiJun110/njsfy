<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%String url = "/login/go-login.do";%>
<%response.sendRedirect(request.getContextPath() + url);%>
