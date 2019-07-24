<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<jsp:forward page="/pages/index.jsf" />--%>
<%
    String ruta = request.getContextPath();
    response.sendRedirect(ruta + "/pages/home.jsf?faces-redirect=true");
%>