<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String id = (String) session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>


<link rel = "stylesheet" type="text/css" href="testStyle.css">
<meta charset="UTF-8">
<title>ShopMain JSP</title>
<script type="text/javascript">
function fnCart(name, price) {
	//alert("Name : " + name + "\nPrice : " + price);
	if(confirm("장바구니에 담으시겠습니까?")) {
		location.href = "CartProcess.do?name=" + name + "&price=" + price;
	}
}

function fnView() {
	if(confirm("장바구니를 보시겠습니까?")){
		location.href = "cartView.do";
	}
}
</script>
</head>
<body>

	   <header class="header-area">

        <!-- Logo Area -->
        <div class="logo-area" align="center">
            <a href="index.do"><img src="img/core-img/logo.png" alt=""></a>
        </div>

    	</header>

	<div align="center">
		<h3>[Chinese Food!]</h3>
		<table border="1">
			<tr align="right">
				<td colspan="3"><input type="button" value="장바구니 보기"
					onclick="fnView()" /></td>
			</tr>


			<tr align='center'>


				<td>
					<form action="CartProcess.do" method="post" name ="frm" align = 'center'>
					<table>
						<tr align='center'>
							<td><img src='img/food/china/기스면.jpg' width='150'
								height='150' /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' class="apple1" name="name" value='기스면' readonly style="border:none" /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' name="price" value='5500' readonly style="border:none;width:30px"/>원
							<td><input type='hidden' name="pagename" value='3' />
							</td>
						</tr>

						<tr align='center'>
							<td><input type='submit' value='장바구니 담기' /><input type="submit" value="찜하기" formaction="WishProcess.do"><input type="submit" value="상세보기" formaction="detailProcess.do"></td>
						</tr>

					</table>
					</form>
				</td>


				<td>
					<form action="CartProcess.do" method="post" name ="frm" align = 'center'>
					<table>
						<tr align='center'>
							<td><img src='img/food/china/라조기.jpg' width='150'
								height='150' /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' class="apple1" name="name" value='라조기' readonly style="border:none" /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' name="price" value='9000' readonly style="border:none;width:30px"/>원
							<td><input type='hidden' name="pagename" value='3' />
							</td>
						</tr>

						<tr align='center'>
							<td><input type='submit' value='장바구니 담기' /><input type="submit" value="찜하기" formaction="WishProcess.do"><input type="submit" value="상세보기" formaction="detailProcess.do"></td>
						</tr>

					</table>
					</form>

				</td>

				<td>
					<form action="CartProcess.do" method="post" name ="frm" align = 'center'>
					<table>
						<tr align='center'>
							<td><img src='img/food/china/마파두부.jpg' width='150'
								height='150' /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' class="apple1" name="name" value='마파두부' readonly style="border:none" /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' name="price" value='5500' readonly style="border:none;width:30px"/>원
							<td><input type='hidden' name="pagename" value='3' />
							</td>
						</tr>

						<tr align='center'>
							<td><input type='submit' value='장바구니 담기' /><input type="submit" value="찜하기" formaction="WishProcess.do"><input type="submit" value="상세보기" formaction="detailProcess.do"></td>
						</tr>

					</table>
					</form>
				</td>
			</tr>



			<tr align='center'>

				<td>
					<form action="CartProcess.do" method="post" name ="frm" align = 'center'>
					<table>
						<tr align='center'>
							<td><img src='img/food/china/양장피.jpg' width='150'
								height='150' /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' class="apple1" name="name" value='양장피' readonly style="border:none" /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' name="price" value='6000' readonly style="border:none;width:30px"/>원
							<td><input type='hidden' name="pagename" value='3' />
							</td>
						</tr>

						<tr align='center'>
							<td><input type='submit' value='장바구니 담기' /><input type="submit" value="찜하기" formaction="WishProcess.do"><input type="submit" value="상세보기" formaction="detailProcess.do"></td>
						</tr>
					</table>
					</form>
				</td>


				<td>
					<form action="CartProcess.do" method="post" name ="frm" align = 'center'>
					<table>
						<tr align='center'>
							<td><img src='img/food/china/유산슬.jpg' width='150'
								height='150' /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' class="apple1" name="name" value='유산슬' readonly style="border:none" /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' name="price" value='7000' readonly style="border:none;width:30px"/>원
							<td><input type='hidden' name="pagename" value='3' />
							</td>
						</tr>

						<tr align='center'>
							<td><input type='submit' value='장바구니 담기' /><input type="submit" value="찜하기" formaction="WishProcess.do"><input type="submit" value="상세보기" formaction="detailProcess.do"></td>
						</tr>

					</table>
					</form>

				</td>


				<td>
					<form action="CartProcess.do" method="post" name ="frm" align = 'center'>
					<table>
						<tr align='center'>
							<td><img src='img/food/china/짜장면.jpg' width='150'
								height='150' /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' class="apple1" name="name" value='짜장면' readonly style="border:none" /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' name="price" value='4500' readonly style="border:none;width:30px"/>원
							<td><input type='hidden' name="pagename" value='3' />
							</td>
						</tr>

						<tr align='center'>
							<td><input type='submit' value='장바구니 담기' /><input type="submit" value="찜하기" formaction="WishProcess.do"><input type="submit" value="상세보기" formaction="detailProcess.do"></td>
						</tr>
					</table>
					</form>

				</td>


			</tr>

			<tr align='center'>

				<td>
					<form action="CartProcess.do" method="post" name ="frm" align = 'center'>
					<table>
						<tr align='center'>
							<td><img src='img/food/china/짬뽕.jpg' width='150'
								height='150' /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' class="apple1" name="name" value='짬뽕' readonly style="border:none" /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' name="price" value='5500' readonly style="border:none;width:30px"/>원
							<td><input type='hidden' name="pagename" value='3' />
							</td>
						</tr>

						<tr align='center'>
							<td><input type='submit' value='장바구니 담기' /><input type="submit" value="찜하기" formaction="WishProcess.do"><input type="submit" value="상세보기" formaction="detailProcess.do"></td>
						</tr>

					</table>
					</form>
				</td>


				<td>
					<form action="CartProcess.do" method="post" name ="frm" align = 'center'>
					<table>
						<tr align='center'>
							<td><img src='img/food/china/탕수육.jpg' width='150'
								height='150' /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' class="apple1" name="name" value='탕수육' readonly style="border:none" /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' name="price" value='7500' readonly style="border:none;width:30px"/>원
							<td><input type='hidden' name="pagename" value='3' />
							</td>
						</tr>

						<tr align='center'>
							<td><input type='submit' value='장바구니 담기' /><input type="submit" value="찜하기" formaction="WishProcess.do"><input type="submit" value="상세보기" formaction="detailProcess.do"></td>
						</tr>

					</table>
					</form>
				</td>

				<td>
					<form action="CartProcess.do" method="post" name ="frm" align = 'center'>
					<table>
						<tr align='center'>
							<td><img src='img/food/china/팔보채.jpg' width='150'
								height='150' /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' class="apple1" name="name" value='팔보채' readonly style="border:none" /></td>
						</tr>
						<tr align='center'>
							<td><input type='text' name="price" value='8000' readonly style="border:none;width:30px"/>원
							<td><input type='hidden' name="pagename" value='3' />
							</td>
						</tr>
						<tr align='center'>
							<td><input type='submit' value='장바구니 담기' /><input type="submit" value="찜하기" formaction="WishProcess.do"><input type="submit" value="상세보기" formaction="detailProcess.do"></td>
						</tr>
					</table>
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>