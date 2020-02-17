package com.example.life_deposit_book.ui.login;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlCon {
    // 資料庫定義
    String mysql_ip = "140.123.92.130";
    int mysql_port = 3306; // Port 預設為 3306
    String db_name = "test";
    String url = "jdbc:mysql://"+mysql_ip+":"+mysql_port+"/"+db_name;
    String db_user = "newuser";
    String db_password = "newpassword";

    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.v("DB","加載驅動成功");
        }catch( ClassNotFoundException e) {
            Log.e("DB","加載驅動失敗");
            return;
        }

        // 連接資料庫
        try {
            Connection con = DriverManager.getConnection(url,db_user,db_password);
            Log.v("DB","遠端連接成功");
        }catch(SQLException e) {
            Log.e("DB","遠端連接失敗");
            Log.e("DB", e.toString());
        }
    }

    public String getData() {
        String data = "";
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT * FROM test2";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {
                //int id = rs.getInt("id");
                String stuid = rs.getString("stuid");
                String pwd = rs.getString("pwd");
                data += stuid + " " + pwd + " \n";//這邊我卡超久，原因是換行字元前面沒有空格，所以密碼欄位就會變成123456"換行"...
            }

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

}
