package servlet;

import com.mysql.cj.Session;
import utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

import static java.lang.System.out;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        session.setAttribute("username",username);
        session.setAttribute("password",password);

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql ="select * from users where username = '"+username+"' "+"and password = '"+password+"';";
            ResultSet resultSet = st.executeQuery(sql);
            if (resultSet.next()){
                response.sendRedirect("list.jsp");
            }else {
                response.getWriter().write("<h2>登录失败，请检查账号密码是否正确</h2>" +
                        "<h4><a href=\"login.html\">返回登录</a>" +"&nbsp;&nbsp;&nbsp;"+
                        "<a href=\"register.html\">注册</a></h4>");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}