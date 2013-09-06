<%@page import="net.bitacademy.java41.vo.Member"%>
<%@page import="net.bitacademy.java41.vo.Project"%>
<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="member" class="net.bitacademy.java41.vo.Member" 
		scope="session"></jsp:useBean>   
<jsp:useBean id="member_project" type="java.util.Collection<net.bitacademy.java41.vo.Project>" 
		scope="session"></jsp:useBean>  
<jsp:useBean id="project_detail" type="net.bitacademy.java41.vo.Project" 
		scope="session"></jsp:useBean>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <title>JJL Project - Project Detail</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
    
    <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>

    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  </head>

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  <body class=""> 
  <!--<![endif]-->
    
    <div class="navbar">
        <div class="navbar-inner">
                <ul class="nav pull-right">
                    
                 <!--    <li><a href="#" class="hidden-phone visible-tablet visible-desktop" role="button">Settings</a></li> -->
                    <li id="fat-menu" class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i> <% if(member.getName() == null) {out.println("로그인하세요!");}else{out.println(member.getName());}%>
                            
                            <i class="icon-caret-down"></i>
                        </a>

                        <ul class="dropdown-menu">
                            <!-- <li><a tabindex="-1" href="#">My Account</a></li> -->
                            <!-- <li class="divider"></li> -->
                            <li><a tabindex="-1" class="visible-phone" href="#">Settings</a></li>
                            <li class="divider visible-phone"></li>
                            <li><a tabindex="-1" href="../auth/logout">Logout</a></li>
                             
                        </ul>
                    </li>
                    
                </ul>
                <a class="brand" href="../auth/main"><span class="first">JJL</span> <span class="second">Project</span></a>
        </div>
    </div>
    


    
    <div class="sidebar-nav">
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
        <a href="#legal-menu" class="nav-header" data-toggle="collapse"><i class="icon-legal"></i>Project<span class="label label-info"><%=member_project.size() %></span></a>
        <ul id="legal-menu" class="nav nav-list collapse">
            
            <% for(Project p : member_project){%>
            <li ><a href="../auth/projectdetail?pno=<%=p.getPno()%>" ><%=p.getTitle()%><%if(p.getLevel() == 2){%><span class="label label-info">Leader</span><%}%></a></li>
            
            <% }%>
            <li ><a href="../auth/allproject">View All Projects</a></li>
            
        </ul>
<!-- 
        <a href="help.html" class="nav-header" ><i class="icon-question-sign"></i>Help</a>

        <a href="faq.html" class="nav-header" ><i class="icon-comment"></i>Faq</a>
         -->
    </div>
    

    
    <div class="content">
        
        <div class="header">
            <div class="stats">
    
</div>

            <h1 class="page-title">Project Detail</h1>
        </div>
        
                <ul class="breadcrumb">
            <li><a href="../auth/main">Home</a> <span class="divider">/</span></li>
            <li class="active">ProjectDetail</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    


<div class="row-fluid">
    <div class="block span6">
        <a href="#tablewidget" class="block-heading" data-toggle="collapse"><h2><%=project_detail.getTitle() %></h2></a>
        <div id="tablewidget" class="block-body collapse in">
            <h2>프로젝트 번호 </h2>
            <%= project_detail.getPno() %>
            
            <h2>프로젝트 시작일  </h2>
            <%= project_detail.getStartDate() %><br>
            
            <h2>프로젝트 종료일 </h2>
            <%= project_detail.getEndDate() %><br>
            
            <h2>프로젝트 상세정보  </h2>
            <%= project_detail.getContent() %><br>
            
            <h2>TAG </h2>
            <%= project_detail.getTag() %><br>
            
        </div>
    </div>
    
</div>



                    
                    <footer>
                        <hr>

                        <!-- Purchase a site license to remove this link from the footer: http://www.portnine.com/bootstrap-themes -->
                        <p class="pull-right">A <a href="http://www.portnine.com/bootstrap-themes" target="_blank">Free Bootstrap Theme</a> by <a href="http://www.portnine.com" target="_blank">Portnine</a></p>

                        <p>&copy; 2012 <a href="http://www.portnine.com" target="_blank">Portnine</a></p>
                    </footer>
                    
            </div>
        </div>
    </div>
    


    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  </body>
</html>


