<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@page session="false"%> --%>
<!DOCTYPE html>
<!-- http://www.cssflow.com/snippets/login-form/demo/html -->
<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="stylesheet/normalize.css">
<link rel="stylesheet" href="stylesheet/style.css">
</head>
<body>
	<section class="loginform cf">
		<form name="login" action="<%=response.encodeURL("login") %>" method="POST" accept-charset="utf-8">
			<ul>
				<li><label for="usermail">Username</label> <input type="text"
					name="username" id="username" placeholder="yourname@email.com" required>
				</li>
				<li><label for="password">Password</label> <input
					type="password" name="password" placeholder="password" required></li>
				<li><input type="submit" value="Login"></li>
			</ul>
		</form>
	</section>
	<script type="text/javascript">
		document.getElementById("username").focus();
	</script>
</body>
</html>