/**
 * Created by Angzhi on 11/11/2016.
 */
/*addEvent(document, "keypress", function (e) {
    e = e || window.event;
    // use e.keyCode
});

function addEvent(element, eventName, callback) {
    if (element.addEventListener) {
        element.addEventListener(eventName, callback, false);
    } else if (element.attachEvent) {
        element.attachEvent("on" + eventName, callback);
    } else {
        element["on" + eventName] = callback;
    }
}*/

window.onload = function () {
    var click1 = document.getElementById('click1');
    var click2 = document.getElementById('click2');
    var click3 = document.getElementById('click3');
    var click4 = document.getElementById('click4');

    var container = document.getElementById('container');
    var start = document.getElementById('start');
    // var gamePause = document.getElementById('gamePause');
    var gameRestart = document.getElementById('gameRestart');

    var ul = container.getElementsByTagName('ul');
    var p = container.getElementsByTagName('p');
    var li = container.getElementsByTagName('li');
    var score = document.getElementById('score');
    var status = document.getElementById('status');
    var timeSet = document.getElementById('time');

    var music = document.getElementById('music');
    var musicWelcome = document.getElementById('musicWelcome');
    var musicWin = document.getElementById('musicWin');
    var musicPause = document.getElementById('musicPause');
    var musicBtn01 = document.getElementById('musicBtn01');
    var musicBtn02 = document.getElementById('musicBtn02');
    var musicBtn03 = document.getElementById('musicBtn03');
    var musicBtn04 = document.getElementById('musicBtn04');

    musicBtn01.volume = 0.1;
    musicBtn02.volume = 0.1;
    musicBtn03.volume = 0.1;
    musicBtn04.volume = 0.1;


    var music4 = document.getElementById('music4');
    var musictime = document.getElementById('musictime');
    var unbelievable = document.getElementById('unbelievable');

    var scoreChange = 0;
    var speed = 7;
    var timer = null;
    var timer2 = null;
    var flag = 0;


    // create the four uls that acts as columns dropping notes
    for (var i = 0; i < 4; i++) {
        var ulChange = document.createElement('ul');

        container.appendChild(ulChange);
    }
    start.onclick = play;


    // start game
    function play() {
        music.play();
        musicWelcome.pause();
        window.setInterval(timeCheck, 1000);
        start.style.display = "none";
        status.innerHTML = "";

        function generate() {
            var num = numRandom(0, 4);

            var liChange = document.createElement('li');

            liChange.style.background = "-webkit-linear-gradient( top,transparent," + colorRandom() + ")";

            ul[num].appendChild(liChange);

            move(liChange, function () {
                if (li.length >= 20) {
                    EndGame();

                    alert('Demacia！， Score' + scoreChange);
                }

            });
            // timer2=setInterval(cut, 3000);
        }

        generate();

        timer = setInterval(generate, 500);
    }

    // Pause game
    function pause() {
        music.pause();
        musicPause.play();
        clearInterval(timer);

        for (var i = 0; i < li.length; i++) {
            clearTimeout(li[i].timeout);
        }
        status.innerHTML = "Pause" + '<br />' + "Score：" + scoreChange;
        for (i = 0; i < ul.length; i++) {
            ul[i].innerHTML = '';
        }
    }

    // end game
    function EndGame() {
        music.pause();
        musicWin.play();
        clearInterval(timer);

        for (var i = 0; i < li.length; i++) {
            clearTimeout(li[i].timeout);
        }

        for (i = 0; i < ul.length; i++) {
            ul[i].innerHTML = '';
        }

        scoreChange = 0;
        speed = 7;
    }

    // create random color
    function colorRandom() {

        return "#" + ("00000" + ((Math.random() * 16777215 + 0.5) >> 0).toString(16)).slice(-6);
    }

    // create random number
    function numRandom(m, n) {

        return parseInt(Math.random() * (n - m) + m);
    }

    // countdown
    function timeCheck() {
        if (timeSet.innerHTML == 0) {
            timeSet.innerHTML = "Time's Up!";
            if (scoreChange < 100) {
                musictime.play();
                status.innerHTML = "Bronze 5!" + '<br />' + " Score: " + scoreChange + '<br />' + "Press F to restart";
            }
            if (scoreChange > 100) {
                musictime.play();
                status.innerHTML = "meh..." + '<br />' + " Score: " + scoreChange + '<br />' + "Press F to restart";
            }
            if (scoreChange > 200) {
                musictime.play();
                status.innerHTML = "Nice!" + '<br />' + " Score: " + scoreChange + '<br />' + "Press F to restart";
            }
            if (scoreChange > 500) {
                music4.play();
                status.innerHTML = "Great!" + '<br />' + " Score: " + scoreChange + '<br />' + "Press F to restart";
            }
            if (scoreChange > 1000) {
                music4.play();
                status.innerHTML = "Wow!" + '<br />' + " Score: " + scoreChange + '<br />' + "Press F to restart";
            }
            EndGame();
            return false;
        }
        timeSet.innerHTML = timeSet.innerHTML * 1 - 1;
    }

    // speedup with alert sound
    function testScoreChange() {
        switch (scoreChange) {
            case 100:
                unbelievable.play();
                speed = 8;
                break;
            case 150:
                unbelievable.play();
                break;
            case 200:
                unbelievable.play();
                speed = 10;
                break;
            case 250:
                unbelievable.play();
                break;
            case 300:
                unbelievable.play();
                break;
            case 350:
                unbelievable.play();
                break;
            case 400:
                unbelievable.play();
                break;
            case 500:
                unbelievable.play();
                speed = 12;
                break;
            case 1000:
                unbelievable.play();
                speed = 16;
                break;
        }
    }

    // Core judgement functions
    function judge(n) {
        if (!ul[n].children.length) {
            if (scoreChange < 100) {
                status.innerHTML = "Bronze 5!" + '<br />' + " Score: " + scoreChange + '<br />' + "Press F to restart";
            }
            if (scoreChange > 100) {
                status.innerHTML = "meh..." + '<br />' + " Score: " + scoreChange + '<br />' + "Press F to restart";
            }
            if (scoreChange > 200) {
                status.innerHTML = "Nice!" + '<br />' + " Score: " + scoreChange + '<br />' + "Press F to restart";
            }
            if (scoreChange > 500) {
                music4.play();
                status.innerHTML = "Great!" + '<br />' + " Score: " + scoreChange + '<br />' + "Press F to restart";
            }
            if (scoreChange > 1000) {
                music4.play();
                status.innerHTML = "Wow!" + '<br />' + " Score: " + scoreChange + '<br />' + "Press F to restart";
            }

            EndGame();

            return;
        }
        var height = coll(ul[n].children[0], p[n]);

        console.log(typeof(height));
        console.log(height);

        if (height) {
            // ul[n].children[0].style.background="#000";
            ul[n].removeChild(ul[n].children[0]);
            // p[n].style.background="rgba(255,255,255,1.0)";

            score.innerHTML = scoreChange;
        } else {
            status.innerHTML = "MISS";
            ul[n].removeChild(ul[n].children[0]);
        }
    }

    function reload() {
        document.location.reload();
    }

    // mouse onclick events
    // gamePause.onclick=pause;
    gameRestart.onclick = reload;

    click1.onclick = function () {
        testScoreChange();
        judge(0);
    };
    click2.onclick = function () {
        testScoreChange();
        judge(1);
    };
    click3.onclick = function () {
        testScoreChange();
        judge(2);
    };
    click4.onclick = function () {
        testScoreChange();
        judge(3);
    };


    // key down event
    document.onkeydown = function (ev) {
        var event = ev || event;

        // change speed when reaching certain score
        testScoreChange();

        // judge key press
        switch (event.keyCode) {
            // change these number for different keys, default qwop
            case 81:
                musicBtn01.play();
                judge(0);
                break;
            case 87:
                musicBtn02.play();
                judge(1);
                break;
            case 79:
                musicBtn03.play();
                judge(2);
                break;
            case 80:
                musicBtn04.play();
                judge(3);
                break;
            case 13:
                if (flag == 0) {
                    play();
                    //set as 1，stop changing
                    flag = 1;
                } else {
                    pause();
                    flag = 0;
                }
                break;
            case 70:
                reload();
                break;
        }
    };


    // move notes down
    function move(obj) {

        clearInterval(obj.timer);
        obj.timer = setInterval(function () {
            obj.style.top = obj.offsetTop + speed + 'px';

            // fnDuring && fnDuring.call(obj);
        }, 30);
    }

    // obtain note location
    function getPos(obj) {

        var left = 0;
        var top = 0;

        while (obj) {
            left += obj.offsetLeft;
            top += obj.offsetTop;

            obj = obj.offsetParent
        }

        return {'left': left, 'top': top};
    }

    // judge if scores
    function coll(obj1, obj2) {
        var l1 = getPos(obj1).left;
        var r1 = getPos(obj1).left + obj1.offsetWidth;
        var t1 = getPos(obj1).top;
        var b1 = getPos(obj1).top + obj1.offsetHeight;
        // console.log("distance from the top:" + t1);
        var l2 = getPos(obj2).left;
        var r2 = getPos(obj2).left + obj2.offsetWidth;
        var t2 = getPos(obj2).top;
        var b2 = getPos(obj2).top + obj2.offsetHeight;

        if (l1 > r2 || r1 < l2) {
            // status.innerHTML="WRONG!";
            return false;
        }
        console.log("distance from the left:" + l1);
        console.log("distance from the top:" + b1);

        if (b1 > 460 && b1 < 560) {
            status.innerHTML = "Good!";
            if (b1 >= 485 && b1 <= 528) {
                status.innerHTML = "Perfect!";
                scoreChange += 10;
                return true;
            }
            scoreChange += 5;
            return true;
        }

    }
};