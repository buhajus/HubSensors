<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Sensor details</title>
		<jsp:include page="header.jsp"/>
		<jsp:include page="menu.jsp"/>

	</head>
	<body class="container">
		<div class="table-responsive">
			<caption>Sensor details</caption>
			<table class="table table-striped">
				<tr>
					<td><b>Id:</b></td>
					<td>${sensor.id}</td>

				</tr>
				<tr>
					<td><b>Sensor name:</b></td>
					<td>${sensor.sensorName}</td>
				</tr>
				<tr>
					<td><b>Sensor model:</b></td>
					<td>${sensor.sensorModel}</td>
				</tr>
				<tr>
                	<td><b>GPIO:</b></td>
                	<td>${sensor.gpio}</td>
                </tr>


			</table>
		</div>
		<br>

		<a type="button" href="/sensors" class="button">Atgal</a>
	</body>
	<jsp:include page="footer.jsp"/>
</html>
