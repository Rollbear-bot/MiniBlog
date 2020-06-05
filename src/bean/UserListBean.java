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
    public String getFollowing(int userID){
        ArrayList<String> lt = new ArrayList<>();
        try {
            ResultSet rs = dbConn.exec(
                    "SELECT * FROM follow, registered_user WHERE "
                            + "follower = '"
                            + userID
                            + "' and target = registered_user.id");
            while(rs.next()){
                lt.add(rs.getString("target"));
                lt.add(String.valueOf(rs.getString("name")));
                lt.add(String.valueOf(rs.getDate("following_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table>");
        stringBuilder.append("<tr><td>id</td><td>�û���</td><td>��עʱ��</td></tr>");
        for (int index=0; index < lt.size(); index++) {
            stringBuilder.append("<tr>");
            stringBuilder.append("<td><a href=\"ArticlePage.jsp?title=")
                    .append(lt.get(index)).append("\">").append(lt.get(index)).append("</a></td>");
            index++;
            stringBuilder.append("<td>").append(lt.get(index)).append("</td>");
            index++;
            stringBuilder.append("<td>").append(lt.get(index)).append("</td>");
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }

    private final DBConn dbConn;
}
