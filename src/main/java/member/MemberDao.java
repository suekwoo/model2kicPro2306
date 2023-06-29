package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/*
DAO
DAO(Data Access Object) 는 데이터베이스의 data에 접근하기 위한 객체입니다. 
DataBase에 접근 하기 위한 로직 & 비지니스 로직을 분리하기 위해 사용합니다.

DTO
DTO(Data Transfer Object) 는 계층 간 데이터 교환을 하기 위해 사용하는 객체로, 
DTO는 로직을 가지지 않는 순수한 데이터 객체(getter & setter 만 가진 클래스)입니다.

*/

public class MemberDao {

	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kic", "1111");
			System.out.println("db ok");
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("db error");
		return null;
	}

	public int insertMember(Member m) {
		Connection con = getConnection(); // 1

		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement("insert into member values (?,?,?,?,?,?,'',sysdate)");
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPass());
			pstmt.setString(3, m.getName());
			pstmt.setInt(4, m.getGender());
			pstmt.setString(5, m.getTel());
			pstmt.setString(6, m.getEmail());
			return pstmt.executeUpdate(); // 3 dml시 실행

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}

	public Member oneMember(String id) {
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;
		ResultSet rs=null;
		try {
			pstmt 
			= con.
			prepareStatement("select * from member where id = ?");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery() ;
			if (rs.next()) {
				Member m = new Member();
				m.setId(rs.getString("id"));
				m.setPass(rs.getString("pass"));
				m.setName(rs.getString("name"));
				m.setGender(rs.getInt("gender"));
				m.setTel(rs.getString("tel"));
				m.setEmail(rs.getString("email"));
				return m;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int updateMember(Member m) {
		Connection con = getConnection(); // 1

		PreparedStatement pstmt;
		String sql = "update member set name=?, gender=?, tel=?, "
				+ " email=?, picture=? where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getName());
			pstmt.setInt(2, m.getGender());
			pstmt.setString(3, m.getTel());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getPicture());
			pstmt.setString(6, m.getId());
			
			
			return pstmt.executeUpdate(); // 3 dml시 실행

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}
	
	public int deleteMember(String id) {
		Connection con = getConnection(); // 1

		PreparedStatement pstmt;
		String sql = "delete from member where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate(); // 3 dml시 실행

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	
	
	public int changePass(String id, String newPass) {
		Connection con = getConnection(); // 1

		PreparedStatement pstmt;
		String sql = "update member set pass=?  where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newPass);
			pstmt.setString(2, id);
			return pstmt.executeUpdate(); // 3 dml시 실행

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	
	
	public List<Member> memberList() {
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;
		ResultSet rs=null;
		List<Member> li = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("select * from member");
			rs=pstmt.executeQuery() ;
			while (rs.next()) {
				Member m = new Member();
				m.setId(rs.getString("id"));
				m.setPass(rs.getString("pass"));
				m.setName(rs.getString("name"));
				m.setGender(rs.getInt("gender"));
				m.setTel(rs.getString("tel"));
				m.setEmail(rs.getString("email"));
				m.setPicture(rs.getString("picture"));
				li.add(m);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return li;
	}
	
}  //end class
