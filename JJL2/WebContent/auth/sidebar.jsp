<%@page import="net.bitacademy.java41.vo.Member"%>
<%@page import="net.bitacademy.java41.vo.Project"%>
<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="member" class="net.bitacademy.java41.vo.Member" 
		scope="session"></jsp:useBean>   
<jsp:useBean id="member_project" type="java.util.Collection<net.bitacademy.java41.vo.Project>" 
		scope="session"></jsp:useBean>  


    <div class="sidebar-nav">
       <center>
        <img src="../auth/111.jpeg" >
        </center>
        <table class="table">
              <thead>
                <tr>
                  <th><span class="label label-info">Name</span></th>
                  <th><a href="../auth/mymemberupdate">${member.name}</a></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                <td><span class="label label-info">E-mail </span></td>
                <td>${member.email}</td>
                </tr>
                <tr>
                <td><span class="label label-info">Tel No</span></td>
                <td>${member.tel}</td></tr>
                <tr>
                </tr>
              </table>
        
        <a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i class="icon-dashboard"></i>Dashboard</a>
        <ul id="dashboard-menu" class="nav nav-list collapse">
            <li><a href="../auth/main">Home</a></li>
         <!--    <li ><a href="users.html">Sample List</a></li>
            <li ><a href="user.html">Sample Item</a></li>
            <li ><a href="media.html">Media</a></li>
            <li ><a href="calendar.html">Calendar</a></li>
             -->
        </ul>

        <a href="#accounts-menu" class="nav-header" data-toggle="collapse"><i class="icon-briefcase"></i>Account<!-- <span class="label label-info">+3</span> --></a>
        <ul id="accounts-menu" class="nav nav-list collapse">
            <li ><a href="../auth/login">Sign In</a></li>
            <li ><a href="../auth/signin">Sign Up</a></li>
            <!-- <li ><a href="reset-password.html">Reset Password</a></li> -->
        </ul>

<!--         <a href="#error-menu" class="nav-header collapsed" data-toggle="collapse"><i class="icon-exclamation-sign"></i>Error Pages <i class="icon-chevron-up"></i></a>
        <ul id="error-menu" class="nav nav-list collapse">
            <li ><a href="403.html">403 page</a></li>
            <li ><a href="404.html">404 page</a></li>
            <li ><a href="500.html">500 page</a></li>
            <li ><a href="503.html">503 page</a></li>
        </ul>
 -->
        <a href="../auth/allproject" class="nav-header"><i class="icon-legal"></i>View All Projects</a>
        <c:choose>
	        <c:when test="${member.level == '1'}">
		        <a href="#legal-menu" class="nav-header" data-toggle="collapse"><i class="icon-user"></i>Manager</a>
		        <ul id="legal-menu" class="nav nav-list collapse">
		            
		            <li ><a href="../auth/allmember">Member Control </a></li>
		            <li ><a href="../auth/projectdetail">Project Control </a></li>
		        </ul>
		    </c:when>
	    <c:otherwise>
       	 	<a href="#legal-menu" class="nav-header" data-toggle="collapse"><i class="icon-legal"></i>Project<span class="label label-info"><%=member_project.size() %></span></a>
        		<ul id="legal-menu" class="nav nav-list collapse">
            
            	<c:forEach var="project" items="${member_project}">
					<li><a href="${rootPath}../auth/projectdetail?pno=${project.pno}">${project.title}
						<c:if test="${project.level == 0}">
						<span class="label label-info">Leader</span>
						</c:if>
					</a>
					</li>
				</c:forEach>
            	
        		</ul>
        
        </c:otherwise>
        </c:choose>
<!-- 
        <a href="help.html" class="nav-header" ><i class="icon-question-sign"></i>Help</a>

        <a href="faq.html" class="nav-header" ><i class="icon-comment"></i>Faq</a>
         -->
    </div>
