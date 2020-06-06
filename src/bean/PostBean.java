package bean;/*
1 * 
2 * @Author: Rollbear
3 * @Date: 2020/5/31 23:01 */

import util.DBConn;

import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

/**
 * （单个）帖子对象
 */
public class PostBean {
    public PostBean(){
        dbConn = new DBConn();  //连接数据库
    }

    private int id;
    private String title;
    private String text;
    private int postView;
    private String type;
    private Date publishing_date;
    private final DBConn dbConn;

    /**
     * 从JSP接收主键（文章ID）的同时，
     * 从数据库抓取对应的记录，并初始化各个字段
     * @param id 文章/评论ID
     */
    public void setId(int id) {
        this.id = id;
        String sql = "SELECT * FROM post WHERE id='" + id + "'";
        try{
            ResultSet rs = dbConn.exec(sql);
            rs.next();
            this.title = rs.getString("title");
            this.text = rs.getString("text");
            this.postView = rs.getInt("post_view");
            this.type = rs.getString("type");
            this.publishing_date = rs.getDate("publishing_date");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将当前文章添加到一个用户的收藏夹
     * @param userID 收藏用户的ID
     * @return 收藏成功或失败
     */
    public boolean addToFavorite(int userID){
        if(this.id==0) return false;
        String sql = "INSERT INTO favorite (user_id, article_id)" +
                " VALUES ('" + userID + "','" + this.id + "')";
        try{
            dbConn.exec(sql);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            if(e.getMessage().startsWith("该语句没有返回结果集"))
                return true;
        }
        return false;
    }

    /**
     * 获取某篇文章的作者名
     * 需要bean的id字段已经初始化
     * @return 作者名
     */
    public String getAuthorName(){
        if(id == 0) return null;
        String sql = "SELECT name FROM post, registered_user" +
                " WHERE publisher = registered_user.id" +
                " and post.id = " + this.id;
        try{
            ResultSet rs = dbConn.exec(sql);
            if (rs.next()){
                return rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //getters below
    public int getPostView() {
        return postView;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPublishingDate(){
        return this.publishing_date.toString();
    }

}
