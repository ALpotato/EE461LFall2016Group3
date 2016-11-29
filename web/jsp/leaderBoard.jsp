<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Welcome To R2Beat!</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
</head>
<body>
<div class="container" id="LB_container">
    <div id="content">
        <c:choose>
            <c:when test="${empty leaderBoard}">
                <h1>No Scores on LeaderBoard yet</h1>
            </c:when>
            <c:otherwise>
                <c:forEach var="i" begin="0" end="${fn:length(songs) - 1}">
                    <h4>High Scores for ${songs[i]}:</h4>
                    <c:choose>
                        <c:when test="${empty leaderBoard[i].scoreList}">
                            <h5>No Scores for Song ${songs[i]} yet</h5>
                        </c:when>
                        <c:when test="${5 gt fn:length(leaderBoard[i].scoreList)}">
                            <c:forEach items="${leaderBoard[i].scoreList}" var="score">
                                <h4>Player: ${score.userId}</h4>
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
                            <c:forEach var="j" begin="0" end="4">
                                <h4>Player: ${leaderBoard[i].scoreList[j].userId}</h4>
                                <div id="Scores">
                                    <p>${leaderBoard[i].scoreList[j].score}</p>
                                    <p>${leaderBoard[i].scoreList[j].perfect}</p>
                                    <p>${leaderBoard[i].scoreList[j].great}</p>
                                    <p>${leaderBoard[i].scoreList[j].good}</p>
                                    <p>${leaderBoard[i].scoreList[j].bad}</p>
                                    <p>${leaderBoard[i].scoreList[j].miss}</p>
                                    <p>${leaderBoard[i].scoreList[j].maxCombo}</p>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <p>*** only top 5 for each song are shown here ***</p>
            </c:otherwise>
        </c:choose>
    </div>
    <div id="return_main">
        <a href="/">Return</a>
    </div>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
</html>
