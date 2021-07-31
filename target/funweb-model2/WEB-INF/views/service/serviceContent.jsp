<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <%-- head 영역 --%>
    <jsp:include page="/WEB-INF/views/include/headContent.jsp"/>
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
    <div class="bueno-search-area section-padding-100-0 pb-70 bg-img" style="background-image: url(img/core-img/pattern.png);">
        <div class="container">
            <div class="row">
            	<div class="col-3"></div>
                <div class="col-3">
                    <form action="userJoinPro.do" method="post" name="frm">
                        <div class="row">
                            <div>
                                <div class="form-group mb-30">
                                	<h1>고객 게시판 상세보기</h1>
		
									<table id="service">
										<tr>
											<th scope="col" class="tno">글번호</th>
											<td class="left" width="500">${ serviceVo.num }</td>
										</tr>
										<tr>
											<th scope="col" class="tread">조회수</th>
											<td class="left">${ serviceVo.readcount }</td>
										</tr>
										<tr>
											<th scope="col" class="twrite">작성자</th>
											<td class="left">${ id }</td>
										</tr>
										<tr>
											<th scope="col" class="tdate">작성일자</th>
											<td class="left"><fmt:formatDate value="${ serviceVo.regDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										</tr>
										<tr>
											<th scope="col" class="ttitle">글제목</th>
											<td class="left">${ serviceVo.subject }</td>
										</tr>
										<tr>
											<th scope="col" class="ttitle">글내용</th>
											<td class="left">${ serviceVo.content }</td>
										</tr>
										<tr>
											<th scope="col" class="ttitle">첨부파일</th>
											<td class="left">
											
											<c:if test="${ not empty serviceAttachList }">
											
												<c:forEach var="serviceattach" items="${ serviceAttachList }">
												
													<c:choose>
													<c:when test="${ serviceattach.image eq 'I' }">
														<p>
															<a href="/upload1/${ serviceattach.uploadpath }/${ serviceattach.filename }">
																<img src="/upload1/${ serviceattach.uploadpath }/${ serviceattach.filename }" width="100" height="100">
															</a>
														</p>
													</c:when>
													<c:otherwise>
														<p>
															<a href="/upload1/${ serviceattach.uploadpath }/${ serviceattach.filename }">
																${ serviceattach.filename }
															</a>
														</p>
													</c:otherwise>
													</c:choose>
													
												</c:forEach>
											</c:if>
								
											</td>
										</tr>
									</table>
								
									<div id="table_search">
									
										<c:if test="${ not empty id }">
											<%-- 로그인 했을때 --%>
											<c:if test="${ id eq serviceVo.id }">
												<%-- 로그인 아이디와 글작성자 아이디가 같을때 --%>
												<input type="button" value="글수정" class="btn" onclick="location.href = 'serviceModifyForm.do?num=${ serviceVo.num }&pageNum=${ pageNum }'">
												<input type="button" value="글삭제" class="btn" onclick="remove()">
											</c:if>
											<input type="button" value="답글쓰기" class="btn" onclick="location.href = 'serviceReplyForm.do?num=${ serviceVo.num }&pageNum=${ pageNum }&reRef=${ serviceVo.reRef }&reLev=${ serviceVo.reLev }&reSeq=${ serviceVo.reSeq }'">
										</c:if>
										
										<input type="button" value="목록보기" class="btn" onclick="location.href = 'serviceBoard.do?pageNum=${ pageNum }'">
									</div>
									
									<div class="clear"></div>
									<div id="page_control">
									</div>
                                </div>
                            </div>
                        </div>
                    </form>
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
    <script>
		function remove() {
			let isDelete = confirm('${ serviceVo.num }번 글을 정말 삭제하시겠습니까?');
			if (isDelete) {
				location.href = 'serviceDelete.do?num=${ serviceVo.num }&pageNum=${ pageNum }';
			}
		}
	</script>
</body>

</html>