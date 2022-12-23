<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="utils.JdbcUtils" %>
<%@ page import="java.sql.SQLException" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Liu
  Date: 2022/12/22
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改学生信息</title>
    <%
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf8");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String classlist = request.getParameter("classlist");
        String hobbies = request.getParameter("hobbies");
    %>
</head>
<body>
<form action="editServlet" method="post">
    <c:set var="gender" value="<%=gender%>"/>
    <c:set var="classlist" value="<%=classlist%>"/>
    <c:set var="hobbies" value="<%=hobbies%>"/>
    <label>编号：<input type="text" name="id" value="<%=id%>" readonly="readonly"></label><br>
    <label>姓名：<input type="text" name="name" value="<%=name%>"></label><br>
    <label>性别：<input type="radio" name="gender" value="男" <c:if test="${fn:contains(gender,'男') }">checked</c:if>>男</label>
    <label><input type="radio" name="gender" value="女" <c:if test="${fn:contains(gender,'女') }">checked</c:if>>女</label><br>
    <label>班级：
        <select name="classlist">
            　　<option value="一班" <c:if test="${fn:contains(classlist,'一班') }">selected="selected"</c:if>>一班</option>
            　　<option value="二班" <c:if test="${fn:contains(classlist,'二班') }">selected="selected"</c:if>>二班</option>
        </select>
    </label><br>
    <label>兴趣：<input type="checkbox" name="hobbies" value="唱" <c:if test="${fn:contains(hobbies,'唱') }">checked</c:if>>唱
        <input type="checkbox" name="hobbies" value="跳" <c:if test="${fn:contains(hobbies,'跳') }">checked</c:if>>跳
        <input type="checkbox" name="hobbies" value="rap" <c:if test="${fn:contains(hobbies,'rap') }">checked</c:if>>rap
        <input type="checkbox" name="hobbies" value="篮球" <c:if test="${fn:contains(hobbies,'篮球') }">checked</c:if>>篮球
    </label>
    <br>
    <label>
        <input type="submit" value="更新">
    </label>
</form>

</body>
</html>
