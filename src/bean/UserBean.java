package bean;/*
1 * �����й�ע���û�ҵ���߼���Bean
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
     * �ж��û��ĵ�¼���������Ƿ���ȷ
     * @return ��¼�ɹ����
     */
    public boolean isLegal(){
        DBConn db = new DBConn();  //�������ݿ�
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
    private String email;  //email��Ϊ��¼��

}
