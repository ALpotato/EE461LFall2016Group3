<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LeaderBoard</title>
</head>
<body>
<div id="content">
    <c:choose>
        <c:when test="${empty leaderBoard}">
            <h1>No Scores on LeaderBoard yet</h1>
        </c:when>
        <c:otherwise>
            <c:forEach var="i" begin="0", end="${fn:length(songs)}">
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
</body>
</html>
