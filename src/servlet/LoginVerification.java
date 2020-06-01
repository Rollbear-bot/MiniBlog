package servlet;/*
1 * 
2 * @Author: Rollbear
3 * @Date: 2020/6/1 18:52 */

import bean.UserBean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginVerification")
public class LoginVerification extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取表单中的参数
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");

        //实例化一个JavaBean
        UserBean bean = new UserBean();
        bean.setEmail(email);
        bean.setPassword(pwd);

        if(bean.isLegal()){
            //登陆成功
            req.getRequestDispatcher("HomePage.jsp").forward(req, resp);
        }
        else {
            //登陆失败，返回登录页面
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
