package servlet;/*
1 * ���·���������
2 * @Author: Rollbear
3 * @Date: 2020/6/5 18:15 */

import bean.PublishBean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PublishController")
public class PublishController extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        //�ӱ��л�ȡ����
        int userID = Integer.parseInt(req.getParameter("userID"));
        String title = (String) req.getParameter("title");
        String text = (String) req.getParameter("text");

        if(title.equals("") || text.equals("")){
            req.setAttribute("message", "����ʧ�ܣ����������д�Ƿ�����");
            req.setAttribute("login", true);
            req.getRequestDispatcher("HomePage.jsp").forward(req, resp);
        }

        //ʵ����һ��Bean���������������
        PublishBean bean = new PublishBean();
        boolean done = bean.commitNewArticle(title, text, userID);

        //������״̬ת��
        if(done)
            req.setAttribute("message", "�����ɹ���");
        else
            req.setAttribute("message", "����ʧ�ܣ�");
        //ת���ص���ҳ��
        req.setAttribute("login", true);
        req.getRequestDispatcher("HomePage.jsp").forward(req, resp);
    }
}
