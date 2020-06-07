package bean;/*
1 * �û��б�
2 * @Author: Rollbear
3 * @Date: 2020/6/3 15:46 */

import util.DBConn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * �û��б�Bean
 */
public class UserListBean {
    public UserListBean(){
        dbConn = new DBConn();  //�������ݿ�
    }

    /**
     * ��ȡĳ���û��Ĺ�ע�û��б�
     * @param userID �û�ID
     * @return �ַ�������
     */
    public ArrayList<String> getFollowing(int userID){
        ArrayList<String> lt = new ArrayList<>();
        try {
            ResultSet rs = dbConn.exec(
                    "SELECT * FROM follow, registered_user WHERE "
                            + "follower = '"
                            + userID
                            + "' and target = registered_user.id"
                            + " and ban=0");
            while(rs.next()){
                lt.add(rs.getString("target"));
                lt.add(String.valueOf(rs.getString("name")));
                lt.add(String.valueOf(rs.getDate("following_date")));
            }
            return lt;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ����ĳ���ִ���ƥ���û�
     * @param pattern ƥ���ִ�
     * @return String array of(�û�id, �û���, ע��ʱ��)
     */
    public ArrayList<String> getSearchResult(String pattern){
        ArrayList<String> res = new ArrayList<>();
        String sql = "SELECT * FROM registered_user " +
                " WHERE ban=0 and name='" + pattern + "'" +
                " OR name LIKE '%" + pattern + "'" +
                " OR name LIKE '" + pattern + "%'" +
                " OR name LIKE '%" + pattern + "%'";
        try{
            ResultSet resultSet = dbConn.exec(sql);
            while(resultSet.next()){
                res.add(String.valueOf(resultSet.getInt("id")));
                res.add(resultSet.getString("name"));
                res.add(resultSet.getDate("registering_date")
                        .toString());
            }
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ���û���Ϣ����ת��ΪHTML������ַ���
     * @param lt �û���Ϣ����
     * @return HTML����ǩ�ַ���
     */
    public String toTableLabel(ArrayList<String> lt, String dateLabel){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table>");
        stringBuilder.append("<tr><td>id</td><td>�û���</td><td>")
                .append(dateLabel).append("</td></tr>");
        for (int index=0; index < lt.size(); index+=3) {
            stringBuilder.append("<tr>");
            stringBuilder.append("<td>")
                    .append(lt.get(index)).append("</td>");
            stringBuilder.append("<td><a href=\"UserProfile.jsp?userID=")
                    .append(lt.get(index)).append("\">")
                    .append(lt.get(index+1)).append("</a></td>");
            stringBuilder.append("<td>").append(lt.get(index+2)).append("</td>");
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }

    private final DBConn dbConn;
}
