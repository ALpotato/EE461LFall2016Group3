package r2beat.servlets;

import r2beat.model.KeySetting;

import javax.servlet.http.*;
import java.io.IOException;

@SuppressWarnings("serial")
public class KeySettingServlet extends HttpServlet {
    private KeySetting setting;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //TODO: take user key input and remember it with KeySetting class
        resp.setContentType("text/plain");
        resp.getWriter().println("Hello, world");
    }
}
