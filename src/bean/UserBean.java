package bean;/*
1 * 处理有关注册用户业务逻辑的Bean
2 * @Author: Rollbear
3 * @Date: 2020/5/31 23:01 */

import util.DBConn;
import java.sql.*;

import java.sql.SQLException;

public class UserBean {
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
    public boolean isLegal(){
        DBConn db = new DBConn();  //连接数据库
        try{
            ResultSet rs = db.exec("SELECT * FROM registered_user WHERE email='"
                    + email + "'"
                    + "and password='"
                    + password + "'");
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String password;
    private String email;  //email作为登录名

}
