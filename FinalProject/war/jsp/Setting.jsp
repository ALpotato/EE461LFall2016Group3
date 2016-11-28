<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome To R2Beat!</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
</head>
<body>
<%-- TODO: make this prettier...so ugly now....--%>
<div class="container" id="setting_container">
    <p>Choose your Key Settings</p>
    <p>***remember, you have to sign in to save the settings***</p>
    <form action="${pageContext.request.contextPath}/Setting" method="post">
        <label>
            <input list="left" name="left">
        </label>
        <datalist id="left">
            <option value="Q" selected>Q</option>
            <option value="A">A</option>
            <option value="D">D</option>
        </datalist>
        <br>
        <label>
            <input list="down" name="down">
        </label>
        <datalist id="down">
            <option value="W" selected>W</option>
            <option value="S">S</option>
            <option value="F">F</option>
        </datalist>
        <br>
        <label>
            <input list="up" name="up">
        </label>
        <datalist id="up">
            <option value="O" selected>O</option>
            <option value="W">W</option>
            <option value="J">J</option>
        </datalist>
        <br>
        <label>
            <input list="right" name="right">
        </label>
        <datalist id="right">
            <option value="P" selected>P</option>
            <option value="D">D</option>
            <option value="K">K</option>
        </datalist>
        <input type="submit" value="Save">
        <br>
        <input type="submit" name="cancel" value="Cancel">
    </form>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
</html>
