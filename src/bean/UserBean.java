package bean;/*
1 * 处理有关注册用户业务逻辑的Bean
2 * @Author: Rollbear
3 * @Date: 2020/5/31 23:01 */

import util.DBConn;
import java.sql.*;

import java.sql.SQLException;

public class UserBean {
    /**
     * 构造函数
     */
    public UserBean(){
        dbConn = new DBConn();  //连接数据库
    }

    public void setEmail(String e){
        email = e;
    }

    public void setPassword(String pwd){
        password = pwd;
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
                userID = rs.getInt(1);
                gender = rs.getString("gender");
                userName = rs.getString("name");
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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

    private int userID;
    private String password;
    private String email;  //email作为登录名
    private String gender;
    private String userName;
    private final DBConn dbConn;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName(){ return userName;}

    public int getUserID() {
        return userID;
    }
}
