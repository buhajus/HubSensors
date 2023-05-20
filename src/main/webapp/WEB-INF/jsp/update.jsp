<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<title>Edit sensor</title>
		<jsp:include page="header.jsp"/>
		<jsp:include page="menu.jsp"/>
		  <style>
                    .error{color:red}
                </style>
	</head>
	<body class="container">
		<form:form name="sensor" action="/update-sensor" method="post">
			<!-- id būtina pateikti formoje, kitaip į back-end nueis null. Todėl darome hidden, kad neredaguotų -->
			<input type="hidden"	name="id"  			value="${sensor.id}"><p>
			Sensor name:<br>
			<input type="text"   	name="sensorName"  		value="${sensor.sensorName}"><p>
			Sensor Model:<br>
			<input type="text" 		name="sensorModel"  	value="${sensor.sensorModel}"><p>
			GPIO of raspberry:<br>
            <input type="number" 		name="gpio"  	value="${sensor.gpio}"><p>
                 <form:errors path="gpio" cssClass="error"/></span>
		    <input type="submit" value="Update" class="btn btn-info">

		</form:form>
	</body>
	<jsp:include page="footer.jsp"/>
</html>
