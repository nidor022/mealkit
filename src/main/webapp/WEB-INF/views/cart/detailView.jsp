<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
request.setCharacterEncoding("utf-8");

String name = (String)request.getParameter("name");
String price = (String)request.getParameter("price");


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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


 <form action="CartProcess.do" method="post" name ="frm" align = 'center'>
<div align="center">
<table class="tg" border="1">
<thead>
  <tr>
    <th class="tg-0pky" rowspan="3">
    	<c:if test="${ name eq '비빔밥'}">
    		<img src='img/food/bibimbap.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '부대찌개'}">
    		<img src='img/food/BG.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '불고기'}">
    		<img src='img/food/bgg.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '떡볶이'}">
    		<img src='img/food/DDUK.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '된장찌개'}">
    		<img src='img/food/DG.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '김치전'}">
    		<img src='img/food/gimchi.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '김치찌개'}">
    		<img src='img/food/KG.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '순두부찌개'}">
    		<img src='img/food/SG.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '삼계탕'}">
    		<img src='img/food/ST.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '알리오 올리오'}">
    		<img src='img/food/western/alio.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '아라비아따 파스타'}">
    		<img src='img/food/western/arabiatta.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '까르보나라 파스타'}">
    		<img src='img/food/western/carbonara.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '감자튀김 1KG'}">
    		<img src='img/food/western/frenchfry.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '피자'}">
    		<img src='img/food/western/pizza.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '폭립'}">
    		<img src='img/food/western/porkrip.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '라자냐'}">
    		<img src='img/food/western/rajanya.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '리코타 샐러드'}">
    		<img src='img/food/western/ricottasalad.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '스테이크'}">
    		<img src='img/food/western/steak.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '기스면'}">
    		<img src='img/food/china/기스면.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '라조기'}">
    		<img src='img/food/we라조기stern/steak.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '마파두부'}">
    		<img src='img/food/china/마파두부.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '양장피'}">
    		<img src='img/food/china/양장피.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '유산슬'}">
    		<img src='img/food/china/유산슬.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '짜장면'}">
    		<img src='img/food/china/짜장면.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '짬뽕'}">
    		<img src='img/food/china/짬뽕.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '탕수육'}">
    		<img src='img/food/china/탕수육.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '팔보채'}">
    		<img src='img/food/china/팔보채.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '부타동'}">
    		<img src='img/food/japan/butadong.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '규카츠'}">
    		<img src='img/food/japan/gyu.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '밀푀유 나베'}">
    		<img src='img/food/japan/milfeyou.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '오야코동'}">
    		<img src='img/food/japan/oyakodong.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '돈까스'}">
    		<img src='img/food/japan/porkcutlet.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '라멘'}">
    		<img src='img/food/japan/ramen.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '메밀 소바'}">
    		<img src='img/food/japan/soba.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '스키야키'}">
    		<img src='img/food/japan/suki.jpg' width='150' height='150'/>
    	</c:if>
    	<c:if test="${ name eq '우동'}">
    		<img src='img/food/japan/udong.jpg' width='150' height='150'/>
    	</c:if>

    </th>
    <th class="tg-c3ow">상품명</th>

    <td class="tg-0pky" align="center"><input type='text' class=apple1" name="name" value=${ name } readonly style="border:none"/><input type='hidden' name="id" value=${id }/></td>
  </tr>
  <tr>
    <td class="tg-c3ow">가격</td>
    <td class="tg-0pky" align="center"><input type='text' class="apple1" name="price" value=${ price } readonly style="border:none"/>
    <input type='hidden' name="pagename" value=${pagename }></td>
  </tr>
  <tr>
    <td class="tg-c3ow">구성품</td>
    <td class="tg-0pky" width='300' height='200'>
  	  	<c:if test="${ name eq '비빔밥'}">
			밥,비빔고추장,참기름,콩나물,고사리,무나물
    	</c:if>
    	<c:if test="${ name eq '부대찌개'}">
    		육수, 라면사리,치즈,햄,콩나물,떡,마카로니 양념장
    	</c:if>
    	<c:if test="${ name eq '불고기'}">
    		당면,양념,팽이버섯,양파,파,소고기
    	</c:if>
    	<c:if test="${ name eq '떡볶이'}">
    		떡,어묵,양파,파,양념,육수,
    	</c:if>
    	<c:if test="${ name eq '된장찌개'}">
    		육수, 된장, 두부, 애호박, 감자, 양파,
    	</c:if>
    	<c:if test="${ name eq '김치전'}">
    		부침가루, 김치, 양파, 고추
    	</c:if>
    	<c:if test="${ name eq '김치찌개'}">
    		김치, 육수, 두부, 돼지고기, 파, 양파
    	</c:if>
    	<c:if test="${ name eq '순두부찌개'}">
    		육수, 양념장, 순두부, 양파, 파
    	</c:if>
    	<c:if test="${ name eq '삼계탕'}">
    		한방팩, 닭, 인삼, 찹쌀, 대추, 마늘
    	</c:if>
    	<c:if test="${ name eq '알리오 올리오'}">
    		마늘, 엑스트라 버진 올리브 오일, 면, 후추, 치킨스톡, 파슬리가루, 베이컨
    	</c:if>
    	<c:if test="${ name eq '아라비아따 파스타'}">
    		토마토 페이스트, 페페로치노, 면, 베이컨
    	</c:if>
    	<c:if test="${ name eq '까르보나라 파스타'}">
    		생크림, 우유, 베이컨, 양파, 후추, 면
    	</c:if>
    	<c:if test="${ name eq '감자튀김 1KG'}">
    		감자튀김 1KG 1봉
    	</c:if>
    	<c:if test="${ name eq '피자'}">
    		밀가루, 토마토 페이스, 모짜렐라 치즈, 올리브, 베이컨, 옥수수
    	</c:if>
    	<c:if test="${ name eq '폭립'}">
    		립, 양념장, 양송이 버섯, 데리야키 소스
    	</c:if>
    	<c:if test="${ name eq '라자냐'}">
    		화이트 소스, 사워 크림, 토마토 페이스트, 라자냐면, 모짜렐라 치즈, 소고기 다짐육
    	</c:if>
    	<c:if test="${ name eq '리코타 샐러드'}">
    		양상추, 케일, 방울 토마토, 블랙올리브, 발사믹글레이즈
    	</c:if>
    	<c:if test="${ name eq '스테이크'}">
    		소고기, 아스파라거스, 시즈닝, 양송이 버섯, 스테아크 소스
    	</c:if>
    	<c:if test="${ name eq '기스면'}">
    		육수, 면, 대파, 생강, 육수, 면
    	</c:if>
    	<c:if test="${ name eq '라조기'}">
    		마늘, 대파, 생강, 피망, 죽순, 고추기름, 굴소스, 닭다리살, 물녹말
    	</c:if>
    	<c:if test="${ name eq '마파두부'}">
    		소스, 두부, 새우, 파프리카, 양파, 마늘, 고추 기름
    	</c:if>
    	<c:if test="${ name eq '양장피'}">
    		양장피, 새우, 오징어, 돼지고기, 잡채용, 당근, 파프리카, 양파,오이, 표고버섯, 게맛살, 마늘, 소스
    	</c:if>
    	<c:if test="${ name eq '유산슬'}">
    		돼지고기, 양파, 표고버섯, 죽순, 새우, 팽이버섯, 마늘, 대파, 소스, 육수, 생강, 전분
    	</c:if>
    	<c:if test="${ name eq '짜장면'}">
			대파, 다진생강, 짜장소스, 양파, 호박, 다진마늘, 전분, 생면
    	</c:if>
    	<c:if test="${ name eq '짬뽕'}">
    		대파, 홍고추, 청양고추, 양파, 애호박, 육수, 양념
    	</c:if>
    	<c:if test="${ name eq '탕수육'}">
    		돼지고기, 탕수육 소스, 튀김가루, 전분가루, 파프리카, 후르츠 칵테일, 양파
    	</c:if>
    	<c:if test="${ name eq '팔보채'}">
    		오징어, 새우, 브로콜리, 죽순, 대파, 양배추, 고추, 팔보채 소스
    	</c:if>
    	<c:if test="${ name eq '부타동'}">
    		돼지고기, 양파, 대파, 쪽파, 간장 소스
    	</c:if>
    	<c:if test="${ name eq '규카츠'}">
    		소고기, 양배추, 밀가루, 빵가루, 양배추소스, 규카츠 소스
    	</c:if>
    	<c:if test="${ name eq '밀푀유 나베'}">
    		블고기용 소고기, 배추, 깻잎, 버섯, 숙주, 육수, 찍어먹는 소스
    	</c:if>
    	<c:if test="${ name eq '오야코동'}">
    		닭다리살, 가쓰오부시, 쪽파, 밥, 대파, 비빔 소스, 양파
    	</c:if>
    	<c:if test="${ name eq '돈까스'}">
    		돼지고기 등심, 빵가루, 돈까스 소스
    	</c:if>
    	<c:if test="${ name eq '라멘'}">
    		육수, 면, 차슈
    	</c:if>
    	<c:if test="${ name eq '메밀 소바'}">
    		간무, 쪽파, 고추냉이, 김가루, 오이, 동치미무, 간장육수, 메밀면
    	</c:if>
    	<c:if test="${ name eq '스키야키'}">
    		소고기 샤브샤브용, 두부, 배추, 곤약, 새송이버섯, 느타리버섯, 표고버섯, 대파, 쑥갓, 육수, 소스
    	</c:if>
    	<c:if test="${ name eq '우동'}">
    		우동 육수, 유부, 어묵, 면, 가쓰오부시
    	</c:if>

    </td>
  </tr>
</thead>
</table>
<div align="center">


</div>

</div>
	 <input type='submit' value='장바구니 담기' /><input type="submit" value="찜하기" formaction="WishProcess.do">
 </form>

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