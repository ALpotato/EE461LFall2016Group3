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
<div class="container" id="ui_container">
    <c:if test="${not empty songs}">
        <div id="song_select">
        <label>Select which song you would like to play:</label>
        <form action="${pageContext.request.contextPath}/ui" method="post">
            <c:forEach items="${songs}" var="song">
                <input class="songs" id="song${song.index}" type="submit" name="song_name" value="${song.songName}">
                <br>
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
    <div class="inGameButtons" id="back_to_main"><a href="${pageContext.request.contextPath}/">Back</a></div>
    <div class="inGameButtons" id="game_restart">Restart</div>

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
    var speed = ${speed};
</script>
<!-- music selected by player -->
<audio id="music" preload="auto">
    <source src="${pageContext.request.contextPath}/music/${songName}.mp3" type="audio/mpeg">
</audio>
</c:if>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
</html>

