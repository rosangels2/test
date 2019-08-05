<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<style>
.login-box{
	border : 1px solid black;
	width : 400px;
	height : 200px;
	margin : 100px auto 0;
	padding : 20px;
}
</style>
</head>
<body>
	<div style="height: 600px;">
	<c:if test="${user eq null}">
		<div class="login-box">
			<form method="post" action="<%=request.getContextPath()%>/">
				<div class="form-group">
					<input type="text" class="form-control" id="id" placeholder="아이디" name="id">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="pw" placeholder="비밀번호" name="pw">
				</div>
				<button type="submit" class="btn btn-outline-success col-12">로그인</button>
			</form>
		</div>
	</c:if>
	</div>
</body>
</html>