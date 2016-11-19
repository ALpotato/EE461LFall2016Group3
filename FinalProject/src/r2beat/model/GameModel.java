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
    private User user; // information about the user
    private NoteFile noteFile; // a reference to the current selected music NoteFile
    private List<Score> scoreList; // a list of scores the player made for every song

    // TODO: most likely needed in JS
    /*private final DecimalFormat decimalFormat = new DecimalFormat("#.##%");*/

    /* not used */
    private GameModel() {
    }

    public GameModel(User user) {
        this.user = user;
        this.scoreList = new ArrayList<>();
    }


    public NoteFile getNoteFile() {
        return noteFile;
    }

    public void setNoteFile(NoteFile noteFile) {
        this.noteFile = noteFile;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getScoreListJSON() {
        Gson gson = new Gson();
        return gson.toJson(scoreList);
    }

    public List<Score> getScoreList() {
        return scoreList;
    }
}
