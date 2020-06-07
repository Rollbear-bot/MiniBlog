package servlet;/*
1 * ����������
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
     * �����û���������Ϣ��
     * @param req request����
     * @param resp response����
     * @throws ServletException Servlet�쳣
     * @throws IOException IO�쳣
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

        if(type.equals("��������")){
            result = articleListBean.toTableLabel(
                    articleListBean.getSearchResult(text));
        }
        else if(type.equals("�����û�")){
            result = userListBean.toTableLabel(
                    userListBean.getSearchResult(text), "ע��ʱ��");
        }

        req.setAttribute("result", result);
        req.getRequestDispatcher("SearchResult.jsp").forward(req, resp);
    }
}
