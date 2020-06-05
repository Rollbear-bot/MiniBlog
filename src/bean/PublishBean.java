package bean;/*
1 * ����/���Է������
2 * @Author: Rollbear
3 * @Date: 2020/6/5 17:37 */

import util.DBConn;

import java.sql.Date; 
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ���·������
 */
public class PublishBean {
    public PublishBean(){
        dbConn = new DBConn();  //�������ݿ�
    }

    /**
     * ��������/����id��ͬʱ��ץȡ���ݿ��еĶ�Ӧ��¼����ʼ�������ֶ�
     * @param id ����/���Զ���
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
     * ����һƪ���µ����ݿ�
     * @param title ���±���
     * @param text ����
     * @param userID �������µ��û���ID
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
