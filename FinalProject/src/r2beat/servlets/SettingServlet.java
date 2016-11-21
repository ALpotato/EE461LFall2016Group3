package r2beat.servlets;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;
import r2beat.model.Setting;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;

@SuppressWarnings("serial")
public class SettingServlet extends HttpServlet {
    static {
        ObjectifyService.register(Setting.class);
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/jsp/Setting.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(req.getAttribute("cancel") == null) {
            User user = getUser();
            Long id = (long) user.hashCode();
            if (ofy().load().type(Setting.class).id(id) != null) {
                Ref<Setting> setting = ofy().load().type(Setting.class).id(id);
                //FIXME:make sure it is getValue() instead of get()
                //FIXME:make sure this actually saves to the setting object on objectify
                setKeys(req, setting.getValue());
            } else {
                Setting setting = new Setting(user);
                setKeys(req, setting);
                ofy().save().entities(setting).now();
            }
        }
        resp.sendRedirect("/index.jsp");
    }

    private void setKeys(HttpServletRequest req, Setting setting) {
        setting.setLeft(Integer.getInteger(req.getAttribute("left").toString()));
        setting.setDown(Integer.getInteger(req.getAttribute("down").toString()));
        setting.setUp(Integer.getInteger(req.getAttribute("up").toString()));
        setting.setRight(Integer.getInteger(req.getAttribute("right").toString()));
    }

    private User getUser() {
        UserService userService = UserServiceFactory.getUserService();
        return userService.getCurrentUser();
    }
}
