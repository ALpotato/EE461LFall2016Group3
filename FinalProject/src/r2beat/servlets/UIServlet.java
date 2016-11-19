package r2beat.servlets;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import r2beat.model.GameModel;
import r2beat.model.NoteFile;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO:create the UI with initial game setup for running
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        GameModel model = new GameModel(user);
        String songName = req.getParameter("songName");
        NoteFile file = new NoteFile(new File(songName));

        ofy().save().entities(model).now();
        req.setAttribute("noteOffSet", file.getOffset());
        req.setAttribute("noteFile", file.getNotesJSON());
/*        req.setAttribute("scoreList", model.getScoreList());*/
        req.getRequestDispatcher("/jsp/ui.jsp").forward(req, resp);
    }
}
