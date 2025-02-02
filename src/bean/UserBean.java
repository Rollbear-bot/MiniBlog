package bean;/*
1 * 用户业务逻辑的Bean
2 * @Author: Rollbear
3 * @Date: 2020/5/31 23:01 */

import util.DBConn;
import java.sql.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBean {
    /**
     * 构造函数
     */
    public UserBean(){
        dbConn = new DBConn();  //连接数据库
    }

    /**
     * 判断用户的登录名和密码是否正确
     * @return 登录成功与否
     */
    public boolean isExisted(){
        try{
            ResultSet rs = dbConn.exec("SELECT * FROM registered_user WHERE email='"
                    + email + "'"
                    + "and password='"
                    + password + "'");
            if (rs.next()){
                userID = rs.getInt("id");
                gender = rs.getString("gender");
                userName = rs.getString("name");
                return rs.getInt("ban") != 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置用户ID的同时从数据库取出对应记录
     * 并初始化用户对象的各个字段
     * @param id 用户id
     * @return 执行是否成功
     */
    public boolean setID(int id){
        this.userID = id;
        String sql = "SELECT * FROM registered_user WHERE id=" + id;
        try {
            ResultSet resultSet = dbConn.exec(sql);
            if (resultSet.next()){
                userName = resultSet.getString("name");
                gender = resultSet.getString("gender");
                email = resultSet.getString("email");
                password = resultSet.getString("password");
                permission = resultSet.getInt("permission");
                registering_date
                        = resultSet.getDate("registering_date");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将当前对象保存的用户信息插入到数据库
     * @throws SQLException SQL异常（尤其是邮箱重复）
     */
    public void commit() throws SQLException {
        dbConn.exec("INSERT INTO registered_user " +
                "(name, gender, email, password) VALUES("
                + "'" + userName + "',"
                + "'" + gender + "',"
                + "'" + email + "',"
                + "'" + password + "')");
    }

    /**
     * 记录用户浏览了某篇文章
     * @param userID 用户ID
     * @param postID 文章ID
     * @return 记录是否成功
     */
    public boolean view(int userID, int postID){
        String update = "UPDATE post SET post_view = post_view + 1"
                + " WHERE id=" + postID;
        String insert = "INSERT INTO page_view (user_id, post_id)"
                + " VALUES ('" + userID + "','" + postID + "')";

        String sql = "";
        if(userID != 0)
            sql = update + ";" + insert;
        //id为0表示访问者是游客，也需要记录访问量，只是不登录浏览记录
        else sql = update;

        try {
            dbConn.exec(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            if(e.getMessage().startsWith("该语句没有返回结果集"))
                return true;
        }
        return false;
    }

    /**
     * 将某个用户添加到当前用户的关注列表
     * @param targetID 目标用户的ID
     * @return 操作执行是否成功
     */
    public boolean follow(int targetID){
        if (this.userID == targetID) return false; //不能关注自己
        if(this.userID == 0) return false;  //尚未设置当前用户的主键

        //查找当前用户已关注的用户
        ArrayList<Integer> followed = new ArrayList<>();
        String select = "SELECT * FROM follow WHERE follower =" + userID;
        try{
            ResultSet resultSet = dbConn.exec(select);
            while (resultSet.next())
                followed.add(resultSet.getInt("target"));
            for(int f: followed)
                if (f == targetID) {
                    //此处关注失败是因为该用户已经在关注列表中
                    return false;
                }
        } catch (SQLException e) {
            return false;
        }
        //插入关注记录
        String insert = "INSERT INTO follow (follower, target)" +
                " VALUES ('" + this.userID + "','" + targetID + "')";
        try{
            dbConn.exec(insert);
        } catch (SQLException e) {
            e.printStackTrace();
            if(e.getMessage().startsWith("该语句没有返回结果集"))
                return true;
        }
        return false;
    }

    /**
     * 封禁当前用户
     * @return 操作是否成功
     */
    public boolean ban(){
        String sql = "UPDATE registered_user SET ban=1 " +
                "WHERE id=" + this.userID;
        try{
            dbConn.exec(sql);
        } catch (SQLException e) {
            if(e.getMessage().startsWith("该语句没有返回结果集"))
                return true;
        }
        return false;
    }

    private int userID;
    private String password;
    private String email;  //email作为登录名
    private String gender;
    private String userName;
    private Date registering_date;
    private int permission;
    private final DBConn dbConn;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender(){return gender;}

    public String getEmail(){return email;}

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName(){ return userName;}

    public int getUserID() {
        return userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistering_date() {
        return registering_date;
    }

    public int getPermission() {
        return permission;
    }
}
