<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Spausdintuvų sąrašas</title>
    <jsp:include page="header.jsp"/>
    <jsp:include page="menu.jsp"/>
</head>
<body class="container">
    <div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th>Pirmas skaičius</th>
                <th>Ženklas</th>
                <th>Antras skaičius</th>
                <th>Rezultatas</th>
                <th>Veiksmas</th>
            </tr>

            <!-- iteruoja per visą skaičių sarašą -->
            <c:forEach var="skaicius" items="${sarasas}">

                <!-- konstruoja įrašo atnaujinimo adresą su skaičiaus id -->
                <c:url var="update" value="/update">
                    <c:param name="id" value="${skaicius.id}"/>
                </c:url>

                <!-- konstruoja įrašo trynimo adresą su skaičiaus id -->
                <c:url var="trinti" value="/trinti">
                    <c:param name="id" value="${skaicius.id}"/>
                </c:url>

                <!-- konstruoja įrašo peržiūros adresą su skaičiaus id -->
                <c:url var="rodyti" value="/rodyti">
                    <c:param name="id" value="${skaicius.id}"/>
                </c:url>

                <tr>
                    <td>${skaicius.sk1}</td>
                    <td>${skaicius.zenklas}</td>
                    <td>${skaicius.sk2}</td>
                    <td>${skaicius.rezult}</td>

                    <td>
                        <!-- atvaizduoti atnaujinimo adresą --> <a href="${atnaujinti}">Keisti</a>
                        | <a href="${trinti}"
                             onclick="if (!(confirm('Ar tikrai norite ištrinti šį įrašą?'))) return false">Trinti</a>
                        | <!-- atvaizduoti rodymo adresą --> <a href="${rodyti}">Rodyti</a>
                    </td>

                </tr>

            </c:forEach>

        </table>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
