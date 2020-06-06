package bean;/*
1 * �û�ҵ���߼���Bean
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
                userID = rs.getInt("id");
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
     * �����û�ID��ͬʱ�����ݿ�ȡ����Ӧ��¼
     * ����ʼ���û�����ĸ����ֶ�
     * @param id �û�id
     * @return ִ���Ƿ�ɹ�
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
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

    /**
     * ��¼�û������ĳƪ����
     * @param userID �û�ID
     * @param postID ����ID
     * @return ��¼�Ƿ�ɹ�
     */
    public boolean view(int userID, int postID){
        String update = "UPDATE post SET post_view = post_view + 1"
                + " WHERE id=" + postID;
        String insert = "INSERT INTO page_view (user_id, post_id)"
                + " VALUES ('" + userID + "','" + postID + "')";

        String sql = "";
        if(userID != 0)
            sql = update + ";" + insert;
        //idΪ0��ʾ���������οͣ�Ҳ��Ҫ��¼��������ֻ�ǲ���¼�����¼
        else sql = update;

        try {
            dbConn.exec(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            if(e.getMessage().startsWith("�����û�з��ؽ����"))
                return true;
        }
        return false;
    }

    /**
     * �����û�
     * @param pattern ƥ���ִ�
     */
    public void userSearcher(String pattern){

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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
