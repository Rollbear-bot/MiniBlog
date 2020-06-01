package util;/*
1 * 数据库连接相关
2 * @Author: Rollbear
3 * @Date: 2020/5/31 23:25 */

import java.sql.*;

public class DBConn {
    final private String login_name = "blog_admin";
    final private String pwd = "psd_123456";

    Connection conn;
    Statement statement;

    /**
     * 构造函数
     * 在构造函数中建立数据库连接
     */
    public DBConn(){
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL = "jdbc:sqlserver://localhost:1433;Database=MiniBlog";

        //连接数据库
        try{
            conn = DriverManager.getConnection(dbURL, login_name, pwd);
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行SQL语句并返回结果集
     * @param sql SQL语句
     * @return 结果集ResultSet对象
     * @throws SQLException 标准库SQL内置异常
     */
    public ResultSet exec(String sql) throws SQLException {
        return statement.executeQuery(sql);
    }

    /**
     * 测试主类
     * @param args sys params
     */
    public static void main(String[] args) {
        try{
            DBConn db = new DBConn();
            ResultSet rs = db.exec("SELECT * FROM registered_user");
            while (rs.next()){
               String s = rs.getString(2);
               System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

