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

import static r2beat.objectify.OfyService.ofy;

public class LeaderBoardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ScoreList> leaderBoard = getSortedBoard();
        req.setAttribute("leaderBoard", leaderBoard);
        List<String> songs = new ArrayList<>();
        for (Song song : Song.values()) {
            for (ScoreList list : leaderBoard) {
                if (list.index.compareToIgnoreCase(String.valueOf(song.getIndex())) == 0) {
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
        ScoreList scoreList = ofy().load().type(ScoreList.class).filter("index", songIndex).first().now();
        Score score;
        if (user != null) {
            score = new Score(user.getNickname());
        } else {
            score = new Score("PlayerUnknown");
        }
        setScore(req, score);
        if (scoreList == null) {
            scoreList = new ScoreList(songIndex);
            scoreList.getScoreList().add(score);
        } else {
            List<Score> list = scoreList.getScoreList();
            ofy().delete().entity(scoreList).now();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getScore() < Double.parseDouble(req.getParameter("score"))) {
                    list.add(i, score);
                    return;
                }
            }
            list.add(score);
        }
        ofy().save().entity(scoreList).now();
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
        List<ScoreList> leaderBoard = new ArrayList<>();
        for (int i = 1; i < Song.values().length; i++) {
            if (i == 2) {
                continue;
            }
            ScoreList list = ofy().load().type(ScoreList.class).filter("index", String.valueOf(i)).first().now();
            if (list != null) {
                leaderBoard.add(list);
            }
        }
        if (!leaderBoard.isEmpty()) {
            Collections.sort(leaderBoard, new Comparator<ScoreList>() {
                @Override
                public int compare(ScoreList s1, ScoreList s2) {
                    return s1.index.compareToIgnoreCase(s2.index);
                }
            });
        }
        return leaderBoard;
    }
}
