/**
 * Created by Angzhi on 11/11/2016.
 */
function play() {
    var audio = document.getElementById('audio');
    if (audio.paused) {
        audio.play();
    } else {
        audio.pause();
        audio.currentTime = 0;
    }
}
