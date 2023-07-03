package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import board.Board;
import board.BoardDao;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;

@WebServlet(urlPatterns= {"/board/*"},  
initParams= {@WebInitParam(name="view",value="/view/board/"),//jsp 위치
		@WebInitParam(name="login",value="login")} )  //package



public class BoardController extends  MskimRequestMapping{
	
	@RequestMapping("index")  
	public String  index(HttpServletRequest request, 
			HttpServletResponse response) {
		request.setAttribute("index", "board 입니다");
		// /view/board/index.jsp
		return "index";
	}
	
	@RequestMapping("boardForm")  
	public String  boardForm(HttpServletRequest request, 
			HttpServletResponse response) {
		
		
		return "boardForm";
	}
	
	
	@RequestMapping("boardPro")  
	public String  boardPro(HttpServletRequest request, 
			HttpServletResponse response) {
		String path = 
    			request.getServletContext().getRealPath("/")
    			+"view/board/images/";
		String msg="게시물 등록 실패";
		String url="/board/boardForm";
		
    	String filename = "";
    	try {
			MultipartRequest  multi = 
			new MultipartRequest(request,path,10*1024*1024,"UTF-8");
			filename = multi.getFilesystemName("file1");
			Board board = new Board();
			board.setBoardid("1");
			board.setName(multi.getParameter("name"));
			board.setPass(multi.getParameter("pass"));
			board.setSubject(multi.getParameter("subject"));
			
			board.setContent(multi.getParameter("content"));
			board.setFile1(filename);
			BoardDao bd = new BoardDao();
			System.out.println(board);
			int num = bd.insertBoard(board);
			if (num > 0) {
				 msg="게시물 등록 성공";
				 url="/board/boardList";
				
			}
			
				//upload filename   type="file" name="file1">
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
	   
		request.setAttribute("msg", filename+":"+msg);
    	request.setAttribute("url", url);
		return "alert";
	}
	
	
	@RequestMapping("boardList")  
	public String  boardList(HttpServletRequest request, 
			HttpServletResponse response) {
		 BoardDao bd = new BoardDao();
		 List<Board> list=bd.boardList();
		 request.setAttribute("list", list);
		return "boardList";  //view/board/boardList.jsp
	}

}
