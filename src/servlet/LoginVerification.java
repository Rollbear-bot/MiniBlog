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
        //��ȡ���еĲ���
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");

        //ʵ����һ��JavaBean
        UserBean bean = new UserBean();
        bean.setEmail(email);
        bean.setPassword(pwd);

        if(bean.isLegal()){
            //��½�ɹ�
            req.getRequestDispatcher("HomePage.jsp").forward(req, resp);
        }
        else {
            //��½ʧ�ܣ����ص�¼ҳ��
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
