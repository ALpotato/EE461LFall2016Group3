<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Welcome to R2Beat!</title>
    <link type="text/css" rel="stylesheet" href="../WEB-INF/css/common.css" />
</head>
<body>
<!-- system sound effects -->
<audio id="music" preload="auto">
    <source src="http://www.8king.cn/myproject-multi/music/bg.m4a" type="audio/ogg">
</audio>
<audio id="musicWelcome" preload="auto" autoplay="autoplay" loop="loop">
    <source src="http://www.8king.cn/myproject-multi/music/welcome.mp3" type="audio/ogg">
</audio>
<audio id="musicWin" preload="auto">
    <source src="http://www.8king.cn/myproject-multi/music/win.mp3" type="audio/ogg">
</audio>
<audio id="musicPause" preload="auto">
    <source src="http://www.8king.cn/myproject-multi/music/pause.wav" type="audio/ogg">
</audio>
<!-- keyboard sound effects -->
<audio id="musicBtn01" preload="auto">
    <source src="http://www.8king.cn/myproject-multi/music/1.wav" type="audio/ogg">
</audio>
<audio id="musicBtn02" preload="auto">
    <source src="http://www.8king.cn/myproject-multi/music/2.wav" type="audio/ogg">
</audio>
<audio id="musicBtn03" preload="auto">
    <source src="http://www.8king.cn/myproject-multi/music/3.wav" type="audio/ogg">
</audio>
<audio id="musicBtn04" preload="auto">
    <source src="http://www.8king.cn/myproject-multi/music/4.wav" type="audio/ogg">
</audio>
<!-- special effect sounds -->
<audio id="music4" preload="auto">
    <source src="http://www.8king.cn/myproject-multi/music/4.mp3" type="audio/ogg">
</audio>
<audio id="musictime" preload="auto">
    <source src="http://www.8king.cn/myproject-multi/music/time.mp3" type="audio/ogg">
</audio>
<audio id="unbelievable" preload="auto">
    <source src="http://www.8king.cn/myproject-multi/music/unbelieve.mp3" type="audio/ogg">
</audio>

<div class="container" id="container">
    <div id="start">
        <div class="head_name">R2Beat</div>
        <div class="start_inner">Start</div>
    </div>
    <div class="status" id="status"></div>
    <div class="score" id="score">0</div>
    <div class="time" id="time">120</div>

    <!-- <div class="gamePause" id="gamePause">Pause</div> -->
    <div class="gameRestart" id="gameRestart">Restart</div>

    <div class="ruler01"></div>
    <div class="ruler02"></div>

    <div class="keyContainer">
        <p id="click1">Q</p>
        <p id="click2">W</p>
        <p id="click3">O</p>
        <p id="click4">P</p>
    </div>
</div>
</body>
<script src="../WEB-INF/js/logic.js"></script>
</html>

