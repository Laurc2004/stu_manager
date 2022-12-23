package utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class JdbcUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static {

        try{
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(in);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
			//加载驱动
            Class.forName(driver);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {
//        System.out.println(url);
//        System.out.println(username);
//        System.out.println(password);
        return DriverManager.getConnection(url,username,password);
    }

    public static void release(Connection conn, Statement st, ResultSet rs){
        if(rs!=null){
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(st!=null){
            try{
                st.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

    }

}

