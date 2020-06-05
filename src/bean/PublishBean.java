package bean;/*
1 * 文章/留言发布相关
2 * @Author: Rollbear
3 * @Date: 2020/6/5 17:37 */

import util.DBConn;

import java.sql.Date; 
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 文章发布相关
 */
public class PublishBean {
    public PublishBean(){
        dbConn = new DBConn();  //连接数据库
    }

    /**
     * 设置文章/留言id的同时，抓取数据库中的对应记录来初始化各个字段
     * @param id 文章/留言对象
     */
    public void setId(int id) {
        this.id = id;
        String sql = "SELECT * FROM post WHERE id=" + id;
        try{
            ResultSet rs = dbConn.exec(sql);
            if(rs.next()){
                title = rs.getString("title");
                text = rs.getString("text");
                post_view = rs.getInt("post_view");
                type = rs.getString("type");
                publisher = rs.getInt("publisher");
                parent_post = rs.getInt("parent_post");
                publishing_date = rs.getDate("publishing_date");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入一篇文章到数据库
     * @param title 文章标题
     * @param text 正文
     * @param userID 发布文章的用户的ID
     */
    public void CommitNewArticle(String title, String text, int userID){
        String sql = "INSERT INTO post (title, text, publisher) VALUES"
                + "(" + title + "," + text + "," + userID + ")";
        try {
            dbConn.exec(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private final DBConn dbConn;
    private int id;
    private String title;
    private String text;
    private int post_view;
    private String type;
    private int publisher;
    private int parent_post;
    private Date publishing_date;
    
    //getters below
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public int getPost_view() {
        return post_view;
    }

    public String getType() {
        return type;
    }

    public int getPublisher() {
        return publisher;
    }

    public int getParent_post() {
        return parent_post;
    }

    public Date getPublishing_date() {
        return publishing_date;
    }
}
