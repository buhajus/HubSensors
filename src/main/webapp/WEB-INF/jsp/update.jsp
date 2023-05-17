<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<title>Skaičiaus atnaujinimas</title>
		<jsp:include page="header.jsp"/>
		<jsp:include page="menu.jsp"/>
	</head>
	<body class="container">
		<form:form name="skaicius" action="/atnaujintiSkaiciu" method="post">
			<!-- id būtina pateikti formoje, kitaip į back-end nueis null. Todėl darome hidden, kad neredaguotų -->
			<input type="hidden"	name="id"  			value="${skaicius.id}"><p>
			Pirmas skaičius:<br>
			<input type="number"   	name="sk1"  		value="${skaicius.sk1}"><p>
			Ženklas:<br>
			<input type="text" 		name="zenklas"  	value="${skaicius.zenklas}"><p>
			Antras skaičius:<br>
			<input type="number"   	name="sk2" 			value="${skaicius.sk2}"><p>
			Rezultatas:<br>
			<input type="number" 	name="rezult"   	value="${skaicius.rezult}"><p>
			<input type="submit" value="Atnaujinti">
		</form:form>
	</body>
	<jsp:include page="footer.jsp"/>
</html>
