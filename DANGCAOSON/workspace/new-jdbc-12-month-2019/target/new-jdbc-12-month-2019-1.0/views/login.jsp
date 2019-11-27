<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng Nhập</title>
</head>
<body>
<style>
	body#LoginForm{ background-image:url("https://hdwallsource.com/img/2014/9/blur-26347-27038-hd-wallpapers.jpg");
		background-repeat:no-repeat;background-position:center;background-size:cover;padding:170px;}
	.form-heading { color:#fff; font-size:23px;}
	.panel h2{ color:#444444; font-size:18px; margin:0 0 8px 0;}
	.panel p { color:#777777; font-size:14px; margin-bottom:30px; line-height:24px;}
	.login-form .form-control {background: #f7f7f7 none repeat scroll 0 0;border: 1px solid #d4d4d4;
		border-radius: 4px;font-size: 14px;height: 50px;line-height: 50px;
	}
	.main-div {
		background: #ffffff none repeat scroll 0 0;border-radius: 2px;margin: 10px auto 30px;
		max-width: 38%;padding: 50px 70px 70px 71px;
	}
	.login-form .form-group {
		margin-bottom:10px;
	}
	.login-form{ text-align:center;}
	.forgot a {color: #777777;font-size: 14px;text-decoration: underline;
	}
	.login-form  .btn.btn-primary {
		background: #f0ad4e none repeat scroll 0 0;
		border-color: #f0ad4e;}
</style>
	<div class="container">
		<div class="login-form">
			<div class="main-div">
				<c:if test="${not empty messager}">
					<div class="alert alert-${alert}">
							${message}
					</div>
				</c:if>
				<form href='<c:url value="/dang-nhap"/>' id="formLogin" method="post">
					<div class="form-group">
						<input type="text" class="form-control" id="userName" name="userName"
							placeholder="Tài khoản">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="password" name="password"
							placeholder="Mật khẩu">
					</div>
					<input type="hidden" value="login" name="action"/>
					<button type="submit" class="btn btn-primary">Login</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>