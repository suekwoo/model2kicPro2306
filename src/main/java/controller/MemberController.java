package controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kic.mskim.Login;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import member.Member;
import member.MemberDao;

@WebServlet(urlPatterns= {"/member/*"},  
initParams= {@WebInitParam(name="view",value="/view/member/"),//jsp 위치
		@WebInitParam(name="login",value="login")} )  //package

public class MemberController extends  MskimRequestMapping{

	    @RequestMapping("index")  // /member/index
		public String  index(HttpServletRequest request, 
				HttpServletResponse response) {
			request.setAttribute("index", "member 입니다");
			// /view/member/index.jsp
			return "index";
		}
	    
	    @RequestMapping("joinForm") //  /member/joinForm
		public String  joinForm(HttpServletRequest request, 
				HttpServletResponse response) {
			
			return "joinForm";
		}
	    @RequestMapping("loginForm") //  /member/joinForm
		public String  loginForm(HttpServletRequest request, 
				HttpServletResponse response) {
			
			return "loginForm";
		}
	    
	    @RequestMapping("joinPro") //  /member/joinForm
		public String  joinPro(HttpServletRequest request, 
				HttpServletResponse response) {
	    	try {
				request.setCharacterEncoding("utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	Member mem = new Member();
	    	mem.setId(request.getParameter("id"));
	    	mem.setPass(request.getParameter("pass"));
	    	mem.setName(request.getParameter("name"));
	    	mem.setGender(Integer.parseInt(request.getParameter("gender")));
	    	mem.setTel(request.getParameter("tel"));
	    	mem.setEmail(request.getParameter("email"));

	    	MemberDao md = new MemberDao();
	    	int num = md.insertMember(mem);
	    	String msg = "";
	    	String url = "";
	    	if (num > 0) {
	    		//insert ok
	    		msg = mem.getName() + "님이 가입을 하였습니다";
	    		url = "member/loginForm";
	    	} else {
	    		// insert error
	    		msg = "회원가입이 실패 하였습니다";
	    		url = "member/joinForm";
	    	}
	    	request.setAttribute("msg", msg);
	    	request.setAttribute("url", url);
			return "alert";
		}
		
	    
	    @RequestMapping("loginPro") //  /member/joinForm
		public String  loginPro(HttpServletRequest request, 
				HttpServletResponse response) {
	    	
	    	String id = request.getParameter("id");
	    	String pass = request.getParameter("pass");

	    	MemberDao md = new MemberDao();
	    	Member mem = md.oneMember(id); 
	    	String msg = "";
	    	String url = "";
	    	if (mem ==null) {   //id 없음
	    		msg="아이디를 확인 하세요";
	    	    url="member/loginForm";
	    	} else  {	
	    		if (pass.equals(mem.getPass())) {  //login ok
	    			request.getSession().setAttribute("id", id);
	    		    msg=mem.getName()+"님이 로그인 하셨습니다";
	    			url="member/index";
	    		} else {
	    			msg = "비밀번호를 확인 하세요";
	    			url="member/loginForm";
	    		}}
	    	request.setAttribute("msg", msg);
	    	request.setAttribute("url", url);
			return "alert";
		}
	    
	    
	    
	    @RequestMapping("logout") //  /member/joinForm
		public String  logout(HttpServletRequest request, 
				HttpServletResponse response) {
	    	HttpSession session = request.getSession();
	    	String login = (String)session.getAttribute("id");
	    	session.invalidate();
	    	String msg = login + "님이 로그아웃 되었습니다";
	    	String url = "member/loginForm";
	    	request.setAttribute("msg", msg);
	    	request.setAttribute("url", url);
	    	return "alert";
		}
	    
	  
	    @RequestMapping("memberInfo") //  /member/joinForm
	    @Login("LoginUser")
		public String  memberInfo(HttpServletRequest request, 
				HttpServletResponse response) {
			
			return "index";
		}
	    
	    
	    
}  //end class
