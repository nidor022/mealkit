<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <%-- head 영역 --%>
    <jsp:include page="/WEB-INF/views/include/headContent.jsp"/>
    <link rel="stylesheet" type="text/css" href="/css/style1.css">
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
            <a href="index.do"><img src="img/core-img/logo.png" alt=""></a>
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

    <%-- TreadingPost 영역 --%>
    <jsp:include page="/WEB-INF/views/include/treadingPost.jsp"/>

    <!-- ##### Search Area Start ##### -->
    <div  style="background-image: url(img/core-img/pattern.png);">
        <div class="container">
            <div class="jaryo2">
            	<div class="jaryo1"></div>
                	<div class="jaryo">
                    	<div>
                      		<div >
                            <h1>고객 게시판 [글갯수: ${ pageDtoService.count }]</h1>
							<table id="service">
								<tr>
									<th scope="col" class="tno">글번호</th>
									<th scope="col" class="ttitle">글제목</th>
									<th scope="col" class="twrite">작성자</th>
									<th scope="col" class="tdate">작성일자</th>
									<th scope="col" class="tread">조회수</th>
								</tr>
								
								<c:choose>
								<c:when test="${ not empty serviceList }"><%-- ${ pageDto.count gt 0 } --%>
									
									<c:forEach var="service" items="${ serviceList }">
										<tr>
											<td>${ service.num }</td>
											<td class="left">
												<c:if test="${ service.reLev gt 0 }"><%-- 답글이면 --%>
													<img src="/img/level.gif" width="${ service.reLev * 15 }" height="13">
													<img src="/img/re.gif">
												</c:if>
												<a href="serviceContent.do?num=${ service.num }&pageNum=${ pageNum }">${ service.subject }</a>
											</td>
											<td>${ service.id }</td>
											<td><fmt:formatDate value="${ service.regDate }" pattern="yyyy.MM.dd"/></td>
											<td>${ service.readcount }</td>
										</tr>
									</c:forEach>
									
								</c:when>		
								<c:otherwise>
									<tr>
										<td colspan="5">게시판 글 없음</td>
									</tr>
								</c:otherwise>
								</c:choose>
						
							</table>
						
							<div id="table_search">
								<form action="serviceNotice.do" method="get">
									<select name="category">
										<option value="subject" ${ pageDtoService.category eq 'subject' ? 'selected' : '' }>글제목</option>
										<option value="content" ${ pageDtoService.category eq 'content' ? 'selected' : '' }>글내용</option>
										<option value="id" ${ pageDtoService.category eq 'id' ? 'selected' : '' }>작성자ID</option>
									</select>
									<input type="text" class="input_box" name="search" value="${ pageDtoService.search }">
									<input type="submit" value="검색" class="btn">
									
									<%-- 로그인 했을때만 [글쓰기] 버튼 보이기 --%>
									<c:if test="${ not empty sessionScope.id }">
										<input type="button" value="파일글쓰기" class="btn" onclick="location.href='serviceWriteForm.do?pageNum=${ pageNum }'">
									</c:if>
						
								</form>
							</div>
							
							<div class="clear"></div>
							<div id="page_control">
							
							<%-- 글갯수가 0보다 크면 페이지블록 계산해서 출력하기 --%>
							<c:if test="${ pageDtoService.count gt 0 }">
								<%-- [이전] --%>
								<c:if test="${ pageDtoService.startPage gt pageDtoService.pageBlock }">
									<a href="serviceNotice.do?pageNum=${ pageDtoService.startPage - pageDtoService.pageBlock }&category=${ pageDtoService.category }&search=${ pageDtoService.search }">[이전]</a>
								</c:if>
								
								<%-- 시작페이지 ~ 끝페이지 --%>
								<c:forEach var="i" begin="${ pageDtoService.startPage }" end="${ pageDtoService.endPage }" step="1">
									
									<c:choose>
									<c:when test="${ i eq pageNum }">
										<a href="serviceNotice.do?pageNum=${ i }&category=${ pageDtoService.category }&search=${ pageDtoService.search }" class="active">[${ i }]</a>
									</c:when>
									<c:otherwise>
										<a href="serviceNotice.do?pageNum=${ i }&category=${ pageDtoService.category }&search=${ pageDtoService.search }">[${ i }]</a>
									</c:otherwise>
									</c:choose>
									
								</c:forEach>
								
								<%-- [다음] --%>
								<c:if test="${ pageDtoService.endPage lt pageDtoService.pageCount }">
									<a href="serviceNotice.do?pageNum=${ pageDtoService.startPage + pageDtoService.pageBlock }&category=${ pageDtoService.category }&search=${ pageDtoService.search }">[다음]</a>
								</c:if>
							</c:if>
							
							</div>
                        	</div>
                      	</div>
                      </div>
              	  
                <div class="col-3"></div>
            </div>
        </div>
    </div>
    <!-- ##### Search Area End ##### -->

    <!-- ##### Catagory Area Start ##### -->
    <div class="post-catagory section-padding-100">
        <div class="container">
            <div class="row">

            </div>
        </div>
    </div>
    <!-- ##### Catagory Area End ##### -->

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