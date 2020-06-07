package servlet;/*
1 * 用户关注行为控制器
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

        //若当前用户是游客，将其转发至登陆界面
        if (cur_user.equals("null")){
            req.setAttribute("message", "请先登录");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        else {
            //实例化一个bean来处理关注事务
            UserBean bean = new UserBean();
            bean.setID(Integer.parseInt(cur_user));
            done = bean.follow(target);

            if(done) message = "关注成功！";
            else message = "关注失败！可能是该用户已经在关注列表中";
            req.setAttribute("message", message);
            req.getRequestDispatcher("UserProfile.jsp?userID=" + target)
                    .forward(req, resp);
        }
    }
}
