package util;/*
1 * ���ݿ��������
2 * @Author: Rollbear
3 * @Date: 2020/5/31 23:25 */

import java.sql.*;

public class DBConn {
    final private String login_name = "blog_admin";
    final private String pwd = "psd_123456";

    Connection conn;
    Statement statement;

    public DBConn(){
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL = "jdbc:sqlserver://localhost:1433;Database=MiniBlog";

        //�������ݿ�
        try{
            conn = DriverManager.getConnection(dbURL, login_name, pwd);
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

