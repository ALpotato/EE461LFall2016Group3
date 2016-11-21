<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Welcome to R2Beat!</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
</head>
<body>
<div class="container" id="container">
    <c:if test="${not empty Songs}">
        <%-- TODO: make this prettier...so ugly now....--%>
        <div id="song_select">
        <label>Select which song you would like to play:</label>
        <form action="${pageContext.request.contextPath}/ui" method="post">
            <c:forEach items="${Songs}" var="song">
                <input type="submit" name="song_name" value="${song.songName}">
            </c:forEach>
        </form>
    </div>
</div>
</c:if>
<c:if test="${not empty songName}">
    <div id="start_inner">Start!</div>
    <div id="status"></div>
    <div id="score">0</div>
    <div id="life">100</div>

    <div id="gameRestart">Restart</div>

    <div class="ruler01"></div>
    <div class="ruler02"></div>

    <div class="keyContainer">
        <p id="p01"></p>
        <p id="p02"></p>
        <p id="p03"></p>
        <p id="p04"></p>
    </div>
</div>
<!-- variables passed from UIServlet -->
<script>
    var noteFile = ${noteFile}; //noteFile required in common.js, JSON format
    var left = ${left};
    var down = ${down};
    var up = ${up};
    var right = ${right};
    var songIndex = ${songIndex};
</script>
<!-- system sound effects -->
<audio id="music" preload="auto">
    <source src="${pageContext.request.contextPath}/music/${songName}" type="audio/ogg">
</audio>
</c:if>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
</html>

