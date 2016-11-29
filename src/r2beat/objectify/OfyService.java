package r2beat.objectify;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import r2beat.model.ScoreList;
import r2beat.model.Setting;

public class OfyService {
    static {
        ObjectifyService.register(ScoreList.class);
        ObjectifyService.register(Setting.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }
}