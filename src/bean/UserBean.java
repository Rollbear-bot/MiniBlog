package bean;/*
1 * �����й�ע���û�ҵ���߼���Bean
2 * @Author: Rollbear
3 * @Date: 2020/5/31 23:01 */

import util.DBConn;
import java.sql.*;

import java.sql.SQLException;

public class UserBean {
    /**
     * ���캯��
     */
    public UserBean(){
        dbConn = new DBConn();  //�������ݿ�
    }

    public void setEmail(String e){
        email = e;
    }

    public void setPassword(String pwd){
        password = pwd;
    }

    /**
     * �ж��û��ĵ�¼���������Ƿ���ȷ
     * @return ��¼�ɹ����
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
     * ����ǰ���󱣴���û���Ϣ���뵽���ݿ�
     * @throws SQLException SQL�쳣�������������ظ���
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
    private String email;  //email��Ϊ��¼��
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
