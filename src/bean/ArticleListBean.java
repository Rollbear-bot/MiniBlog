package bean;/*
1 * 
2 * @Author: Rollbear
3 * @Date: 2020/6/1 23:46 */

import util.DBConn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * （多篇）文章对象 / 文章列表模型
 */
public class ArticleListBean {
    public ArticleListBean(){
        dbConn = new DBConn();  //连接数据库
    }

    /**
     * 获取所有已发表文章的信息（用于home page展示）
     * @return 文章信息列表（标题与阅读量）
     */
    public ArrayList<String> getArticleProfile(){
        ArrayList<String> res = new ArrayList<>();
        try {
            ResultSet rs = dbConn.exec(
                    "SELECT * FROM post WHERE type='article'");
            while(rs.next()){
                res.add(rs.getString("title"));
                res.add(String.valueOf(rs.getInt("post_view")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    private final DBConn dbConn;
}
