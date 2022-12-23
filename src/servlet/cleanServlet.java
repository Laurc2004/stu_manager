package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "cleanServlet", value = "/cleanServlet")
public class cleanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        session.invalidate();
        response.getWriter().write("<h2>退出登录成功</h2>" +
                "<h4><a href=\"login.html\">返回登录</a>" +"&nbsp;&nbsp;&nbsp;"+
                "<a href=\"register.html\">注册</a></h4>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
