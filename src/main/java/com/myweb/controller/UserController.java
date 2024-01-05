package com.myweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.user.model.UserVO;
import com.myweb.user.service.UserService;
import com.myweb.user.service.UserServiceImpl;

@WebServlet("*.user") //확장자패턴
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//3. URL주소를 분기(각 요청별로 코드를 만듬)
		request.setCharacterEncoding("utf-8"); //한글처리
		
		String uri = request.getRequestURI();
		String path = uri.substring( request.getContextPath().length()  );
		
		System.out.println(path);
		
		
		//서비스 영역 선언
		UserService service = new UserServiceImpl();
		
		if(path.equals("/user/join.user")) { //가입화면
			
			//화면이동 기본값 - forward형식이 되어야함
			request.getRequestDispatcher("user_join.jsp").forward(request, response);

		} else if(path.equals("/user/login.user") ) { //로그인화면

			request.getRequestDispatcher("user_login.jsp").forward(request, response);
		
		} else if(path.equals("/user/joinForm.user") ) { //회원가입
			
			//String id = request.getParameter("id");
			//.....
			int result = service.join(request, response);
			
			if(result == 1){
				request.setAttribute("msg", "아이디가 중복되었습니다");
				request.getRequestDispatcher("user_join.jsp").forward(request, response);
			}else {//회원가입성공
				response.sendRedirect("login.user"); //MVC2 방식에서 리다이렉트는 다시 컨트롤러를 태우는데 사용함.
			}
		
		}else if(path.equals("/user/loginForm.user")) { //로그인
			
			UserVO vo = service.login(request, response);
			if(vo != null) {//로그인 성공
				//서블릿에서는 request.getSession 현재세션을 얻을 수 있습니다.
				HttpSession session = request.getSession();
				session.setAttribute("user_id", vo.getId());
				session.setAttribute("user_name", vo.getName());
				
				response.sendRedirect(request.getContextPath());//홈화면
				
			}else { //로그인 실패
				request.setAttribute("msg", "아이디 비밀번호를 확인하세요");
				request.getRequestDispatcher("user_login.jsp").forward(request, response);
			}
		}else if(path.equals("/user/logout.user")) { //로그아웃
			HttpSession session = request.getSession();
			session.invalidate();
			
			response.sendRedirect(request.getContextPath()); //홈화면
		}else if(path.equals("/user/mypage.user")) {
			
			request.getRequestDispatcher("user_mypage.jsp").forward(request, response);
		}else if(path.equals("/user/update.user")) { //정보수정하면
			
			//여기에서 회원에 대한 데이터를 가지고 화면으로
			/*
			 * 1.DAO에서는 id기준으로 회원정보를 조회해서 UserVO저장
			 * 2.service영역에서는 리턴해서 컨트롤까지 회원정보를 가지고옵니다.
			 * 3.컨트롤러에서 vo를 request에 저장합니다.
			 * 4.화면에서 EL태그를 사용해서 value속성에 찍어주면 됩니다.
			 * 
			 */
			
			UserVO vo= service.getUserInfo(request, response);
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("user_update.jsp").forward(request, response);
			
		}else if (path.equals("/user/updateForm.user")) {
			
		//0이면, 1이면 성공
		int result = service.update(request, response);
		
		if(result == 1) {
			//브라우저 화면에 직접 응답을 해주는 형태
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('업데이트에 성공했습니다');");
			out.println("location.href='mypage.user';");
			out.println("</script>");
			
		}else {
			response.sendRedirect("mypage.user");
		}
	}
		
}

}
