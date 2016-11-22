
window.onload = function () {
    var container = document.getElementById('container');
    var start = document.getElementById('start');
    var start_inner = document.getElementById('start_inner');
    var gameRestart = document.getElementById('gameRestart');
    var ul = container.getElementsByTagName('ul');
    var p = container.getElementsByTagName('p');
    var li = container.getElementsByTagName('li');
    var scoreTag = document.getElementById('score');
    var status = document.getElementById('status');
    var life = document.getElementById('life');
    var music = document.getElementById('music');

    var scoreChange = 0;
    var lifeChange = 100;
    var combo = 0;
    var maxCombo = 0;
    var miss = 0;
    var bad = 0;
    var good = 0;
    var great = 0;
    var perfect = 0;
    var ended = 1;
    var startTime = Date.now();
    var noteArray = [];
    var maxScore = noteFile.length * 2;

    // create the four uls that acts as columns for the four arrows moving up
    for (var i = 0; i < 4; i++) {
        var ulChange = document.createElement('ul');
        container.appendChild(ulChange);
    }
    start_inner.onclick = function() {
        if(start_inner.innerHTML == "Start!") {
            play();
        } else {
            start_inner.innerHTML = "loading...";
        }
    };

    // move arrows up
    function move() {
        for (var n = 0; n < noteArray.length; n++) {
            var time = Date.now();
            var t1 = time - startTime;
            if ((noteArray[n].time - t1) > 1000) {
                break;
            }
            if ((noteArray[n].offsetTop) < -100) {
                ul[noteArray[n].column].removeChild(ul[noteArray[n].column].children[0]);
                noteArray.splice(n, 1);
                status.innerHTML = "Miss";
                miss += 1;
                combo = 0;
                scoreChange -= 2;
                lifeChange -= 10;
                scoreTag.innerHTML = scoreChange;
                life.innerHTML = lifeChange;
                lifeCheck();
            }
            else {
                noteArray[n].style.top = 100 + noteArray[n].time - t1 + 'px';
            }
        }
    }

    function generate(column, time) {
        var liChange = document.createElement('li');
        liChange.style.top = time + 100 + 'px';
        liChange.style.background = arrow(column);
        liChange["column"] = column;
        liChange["time"] = time;
        ul[column].appendChild(liChange);
        noteArray[noteArray.length] = liChange;
    }

    function arrow(column) {
        switch (column) {
            case 0:
                return 'url(leftArrow.png)';
            case 1:
                return 'url(downArrow.png)';
            case 2:
                return 'url(upArrow.png)';
            case 3:
                return 'url(rightArrow.png)';
        }
    }

    // start game
    function play() {
        startTime = Date.now();
        ended = 0;
        start.style.display = "none";
        gameRestart.style.display = "initial";
        status.innerHTML = "";
        for (var n in noteFile) {
            var column = parseInt(noteFile[n].column) - 1;
            var time = parseInt(noteFile[n].time);
            generate(column, time);
        }
        setInterval(move, 10);
        music.play();
    }

    music.addEventListener("ended", function () {
        music.currentTime = 0;
        if (scoreChange == maxScore) {
            status.innerHTML = " Grade: AAA" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press R to restart";
        }
        else if (scoreChange >= maxScore * 0.99) {
            status.innerHTML = " Grade:  AA" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press R to restart";
        }
        else if (scoreChange >= maxScore * 0.97) {
            status.innerHTML = " Grade:  A+" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press R to restart";
        }
        else if (scoreChange >= maxScore * 0.92) {
            status.innerHTML = " Grade:  A" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press R to restart";
        }
        else if (scoreChange >= maxScore * 0.90) {
            status.innerHTML = " Grade:  A-" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press R to restart";
        }
        else if (scoreChange >= maxScore * 0.88) {
            status.innerHTML = " Grade:  B+" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press R to restart";
        }
        else if (scoreChange >= maxScore * 0.82) {
            status.innerHTML = " Grade:  B" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press R to restart";
        }
        else if (scoreChange >= maxScore * 0.80) {
            status.innerHTML = " Grade:  B-" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press R to restart";
        }
        else if (scoreChange >= maxScore * 0.78) {
            status.innerHTML = " Grade:  C+" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press R to restart";
        }
        else if (scoreChange >= maxScore * 0.72) {
            status.innerHTML = " Grade:  C" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press R to restart";
        }
        else if (scoreChange >= maxScore * 0.70) {
            status.innerHTML = " Grade:  C-" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press R to restart";
        }
        else if (scoreChange >= maxScore * 0.68) {
            status.innerHTML = " Grade:  D+" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press R to restart";
        }
        else if (scoreChange >= maxScore * 0.62) {
            status.innerHTML = " Grade:  D" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press R to restart";
        }
        else if (scoreChange >= maxScore * 0.60) {
            status.innerHTML = " Grade:  D-" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press R to restart";
        }
        else {
            status.innerHTML = "done";
        }
        EndGame();
        ended = 1;
        console.log("ended");
    });

    // end game
    function EndGame() {
        console.log(status.innerHTML.toString());
        music.pause();
        clearInterval(move);
        for (i = 0; i < ul.length; i++) {
            ul[i].innerHTML = '';
        }
        scoreChange = 0;
        //TODO: check if this ajax function work
        //posting the current score up to scoreboard
        $.ajax({
            type: "POST",
            url: '/leaderBoard',
            data: '{"score":' + scoreChange + ', "perfect":' + perfect + ', "great":' + great + ', "good":' + good + ', "bad":' + bad + ', "maxCombo":' + maxCombo + ', "songIndex":' + songIndex + '}',
            success: function(){
                alert("score information uploaded successfully");
            }
        });
    }

    // check remaining life
    function lifeCheck() {
        if (lifeChange <= 0) {
            life.innerHTML = "FAIL";
            status.innerHTML = " Score: F" + '<br />' + "Press R to restart";
            EndGame();
        }
    }

    // Core judgement functions
    function processKeyPress(n) {
        arrow = ul[n].children[0];
        var arrowL = getPos(arrow).left;
        var arrowR = getPos(arrow).left + arrow.offsetWidth;
        var arrowT = getPos(arrow).top;
        var receptorL = n * 100;
        var receptorR = n * 100 + 100;
        if (arrowL > receptorR || arrowR < receptorL) {
            return false;
        }
        if (arrowT >= 50 && arrowT <= 150) {
            status.innerHTML = "Perfect!";
            scoreChange += 2;
            perfect += 1;
            combo += 1;
            lifeChange += 2;
            if (lifeChange >= 200)
                lifeChange = 200;
            ul[n].removeChild(ul[n].children[0]);
            scoreTag.innerHTML = scoreChange;
        }
        else if (arrowT >= 0 && arrowT <= 200) {
            status.innerHTML = "Great!";
            scoreChange += 1;
            great += 1;
            combo += 1;
            lifeChange += 1;
            if (lifeChange >= 200)
                lifeChange = 200;
            ul[n].removeChild(ul[n].children[0]);
            scoreTag.innerHTML = scoreChange;
        }
        else if (arrowT >= -50 && arrowT <= 250) {
            status.innerHTML = "Good";
            good += 1;
            combo += 1;
            ul[n].removeChild(ul[n].children[0]);
        }
        else if (arrowT >= -100 && arrowT <= 300) {
            status.innerHTML = "Bad";
            bad += 1;
            combo = 0;
            scoreChange -= 1;
            lifeChange -= 5;
            lifeCheck();
            ul[n].removeChild(ul[n].children[0]);
            scoreTag.innerHTML = scoreChange;
        }
        life.innerHTML = lifeChange;
        if (combo > maxCombo) {
            maxCombo = combo;
        }
    }

    function reload() {
        document.location.reload();
        gameRestart.style.display = "none";
    }

    // mouse onclick events
    gameRestart.onclick = reload;

    // key down event
    document.onkeydown = function (ev) {
        // processKeyPress key press TODO:make sure the conversions work
        switch (ev.keyCode) {
            // change these number for different keys passed in from ui.jsp, default qwop
            case left:
                processKeyPress(0);
                break;
            case down: //W
                processKeyPress(1);
                break;
            case up: //O
                processKeyPress(2);
                break;
            case right: //P
                processKeyPress(3);
                break;
            case 82: //R for reload
                reload();
                break;
/*            case 13: //Enter to start the game
                if(ended == 1) {
                    play();
                }*/
        }
    };

    // obtain note location
    function getPos(obj) {
        var left = 0;
        var top = 0;
        while (obj) {
            left += obj.offsetLeft;
            top += obj.offsetTop;
            obj = obj.offsetParent;
        }
        return {'left': left, 'top': top};
    }
};