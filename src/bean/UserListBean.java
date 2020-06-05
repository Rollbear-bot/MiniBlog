package bean;/*
1 * 用户列表
2 * @Author: Rollbear
3 * @Date: 2020/6/3 15:46 */

import util.DBConn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 用户列表Bean
 */
public class UserListBean {
    public UserListBean(){
        dbConn = new DBConn();  //连接数据库
    }

    /**
     * 获取某个用户的关注用户列表
     * @param userID 用户ID
     * @return 字符串数组
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
        stringBuilder.append("<tr><td>id</td><td>用户名</td><td>关注时间</td></tr>");
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
