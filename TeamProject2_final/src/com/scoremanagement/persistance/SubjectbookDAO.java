package com.scoremanagement.persistance;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import com.scoremanagement.connection.OracleConnection;
import com.scoremanagement.domain.Subjectbook;
 
public class SubjectbookDAO {
    
    // 교재 입력 메소드
    public int insert(Subjectbook sb) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            String sql = "INSERT INTO subjectbook (subjectbook_id, subjectbook_name,subjectbook_publisher)   \r\n" + 
                    "    VALUES ((SELECT CONCAT('SB',\r\n" + 
                    "         LPAD(NVL(SUBSTR(MAX(subjectbook_id),3), 0) + 1, 4, 0)) AS newId FROM subjectbook)\r\n" + 
                    "         , ?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sb.getSubjectbook_name());
            pstmt.setString(2, sb.getSubjectbook_publisher());
            
            result = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return result;
    }
    
    // 교재 출력 메소드
    // 교재번호 / 교재명 / 교재출판사
    public List<Subjectbook> list1() {
        List<Subjectbook> list = new ArrayList<Subjectbook>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            
            String sql = "SELECT subjectbook_id, subjectbook_name, subjectbook_publisher\r\n" + 
                    "    FROM subjectbook\r\n" + 
                    "    ORDER BY subjectbook_id";
            
            pstmt = conn.prepareStatement(sql);
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                String subjectbook_id = rs.getString("subjectbook_id");
                String subjectbook_name = rs.getString("subjectbook_name");
                String subjectbook_publisher = rs.getString("subjectbook_publisher");
                
                Subjectbook sb = new Subjectbook(subjectbook_id, subjectbook_name, subjectbook_publisher);
                list.add(sb);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return list;                
    }
    
    // 교재 출력 메소드
    // 교재번호 / 교재명 / 교재출판사 / 삭제가능여부
    public List<Subjectbook> list2() {
        List<Subjectbook> list = new ArrayList<Subjectbook>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            
            String sql = "SELECT subjectbook_id, subjectbook_name, subjectbook_publisher\r\n" + 
                    "        ,(SELECT COUNT(*) FROM open_subject\r\n" + 
                    "            WHERE subjectbook_id=s.subjectbook_id) count_\r\n" + 
                    "            FROM subjectbook s\r\n" + 
                    "            ORDER BY subjectbook_id";
            
            pstmt = conn.prepareStatement(sql);
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                String subjectbook_id = rs.getString("subjectbook_id");
                String subjectbook_name = rs.getString("subjectbook_name");
                String subjectbook_publisher = rs.getString("subjectbook_publisher");
                int count_ = rs.getInt("count_");
                
                Subjectbook sb = new Subjectbook(subjectbook_id, subjectbook_name, subjectbook_publisher, count_);
                list.add(sb);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return list;                
    }
    
    // 교재 검색 메소드
    public List<Subjectbook> search(String key, String value) {
        List<Subjectbook> list = new ArrayList<Subjectbook>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
 
            if(key.equals("subjectbook_id")) {
                String sql = "SELECT subjectbook_id, subjectbook_name, subjectbook_publisher \r\n" + 
                        "    FROM subjectbook\r\n" + 
                        "    WHERE subjectbook_id = UPPER(?)\r\n" + 
                        "    ORDER BY subjectbook_id";
                
                pstmt = conn.prepareStatement(sql);
                
                pstmt.setString(1, value);
                
                ResultSet rs = pstmt.executeQuery();
                
                while(rs.next()) {
                    String subjectbook_id = rs.getString("subjectbook_id");
                    String subjectbook_name = rs.getString("subjectbook_name");
                    String subjectbook_publisher = rs.getString("subjectbook_publisher");
                    
                    Subjectbook sb = new Subjectbook(subjectbook_id, subjectbook_name, subjectbook_publisher);
                    list.add(sb);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
        
    // 교재 삭제 메소드
    public int remove(Subjectbook sb) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            String sql = "DELETE FROM subjectbook WHERE UPPER(subjectbook_id)=UPPER(?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, sb.getSubjectbook_id());
            
            result = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }
}
 
