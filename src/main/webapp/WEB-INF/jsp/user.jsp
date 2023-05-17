<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>User details</title>
		<jsp:include page="header.jsp"/>
		<jsp:include page="menu.jsp"/>
	</head>
	<body class="container">
		<div class="table-responsive">
			<caption>Sensor details</caption>
			<table class="table table-striped">
				<tr>
					<td><b>Id:</b></td>
					<td>${user.id}</td>

				</tr>
				<tr>
					<td><b>Sensor name:</b></td>
					<td>${user.username}</td>
				</tr>
				<tr>
					<td><b>User role :</b></td>
					<td>${user.authorities}</td>
				</tr>

			</table>
		</div>
		<br>

		<a type="button" href="/users" class="button">Atgal</a>
	</body>
	<jsp:include page="footer.jsp"/>
</html>
