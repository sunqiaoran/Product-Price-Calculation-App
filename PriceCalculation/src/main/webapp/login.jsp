<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

	<h2>ログイン</h2>

	<form action="login-servlet" method="post">
		ユーザID： <input type="text" name="userId" required><br>
		パスワード： <input type="password" name="password" ><br><br>
		<input type="submit" value="ログイン">
	</form>

	<%@include file = "msg.jsp"%>

</body>
</html>
