<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sensors data list</title>
    <jsp:include page="header.jsp"/>
    <jsp:include page="menu.jsp"/>
</head>
<body class="container">
    <div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th>Sensor name</th>
                <th>Sensor location</th>
                <th>Status</th>
                <a href="${sortDESC}"><th>Sensor trigger time</th></a>
               <!-- <th>Action</th> -->
            </tr>

            <!-- iteruoja per visą skaičių sarašą -->
            <c:forEach var="sensor" items="${list.content}">

                <!-- konstruoja įrašo atnaujinimo adresą su skaičiaus id -->
                <c:url var="update" value="/update">
                    <c:param name="id" value="${sensor.id}"/>
                </c:url>

                <!-- konstruoja įrašo trynimo adresą su skaičiaus id -->
                <c:url var="delete" value="/delete">
                    <c:param name="id" value="${sensor.id}"/>
                </c:url>

                <!-- konstruoja įrašo peržiūros adresą su skaičiaus id -->
                <c:url var="show" value="/show">
                    <c:param name="id" value="${sensor.id}"/>
                </c:url>

                <tr>
                    <td>${sensor.sensorName}</td>
                    <td>${sensor.sensorLocation}</td>
                    <td>${sensor.status}</td>
                    <td>${sensor.date}</td>

                   <!--  <td>
                        <a href="${update}">Keisti</a>
                        | <a href="${delete}"
                             onclick="if (!(confirm('Ar tikrai norite ištrinti šį įrašą?'))) return false">Trinti</a>
                        |    <a href="${show}">Rodyti</a>
                    </td> -->

                </tr>

            </c:forEach>

        </table>
        <c:if test="${list.totalPages >= 0}">
          <ul class="pagination">
            <c:forEach  var="pageNumber" items="${numbers}">
                <li>
                  <a href="/list?page=${pageNumber+1}">${pageNumber + 1}</a>
                </li>
             </c:forEach>
          </ul>
       </c:if>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
