package bean;/*
1 * 
2 * @Author: Rollbear
3 * @Date: 2020/5/31 23:01 */

import util.DBConn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

}
