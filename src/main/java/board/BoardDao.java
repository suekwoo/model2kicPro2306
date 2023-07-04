package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.Member;

public class BoardDao {
	
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

	
	public int insertBoard(Board board) {
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement
			("insert into board values (boardseq.nextval,?,?,?,?,?,?,sysdate,0,boardseq.currval,0,0)");
			
			pstmt.setString(1, board.getBoardid());
			pstmt.setString(2, board.getName());
			pstmt.setString(3, board.getPass());
			pstmt.setString(4, board.getSubject());
			pstmt.setString(5,board.getContent());
			pstmt.setString(6, board.getFile1());
			
			return pstmt.executeUpdate(); // 3 dml시 실행

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	
	public int boardCount(String boardid) {
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;		ResultSet rs=null;
		String sql = "select nvl(count(*),0) from board where boardid = ? ";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardid);
			rs=pstmt.executeQuery() ;
			if (rs.next())
			{  return rs.getInt(1);
						}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return 0;		
	}
	
	

	public List<Board> boardList(int pageInt, int limit, String boardid) {
		Connection con = getConnection(); // 1
		PreparedStatement pstmt;		ResultSet rs=null;
		List<Board> li = new ArrayList<>();
		try {
			/*
			select * from (
select rownum rnum , a.* from 
(select * from board where boardid = '1'
order by num desc) a) where rnum BETWEEN 10 and 13;
			 */
		String sql = 	" select * from ("+
				" select rownum rnum , a.* from "+
				" (select * from board where boardid = ?"+
				" order by num desc) a) where rnum BETWEEN ? and ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardid);
			pstmt.setInt(2, (pageInt-1)*limit + 1); //1 ,11, 21,  .....
			pstmt.setInt(3, pageInt*limit);  //10, 20, 30
			rs=pstmt.executeQuery() ;
			
			while (rs.next()) {
				Board b = new Board();
				b.setNum(rs.getInt("num"));
				b.setName(rs.getString("name"));
				b.setPass(rs.getString("pass"));
				b.setSubject(rs.getString("subject"));
				b.setContent(rs.getString("content"));
				b.setFile1(rs.getString("file1"));
				b.setRegdate(rs.getDate("regdate"));
				b.setReadcnt(rs.getInt("readcnt"));
				li.add(b);		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return li;		
	}
	
}  //end class
