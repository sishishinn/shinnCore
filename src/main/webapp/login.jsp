<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<!-- Custom styles for this template -->
<link href="${ctx}/static/bootstrap/3.3.7/other/signin.css" rel="stylesheet">
</head>
<body>
	<div class="container">
			
		<form class="form-signin" action="login" method="post">
			<h2 class="form-signin-heading">Please sign in </h2>
			<input type="text" id="username" name="username" value="${username }" class="form-control"  placeholder="Username" autocomplete="off" required autofocus>
			<input type="password" id="password" name="password" class="form-control" placeholder="Password" required readonly onfocus="this.removeAttribute('readonly');" style="background-color: white">
			<label style="color: red">${message }</label>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		</form>
	</div>
</body>
</html>