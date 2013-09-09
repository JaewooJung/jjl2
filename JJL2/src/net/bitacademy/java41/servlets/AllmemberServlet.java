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

@WebServlet("/auth/allmember")
@SuppressWarnings("serial")
public class AllmemberServlet extends HttpServlet {
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		MemberDao memberDao = 
				(MemberDao) this.getServletContext().getAttribute("memberDao");
		try {
			
			ArrayList<Member> member = (ArrayList<Member>) memberDao.list();
			HttpSession session = request.getSession();
			
			if (member != null) {
				RequestDispatcher rd = request.getRequestDispatcher("../auth/allmember.jsp");
				session.setAttribute("allmember", member);
				rd.forward(request, response);
			
			} else {
				session.invalidate();
				RequestDispatcher rd = 
						request.getRequestDispatcher("/auth/error.jsp");
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







