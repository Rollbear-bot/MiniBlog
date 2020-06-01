package bean;/*
1 * 
2 * @Author: Rollbear
3 * @Date: 2020/6/1 23:46 */

import util.DBConn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ����ƪ�����¶��� / �����б�ģ��
 */
public class ArticleListBean {
    public ArticleListBean(){
        dbConn = new DBConn();  //�������ݿ�
    }

    /**
     * ��ȡ�����ѷ������µ���Ϣ������home pageչʾ��
     * @return ������Ϣ�б��������Ķ�����
     */
    public ArrayList<String> getArticleProfile(){
        ArrayList<String> res = new ArrayList<>();
        try {
            ResultSet rs = dbConn.exec(
                    "SELECT * FROM post WHERE type='article'");
            while(rs.next()){
                res.add(rs.getString("title"));
                res.add(String.valueOf(rs.getInt("post_view")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    private final DBConn dbConn;
}
