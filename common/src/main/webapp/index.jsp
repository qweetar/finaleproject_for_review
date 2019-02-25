<!--

<+++++%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<+++++%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
</head>
<body>
<button>
	<++++%=request.getAttribute("testList")%>
</button>
<a href="FrontController?command=test_command">View List </a>

<+++++c:forEach items="$++++++{request.getAttributes(\"testList\")}" var="user">
	<tr>
	<td>
	<p>$++++{user.userId}</p>
	</td>
	<td>
	<p>$+++++++{user.userName}</p>
	</td>
	<td>
	<p>$++++++{user.surname}</p>
	</td>
	<td>
	<p>$++++++++{user.mobilePhone}</p>
	</td>
	</tr>
<++++++++++++/c:forEach>

	<td>
		<form action="FrontController" method="post">
			<input type="hidden" name="userId" value="$++++++++{element.userId}">
			<input type="hidden" name="command" value="user_block">
			<input class="btn btn-danger" type="submit" value="Block">
		</form>
	</td>
	<td>
		<form action="FrontController" method="put">
			<input type="hidden" name="userId" value="$++++++++={element.userId}">
			<input type="hidden" name="command" value="edit_block">
			<input class="btn btn-danger" type="submit" value="Block">
		</form>
	</td>


</body>
</html>
-->


<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Style-Type" content="text/css">
	<meta http-equiv="Content-Type" content="text/html">
	<meta charset="UTF-8">
	<title>Authorization</title>

	<link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<div align="center">
	<div>
		<form action="FrontController" id="form-page-registration">
			<fieldset class="account-info">
				<input type="hidden" name="command" value="authorization">
			<label>
				Username
				<input type="text" id="Login" name="login" size="20" maxlength="25" placeholder="Login">
			</label>
			<label>
				Password
				<input type="password" id="Password" name="password" size="20" maxlength="25" placeholder="Password">
			</label>
			</fieldset>
			<fieldset class="account-action">
				<input class="btn" type="submit" id="loginBtn" value="Login">
			</fieldset>
			<fieldset class="account-action">
				<input class="btn" type="submit" id="registerBtn" value="Register">
			</fieldset>
		</form>
	</div>
</div>
</body>
</html>

