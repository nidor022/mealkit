<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.exam.vo.UserVo"%>
<%@page import="com.exam.dao.UserDao"%>
<%@page import="java.util.ArrayList"%>
<%

	String id = (String) session.getAttribute("id");

	UserDao userDao = UserDao.getInstance();
	
	UserVo user = null;
	user = userDao.getUserById(id);
	
	String name = user.getName();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>느그페이지</title>

<head>
	<%-- head 영역 --%>
    <jsp:include page="/WEB-INF/views/include/headContent.jsp"/>
</head>
</head>
<body>

 <%-- preLoader 영역 --%>
    <jsp:include page="/WEB-INF/views/include/preLoader.jsp"/>

    <!-- ##### Header Area Start ##### -->
    <header class="header-area">

        <!-- Top Header Area -->
        <div class="top-header-area bg-img bg-overlay" style="background-image: url(img/bg-img/header.jpg);">
            <div class="container h-100">
                <div class="row h-100 align-items-center justify-content-between">
                    <div class="col-12 col-sm-6">
                        <!-- Top Social Info -->
                        <div class="top-social-info">
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Pinterest"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Dribbble"><i class="fa fa-dribbble" aria-hidden="true"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Behance"><i class="fa fa-behance" aria-hidden="true"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Linkedin"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
                        </div>
                    </div>
                    <div class="col-12 col-sm-6 col-lg-5 col-xl-4">
                        <!-- Top Search Area, 검색바 -->
                        <div class="top-search-area">
                            <form action="#" method="post">
                                <input type="search" name="top-search" id="topSearch" placeholder="Search">
                                <button type="submit" class="btn"><i class="fa fa-search"></i></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Logo Area -->
        <div class="logo-area">
            <a href="index.do"><img src="img/core-img/logo.png" alt=""></a>
        </div>

        <!-- Navbar Area -->
        <div class="bueno-main-menu" id="sticker">
            <div class="classy-nav-container breakpoint-off">
                <div class="container">
                    <!-- Menu -->
                    <nav class="classy-navbar justify-content-between" id="buenoNav">

                        <!-- Toggler, 삼선메뉴 -->
                        <div id="toggler"><img src="img/core-img/toggler.png" alt=""></div>

                        <!-- Navbar Toggler, 빈공간인듯 에바임 -->
                        <div class="classy-navbar-toggler">
                            <span class="navbarToggler"><span></span><span></span><span></span></span>
                        </div>

                        <!-- Menu -->
                        <div class="classy-menu">
                            <!-- Close Button -->
                            <div class="classycloseIcon">
                                <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                            </div>

                            <%-- Nav 영역 --%>
                            <jsp:include page="/WEB-INF/views/include/classyNav.jsp"/>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </header>
    <!-- ##### Header Area End ##### -->

    <%-- TreadingPost 영역 --%>
    <jsp:include page="/WEB-INF/views/include/treadingPost.jsp"/>

<div align = "center">
<form method = "post" action = "UserUpdatePro.do">
<table width = "600" border = "1" cellspacing = "0" cellpadding = "3" align = "center">
<tr>
      <td colspan = "2" height = "39" align = "center">
            <font size = "+1"><b>회원 정보 수정</b></font>
      </td>
</tr>
<tr>
      <td colspan = "2" class = "normal" align = "center">
            회원의 정보를 수정합니다.
      </td>
</tr>
<tr>
      <td width = "200">
            <b>아이디 입력</b>
      </td>
      <td width = "400"></td>
</tr>
<tr>
      <td width = "200">사용자 ID</td>
      <td width = "400"><%=user.getId() %></td>
</tr>

<tr>
      <td width = "200">비밀번호</td>
      <td width = "400">
            <input type = "password" name = "passwd" size = "10" maxlength = "10" value = "">
      </td>
</tr>
<tr>
      <td width = "200">
            <b>개인정보 입력</b>
      </td>
      <td width = "400"></td>
</tr>
<tr>
      <td width = "200">사용자 이름</td>
      <td width = "400">
            <input type = "text" name = "name" size = "15" maxlength = "20" value = "<%=name %>">
      </td>
</tr>
<tr>
      <td width = "200">나이</td>
      <td width = "400">
            <input type = "number" name = "age" size = "15" maxlength = "20" value = "<%=user.getAge() %>">
      </td>
</tr>
<tr>
      <td width = "200">성별</td>
      <td width = "400">
       <%
             if(user.getGender().equals("남"))
             {
       %>
            <input type="radio" name="gender" value="남"  maxlength = "30" checked="checked">남  
            <input type="radio" name="gender" value="여"  maxlength = "30" >여  
            
       <%
             }
             else
             {
       %>
            <input type="radio" name="gender" value="남"  maxlength = "30" >남
            <input type="radio" name="gender" value="여"  maxlength = "30" checked="checked">여  
       <%
             }
       %>
      </td>
</tr>
<tr>
      <td width = "200">Email</td>
      <td width = "400">
       <%
             if(user.getEmail() == null)
             {
       %>
            <input type = "email" name = "email" size = "60" maxlength = "50">
       <%
             }
             else
             {
       %>
            <input type = "email" name = "email" size = "60" maxlength = "50" value = "<%=user.getEmail() %>">
       <%
             }
       %>
      </td>
</tr>

<tr>
      <td width = "200">생년월일</td>
      <td width = "400">
       <%
             if(user.getBirthDay() == null)
             {
       %>
            <input type = "date" name = "birth" size = "50">
       <%
             }
             else
             {
       %>
            <input type = "date" name = "birth" size = "50" value="<%=user.getBirthDay() %>">
       <%
             }
       %>
      </td>
</tr>

<tr>
      <td width = "200">주소</td>
      <td width = "400">
       <%
             if(user.getAddress() == null)
             {
       %>
            <input type = "text" name = "address" size = "70">
            주소를 적어 주세요.
       <%
             }
             else
             {
       %>
            <input type = "text" name = "address" size = "70"
                  value = "<%=user.getAddress() %>">
       <%
             }
       %>
      </td>
</tr> 

<tr>
      <td width = "200">전화번호</td>
      <td width = "400">
       <%
             if(user.getTel() == null)
             {
       %>
            <input type = "tel" name = "tel" size = "70">
            전화번호를 적어주세요.
       <%
             }
             else
             {
       %>
            <input type = "text" name = "tel" size = "70"
                  value = "<%=user.getTel() %>">
       <%
             }
       %>
      </td>
</tr> 
<tr>
      <td colspan = "2" align = "center" >
            <input type = "submit" name = "modify" value = "수  정">
            <input type = "button" value = "취  소" />
      </td>
</tr>
</table>
</form>
</div>
	 


    <!-- ##### Instagram Area Start ##### -->
    <div class="instagram-feed-area d-flex flex-wrap">
        <!-- Single Instagram -->
        <div class="single-instagram">
            <img src="img/bg-img/insta1.jpg" alt="">
            <!-- Image Zoom -->
            <a href="img/bg-img/insta1.jpg" class="img-zoom" title="Instagram Image">+</a>
        </div>

        <!-- Single Instagram -->
        <div class="single-instagram">
            <img src="img/bg-img/insta2.jpg" alt="">
            <!-- Image Zoom -->
            <a href="img/bg-img/insta2.jpg" class="img-zoom" title="Instagram Image">+</a>
        </div>

        <!-- Single Instagram -->
        <div class="single-instagram">
            <img src="img/bg-img/insta3.jpg" alt="">
            <!-- Image Zoom -->
            <a href="img/bg-img/insta3.jpg" class="img-zoom" title="Instagram Image">+</a>
        </div>

        <!-- Single Instagram -->
        <div class="single-instagram">
            <img src="img/bg-img/insta4.jpg" alt="">
            <!-- Image Zoom -->
            <a href="img/bg-img/insta4.jpg" class="img-zoom" title="Instagram Image">+</a>
        </div>

        <!-- Single Instagram -->
        <div class="single-instagram">
            <img src="img/bg-img/insta5.jpg" alt="">
            <!-- Image Zoom -->
            <a href="img/bg-img/insta5.jpg" class="img-zoom" title="Instagram Image">+</a>
        </div>

        <!-- Single Instagram -->
        <div class="single-instagram">
            <img src="img/bg-img/insta6.jpg" alt="">
            <!-- Image Zoom -->
            <a href="img/bg-img/insta6.jpg" class="img-zoom" title="Instagram Image">+</a>
        </div>

        <!-- Single Instagram -->
        <div class="single-instagram">
            <img src="img/bg-img/insta7.jpg" alt="">
            <!-- Image Zoom -->
            <a href="img/bg-img/insta7.jpg" class="img-zoom" title="Instagram Image">+</a>
        </div>

        <!-- Single Instagram -->
        <div class="single-instagram">
            <img src="img/bg-img/insta8.jpg" alt="">
            <!-- Image Zoom -->
            <a href="img/bg-img/insta8.jpg" class="img-zoom" title="Instagram Image">+</a>
        </div>

        <!-- Single Instagram -->
        <div class="single-instagram">
            <img src="img/bg-img/insta9.jpg" alt="">
            <!-- Image Zoom -->
            <a href="img/bg-img/insta9.jpg" class="img-zoom" title="Instagram Image">+</a>
        </div>

        <!-- Single Instagram -->
        <div class="single-instagram">
            <img src="img/bg-img/insta10.jpg" alt="">
            <!-- Image Zoom -->
            <a href="img/bg-img/insta10.jpg" class="img-zoom" title="Instagram Image">+</a>
        </div>
    </div>
    <!-- ##### Instagram Area End ##### -->

    <%-- Footer 영역 --%>
    <jsp:include page="/WEB-INF/views/include/bottomFooter.jsp"/>

    <!-- ##### All Javascript Script ##### -->
    <!-- jQuery-2.2.4 js -->
    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="js/bootstrap/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <!-- All Plugins js -->
    <script src="js/plugins/plugins.js"></script>
    <!-- Active js -->
    <script src="js/active.js"></script>

</body>
</html>