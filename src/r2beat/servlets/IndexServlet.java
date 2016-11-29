package r2beat.servlets;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {

    // happens when home page being loaded
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user == null) {
            request.setAttribute("logInUrl", userService.createLoginURL(request.getRequestURI()));
        } else {
            request.setAttribute("logOutUrl", userService.createLogoutURL(request.getRequestURI()));
        }
        request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
    }
}
