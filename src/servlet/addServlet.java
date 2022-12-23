package servlet;

import utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet(name = "addServlet", value = "/addServlet")
public class addServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf8");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String classlist = request.getParameter("classlist");
        String hobbies[] = request.getParameterValues("hobbies");

        System.out.println(gender);
        System.out.println(classlist);

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = JdbcUtils.getConnection();
            String sql ="insert into students values (?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,name);
            ps.setString(3,gender);
            ps.setString(4,classlist);
            ps.setString(5,Arrays.toString(hobbies).replace("[","").replace("]",""));
            int i = ps.executeUpdate();

            if (i == 1){
                response.getWriter().write("<h4><a href=\"list.jsp\">新增成功！点击返回继续操作</a></h4>");
            }else {
                response.getWriter().write("<h4><a href=\"list.jsp\">新增失败。点击返回重新操作</a></h4>");
            }


        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,ps,rs);
        }
    }
    }
