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
                res.add(String.valueOf(rs.getInt("id")));  //������ȡ��������Ψһ��ʶһ������
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * ��ȡĳ���û����ղؼ�
     * @param userID �û�ID
     * @return ������Ϣ�б�
     */
    public ArrayList<String> getFavorite(int userID){
        ArrayList<String> res = new ArrayList<>();
        try{
            //�������ӱ���ղر������û����ղص�����
            ResultSet rs = dbConn.exec(
                    "SELECT * FROM favorite, post WHERE user_id='"
                    + userID
                    + "' and favorite.article_id = post.id");
            while(rs.next()){
                res.add(rs.getString("title"));
                res.add(String.valueOf(rs.getInt("post_view")));
                res.add(String.valueOf(rs.getInt("id")));  //������ȡ��������Ψһ��ʶһ������
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * ��ȡĳ���û��Ľ�����������б�
     * @param userID �û�ID
     * @return ������Ϣ�б�
     */
    public ArrayList<String> getRecentBrowse(int userID){
        ArrayList<String> res = new ArrayList<>();
        try{
            String sql = "select distinct title, post_id, post_view\n" +
                    "FROM page_view, post \n" +
                    "WHERE [user_id]=" + userID +
                    "\tand post_id = post.id";
            ResultSet rs = dbConn.exec(sql);
            while(rs.next()){
                res.add(rs.getString("title"));
                res.add(String.valueOf(rs.getInt("post_view")));
                res.add(String.valueOf(rs.getInt("post_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * ����HTML������ǩ����ͼ��أ�
     * @param lt ���������±�����������Ϣ������
     * @return HTML��ǩ�ַ���
     */
    public String toTableLabel(ArrayList<String> lt){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table>");
        for (int index=0; index < lt.size(); index += 3) {
            stringBuilder.append("<tr>");
            stringBuilder.append("<td><a href=\"ArticlePage.jsp?article_id=")
                    .append(lt.get(index+2)).append("\">").append(lt.get(index)).append("</a></td>");
            stringBuilder.append("<td>" + "�������").append(lt.get(index+1)).append("</td>");
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }

    private final DBConn dbConn;
}
