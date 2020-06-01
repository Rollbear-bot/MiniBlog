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

    /**
     * ���캯��
     * �ڹ��캯���н������ݿ�����
     */
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

    /**
     * ִ��SQL��䲢���ؽ����
     * @param sql SQL���
     * @return �����ResultSet����
     * @throws SQLException ��׼��SQL�����쳣
     */
    public ResultSet exec(String sql) throws SQLException {
        return statement.executeQuery(sql);
    }

    /**
     * ��������
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

