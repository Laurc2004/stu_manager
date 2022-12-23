<%@ page import="utils.JdbcUtils" %>
<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: Liu
  Date: 2022/12/22
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    try{
        conn = JdbcUtils.getConnection();
        st = conn.createStatement();
        String sql ="select * from students";
        rs = st.executeQuery(sql);

    }catch(SQLException e){
        e.printStackTrace();
    }
%>
<head>
    <meta charset="UTF-8">
    <title>用户界面</title>
    <!--    EasyUI 核心CSS文件-->
    <link rel="stylesheet" href="jq-easyui/themes/metro/easyui.css" />
    <!--    EasuUI 图标CSS文件-->
    <link rel="stylesheet" href="jq-easyui/themes/icon.css" />
    <style>
       .side a {
            /* 1.把a转换为块级元素 这样就可以让链接竖着表示了*/
            display: block;

            /* 设置长度与宽度 */
            width: 205px;
            height: 45px;

            /* 设置背景颜色 */
            background-color: white;

            /* 设置字体大小 */
            font-size: 14px;

            /* 设置字体颜色 */
            color: grey;

            /* 链接没有下划线 */
            text-decoration: none;

            /* 设置左边的内边距 */
            padding-left: 40px;

            /* 设置文本边距 */
            line-height: 45px;
        }

        /* 2.鼠标经过链接变换背景颜色 */
        .side a:hover {
            background-color: #b8c7ce;
        }
    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'north',title:'公告栏'" style="height:80px;text-align: center;line-height:45px;">
欢迎! 您的账户名为 <b><%=session.getAttribute("username")%></b>
</div>
<!--<div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>-->
<!--<div data-options="region:'east',title:'',split:true" style="width:100px;"></div>-->
<div data-options="region:'west',title:'菜单',split:true" style="width:220px;">
    <div class="side">
        <a href="cleanServlet">退出登录</a>
        <a href="register.html">注册新用户</a>
        <a href="add.html">新增学生</a>
        <a href="#">更多</a>
    </div>
</div>
<div data-options="region:'center',title:'学生表'" style="padding:5px;background:#eee;">

    <form action="listServlet" method="post">
        <table class="easyui-datagrid" style="height: auto;width: 1060px" data-options="fitColumns:false,singleSelect:true">
            <thead>
            <tr>
                <th data-options="field:'id',width:150,align:'center'">编号</th>
                <th data-options="field:'name',width:150,align:'center'">姓名</th>
                <th data-options="field:'gender',width:150,align:'center'">性别</th>
                <th data-options="field:'classlist',width:150,align:'center'">班级</th>
                <th data-options="field:'hobbies',width:150,align:'center'">兴趣爱好</th>
                <th data-options="field:'del',width:150,align:'center'">操作1</th>
                <th data-options="field:'update',width:150,align:'center' ">操作2</th>
            </tr>

                <% while (rs.next()){
                    out.println("<tr>");
                String id = rs.getString(1);
                String name = rs.getString(2);
                String gender = rs.getString(3);
                String classlist = rs.getString(4);
                String hobbies = rs.getString(5);
                out.println("<th data-options=\"field:'id',width:150,align:'center'\">"+id+"</th>");
                out.println("<th data-options=\"field:'name',width:150,align:'center'\">"+name+"</th>");
                out.println("<th data-options=\"field:'gender',width:150,align:'center'\">"+gender+"</th>");
                out.println("<th data-options=\"field:'classlist',width:150,align:'center'\">"+classlist+"</th>");
                out.println("<th data-options=\"field:'hobbies',width:150,align:'center'\">"+hobbies+"</th>");
                out.println("<th data-options=\"field:'hobbies',width:150,align:'center'\">" +
                        " <a style=\"color:#444444;text-decoration: none\" href=\"delServlet?id="+id+"\"> 删除"+"</a></th>");
                out.println("<th data-options=\"field:'hobbies',width:150,align:'center'\">" +
                                " <a style=\"color:#444444;text-decoration: none\" href=\"edit.jsp?id="+id+"&name=" +name + "&gender=" +gender+"&classlist=" +classlist+"&hobbies=" +hobbies + "\"> 修改"+"</a> "+"</th>");
            }
                    out.println("</tr>");
            %>

            </thead>
        </table>
    </form>
</div>

<!--jQuery 核心js文件-->
<script type="text/javascript" src="jq-easyui/jquery.min.js"></script>
<!--EasyUI 核心js文件-->
<script type="text/javascript" src="jq-easyui/jquery.easyui.min.js"></script>
<!--EasyUI 本地化js文件-->
<script type="text/javascript" src="jq-easyui/locale/easyui-lang-zh_CN.js"></script>

<%
    JdbcUtils.release(conn,st,rs);
%>
</body>

