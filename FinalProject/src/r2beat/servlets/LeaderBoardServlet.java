package r2beat.servlets;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import r2beat.model.Score;
import r2beat.model.ScoreList;
import r2beat.model.Song;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class LeaderBoardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ScoreList> leaderBoard = getSortedBoard();
        req.setAttribute("leaderBoard", leaderBoard);
        List<String> songs = new ArrayList<>();
        for(Song song : Song.values()) {
            for(int i = 0; i < leaderBoard.size(); i++) {
                if(leaderBoard.get(i).getId().compareToIgnoreCase(String.valueOf(song.getIndex())) == 0){
                    songs.add(song.getSongName());
                }
            }
        }
        req.setAttribute("songs", songs);
        req.getRequestDispatcher("/jsp/leaderBoard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        String songIndex = req.getParameter("songIndex");
        List<Score> scoreList = ofy().load().type(ScoreList.class).id(songIndex).get().getScoreList();
        Score score = new Score(user.getUserId());
        setScore(req, score);
        if(scoreList.isEmpty()) {
            scoreList.add(score);
            return;
        }
        for(int i = 0; i < scoreList.size(); i++) {
            if(scoreList.get(i).getScore() < Double.parseDouble(req.getParameter("score"))) {
                scoreList.add(i, score);
                return;
            }
        }
        scoreList.add(score);
    }

    private void setScore(HttpServletRequest req, Score score) {
        score.setBad(Integer.parseInt(req.getParameter("bad")));
        score.setGood(Integer.parseInt(req.getParameter("good")));
        score.setGreat(Integer.parseInt(req.getParameter("great")));
        score.setMaxCombo(Integer.parseInt(req.getParameter("maxCombo")));
        score.setMiss(Integer.parseInt(req.getParameter("miss")));
        score.setPerfect(Integer.parseInt(req.getParameter("perfect")));
        score.setScore(Double.parseDouble(req.getParameter("score")));
    }

    private List<ScoreList> getSortedBoard() {
        List<ScoreList> leaderBoard = ofy().load().type(ScoreList.class).list();
        if(leaderBoard != null) {
            Collections.sort(leaderBoard, new Comparator<ScoreList>() {
                @Override
                public int compare(ScoreList s1, ScoreList s2) {
                    return s1.getId().compareToIgnoreCase(s2.getId());
                }
            });
        }
        return leaderBoard;
    }
}
