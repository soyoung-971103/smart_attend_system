package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.*;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

public class LecsjDAO extends DAOBase {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private LecsjDTO dtoLecsj = null;
	private ArrayList<LecsjDTO> dtoListLecsj = null;

//기본정보 검색

	public String info(String stdID, int term) throws SQLException {
		String info = null;
		conn = getConnection();
		stmt = conn.createStatement();
		int sum = 0;

		rs = stmt.executeQuery(
				"SELECT student.name, student.schoolno, depart.name as depart_name, subject.yyyy, student.grade as std_grade FROM student LEFT JOIN depart ON student.depart_id = depart.id LEFT JOIN mylecture ON student.id = mylecture.student_id LEFT JOIN lecture ON mylecture.lecture_id=lecture.id LEFT JOIN subject ON lecture.subject_id=subject.id Where student.schoolno like '%"
						+ stdID + "%' AND subject.term=" + term + ";");

		// 학생정보 읽어오기 시작
		if (rs.next()) {
			info = rs.getString(1) + "/" + rs.getString(2) + "/" + rs.getString(3) + "/" + rs.getString(4) + "/"
					+ rs.getString(5) + "/" + term;
		} else {
			rs = stmt.executeQuery(
					"SELECT student.name, student.schoolno, depart.name as depart_name, subject.yyyy, student.grade as student_grade FROM student LEFT JOIN depart ON student.depart_id = depart.id LEFT JOIN mylecture ON student.id = mylecture.student_id LEFT JOIN lecture ON mylecture.lecture_id=lecture.id LEFT JOIN subject ON lecture.subject_id=subject.id Where student.schoolno like '%"
							+ stdID + "%';");
			if (rs.next()) {
				if (rs.getString(4) == null) {
					String year=rs.getString(2);
					info = rs.getString(1) + "/" + rs.getString(2) + "/" + rs.getString(3) + "/" + year.substring(0, 4) + "/"
							+ rs.getString(5) + "/" + term;
				}
				else {
					info = rs.getString(1) + "/" + rs.getString(2) + "/" + rs.getString(3) + "/" + rs.getString(4) + "/"
							+ rs.getString(5) + "/" + term;
				}
			}
		}
		// 학생정보 읽어오기 끝

		// 학점 총점 읽어오기 시작
		rs = stmt.executeQuery(
				"SELECT SUM(subject.ipoint) FROM subject LEFT JOIN lecture ON subject.id=lecture.subject_id LEFT JOIN mylecture ON lecture.id=mylecture.lecture_id LEFT JOIN student ON subject.id=mylecture.student_id WHERE student.schoolno like '"
						+ stdID + "'");
		if (rs.next()) {
			sum += rs.getInt(1);
		}
		// 학점 총점 읽어오기 끝

		info = sum + "/" + info + "/" + creditCalc(stdID);
		// <!-- info 내용 : 학점총점/이름/학번/학과/년도/학년/학기-->
		System.out.println(info);
		return info;
	}

	public String searchInfo(String stdID, int grade, int term) throws SQLException {
		String info = null;
		conn = getConnection();
		stmt = conn.createStatement();
		int sum = 0;
		rs = stmt.executeQuery(
				"SELECT student.name, student.schoolno, depart.name as depart_name, subject.yyyy FROM student LEFT JOIN depart ON student.depart_id = depart.id LEFT JOIN mylecture ON student.id = mylecture.student_id LEFT JOIN lecture ON mylecture.lecture_id=lecture.id LEFT JOIN subject ON lecture.subject_id=subject.id Where student.schoolno like '%"
						+ stdID + "%' AND subject.term=" + term + " AND mylecture.grade=" + grade + ";");
		// rs=stmt.executeQuery("SELECT * FROM student WHERE id=1;");

		// 학생정보 읽어오기 시작
		if (rs.next()) {
			info = rs.getString(1) + "/" + rs.getString(2) + "/" + rs.getString(3) + "/" + rs.getString(4) + "/" + grade
					+ "/" + term;
		} else {
			rs = stmt.executeQuery(
					"SELECT student.name, student.schoolno, depart.name as depart_name, subject.yyyy FROM student LEFT JOIN depart ON student.depart_id = depart.id LEFT JOIN mylecture ON student.id = mylecture.student_id LEFT JOIN lecture ON mylecture.lecture_id=lecture.id LEFT JOIN subject ON lecture.subject_id=subject.id Where student.schoolno like '%"
							+ stdID + "%';");
			if (rs.next()) {
				if (rs.getString(4) == null) {
					String year = rs.getString(2);
					info = rs.getString(1) + "/" + rs.getString(2) + "/" + rs.getString(3) + "/" + year.substring(0, 4)
							+ "/" + grade + "/" + term;
				}
				else {
					info = rs.getString(1) + "/" + rs.getString(2) + "/" + rs.getString(3) + "/" + rs.getString(4) + "/"
							+ grade + "/" + term;
				}
			}
		}
		// 학생정보 읽어오기 끝

		// 학점 총점 읽어오기 시작
		rs = stmt.executeQuery(
				"SELECT SUM(subject.ipoint) FROM subject LEFT JOIN lecture ON subject.id=lecture.subject_id LEFT JOIN mylecture ON lecture.id=mylecture.lecture_id LEFT JOIN student ON subject.id=mylecture.student_id WHERE student.schoolno like '"
						+ stdID + "'");
		if (rs.next()) {
			sum += rs.getInt(1);
		}
		// 학점 총점 읽어오기 끝

		info = sum + "/" + info + "/" + creditCalc(stdID);
		// <!-- info 내용 : 학점총점/이름/학번/학과/년도/학년/학기/평점-->

		return info;
	}

//최초 메뉴 진입 시작
	public ArrayList<LecsjDTO> list(String uid, int sel1, int sel2) {
		try {
			conn = getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(
					"select subject.ismajor, subject.ischoice, subject.code, subject.name, subject.ipoint as ipoint_sub, mylecture.iattend, mylecture.inormal, mylecture.imiddle, mylecture.ilast, mylecture.ipractice, mylecture.itotal, mylecture.ipoint, mylecture.igrade, mylecture.retake from subject left join lecture on subject.id=lecture.subject_id left join mylecture on lecture.id=mylecture.lecture_id left join student on student.id=mylecture.student_id where student.schoolno like '%"
							+ uid + "%' and mylecture.grade=" + sel1 + " and mylecture.term=" + sel2
							+ " order by subject.id");
			// email, pw는 form을 구성하는 각 요소의 이름
			dtoListLecsj = new ArrayList<LecsjDTO>();
			while (rs.next()) {
				dtoLecsj = new LecsjDTO();
				dtoLecsj.setIsmajor(rs.getString(1));
				dtoLecsj.setIschoice(rs.getString(2));
				dtoLecsj.setCode(rs.getString(3));
				dtoLecsj.setSubjectName(rs.getString(4));
				dtoLecsj.setSubjectIpoint(rs.getInt(5));
				dtoLecsj.setIattend(rs.getByte(6));
				dtoLecsj.setInormal(rs.getByte(7));
				dtoLecsj.setImiddle(rs.getByte(8));
				dtoLecsj.setIlast(rs.getByte(9));
				dtoLecsj.setIpractice(rs.getByte(10));
				dtoLecsj.setItotal(rs.getByte(11));
				dtoLecsj.setIpoint(rs.getFloat(12));
				dtoLecsj.setIgrade(rs.getString(13));
				dtoLecsj.setRetake(rs.getByte(14));
				dtoListLecsj.add(dtoLecsj);
			}
			return dtoListLecsj;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoListLecsj;
	}

//최초 메뉴 진입 끝
//학점 계산시작
	public String creditCalc(String stdId) throws SQLException {
		float creadit = 0;
		int total = 0;
		conn = getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(
				"SELECT SUM(mylecture.ipoint) FROM mylecture LEFT JOIN student ON mylecture.student_id=student.id WHERE student.schoolno LIKE '"
						+ stdId + "'");
		// 수강한 과목이 있을때만 검색해서 계산시작
		if (rs.next()) {
			total = rs.getInt(1);

			rs = stmt.executeQuery(
					"SELECT mylecture.ipractice, mylecture.igrade FROM mylecture LEFT JOIN student ON mylecture.student_id=student.id WHERE student.schoolno LIKE '"
							+ stdId + "'");

			while (rs.next()) {
				if ("A+".equals(rs.getString(2))) {
					creadit += (float) (rs.getFloat(1) * 4.5);
				} else if ("A+".equals(rs.getString(2))) {
					creadit += (float) (rs.getFloat(1) * 4.0);
				} else if ("B0".equals(rs.getString(2))) {
					creadit += (float) (rs.getFloat(1) * 3.5);
				} else if ("B+".equals(rs.getString(2))) {
					creadit += (float) (rs.getFloat(1) * 3.0);
				} else if ("C+".equals(rs.getString(2))) {
					creadit += (float) (rs.getFloat(1) * 2.5);
				} else if ("C0".equals(rs.getString(2))) {
					creadit += (float) (rs.getFloat(1) * 2.0);
				} else if ("D+".equals(rs.getString(2))) {
					creadit += (float) (rs.getFloat(1) * 1.5);
				} else if ("D0".equals(rs.getString(2))) {
					creadit += (float) (rs.getFloat(1) * 1.0);
				} else if ("F".equals(rs.getString(2))) {
					creadit += (float) (rs.getFloat(1) * 0);
				}

			}

		}

		return String.format("%.2f", creadit * total);
	}
//학점계산 끝
}
