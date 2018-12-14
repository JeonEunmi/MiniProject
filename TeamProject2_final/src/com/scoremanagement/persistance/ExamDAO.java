package com.scoremanagement.persistance;
 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import com.scoremanagement.connection.OracleConnection;
import com.scoremanagement.domain.Exam;
 
public class ExamDAO {
 
    /*
    CREATE OR REPLACE VIEW exam_print1_view1
    AS
    SELECT os.instructor_id, os.open_course_id, os.open_subject_id, os.subject_end_date, os.subject_id,
    os.subject_start_date, os.subjectbook_id, e.exam_id, e.exam_date, e.exam_file
    FROM open_subject os, exam e
    WHERE os.open_subject_id = e.open_subject_id;
    
    CREATE OR REPLACE VIEW exam_print2_view2
    AS    
    SELECT v1.exam_date , v1.exam_file , v1.instructor_id , v1.open_course_id , v1.open_subject_id , 
    v1.subject_end_date , v1.subject_id , v1.subject_start_date , v1.subjectbook_id, sp.attendance_point ,
    sp.exam_id , sp.skill_point , sp.subject_point_id , sp.write_point 
    FROM  exam_print1_view1 v1, subject_point sp
    WHERE v1.exam_id = sp.exam_id(+);
     */
    // 시험 출력 리스트 메소드(1)
    // 시험번호 / 출결 배점 / 필기 배점 / 실기 배점 / 시험 날짜 / 시험문제 파일
    public List<Exam> list1(String key, Exam value) {
        // exam_list1_VW1
        /*
        CREATE OR REPLACE VIEW exam_list1_VW1
        AS
        SELECT os.instructor_id, os.open_course_id, os.open_subject_id, os.subject_end_date, 
                os.subject_id, os.subject_start_date, os.subjectbook_id, e.exam_id, e.exam_date, e.exam_file
            FROM open_subject os, exam e
            WHERE os.open_subject_id = e.open_subject_id;
        */
        
        // exam_list1_VW2
        /*
        CREATE OR REPLACE VIEW exam_list1_VW2
        AS    
        SELECT v1.exam_date, v1.exam_file, v1.instructor_id, v1.open_course_id, v1.open_subject_id, 
                v1.subject_end_date , v1.subject_id , v1.subject_start_date , v1.subjectbook_id, 
                sp.attendance_point, sp.exam_id, sp.skill_point, sp.subject_point_id, sp.write_point 
            FROM  exam_list1_VW1 v1, subject_point sp
            WHERE v1.exam_id = sp.exam_id(+);
        */
        
        List<Exam> list = new ArrayList<Exam>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            
            if(key.equals("open_subject_id")) {
                String sql = "SELECT exam_id, attendance_point, write_point, skill_point, exam_date, exam_file\r\n" + 
                        "    FROM exam_list1_VW2\r\n" + 
                        "    WHERE UPPER(open_subject_id) = UPPER(?)\r\n" + 
                        "    AND UPPER(instructor_id) = UPPER(?)";
                
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, value.getOpen_subject_id());
                pstmt.setString(2, value.getInstructor_id());
            }
 
            else if(key.equals("exam")) {
                String sql = "SELECT exam_id, attendance_point, write_point, skill_point, exam_date, exam_file\r\n" + 
                        "    FROM exam_list1_VW2\r\n" +
                        "     WHERE open_subject_id = UPPER(?)";
                
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, value.getOpen_subject_id());
            }
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                String exam_id = rs.getString("exam_id");
                int attendance_point = rs.getInt("attendance_point");
                int write_point = rs.getInt("write_point");
                int skill_point = rs.getInt("skill_point");
                Date exam_date = rs.getDate("exam_date");
                String exam_file = rs.getString("exam_file");
                
                Exam e = new Exam(exam_id, attendance_point, write_point, skill_point,
                        0, 0, 0, exam_date, exam_file);
 
                list.add(e);
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
    
    // 시험 출력 리스트 메소드(2)
    // 시험번호 / 출결점수 / 출결배점 / 필기점수 / 필기배점 / 실기점수 / 실기배점 / 시험날짜 / 시험파일
    public List<Exam> list2(String key, Exam value) {
        // exam_list2_VW
        /*
        CREATE OR REPLACE VIEW exam_list2_VW
        AS
        SELECT os.open_subject_id, s.subject_name, os.subject_start_date, os.subject_end_date,
                i.instructor_name, p.attendance_point, p.write_point, p.skill_point, 
                ss.attendance_score, ss.write_score, ss.skill_score, 
                e.exam_id, e.exam_date, e.exam_file, st.student_id
            FROM subject s, open_subject os, instructor i, subject_point p, exam e, 
                student_score ss , student st
            WHERE os.subject_id = s.subject_id
                AND i.instructor_id = os.instructor_id
                AND e.exam_id = p.exam_id
                AND e.open_subject_id = os.open_subject_id
                AND ss.exam_id = e.exam_id
                AND st.student_id = ss.student_id;
        */
        List<Exam> list = new ArrayList<Exam>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            
            if(key.equals("student_id")) {
                String sql = "SELECT exam_id, attendance_point, write_point, skill_point," +
                        " attendance_score, write_score, skill_score, student_id, exam_date, exam_file\r\n" + 
                        "    FROM exam_list2_VW\r\n" + 
                        "    WHERE UPPER(student_id) = UPPER(?)";
                
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, value.getStudent_id());
            }
            
            else if(key.equals("open_subject_idANDstudent_id")) {
                String sql = "SELECT exam_id, attendance_point, write_point, skill_point," +
                        " attendance_score, write_score, skill_score, student_id, exam_date, exam_file\r\n" + 
                        "    FROM exam_list2_VW\r\n" + 
                        "    WHERE open_subject_id = UPPER(?)\r\n" + 
                        "    AND student_id = UPPER(?)";
 
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, value.getOpen_subject_id());
                pstmt.setString(2, value.getStudent_id());
            }
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                String exam_id = rs.getString("exam_id");
                int attendance_point = rs.getInt("attendance_point");
                int write_point = rs.getInt("write_point");
                int skill_point = rs.getInt("skill_point");
                int attendance_score = rs.getInt("attendance_score");
                int write_score = rs.getInt("write_score");
                int skill_score = rs.getInt("skill_score");
                Date exam_date = rs.getDate("exam_date");
                String exam_file = rs.getString("exam_file");
 
                Exam e = new Exam(exam_id, attendance_point, write_point, skill_point,
                        attendance_score, write_score, skill_score, exam_date, exam_file);
                list.add(e);
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
 
    // 시험 출력 리스트 메소드(3)
    // 시험번호 / 출결 배점 / 필기 배점 / 실기 배점 / 시험 날짜 / 시험문제 파일 / 성적 등록 인원수 / 성적 등록 여부
    public List<Exam> list3(String key, Exam value) {
        // exam_list3_VW1
        /*
        CREATE OR REPLACE VIEW exam_list3_VW1
        AS
        SELECT e.exam_id, attendance_point, write_point, skill_point, exam_date, exam_file, 
                os.open_course_id, instructor_id, os.open_subject_id
            FROM exam e, subject_point sp, open_subject os, open_course oc
            WHERE e.exam_id = sp.exam_id
                AND e.open_subject_id = os.open_subject_id
                AND os.open_course_id = oc.open_course_id;
        */
        
        // exam_list3_VW2
        /*
        CREATE OR REPLACE VIEW exam_list3_VW2
        AS
        SELECT v1.exam_id, attendance_point, write_point, skill_point, exam_date, exam_file, 
                instructor_id, open_subject_id,
                (SELECT COUNT(*) 
                    FROM student_score ss 
                    WHERE v1.exam_id = ss.exam_id) count_,
                (SELECT COUNT(*) 
                    FROM student_history sh 
                    WHERE v1.open_course_id = sh.open_course_id) score_s
            FROM exam_list3_VW1 v1
            ORDER BY v1.exam_id;
        */
        
        // exam_list3_VW3
        /*
        CREATE OR REPLACE VIEW exam_list3_VW3
        AS
        SELECT exam_id, attendance_point, write_point, skill_point, exam_date, exam_file, 
                count_, instructor_id, open_subject_id, 
                CASE count_
                    WHEN score_s THEN '성적 입력 완료'
                    else '성적 입력 전'
                END score_state
            FROM exam_list3_VW2 v2;
        */
        
        List<Exam> list = new ArrayList<Exam>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            
            if(key.equals("open_subject_idANDinstructor_id")) {
                String sql = "SELECT exam_id, attendance_point, write_point, skill_point, exam_date, exam_file, count_, score_state\r\n" + 
                        "    FROM exam_list3_VW3\r\n" + 
                        "    WHERE UPPER(instructor_id) = UPPER(?)\r\n" + 
                        "    AND UPPER(open_subject_id) = UPPER(?)";
                
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, value.getInstructor_id());
                pstmt.setString(2, value.getOpen_subject_id());
            }
            
            else if(key.equals("open_subject_idANDinstructor_idANDexam_id")) {
                String sql = "SELECT exam_id, attendance_point, write_point, skill_point, exam_date, exam_file, count_, score_state\r\n" + 
                        "    FROM exam_list3_VW3\r\n" + 
                        "    WHERE UPPER(instructor_id) = UPPER(?)\r\n" + 
                        "    AND UPPER(open_subject_id) = UPPER(?)\r\n" + 
                        "    AND UPPER(exam_id) = UPPER(?)";
                
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, value.getInstructor_id());
                pstmt.setString(2, value.getOpen_subject_id());
                pstmt.setString(3, value.getExam_id());
            }
 
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                String exam_id1 = rs.getString("exam_id");
                int attendance_point = rs.getInt("attendance_point");
                int write_point = rs.getInt("write_point");
                int skill_point = rs.getInt("skill_point");
                Date exam_date = rs.getDate("exam_date");
                String exam_file = rs.getString("exam_file");
                int class_count = rs.getInt("count_");
                String score_status = rs.getString("score_state");
                
                Exam e = new Exam(exam_id1, attendance_point, write_point, skill_point, exam_date,
                        exam_file, class_count, score_status);
                list.add(e);
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
    
    // 시험 출력 리스트 메소드(4)
    // 수강생 번호 / 수강생 이름 / 수강생 전화번호 / 수강생 등록일 / 수료여부 / 수료날짜 / 출결 점수 / 필기 점수 / 실기 점수 / 총점
    public List<Exam> list4(String key, Exam value) {
        // exam_list4_VW1
        /*
        CREATE OR REPLACE VIEW exam_list4_VW1
        AS
        SELECT s.student_id, student_name, student_phone, student_regDate, 
                (SELECT dropout_date 
                    FROM process_complete pc 
                    WHERE pc.open_course_id = sh.open_course_id 
                        AND pc.student_id = sh.student_id) drop_date, 
                open_course_start_date, open_course_end_date, 
                attendance_score, write_score, skill_score, open_subject_id, instructor_id, exam_id
            FROM student_score ss, student s, student_history sh, open_course oc, open_subject os
            WHERE ss.student_id = s.student_id
                AND s.student_id = sh.student_id
                AND oc.open_course_id = sh.open_course_id
                AND oc.open_course_id = os.open_course_id;
        */
        
        // exam_list4_VW2
        /*
        CREATE OR REPLACE VIEW exam_list4_VW2
        AS
        SELECT student_id, student_name, student_phone, student_regDate, open_subject_id, drop_date,
                open_course_end_date, instructor_id, exam_id, 
                CASE
                    WHEN SYSDATE < open_course_start_date AND drop_date is null THEN '수료예정'
                    WHEN SYSDATE > open_course_end_date AND drop_date is null THEN '수료완료'
                    WHEN drop_date IS NOT NULL THEN '중도탈락'
                    else '진행중'
                END completion, 
                attendance_score, write_score, skill_score, 
                attendance_score+write_score+skill_score AS total
            FROM exam_list4_VW1;
        */
        
        // exam_list4_VW3
        /*
        CREATE OR REPLACE VIEW exam_list4_VW3
        AS
        SELECT student_id, student_name, student_phone, student_regDate, completion, 
                CASE completion 
                    WHEN '중도탈락' THEN drop_date 
                    ELSE open_course_end_date 
                END completion_date,
                attendance_score, write_score, skill_score, total, 
                instructor_id, open_subject_id, exam_id
            FROM exam_list4_VW2;
        */
        
        List<Exam> list = new ArrayList<Exam>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            
            if(key.equals("open_subject_idANDinstructor_idANDexam_id")) {
                String sql = "SELECT student_id, student_name, student_phone, student_regDate, completion, completion_date\r\n" + 
                        "    ,attendance_score, write_score, skill_score, total\r\n" + 
                        "    FROM exam_list4_VW3\r\n" + 
                        "    WHERE UPPER(open_subject_id) = UPPER(?)\r\n" + 
                        "    AND UPPER(instructor_id) = UPPER(?)\r\n" + 
                        "    AND UPPER(exam_id) = UPPER(?)";
                
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, value.getOpen_subject_id());
                pstmt.setString(2, value.getInstructor_id());
                pstmt.setString(3, value.getExam_id());                
            }
 
            else if(key.equals("open_subject_idANDexam_id")) {
                String sql = "SELECT student_id, student_name, student_phone, student_regDate, completion, completion_date \r\n" + 
                        "    ,attendance_score, write_score, skill_score, total \r\n" + 
                        "    FROM exam_list4_VW3 \r\n" + 
                        "    WHERE UPPER(open_subject_id) = UPPER(?)\r\n" + 
                        "    AND UPPER(exam_id) = UPPER(?)";
                
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, value.getOpen_subject_id());
                pstmt.setString(2, value.getExam_id());        
            }
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                String student_id = rs.getString("student_id");
                String student_name = rs.getString("student_name");
                String student_phone = rs.getString("student_phone");
                Date student_regDate = rs.getDate("student_regDate");
                int attendance_score = rs.getInt("attendance_score");
                int write_score = rs.getInt("write_score");
                int skill_score = rs.getInt("skill_score");
                int total_score = rs.getInt("total");                
                String completion_status = rs.getString("completion");
                Date completion_date = rs.getDate("completion_date");
 
                Exam e = new Exam(student_id, student_name, student_phone, student_regDate, 
                        completion_status, completion_date, 
                        attendance_score, write_score, skill_score, total_score);
                list.add(e);
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
    
    // 시험 출력 리스트 메소드(5)
    // 시험번호 / 과목명 / 과목개설기간 / 강사명 / 출결점수 / 출결배점 / 필기점수 / 필기배점 / 실기점수 / 실기배점 / 시험날짜 / 시험파일
    public List<Exam> list5(String key, Exam value) {
        // exam_list5_VW1
        /*
        CREATE OR REPLACE VIEW exam_list5_VW
        AS
        SELECT os.open_subject_id, s.subject_name, os.subject_start_date, os.subject_end_date,
                i.instructor_name, p.attendance_point, p.write_point, p.skill_point, 
                ss.attendance_score, ss.write_score, ss.skill_score, 
                e.exam_id, e.exam_date, e.exam_file, st.student_id
            FROM subject s, open_subject os, instructor i, subject_point p, exam e, 
                student_score ss , student st
            WHERE os.subject_id = s.subject_id
                AND i.instructor_id = os.instructor_id
                AND e.exam_id = p.exam_id
                AND e.open_subject_id = os.open_subject_id
                AND ss.exam_id = e.exam_id
                AND st.student_id = ss.student_id;
        */
        
        List<Exam> list = new ArrayList<Exam>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            
            if(key.equals("student_id")) {
                String sql = "SELECT exam_id, subject_name, subject_start_date, subject_end_date,\r\n" + 
                        " instructor_name, attendance_point, write_point, skill_point," +
                        " attendance_score, write_score, skill_score, student_id, exam_date, exam_file\r\n" + 
                        "    FROM exam_list5_VW\r\n" + 
                        "    WHERE UPPER(student_id) = UPPER(?)";
                
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, value.getStudent_id());
            }
 
            else if(key.equals("student_name")) {
                String sql = "SELECT exam_id, subject_name, subject_start_date, subject_end_date,\r\n" + 
                        " instructor_name, attendance_point, write_point, skill_point," +
                        " attendance_score, write_score, skill_score, student_id, exam_date, exam_file\r\n" + 
                        "    FROM exam_list5_VW\r\n" + 
                        "    WHERE INSTR(student_name, ?) > 0";
                
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, value.getStudent_name());    
            }
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
                String exam_id = rs.getString("exam_id");
                String subject_name = rs.getString("subject_name");
                Date subject_start_date = rs.getDate("subject_start_date");
                Date subject_end_date = rs.getDate("subject_end_date");
                String instructor_name = rs.getString("instructor_name");
                int attendance_point = rs.getInt("attendance_point");
                int write_point = rs.getInt("write_point");
                int skill_point = rs.getInt("skill_point");
                int attendance_score = rs.getInt("attendance_score");
                int write_score = rs.getInt("write_score");
                int skill_score = rs.getInt("skill_score");        
                Date exam_date = rs.getDate("exam_date");
                String exam_file = rs.getString("exam_file");
 
                Exam e = new Exam(exam_id, subject_name, subject_start_date, subject_end_date, 
                        instructor_name, attendance_point, write_point, skill_point,
                        attendance_score, write_score, skill_score, exam_date, exam_file);
                list.add(e);
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
        
    // 시험 배점 입력 메소드
    public int insertPoint(Exam e) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        
        try {
            String exam_id = null;
            conn = OracleConnection.connect();
            String sql1 = "SELECT (CONCAT('EXAM', LPAD(NVL(SUBSTR(MAX(exam_id), 5), 0) + 1, 4, 0)))AS newID\r\n" + 
                            "FROM exam";
            
            pstmt1 = conn.prepareStatement(sql1);
            ResultSet rs = pstmt1.executeQuery();
            
            while(rs.next()) {
                exam_id = rs.getString("newID");
            }
            String sql2 = "Insert into EXAM (EXAM_ID, EXAM_DATE, EXAM_FILE, OPEN_SUBJECT_ID)\r\n" + 
                    "values (?, ?, ?, ?)";
            
            pstmt2 = conn.prepareStatement(sql2);
            
            pstmt2.setString(1, exam_id);
            pstmt2.setDate(2, e.getExam_date());
            pstmt2.setString(3, e.getExam_file());
            pstmt2.setString(4, e.getOpen_subject_id());
            
            int result1 = pstmt2.executeUpdate();
            
            String sql3 = "Insert into SUBJECT_POINT \r\n" + 
                    "(SUBJECT_POINT_ID,EXAM_ID,ATTENDANCE_POINT,WRITE_POINT,SKILL_POINT) \r\n" + 
                    "values ((SELECT(CONCAT('SP', LPAD(NVL(SUBSTR(MAX(SUBJECT_POINT_ID), 3), 0) + 1, 2, 0)))AS newID FROM subject_point),\r\n" + 
                    "UPPER(?), ?, ?, ?)";            
            
            pstmt3 = conn.prepareStatement(sql3);            
            
            pstmt3.setString(1, exam_id);
            pstmt3.setInt(2, e.getAttendance_point());
            pstmt3.setInt(3, e.getWrite_point());
            pstmt3.setInt(4, e.getSkill_point());
            
            int result2 = pstmt3.executeUpdate();
            
            if((result1 != 0) && (result2 != 0)) {
                result = 1;
            }
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (pstmt1 != null)
                    pstmt1.close();
                if (pstmt2 != null)
                    pstmt2.close();
                if (pstmt3 != null)
                    pstmt3.close();
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
    
    // 시험 배점 삭제 메소드
    public int removePoint(Exam e) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        
        try {
            conn = OracleConnection.connect();
            String sql1 = "DELETE FROM subject_point\r\n" + 
                    "    WHERE exam_id = (SELECT sp.exam_id\r\n" + 
                    "                        FROM subject_point sp, exam e, open_subject os, instructor i \r\n" + 
                    "                        WHERE sp.exam_id = e.exam_id\r\n" + 
                    "                            AND e.open_subject_id = os.open_subject_id\r\n" + 
                    "                            AND os.instructor_id = i.instructor_id\r\n" + 
                    "                            AND i.instructor_id = UPPER(?)\r\n" + 
                    "                            AND e.exam_id = UPPER(?))";
            
            pstmt1 = conn.prepareStatement(sql1);
            
            pstmt1.setString(1, e.getInstructor_id());
            pstmt1.setString(2, e.getExam_id());
 
            int result1 = pstmt1.executeUpdate();
            
            String sql2 = "DELETE FROM EXAM\r\n" + 
                    "    WHERE exam_id = (SELECT exam_id\r\n" + 
                    "                        FROM exam e, open_subject os, instructor i \r\n" + 
                    "                        WHERE e.open_subject_id = os.open_subject_id\r\n" + 
                    "                            AND os.instructor_id = i.instructor_id\r\n" + 
                    "                            AND i.instructor_id = ?\r\n" + 
                    "                            AND e.exam_id = ?)";
            
            pstmt2 = conn.prepareStatement(sql2);
            
            pstmt2.setString(1, e.getInstructor_id());
            pstmt2.setString(2, e.getExam_id());
 
            int result2 = pstmt2.executeUpdate();
            
            if((result1 != 0) && (result2 != 0)) {
                result = 1;
            }
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (pstmt1 != null)
                    pstmt1.close();
                if (pstmt2 != null)
                    pstmt2.close();
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
    
    // 시험 성적 입력 메소드
    public int insertScore(Exam e) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            
            String sql = "INSERT INTO student_score (student_score_id, attendance_score, write_score, skill_score, exam_id, student_id)\r\n" + 
                    "    VALUES((SELECT (CONCAT('SS', LPAD(NVL(SUBSTR(MAX(student_score_id), 3), 0) + 1, 5, 0)))AS newID FROM student_score)\r\n" + 
                    "    , ?, ?, ?, UPPER(?), UPPER(?))";
            
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, e.getAttendance_score());
            pstmt.setInt(2, e.getWrite_score());
            pstmt.setInt(3, e.getSkill_score());
            pstmt.setString(4, e.getExam_id());
            pstmt.setString(5, e.getStudent_id());
            
            result = pstmt.executeUpdate();
 
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
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
    
    // 시험 성적 삭제 메소드
    public int removeScore(Exam e) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = OracleConnection.connect();
            String sql = "DELETE FROM student_score\r\n" + 
                    "    WHERE student_id = UPPER(?)";
            
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, e.getStudent_id());
            
            result = pstmt.executeUpdate();
 
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
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
 
