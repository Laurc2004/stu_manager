package servlet;

import utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "delServlet", value = "/delServlet")
public class delServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf8");

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        String id = request.getParameter("id");

        try{
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql ="delete from students where id = " + id;
            int i = st.executeUpdate(sql);
            if (i > 0){
                response.getWriter().write("<h4><a href=\"list.jsp\">删除成功！点击返回登录</a></h4>");
            }else {
                response.getWriter().write("<h4><a href=\"list.jsp\">删除失败。点击返回登录</a></h4>");
            }


        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
