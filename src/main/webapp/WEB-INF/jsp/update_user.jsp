<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<title>Edit user</title>
		<jsp:include page="header.jsp"/>
		<jsp:include page="menu.jsp"/>
	</head>
	<body class="container">
		<form:form name="user" action="/update-user" method="post">
			<!-- id būtina pateikti formoje, kitaip į back-end nueis null. Todėl darome hidden, kad neredaguotų -->
			<input type="hidden"	name="id"  			value="${user.id}"><p>
			User name:<br>
			<input type="text"   	name="username"  		value="${user.username}"><p>
			User role:<br>
			<input type="text" 		name="authorities"  	value="${user.authorities}"><p>
			<br>

			<input type="submit" value="Update">
		</form:form>
	</body>
	<jsp:include page="footer.jsp"/>
</html>
