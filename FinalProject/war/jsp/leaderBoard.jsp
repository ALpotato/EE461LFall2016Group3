<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Welcome To R2Beat!</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/></head>
<body>
<div class="container" id="LB_container">
    <div id="content">
        <c:choose>
            <c:when test="${empty leaderBoard}">
                <h1>No Scores on LeaderBoard yet</h1>
            </c:when>
            <c:otherwise>
                <c:forEach var="i" begin="0" end="${fn:length(songs)}">
                    <h1>High Scores for ${songs[i]}:</h1>
                    <c:choose>
                        <c:when test="${5 gt fn:length(leaderBoard[i])}">
                            <c:forEach items="${leaderBoard[i]}" var="score">
                                <div id="Scores">
                                    <p>${score.score}</p>
                                    <p>${score.perfect}</p>
                                    <p>${score.great}</p>
                                    <p>${score.good}</p>
                                    <p>${score.bad}</p>
                                    <p>${score.miss}</p>
                                    <p>${score.maxCombo}</p>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="i" begin="0" end="4">
                                <div id="Scores">
                                    <p>${blogs[i].content}</p>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <p>*** only top 5 for each song are shown here ***</p>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
</html>
