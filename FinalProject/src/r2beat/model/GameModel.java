package r2beat.model;

import com.google.appengine.api.users.User;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class GameModel {

    @Id
    public
    Long id;
    private List<Score> scoreList; // a list of scores the player made for every song

    /* used for Objectify */
    private GameModel() {
    }

    //TODO: figure out what becomes key for storing in objectify
    public GameModel(User user) {
        this.scoreList = new ArrayList<>();
        this.id = (long) user.hashCode();
    }

    public String getScoreListJSON() {
        Gson gson = new Gson();
        return gson.toJson(scoreList);
    }

    public List<Score> getScoreList() {
        return scoreList;
    }
}
