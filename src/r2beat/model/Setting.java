package r2beat.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Setting {

    @Id
    Long id;
    @Index
    String userName;
    public int left;
    public int up;
    public int down;
    public int right;
    public double speed;

    //used for Objectify
    private Setting() {
    }

    public Setting(String userName) {
        left = 81;
        up = 87;
        down = 79;
        right = 80;
        speed = 1.0;
        this.userName = userName;
    }
}
