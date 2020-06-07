package servlet;/*
1 * 用户/评论/帖子封禁控制器
2 * @Author: Rollbear
3 * @Date: 2020/6/7 22:44 */

import bean.PostBean;
import bean.UserBean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BanController")
public class BanController extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String article_id = req.getParameter("article_id");
        String user_id = req.getParameter("user_id");
        UserBean userBean = new UserBean();
        PostBean postBean = new PostBean();
        String message;
        boolean done = false;  //操作是否成功

        //处理文章屏蔽
        if (article_id != null){
            //用对应的bean来进行操作
            postBean.setPostID(Integer.parseInt(article_id));
            done = postBean.ban();
        }

        //处理用户封禁
        else if (user_id != null){
            //用对应的bean来进行操作
            userBean.setID(Integer.parseInt(user_id));
            done = userBean.ban();
        }

        if(done) message = "操作成功！";
        else message = "操作失败！";
        req.setAttribute("message", message);
        req.getRequestDispatcher("Admin.jsp").forward(req, resp);
    }
}
