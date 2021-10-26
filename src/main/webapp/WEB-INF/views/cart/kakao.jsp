<%@page import="java.io.PrintWriter"%>
<%@page import="com.exam.dao.UserOrderDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));    
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String address = request.getParameter("address");
    String pass = request.getParameter("pass");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>

    <script>
    $(function(){
        var IMP = window.IMP; // 생략가능
        IMP.init('import'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        var msg;
        
        IMP.request_pay({
            pg : 'kakaopay',
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
            name : '냠냠이 밀키트',
            amount : '<%=totalPrice%>',
            buyer_email : '<%=email%>',
            buyer_name : '<%=name%>',
            buyer_tel : '<%=phone%>',
            buyer_addr : '<%=address%>',
            buyer_postcode : '123-456',
//             m_redirect_url : 'http://localhost:8080/orderListPro.do'
        }, function(rsp) {
            if ( rsp.success ) {
//                  var msg = '결제가 완료되었습니다.';
//                  msg += '\n고유ID : ' + rsp.imp_uid;
//                  msg += '\n상점 거래ID : ' + rsp.merchant_uid;
//                  msg += '\결제 금액 : ' + rsp.paid_amount;
//                  msg += '카드 승인번호 : ' + rsp.apply_num;
       
                 alert("결제가 완료되었습니다.");
                 location.href="orderListPro.do?totalPrice=<%=totalPrice%> + &name=<%=name%> + &email=<%=email %> +&phone=<%=phone %> + &address=<%=address %> + &pass=<%=pass %>";
                    } 
            else {
                msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                //실패시 이동할 페이지
                location.href="cartView.do";
                alert(msg);
            }
        });
        
    });
    </script>
 
</body>
</html>
