package r2beat.servlets;

import r2beat.model.Setting;

import javax.servlet.http.*;
import java.io.IOException;

@SuppressWarnings("serial")
public class SettingServlet extends HttpServlet {
    private Setting setting;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //TODO: take user key input and remember it with Setting class
        resp.setContentType("text/plain");
        resp.getWriter().println("Hello, world");
    }
}
