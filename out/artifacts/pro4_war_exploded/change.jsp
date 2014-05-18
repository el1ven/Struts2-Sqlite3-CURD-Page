<%--
  Created by IntelliJ IDEA.
  User: el1ven
  Date: 14-5-12
  Time: 上午11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>change</title>
</head>
<body>
<h1>修改信息界面</h1>
<hr/><br/>
<form name="changeForm" action="change.action" method="post">
    您要改变的用户ID为:<input name="userId" value="<%=request.getParameter("id")%>" readonly="readonly"/><br/>
    修改姓名:<input name="userName" type="text"/><br/>
    修改工作:<input name="userJob" type="text"/><br/>
    <input type="submit" value="确认修改并提交"/>
</form>

</body>
</html>
