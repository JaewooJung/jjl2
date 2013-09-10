package net.bitacademy.java41.servlets.member;

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

@WebServlet("/auth/mymemberupdate")
@SuppressWarnings("serial")
public class MyMemberUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		MemberDao memberDao = 
				(MemberDao) this.getServletContext().getAttribute("memberDao");
		
		try {
			HttpSession session = request.getSession();
			RequestDispatcher rd = request.getRequestDispatcher("../auth/myupdatemember.jsp");
			rd.forward(request, response);
				
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = 
					request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		MemberDao memberDao = 
				(MemberDao) this.getServletContext().getAttribute("memberDao");
		try {
			Member memberUpdate = (Member) request.getSession().getAttribute("memberUpdate");
			memberUpdate.setName(request.getParameter("name"))
			.setPassword(memberUpdate.getEmail())
			.setPassword(request.getParameter("password"))
			.setTel(request.getParameter("tel"))
			.setBlog(request.getParameter("blog"))
			.setDetailAddress(request.getParameter("detailAddr"))
			.setTag(request.getParameter("tag"))
			.setLevel(Integer.parseInt(request.getParameter("level")));
			
			memberDao.change(memberUpdate);
			HttpSession session = request.getSession();
			response.sendRedirect("../auth/allmember");
			//RequestDispatcher rd = request.getRequestDispatcher("../auth/allmember");
			//rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = 
					request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
		
	}

}







