package r2beat.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Setting {

    @Id
    String id;
    public int left;
    public int up;
    public int down;
    public int right;

    //used for Objectify
    private Setting() {
    }

    public Setting(String id) {
        left = 81;
        up = 87;
        down = 79;
        right = 80;
        this.id = id;
    }
}
