<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!-- puslapio turinio keliui nustatyti -->
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<jsp:include page="header.jsp"/>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">



        <security:authorize access="hasAuthority('admin')">
            <ul class="nav navbar-nav">
             <li class="navbar-brand"><a href="/">Sensors history</a></li>
              <li class="navbar-brand"><a href="/pool_data">Pool data</a></li>
                <li class="navbar-brand"><a href="/sensors">Sensors</a></li>
                <li class="navbar-brand"><a href="/gpio_config">Raspberry GPIO configuration</a></li>
                <li class="navbar-brand"><a href="/users">Users</a></li>
            </ul>
        </security:authorize>
 </div>
        <ul class="nav navbar-nav navbar-right">
                <c:if test="${pageContext.request.userPrincipal.name != null}"> <!-- vartotojo vardo gavimas iš užklausos -->
                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <!--
                            A Cross-site Request Forgery (CSRF) arba vieno paspaudimo ataka (one – click – attack),
                            yra paplitusi saugumo problema, kai iš aukos naršyklės jam nepastebint yra
                            siunčiamos užklausos į svetainę prie kurios jis prisijungęs.
                            Praktikoje dažnai išnaudojamas slaptažodžio keitimo funkcionalumas.
                            Pagalvokite, jog dažnai visų naudotojų, nepriklausomai nuo jo rolės,
                            slaptažodžio keitimo parametrai yra tokie patys.
                            Dėl šios priežasties piktavaliui pakankamai nesudėtinga padirbti užklausą,
                            o jei naujo slaptažodžio nustatymui nereikia įvesti senojo,
                            piktavalis nesunkiai gali perimti aukos paskyrą.
                            Web aplikacija naudotojui turėtų išduoti papildomą parametrą, kuris nebūtų nuspėjamas piktavaliui.
                            Paprastai naudotojui yrai išduodamas unikalus CSRF žetonas (token),
                            kuris turi būti validuojamas back-end’e su kiekviena duomenis keičiančia užklausa.
                        -->
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <a onclick="document.forms['logoutForm'].submit()">Atsijungti, ${pageContext.request.userPrincipal.name}</a>
                </c:if>
        </ul>
    </div>
</nav>
