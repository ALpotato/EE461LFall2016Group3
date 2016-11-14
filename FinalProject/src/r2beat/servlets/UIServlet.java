package r2beat.servlets;
import static com.googlecode.objectify.ObjectifyService.ofy;
import r2beat.model.GameModel;
import r2beat.model.Song;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import com.googlecode.objectify.ObjectifyService;

/**
 * Created by Angzhi on 11/11/2016.
 */
public class UIServlet extends HttpServlet {
    static {
        ObjectifyService.register(GameModel.class);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO:create the UI with initial game setup for running
        req.getRequestDispatcher("../war/jsp/ui.jsp").forward(req, resp);
    }
/*    public void init() {
        *//*
        should be taken care of in JS
        try {
            receptors = ImageIO.read(new File("receptors.png"));
            leftArrow = ImageIO.read(new File("leftarrow.png"));
            downArrow = ImageIO.read(new File("downarrow.png"));
            upArrow = ImageIO.read(new File("uparrow.png"));
            rightArrow = ImageIO.read(new File("rightarrow.png"));
        } catch (IOException e) {
        }*//*
        image = createImage(750, 1200);
        bufferGraphics = image.getGraphics();
        Font currentFont = bufferGraphics.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 2F);
        bufferGraphics.setFont(newFont);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        model.setSong(new Song(new File("Extratone Pirates.sm"), new File("Extratone Pirates.mp3")));
        model.getSong().play();
        model.setMaxScore(model.getSong().getNotes().getNotes().size() * 2.0);
    }*/
}
