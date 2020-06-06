package servlet;/*
1 * 文章收藏控制器
2 * @Author: Rollbear
3 * @Date: 2020/6/6 18:30 */

import bean.PostBean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FavoriteController")
public class FavoriteController extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String uid = req.getParameter("userID");
        String pid = req.getParameter("postID");

        PostBean bean = new PostBean();
        bean.setId(Integer.parseInt(pid));

        if (uid == null || uid.equals("") || uid.equals("null")){
            req.setAttribute("message","若要收藏文章，请先登录");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        else{
            bean.addToFavorite(Integer.parseInt(uid));
            req.setAttribute("message","收藏文章成功！");
            req.getRequestDispatcher("ArticlePage.jsp?article_id=" + pid)
                    .forward(req, resp);
        }
    }
}
