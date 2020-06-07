package servlet;/*
1 * �û���ע��Ϊ������
2 * @Author: Rollbear
3 * @Date: 2020/6/7 20:41 */

import bean.UserBean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FollowController")
public class FollowController extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int target = Integer.parseInt(req.getParameter("target"));
        String cur_user = req.getParameter("cur_user");
        boolean done = false;
        String message;

        //����ǰ�û����οͣ�����ת������½����
        if (cur_user.equals("null")){
            req.setAttribute("message", "���ȵ�¼");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        else {
            //ʵ����һ��bean�������ע����
            UserBean bean = new UserBean();
            bean.setID(Integer.parseInt(cur_user));
            done = bean.follow(target);

            if(done) message = "��ע�ɹ���";
            else message = "��עʧ�ܣ������Ǹ��û��Ѿ��ڹ�ע�б���";
            req.setAttribute("message", message);
            req.getRequestDispatcher("UserProfile.jsp?userID=" + target)
                    .forward(req, resp);
        }
    }
}
