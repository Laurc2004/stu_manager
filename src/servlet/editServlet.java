package servlet;

import utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

@WebServlet(name = "editServlet", value = "/editServlet")
public class editServlet extends HttpServlet {
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

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = JdbcUtils.getConnection();
            String sql ="update students set name = ?, gender = ?, classlist = ?,hobbies = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,gender);
            ps.setString(3,classlist);
            ps.setString(4, Arrays.toString(hobbies).replace("[","").replace("]",""));
            ps.setString(5,id);
            int i = ps.executeUpdate();

            if (i == 1){
                response.getWriter().write("<h4><a href=\"list.jsp\">更新成功！点击返回继续操作</a></h4>");
            }else {
                response.getWriter().write("<h4><a href=\"list.jsp\">更新失败。点击返回重新操作</a></h4>");
            }


        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,ps,rs);
        }
    }
}
