package servlet;/*
1 * 文章发布控制器
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
        //从表单中获取数据
        int userID = Integer.parseInt(req.getParameter("userID"));
        String title = (String) req.getParameter("title");
        String text = (String) req.getParameter("text");

        if(title.equals("") || text.equals("")){
            req.setAttribute("message", "发布失败！请检查表项填写是否完整");
            req.setAttribute("login", true);
            req.getRequestDispatcher("HomePage.jsp").forward(req, resp);
        }

        //实例化一个Bean来处理发布相关事务
        PublishBean bean = new PublishBean();
        boolean done = bean.commitNewArticle(title, text, userID);

        //将发布状态转发
        if(done)
            req.setAttribute("message", "发布成功！");
        else
            req.setAttribute("message", "发布失败！");
        //转发回到主页面
        req.setAttribute("login", true);
        req.getRequestDispatcher("HomePage.jsp").forward(req, resp);
    }
}
