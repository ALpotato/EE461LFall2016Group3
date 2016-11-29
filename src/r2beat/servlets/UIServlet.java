package r2beat.servlets;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import r2beat.model.NoteFile;
import r2beat.model.Setting;
import r2beat.model.Song;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static r2beat.objectify.OfyService.ofy;

public class UIServlet extends HttpServlet {
    //happens when player picked the song of choice
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String songName = req.getParameter("song_name");
        for (Song song : Song.values()) {
            if (song.getSongName().equals(songName)) {
                req.setAttribute("songIndex", song.getIndex());
                break;
            }
        }
        NoteFile file = new NoteFile(new File("notefiles/" + songName + ".sm"));
        req.setAttribute("songName", songName);
        req.setAttribute("noteFile", file.getNotesJSON());
        User user = getUser();
        if (user != null) {
            Setting setting = ofy().load().type(Setting.class).filter("userName", user.getNickname()).first().now();
            if (setting != null) {
                setSettings(req, setting.left, setting.down, setting.up, setting.right, setting.speed);
            } else {
                setSettings(req, 81, 87, 79, 80, 1.0);
            }
        } else {
            setSettings(req, 81, 87, 79, 80, 1.0);
        }
        req.getRequestDispatcher("/jsp/ui.jsp").forward(req, resp);
    }

    private void setSettings(HttpServletRequest req, int left, int down, int up, int right, double speed) {
        req.setAttribute("left", left);
        req.setAttribute("down", down);
        req.setAttribute("up", up);
        req.setAttribute("right", right);
        req.setAttribute("speed", speed);
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
