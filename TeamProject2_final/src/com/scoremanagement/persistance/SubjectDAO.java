package com.scoremanagement.persistance;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import com.scoremanagement.connection.OracleConnection;
import com.scoremanagement.domain.Subject;
 
public class SubjectDAO {
    
    // 과목 입력 메소드
    public int insert(Subject s) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            String sql = "INSERT INTO subject (subject_id, subject_name) \r\n" + 
                    "    VALUES ((SELECT CONCAT('SUB',\r\n" + 
                    "         LPAD(NVL(SUBSTR(MAX(subject_id), 4), 0) + 1, 3, 0)) AS newId FROM subject)\r\n" + 
                    "         , ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s.getSubject_name());
            
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
    
    // 과목 출력 리스트 메소드(1)
    // 과목번호 / 과목이름
    public List<Subject> list1() {
        List<Subject> list = new ArrayList<Subject>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            
            String sql = "SELECT subject_id, subject_name\r\n" + 
                    "    FROM subject\r\n" + 
                    "    ORDER BY subject_id";
            
            pstmt = conn.prepareStatement(sql);
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                String subject_id = rs.getString("subject_id");
                String subject_name = rs.getString("subject_name");
                
                Subject s = new Subject(subject_id, subject_name);
                list.add(s);
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
    
    // 과목 출력 리스트 메소드(2)
    // 과목번호 / 과목이름 / 삭제가능여부
    public List<Subject> list2() {
        List<Subject> list = new ArrayList<Subject>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            
            String sql = "SELECT subject_id, subject_name\r\n" + 
                    "        ,(SELECT COUNT(*) FROM open_subject\r\n" + 
                    "            WHERE subject_id=s.subject_id) count_\r\n" + 
                    "            FROM subject s\r\n" + 
                    "            ORDER BY subject_id";
            
            pstmt = conn.prepareStatement(sql);
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                String subject_id = rs.getString("subject_id");
                String subject_name = rs.getString("subject_name");
                int count_ = rs.getInt("count_");
                
                Subject s = new Subject(subject_id, subject_name, count_);
                list.add(s);
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
    
    // 과목 검색 메소드
    // 1. 과목 번호
    public List<Subject> search(String key, String value) {
        List<Subject> list = new ArrayList<Subject>();
 
        Connection conn = null;
        PreparedStatement pstmt = null;
 
        try {
            conn = OracleConnection.connect();
 
            if (key.equals("subject_id")) {
                String sql = "SELECT subject_id, subject_name \r\n" + 
                        "    FROM subject\r\n" + 
                        "    WHERE subject_id = UPPER(?)\r\n" + 
                        "    ORDER BY subject_id";
 
                pstmt = conn.prepareStatement(sql);
 
                pstmt.setString(1, value);
 
                ResultSet rs = pstmt.executeQuery();
 
                while (rs.next()) {
                    String subject_id = rs.getString("subject_id");
                    String subject_name = rs.getString("subject_name");
 
                    Subject s = new Subject(subject_id, subject_name);
                    list.add(s);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return list;
    }
        
    // 과목 삭제 메소드
    public int remove(Subject s) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            String sql = "DELETE FROM subject WHERE UPPER(subject_id)=UPPER(?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, s.getSubject_id());
            
            result = pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }
}