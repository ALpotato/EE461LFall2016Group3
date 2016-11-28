package r2beat.servlets;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import r2beat.model.Setting;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;

@SuppressWarnings("serial")
public class SettingServlet extends HttpServlet {
    //happens when player selects settings
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/jsp/Setting.jsp").forward(req, resp);
    }

    //happens when player save his/her setting
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (req.getParameter("cancel") == null) {
            User user = getUser();
            if (user != null) {
                String id = user.getNickname();
                Setting setting = ofy().load().type(Setting.class).id(id).now();
                if (setting != null) {
                    //FIXME:make sure this actually saves to the setting object on objectify
                    setKeys(req, setting);
                } else {
                    setting = new Setting(user.getNickname());
                    setKeys(req, setting);
                    ofy().save().entities(setting).now();
                }
            }
        }
        resp.sendRedirect("/");
    }

    private void setKeys(HttpServletRequest req, Setting setting) {
        setting.left = (Integer.getInteger(req.getAttribute("left").toString()));
        setting.down = (Integer.getInteger(req.getAttribute("down").toString()));
        setting.up = (Integer.getInteger(req.getAttribute("up").toString()));
        setting.right = (Integer.getInteger(req.getAttribute("right").toString()));
    }

    private User getUser() {
        UserService userService = UserServiceFactory.getUserService();
        return userService.getCurrentUser();
    }
}
