package servlet;

import utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "registerServlet", value = "/registerServlet")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = JdbcUtils.getConnection();
            String sql ="insert into users values (?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            int i = ps.executeUpdate();

            if (i == 1){
                response.getWriter().write("<h4><a href=\"login.html\">注册成功！点击返回登录</a></h4>");
            }else {
                response.getWriter().write("<h4><a href=\"register.html\">注册失败。点击返回重新注册</a></h4>");
            }


        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,ps,rs);
        }
    }
}