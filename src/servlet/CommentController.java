package servlet;/*
1 * �������Կ�����
2 * @Author: Rollbear
3 * @Date: 2020/6/6 20:15 */

import bean.PublishBean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CommentController")
public class CommentController extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String parent = req.getParameter("parent_post");
        String text = req.getParameter("text");
        String uid = req.getParameter("userID");
        boolean done = false;

        //ʵ����bean�������۵����ݿ�
        PublishBean bean = new PublishBean();

        if (uid == null || uid.equals("")
                || uid.equals("null") || uid.equals("0")) {
            req.setAttribute("message", "��Ҫ�������ۣ����ȵ�¼");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        else {
            done = bean.commitNewComment(text,
                    Integer.parseInt(uid),
                    Integer.parseInt(parent));
            if(done)
                req.setAttribute("message", "���۳ɹ���");
            else req.setAttribute("message", "����ʧ�ܣ�");

            req.getRequestDispatcher("ArticlePage.jsp?article_id=" + parent)
                    .forward(req, resp);
        }
    }
}
