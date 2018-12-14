package com.scoremanagement.persistance;
 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import com.scoremanagement.connection.OracleConnection;
import com.scoremanagement.domain.OpenCourse;
 
public class OpenCourseDAO {
 
    // 개설 과정 추가 메소드
    public int insert(OpenCourse oc) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            String sql = "INSERT INTO open_course (open_course_id, class_room_id, course_id\r\n" + 
                    "    , open_course_start_date, open_course_end_date)\r\n" + 
                    "     VALUES ((SELECT CONCAT('OC',\r\n" + 
                    "         LPAD(NVL(SUBSTR(MAX(open_course_id),3), 0) + 1, 4, 0)) AS newId FROM open_course)\r\n" + 
                    "         , UPPER(?), UPPER(?), ?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, oc.getClass_room_id());
            pstmt.setString(2, oc.getCourse_id());
            pstmt.setDate(3, oc.getOpen_course_start_date());
            pstmt.setDate(4, oc.getOpen_course_end_date());
            
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
    
    // 개설 과정 출력 리스트 메소드(1)
    // 개설 과정 번호 / 과정명 / 개설 과정 기간 / 강의실명
    public List<OpenCourse> list1(String student_id) {
        // open_course_list1_VW
        /*
        CREATE OR REPLACE VIEW open_course_list1_VW
        AS
        SELECT oc.open_course_id, c.course_name, oc.open_course_start_date, oc.open_course_end_date,
                st.student_id, cr.class_room_name
            FROM open_course oc, course c, class_room cr, student_history sh, student st
            WHERE oc.course_id = c.course_id
                AND oc.class_room_id = cr.class_room_id
                AND oc.open_course_id = sh.open_course_id
                AND sh.student_id = st.student_id;
        */
        List<OpenCourse> list = new ArrayList<OpenCourse>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
 
        try {
            conn = OracleConnection.connect();
            
            String sql = "SELECT open_course_id, course_name, open_course_start_date, open_course_end_date, class_room_name\r\n" + 
                    "    FROM open_course_list1_VW\r\n" + 
                    "    WHERE UPPER(student_id) = UPPER(?)\r\n" + 
                    "    ORDER BY open_course_id";
 
            pstmt = conn.prepareStatement(sql);
 
            pstmt.setString(1, student_id);
            
            ResultSet rs = pstmt.executeQuery();
 
            while (rs.next()) {
                String open_course_id = rs.getString("open_course_id");
                String course_name = rs.getString("course_name");
                Date open_course_start_date = rs.getDate("open_course_start_date");
                Date open_course_end_date = rs.getDate("open_course_end_date");
                String class_room_name = rs.getString("class_room_name");
                OpenCourse oc = new OpenCourse(open_course_id, null, course_name, open_course_start_date,
                        open_course_end_date, null, class_room_name);
                list.add(oc);
            }
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
        
        return list;
    }
    
    // 개설 과정 출력 리스트 메소드(2)
    // 개설 과정 번호 / 과정명 / 개설 과정 기간 / 강의실명 / 개설 과목 등록 갯수 / 수강생 등록 인원
    public List<OpenCourse> list2() {
        // open_course_list2_VW
        /*
        CREATE OR REPLACE VIEW open_course_list2_VW
        AS
        SELECT open_course_id, course_name, open_course_start_date ,open_course_end_date, class_room_name, 
                (SELECT COUNT(*) 
                    FROM open_subject
                    WHERE open_course_id=oc.open_course_id) os_count,
                (SELECT COUNT(*) 
                    FROM student_history
                    WHERE open_course_id=oc.open_course_id) s_count
            FROM open_course oc, course c, class_room cr
            WHERE oc.course_id = c.course_id
                AND oc.class_room_id=cr.class_room_id;
        */
        
        List<OpenCourse> list = new ArrayList<OpenCourse>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            String sql = "SELECT open_course_id, course_name, open_course_start_date\r\n" + 
                    "            ,open_course_end_date, class_room_name\r\n" + 
                    "            ,os_count, s_count\r\n" + 
                    "            FROM open_course_list2_VW\r\n" + 
                    "        ORDER BY open_course_id";
            
            pstmt = conn.prepareStatement(sql);
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                String open_course_id = rs.getString("open_course_id");
                String course_name = rs.getString("course_name");
                Date open_course_start_date = rs.getDate("open_course_start_date");
                Date open_course_end_date = rs.getDate("open_course_end_date");
                String class_room_name = rs.getString("class_room_name");
                int os_count = rs.getInt("os_count");
                int s_count = rs.getInt("s_count");            
                OpenCourse oc = new OpenCourse(open_course_id, course_name, 
                        open_course_start_date, open_course_end_date, class_room_name, os_count, s_count);
                list.add(oc);
            }
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
        
        return list;
    }
    
    // 개설 과정 출력 리스트 메소드(3)
    // 개설 과정 번호 / 과정명 / 개설 과정 기간 / 강의실명 / 삭제 가능 여부
    public List<OpenCourse> list3() {
        List<OpenCourse> list = new ArrayList<OpenCourse>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            String sql = "SELECT v1.open_course_id, course_name, open_course_start_date\r\n" + 
                    "                ,open_course_end_date, class_room_name\r\n" + 
                    "                ,os_count, s_count,(SELECT COUNT(*) FROM student_history sh\r\n" + 
                    "                    WHERE sh.open_course_id=v1.open_course_id) count_\r\n" + 
                    "                FROM open_course_list2_VW v1\r\n" + 
                    "    ORDER BY open_course_id";
            
            pstmt = conn.prepareStatement(sql);
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                String open_course_id = rs.getString("open_course_id");
                String course_name = rs.getString("course_name");
                Date open_course_start_date = rs.getDate("open_course_start_date");
                Date open_course_end_date = rs.getDate("open_course_end_date");
                String class_room_name = rs.getString("class_room_name");
                int count_ = rs.getInt("count_");
                OpenCourse oc = new OpenCourse(open_course_id, course_name, open_course_start_date,
                        open_course_end_date, class_room_name, count_);
                list.add(oc);
            }
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
        
        return list;
    }
    
    // 개설 과정 출력 리스트 메소드(4)
    // 개설 과정 번호 / 과정명 / 개설 과정 기간 / 강의실명 / 개설 과목명
    public List<OpenCourse> list4() {
        // open_course_list4_VW1
        /*
        CREATE OR REPLACE VIEW open_course_list4_VW1
        AS    
        SELECT cr.class_room_id, cr.class_room_name, cr.max_number, oc.open_course_id,
               oc.open_course_start_date, oc.open_course_end_date, c.course_id, c.course_name
            FROM open_course oc, course c, class_room cr
            WHERE cr.class_room_id = oc.class_room_id
                AND c.course_id = oc.course_id
                AND sysdate < open_course_start_date;    
        */
        
        // open_course_list4_VW2
        /*
        CREATE OR REPLACE VIEW open_course_list4_VW2
        AS    
        SELECT  v1.open_course_id, v1.class_room_name, v1.course_name, 
                v1.open_course_start_date, v1.open_course_end_date,
               (SELECT (LISTAGG(s.subject_name, ', ') WITHIN GROUP(ORDER BY s.subject_id))
                   FROM open_subject t, subject s
                   WHERE s.subject_id = t.subject_id 
                       AND v1.open_course_id= t.open_course_id
               ) subjectlist
            FROM open_course_list4_VW1 v1;
        */
        
        List<OpenCourse> list = new ArrayList<OpenCourse>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
 
        try {
            conn = OracleConnection.connect();
            
            String sql = "SELECT open_course_id, course_name,\r\n" + 
                    "open_course_start_date, open_course_end_date, class_room_name,\r\n" + 
                    "subjectlist\r\n" + 
                    "    FROM open_course_list4_VW2\r\n" + 
                    "    ORDER BY open_course_id";
            
            pstmt = conn.prepareStatement(sql);
 
            ResultSet rs = pstmt.executeQuery();
 
            while (rs.next()) {
                String open_course_id = rs.getString("open_course_id");
                String course_name = rs.getString("course_name");
                Date open_course_start_date = rs.getDate("open_course_start_date");
                Date open_course_end_date = rs.getDate("open_course_end_date");
                String class_room_name = rs.getString("class_room_name");
                String subjectlist = rs.getString("subjectlist");
 
                OpenCourse oc = new OpenCourse(open_course_id, course_name, open_course_start_date,
                        open_course_end_date, class_room_name, subjectlist);
                list.add(oc);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return list;
    }
    
    // 개설 과정 출력 리스트 메소드(5)
    // 개설 과정 번호 / 과정명 / 개설 과정 기간 / 강의실명 / 수료여부 / 날짜
    public List<OpenCourse> list5(String key, OpenCourse value) {
        // open_course_list5_VW1
        /*
        CREATE OR REPLACE VIEW open_course_list5_VW1
        AS
        SELECT oc.open_course_id, course_name, open_course_start_date, open_course_end_date, 
               class_room_name, sh.student_id, 
               (SELECT dropout_date 
                    FROM process_complete pc 
                    WHERE pc.open_course_id = oc.open_course_id 
                        AND pc.student_id = sh.student_id) drop_date
            FROM open_course oc, course c ,class_room cr, student_history sh
            WHERE c.course_id = oc.course_id
                AND cr.class_room_id = oc.class_room_id
                AND oc.open_course_id = sh.open_course_id; 
        */
        
        // open_course_list5_VW2
        /*
        CREATE OR REPLACE VIEW open_course_list5_VW2
        AS
        SELECT open_course_id, course_name, open_course_start_date, open_course_end_date, class_room_name,
                CASE
                    WHEN SYSDATE < open_course_end_date AND drop_date is null THEN '수료예정'
                    WHEN drop_date IS NOT NULL THEN '중도탈락'
                    else '수료'
                END completion, drop_date, student_id
            FROM open_course_list5_VW1;
        */
        
        List<OpenCourse> list = new ArrayList<OpenCourse>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            
            if(key.equals("student_id")) {
                String sql = "SELECT open_course_id, course_name, open_course_start_date, open_course_end_date, class_room_name, completion, drop_date\r\n" + 
                        "    FROM open_course_list5_VW2\r\n" + 
                        "    WHERE UPPER(student_id) = UPPER(?)";
                
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, value.getStudent_id());
            }
            
            else if (key.equals("student_name")){
                String sql = "SELECT open_course_id, course_name, open_course_start_date, open_course_end_date, class_room_name, completion, drop_date\r\n" + 
                        "    FROM open_course_list5_VW2\r\n" + 
                        "    WHERE INSTR(student_name, ?) > 0";
                
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, value.getStudent_name());
            }
            
            else if (key.equals("open_course_idANDstudent_id")) {
                String sql = "SELECT open_course_id, course_name, open_course_start_date, open_course_end_date, class_room_name, completion, drop_date\r\n" + 
                        "    FROM open_course_list5_VW2\r\n" + 
                        "    WHERE student_id = UPPER(?)\r\n" +
                        "    AND open_course_id = UPPER(?)";
                
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, value.getStudent_id());
                pstmt.setString(2, value.getOpen_course_id());
            }
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                String open_course_id = rs.getString("open_course_id");
                String course_name = rs.getString("course_name");
                Date open_course_start_date = rs.getDate("open_course_start_date");
                Date open_course_end_date = rs.getDate("open_course_end_date");
                String class_room_name = rs.getString("class_room_name");
                String completion_status = rs.getString("completion");
                Date dropout_date = rs.getDate("drop_date");
                OpenCourse oc = new OpenCourse(open_course_id, course_name, open_course_start_date,
                        open_course_end_date, class_room_name, completion_status, dropout_date);
                list.add(oc);
            }
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
        
        return list;
    }
    
    // 개설 과정 검색 메소드
    // 1. 개설 과정 번호 2. 과정명
    public List<OpenCourse> search(String key, String value) {
        // open_course_search_VW
        /*
        CREATE OR REPLACE VIEW open_course_search_VW
        AS
        SELECT oc.open_course_id, c.course_name, oc.open_course_start_date, oc.open_course_end_date, 
                cr.class_room_name
            FROM open_course oc, course c, class_room cr
            WHERE oc.course_id = c.course_id
                AND oc.class_room_id = cr.class_room_id;
        */
        
        List<OpenCourse> list = new ArrayList<OpenCourse>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        
        try {
            conn = OracleConnection.connect();
            
            if(key.equals("open_course_id")) {
                String sql = "SELECT open_course_id, course_name, open_course_start_date\r\n" + 
                        "    , open_course_end_date, class_room_name\r\n" + 
                        "    FROM open_course_search_VW\r\n" + 
                        "    WHERE UPPER(open_course_id) = UPPER(?)";
                
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, value);
            }
            
            else if(key.equals("course_name")) {
                String sql = "SELECT open_course_id, course_name, open_course_start_date, open_course_end_date, class_room_name\r\n" + 
                        "    FROM open_course_search_VW\r\n" + 
                        "    WHERE INSTR(course_name, ?) > 0";
                
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, value);
            }
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                String open_course_id = rs.getString("open_course_id");
                String course_name = rs.getString("course_name");
                Date open_course_start_date = rs.getDate("open_course_start_date");
                Date open_course_end_date = rs.getDate("open_course_end_date");
                String class_room_name = rs.getString("class_room_name");
                OpenCourse oc = new OpenCourse(open_course_id, null, course_name, open_course_start_date,
                        open_course_end_date, null, class_room_name);
                list.add(oc);
            }
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
 
        return list;
    }
    
    // 개설 과정 삭제 메소드
    public int remove(OpenCourse oc) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            String sql = "DELETE FROM open_course WHERE UPPER(open_course_id) = UPPER(?)";
            
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, oc.getOpen_course_id());
            
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