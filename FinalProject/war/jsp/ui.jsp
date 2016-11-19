<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Welcome to R2Beat!</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" />
</head>
<body>
<!-- system sound effects -->
<script>
    var noteOffSet = JSON.parse(${noteOffSet});
    //var noteFile = JSON.parse(${noteFile});
</script>
<audio id="music" preload="auto">
    <source src="/music/${songName}.mp3" type="audio/ogg">
</audio>

<div class="container" id="container">
    <div class="keyContainer"></div>
    <div class="ruler01"></div>
    <div class="ruler02"></div>

    <div class="status" id="status"></div>
    <div class="score" id="score">0</div>
    <div class="life" id="life">120</div>
    <div class="gameRestart" id="gameRestart">Restart</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/logic.js"></script>
</html>

