package com.scoremanagement.persistance;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.scoremanagement.connection.OracleConnection;
import com.scoremanagement.domain.Admin;
 
public class AdminDAO {
    
    // 관리자 로그인 메소드
    public String login(Admin a) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        String admin_id = null;
        
        try {
            conn = OracleConnection.connect();
            
            String sql = "SELECT admin_id\r\n" + 
                            "    FROM admin\r\n" + 
                            "    WHERE admin_id = ? AND admin_pw = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, a.getAdmin_id());
            pstmt.setString(2, a.getAdmin_pw());
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                admin_id = rs.getString("admin_id");
            }
            
            rs.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                OracleConnection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return admin_id;
    }
    
    // 관리자 패스워드 변경 메소드
    public int modify(Admin a) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            String sql = "UPDATE admin\r\n" + 
                    "    SET admin_pw = ?\r\n" + 
                    "    WHERE admin_id = ? AND admin_pw = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, a.getAdmin_new_pw());
            pstmt.setString(2, a.getAdmin_id());
            pstmt.setString(3, a.getAdmin_pw());
            
            result = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                OracleConnection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return result;
    }
}