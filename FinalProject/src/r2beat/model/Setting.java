package r2beat.model;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Setting {

    @Id
    Long id;
    private int left;
    private int up;
    private int down;
    private int right;

    private Setting() {

    }
    public Setting(User user) {
        left = 81;
        up = 87;
        down = 79;
        right = 80;
        id = (long) user.hashCode();
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}
