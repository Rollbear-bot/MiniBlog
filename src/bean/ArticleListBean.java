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
                res.add(String.valueOf(rs.getInt("id")));  //把主键取出来才能唯一标识一个帖子
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 获取某个用户的收藏夹
     * @param userID 用户ID
     * @return 文章信息列表
     */
    public ArrayList<String> getFavorite(int userID){
        ArrayList<String> res = new ArrayList<>();
        try{
            //连接帖子表和收藏表，查找用户已收藏的帖子
            ResultSet rs = dbConn.exec(
                    "SELECT * FROM favorite, post WHERE user_id='"
                    + userID
                    + "' and favorite.article_id = post.id");
            while(rs.next()){
                res.add(rs.getString("title"));
                res.add(String.valueOf(rs.getInt("post_view")));
                res.add(String.valueOf(rs.getInt("id")));  //把主键取出来才能唯一标识一个帖子
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 获取某个用户的近期浏览文章列表
     * @param userID 用户ID
     * @return 文章信息列表
     */
    public ArrayList<String> getRecentBrowse(int userID){
        ArrayList<String> res = new ArrayList<>();
        try{
            String sql = "select distinct title, post_id, post_view\n" +
                    "FROM page_view, post \n" +
                    "WHERE [user_id]=" + userID +
                    "\tand post_id = post.id";
            ResultSet rs = dbConn.exec(sql);
            while(rs.next()){
                res.add(rs.getString("title"));
                res.add(String.valueOf(rs.getInt("post_view")));
                res.add(String.valueOf(rs.getInt("post_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 生成HTML风格表格标签（视图相关）
     * @param lt 保存了文章标题和浏览量信息的数组
     * @return HTML标签字符串
     */
    public String toTableLabel(ArrayList<String> lt){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table>");
        for (int index=0; index < lt.size(); index += 3) {
            stringBuilder.append("<tr>");
            stringBuilder.append("<td><a href=\"ArticlePage.jsp?article_id=")
                    .append(lt.get(index+2)).append("\">").append(lt.get(index)).append("</a></td>");
            stringBuilder.append("<td>" + "浏览量：").append(lt.get(index+1)).append("</td>");
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }

    private final DBConn dbConn;
}
