package r2beat.servlets;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;
import r2beat.model.GameModel;
import r2beat.model.NoteFile;
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
    static {
        ObjectifyService.register(GameModel.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUser();
        GameModel model = new GameModel(user);
        String songName = req.getParameter("song_name") + ".mp3";
        NoteFile file = new NoteFile(new File(songName));
        ofy().save().entities(model).now();
        req.setAttribute("songName", songName);
        for(Song song : Song.values()){
            if(song.getSongName().equals(req.getParameter("song_name"))) {
                req.setAttribute("songIndex", song.getIndex());
            }
        }
        req.setAttribute("noteFile", file.getNotesJSON());
        if (ofy().load().type(Setting.class).id(model.id) == null) {
            req.setAttribute("left", 81);
            req.setAttribute("down", 87);
            req.setAttribute("up", 79);
            req.setAttribute("down", 80);
        } else {
            Ref<Setting> setting = ofy().load().type(Setting.class).id(model.id);
            req.setAttribute("left", setting.getValue().getLeft());
            req.setAttribute("down", setting.getValue().getDown());
            req.setAttribute("up", setting.getValue().getUp());
            req.setAttribute("down", setting.getValue().getRight());
        }
        req.getRequestDispatcher("/jsp/ui.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
        req.setAttribute("Songs", Song.values());
        req.getRequestDispatcher("/jsp/ui.jsp").forward(req, resp);
    }

    private User getUser() {
        UserService userService = UserServiceFactory.getUserService();
        return userService.getCurrentUser();
    }
}
