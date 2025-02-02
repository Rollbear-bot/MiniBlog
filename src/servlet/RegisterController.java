package servlet;/*
1 * 用户注册控制
2 * @Author: Rollbear
3 * @Date: 2020/6/2 11:20 */

import bean.UserBean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String email = req.getParameter("email");
        String pwd = req.getParameter("password");
        String ensure = req.getParameter("ensure");
        String userName = req.getParameter("userName");
        String gender = req.getParameter("gender");

        if(email.equals("")||pwd.equals("")||ensure.equals("")
        ||userName.equals("")||gender.equals("")){
            //存在未填写的表项
            req.setAttribute("message","存在未填写的表项");
            req.getRequestDispatcher("RegisterPage.jsp").forward(req, resp);
        }

        if(!pwd.equals(ensure)){
            //若确认密码与密码不同，则重新输入
            req.setAttribute("message","确认密码与密码不符");
            req.getRequestDispatcher("RegisterPage.jsp").forward(req, resp);
        }
        else {
            //将新用户插入到数据库
            //实例化JavaBean
            UserBean bean = new UserBean();
            bean.setUserName(userName);
            bean.setEmail(email);
            bean.setPassword(pwd);
            bean.setGender(gender);
            try {
                bean.commit();
            } catch (SQLException e) {
                if(e.getMessage().startsWith("该语句没有返回结果集")){
                    req.setAttribute("message","注册成功，请登录");
                    req.getRequestDispatcher("index.jsp")
                            .forward(req, resp);
                }
                req.setAttribute("message","注册失败，可能是当前邮箱已被使用");
                req.getRequestDispatcher("RegisterPage.jsp")
                        .forward(req, resp);
            }
        }
    }
}
