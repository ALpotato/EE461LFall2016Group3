package r2beat.servlets;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import r2beat.model.NoteFile;
import r2beat.model.ScoreList;
import r2beat.model.Setting;
import r2beat.model.Song;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class UIServlet extends HttpServlet {
    //happens when player picked the song of choice
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String songName = req.getParameter("song_name");
        for (Song song : Song.values()) {
            if (song.getSongName().equals(songName)) {
                req.setAttribute("songIndex", song.getIndex());
                if (ofy().load().type(ScoreList.class).id(song.getIndex()).now() == null) {
                    ScoreList list = new ScoreList(String.valueOf(song.getIndex()));
                    ofy().save().entities(list).now();
                }
            }
        }
        NoteFile file = new NoteFile(new File("notefiles/" + songName + ".sm"));
        req.setAttribute("songName", songName);
        req.setAttribute("noteFile", file.getNotesJSON());
        User user = getUser();
        if (user != null) {
            Setting setting = ofy().load().type(Setting.class).id(user.getUserId()).now();
            if (setting == null) {
                req.setAttribute("left", 81);
                req.setAttribute("down", 87);
                req.setAttribute("up", 79);
                req.setAttribute("right", 80);
            } else {
                req.setAttribute("left", setting.left);
                req.setAttribute("down", setting.down);
                req.setAttribute("up", setting.up);
                req.setAttribute("right", setting.right);
            }
        } else {
            req.setAttribute("left", 81);
            req.setAttribute("down", 87);
            req.setAttribute("up", 79);
            req.setAttribute("right", 80);
        }
        req.getRequestDispatcher("/jsp/ui.jsp").forward(req, resp);
    }

    //happens when selected play in home page
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("songs", Song.values());
        req.getRequestDispatcher("/jsp/ui.jsp").forward(req, resp);
    }

    private User getUser() {
        UserService userService = UserServiceFactory.getUserService();
        return userService.getCurrentUser();
    }
}
