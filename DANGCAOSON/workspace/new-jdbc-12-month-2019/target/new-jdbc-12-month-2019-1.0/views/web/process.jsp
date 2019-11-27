<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@page import="com.websitetintuc.dao.impl.RegisterDAO"%>
<jsp:useBean id="obj" class="com.websitetintuc.model.UserModel"/>

<jsp:setProperty property="*" name="obj"/>

<%
    int status=RegisterDAO.register(obj);
    if(status>0)
        out.print("You are successfully registered");

%>
</body>
</html>
