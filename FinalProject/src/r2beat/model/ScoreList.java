package r2beat.model;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Entity
public class ScoreList {

    @Id
    String id; //id of the song based on enum Song
    private List<Score> scoreList; // a list of scores the player made for every song

    /* used for Objectify */
    private ScoreList() {
    }

    public ScoreList(String id) {
        this.scoreList = new LinkedList<>();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Score> getScoreList() {
        return sortList();
    }

    private List<Score> sortList() {
        if (!scoreList.isEmpty()) {
            Collections.sort(scoreList, new Comparator<Score>() {
                @Override
                public int compare(Score s1, Score s2) {
                    return (int) s1.getScore() - (int) s2.getScore();
                }
            });
        }
        return scoreList;
    }
}
