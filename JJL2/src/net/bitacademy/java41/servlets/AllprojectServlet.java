package net.bitacademy.java41.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Project;

@WebServlet("/auth/allproject")
@SuppressWarnings("serial")
public class AllprojectServlet extends HttpServlet {
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ProjectDao projectDao = 
				(ProjectDao) this.getServletContext().getAttribute("projectDao");
		try {
			
			ArrayList<Project> project = projectDao.getProject();
			HttpSession session = request.getSession();
			
			if (project != null) {
				RequestDispatcher rd = request.getRequestDispatcher("../auth/allproject.jsp");
				session.setAttribute("project", project);
				rd.forward(request, response);
			
			} else {
				session.invalidate();
				RequestDispatcher rd = 
						request.getRequestDispatcher("/auth/loginFail.jsp");
				rd.forward(request, response);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = 
					request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		MemberDao memberDao = 
				(MemberDao) this.getServletContext().getAttribute("memberDao");
		try {
			Member member = memberDao.getMember(email, password);
			HttpSession session = request.getSession();
			
			if(request.getParameter("saveId") != null) {
				Cookie cookie = new Cookie("email", email);
				cookie.setMaxAge(60); // 쿠키의 생존 시간을 지정한다.
				// 컴퓨터를 껐다가 켜도 해당 시간 동안은 유효하다. 
				// 유효하다는 의미는 서버에 해당 쿠키정보를 보낸다는 의미이다.
				response.addCookie(cookie);
			} else {
				Cookie cookie = new Cookie("email", null);
				cookie.setMaxAge(0); // 브라우저에 더이상 email 쿠키를 보내지 말 것을 요청.
				// 쿠키의 생존 시간을 지정하지 않으면, 웹브라우저가 실행되는 동안만 유효하다.
				response.addCookie(cookie);
			}
			
			if (member != null) {
				session.setAttribute("member", member);
				response.sendRedirect("../auth/main");
				
			} else {
				session.invalidate();
				RequestDispatcher rd = 
						request.getRequestDispatcher("/auth/loginFail.jsp");
				rd.forward(request, response);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = 
					request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}

}







