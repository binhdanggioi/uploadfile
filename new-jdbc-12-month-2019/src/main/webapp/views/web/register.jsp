<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="process.jsp"><br/><br/><br/><br/><br/><br/>
    <input type="text" name="userName" placeholder="UserName" onclick="this.value=''"/><br/>
    <input type="text" name="fullName" placeholder="FullName" onclick="this.value=''"/><br/>
    <input type="password" name="password" placeholder="Password" onclick="this.value=''"/><br/>
    <input type="submit" value="register"/>
</form>

</body>
</html>
