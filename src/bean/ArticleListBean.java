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
                    "SELECT * FROM post WHERE type='article' " +
                            "ORDER BY publishing_date DESC");
            while(rs.next()){
                res.add(rs.getString("title"));
                res.add(String.valueOf(rs.getInt("post_view")));
                //把主键取出来才能唯一标识一个帖子
                res.add(String.valueOf(rs.getInt("id")));
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
                    + "' and favorite.article_id = post.id" +
                            " ORDER BY store_time DESC");
            while(rs.next()){
                res.add(rs.getString("title"));
                res.add(String.valueOf(rs.getInt("post_view")));
                //把主键取出来才能唯一标识一个帖子
                res.add(String.valueOf(rs.getInt("id")));
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
                    " FROM page_view, post \n" +
                    " WHERE [user_id]=" + userID +
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
     * 根据某个字串来匹配文章
     * @param pattern 匹配字串
     * @return 符合要求的文章的若干信息
     */
    public ArrayList<String> getSearchResult(String pattern){
        ArrayList<String> res = new ArrayList<>();
        String sql = "SELECT * FROM post " +
                " WHERE title='" + pattern + "'" +
                " OR title LIKE '%" + pattern + "'" +
                " OR title LIKE '" + pattern + "%'" +
                " OR title LIKE '%" + pattern + "%'" +
                " OR text='" + pattern + "'" +
                " OR text LIKE '%" + pattern + "'" +
                " OR text LIKE '" + pattern + "%'" +
                " OR text LIKE '%" + pattern + "%'";
        try{
            ResultSet resultSet = dbConn.exec(sql);
            while (resultSet.next()){
                res.add(resultSet.getString("title"));
                res.add(String.valueOf(resultSet.getInt("post_view")));
                res.add(String.valueOf(resultSet.getInt("id")));
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加载某篇文章的评论列表
     * @param postID 文章ID
     * @return HTML标签字符串
     */
    public String getCommentList(int postID){
        ArrayList<String> lt = new ArrayList<>();
        String sql = "SELECT * FROM post, registered_user " +
                " WHERE parent_post=" + postID +
                " and post.publisher = registered_user.id";
        try{
            //从数据库抓取对应文章的评论正文、作者和发布时间
            ResultSet resultSet = dbConn.exec(sql);
            while (resultSet.next()){
                lt.add(resultSet.getString("text"));
                lt.add(resultSet.getString("name"));
                lt.add(resultSet.getDate("publishing_date")
                        .toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table>");
        for (int index=0; index < lt.size(); index += 3) {
            stringBuilder.append("<tr>");
            for(int i = 0; i < 3; i++){
                stringBuilder.append("<td>");
                stringBuilder.append(lt.get(index+i));
                stringBuilder.append("</td>");
            }
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</table>");
        return stringBuilder.toString();
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
                    .append(lt.get(index+2)).append("\">")
                    .append(lt.get(index)).append("</a></td>");
            stringBuilder.append("<td>" + "浏览量：")
                    .append(lt.get(index+1)).append("</td>");
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }

    private final DBConn dbConn;
}
