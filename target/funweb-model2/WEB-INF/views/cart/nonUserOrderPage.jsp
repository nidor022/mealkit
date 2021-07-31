<%@page import="com.exam.dao.OrderDao"%>
<%@page import="com.exam.vo.OrderVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%

String orderNumber = request.getParameter("orderNumber");
String passWd = request.getParameter("passWd");

ArrayList<OrderVo> arrOrder = null;

%>

<html>
<head>

 	<%-- head 영역 --%>
    <jsp:include page="/WEB-INF/views/include/headContent.jsp"/>
<meta charset="UTF-8">
<title>Insert title here</title>
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
                        <!-- Top Search Area -->
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
            <a href="index.html"><img src="img/core-img/logo.png" alt=""></a>
        </div>

        <!-- Navbar Area -->
        <div class="bueno-main-menu" id="sticker">
            <div class="classy-nav-container breakpoint-off">
                <div class="container">
                    <!-- Menu -->
                    <nav class="classy-navbar justify-content-between" id="buenoNav">

                        <!-- Toggler -->
                        <div id="toggler"><img src="img/core-img/toggler.png" alt=""></div>

                        <!-- Navbar Toggler -->
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

 <% 
	    OrderDao orderDao = OrderDao.getInstance();
		
		int check = orderDao.idPassCheck(orderNumber, passWd);

		if (check == 0) {
            out.println("<tr align='center'>");
            out.println("<td colspan= '5'>");
            out.println("정보와 일치하는 주문목록이 없습니다.");
            out.println("<a href= 'shopMain.do'>주문하기</a>");
            out.println("</td>");
            out.println("</tr>");
        } else if (check == 1)
        {
            arrOrder = orderDao.getOrderById2(orderNumber, passWd);

            out.println("<div class=" + "center-test" + ">");
            out.println("<table border=" + "1" + ">");
            out.println("<th>주문 번호</th>");
            out.println("<th>주문 날짜</th>");
            out.println("<th>주문자</th>");
            out.println("<th>주소</th>");
            out.println("<th>상품</th>");
            out.println("<th>수량</th>");
            out.println("<th>가격</th>");

            int totalSum = 0, total = 0;
            DecimalFormat df = new DecimalFormat("￦#,##0");
            for (int i = 0; i < arrOrder.size(); i++) {
                OrderVo orderVo = arrOrder.get(i);
                out.println("<tr align= 'center'>");
                out.println("<td>" + orderVo.getId() + "</td>");
                out.println("<td>" + orderVo.getOrder_date() + "</td>");
                out.println("<td>" + orderVo.getName() + "</td>");
                out.println("<td>" + orderVo.getAddress() + "</td>");
                out.println("<td>" + orderVo.getProduct() + "</td>");
                out.println("<td>" + orderVo.getP_count() + "</td>");
                total = orderVo.getPrice() * orderVo.getP_count();
                out.println("<td>" + df.format(total) + "</td>");
                out.println("</tr>");
                totalSum += total;
            }
                out.println("</table>");
                out.println("</div>");

        }

    %>


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