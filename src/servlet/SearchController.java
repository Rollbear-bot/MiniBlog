package servlet;/*
1 * 搜索控制器
2 * @Author: Rollbear
3 * @Date: 2020/6/2 16:17 */

import bean.ArticleListBean;
import bean.UserListBean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /**
     * 处理用户的搜索信息表单
     * @param req request对象
     * @param resp response对象
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        String type = req.getParameter("searchType");
        String text = req.getParameter("text");
        String result = "";

        ArticleListBean articleListBean = new ArticleListBean();
        UserListBean userListBean = new UserListBean();

        if(type.equals("搜索帖子")){
            result = articleListBean.toTableLabel(
                    articleListBean.getSearchResult(text));
        }
        else if(type.equals("搜索用户")){
            result = userListBean.toTableLabel(
                    userListBean.getSearchResult(text), "注册时间");
        }

        req.setAttribute("result", result);
        req.getRequestDispatcher("SearchResult.jsp").forward(req, resp);
    }
}
