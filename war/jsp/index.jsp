<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Welcome To R2Beat!</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
</head>

<body>
<div class="container" id="index_container">
    <div id="start">
        <div class="login">
            <c:choose>
                <c:when test="${not empty logOutUrl}">
                    <a href="${logOutUrl}">Log out</a>
                </c:when>
                <c:otherwise>
                    <a href="${logInUrl}">Log in</a>
                </c:otherwise>
            </c:choose>
        </div>
        <div id="head_name">R2Beat!</div>
        <div class="mainMenuButtons"><a href="${pageContext.request.contextPath}/ui">Start</a></div>
        <div class="mainMenuButtons"><a href="${pageContext.request.contextPath}/Setting">Settings</a></div>
        <div class="mainMenuButtons"><a href="${pageContext.request.contextPath}/leaderBoard">Leader Board</a></div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</html>