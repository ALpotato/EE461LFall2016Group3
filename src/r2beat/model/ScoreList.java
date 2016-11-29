package r2beat.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
public class ScoreList {

    @Id
    Long id;
    @Index
    public String index; //song index used for filtering

    private List<Score> scoreList; // a list of scores the player made for every song

    /* used for Objectify */
    private ScoreList() {
    }

    public ScoreList(String index) {
        this.scoreList = new ArrayList<>();
        this.index = index;
    }

    public List<Score> getScoreList() {
        return sortList();
    }
    
    public void setScoreList(List<Score> sl) {
        this.scoreList = sl;
    }

    private List<Score> sortList() {
        if (scoreList != null && !scoreList.isEmpty()) {
            Collections.sort(scoreList, new Comparator<Score>() {
                @Override
                public int compare(Score s1, Score s2) {
                    return (int) s2.getScore() - (int) s1.getScore();
                }
            });
        }
        return scoreList;
    }
}