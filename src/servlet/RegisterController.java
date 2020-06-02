package servlet;/*
1 * �û�ע�����
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pwd = req.getParameter("password");
        String ensure = req.getParameter("ensure");
        String userName = req.getParameter("userName");
        String gender = req.getParameter("gender");

        if(!pwd.equals(ensure)){
            //��ȷ�����������벻ͬ������������
            req.setAttribute("message","ȷ�����������벻��");
            req.getRequestDispatcher("RegisterPage.jsp").forward(req, resp);
        }
        else {
            //�����û����뵽���ݿ�
            //ʵ����JavaBean
            UserBean bean = new UserBean();
            bean.setUserName(userName);
            bean.setEmail(email);
            bean.setPassword(pwd);
            bean.setGender(gender);
            //todo::ע��ɹ�
            try {
                bean.commit();
            } catch (SQLException e) {
                if(e.getMessage().startsWith("δ����")){
                    req.getRequestDispatcher("HomePage.jsp")
                            .forward(req, resp);
                }
                req.setAttribute("message","ע��ʧ�ܣ������ǵ�ǰ�����ѱ�ʹ��");
                req.getRequestDispatcher("RegisterPage.jsp")
                        .forward(req, resp);
            }
        }
    }
}
