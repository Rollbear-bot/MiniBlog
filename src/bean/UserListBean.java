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
     * 根据某个字串来匹配用户
     * @param pattern 匹配字串
     * @return String array of(用户id, 用户名, 注册时间)
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
     * 将用户信息数组转化为HTML表格风格字符串
     * @param lt 用户信息数组
     * @return HTML表格标签字符串
     */
    public String toTableLabel(ArrayList<String> lt, String dateLabel){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table>");
        stringBuilder.append("<tr><td>id</td><td>用户名</td><td>")
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
