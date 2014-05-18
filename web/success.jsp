<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: el1ven
  Date: 14-5-9
  Time: 下午3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <META HTTP-EQUIV="pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <META HTTP-EQUIV="expires" CONTENT="0">
    <script type="text/javascript" src="js/jquery-2.0.0.js"></script>
    <script type="text/javascript" src="js/prototype-1.7.js"></script>
    <script type="text/javascript" src="js/json2.js"></script>
    <script type="text/javascript">
       function Validate(id){
           alert(id);
       }
    </script>
</head>
<body>
<h1>success页面</h1>
<hr/><br/>

<br/>
共<s:property value="rowCount"/>记录&nbsp;&nbsp; <!--value的值都通过action映射机制对应action中的属性值-->
第<s:property value="pageNow"/>页&nbsp;&nbsp;

<s:url id="url_pre" value="create.action">
    <s:param name="pageNow" value="pageNow-1"></s:param>
</s:url>

<s:url id="url_next" value="create.action">
    <s:param name="pageNow" value="pageNow+1"></s:param>
</s:url>


    <s:a href="%{url_pre}">上一页</s:a>
    <s:a href="%{url_next}">下一页</s:a></br></br>

<s:iterator id="show" value="list" > <!--list对应Action中的list属性不用放到request之中就可以取出来-->
    <%=request.getAttribute("id")%>
    <%=request.getAttribute("name")%>
    <%=request.getAttribute("job")%>
    <button onclick="window.location='change.jsp?id=<%=request.getAttribute("id")%>'">Change</button>
    <button onclick="window.location='delete.action?id=<%=request.getAttribute("id")%>'">Delete</button>
    <hr/><br/>
</s:iterator>



<form name="addForm" action="add.action" method="post">
    用户名:<input name="userName" type="text"/>
    <br/>
    职位:<input name="userJob" type="text"/>
    <br/>
    <input type="submit" value="提交"/>
</form>



</body>
</html>
