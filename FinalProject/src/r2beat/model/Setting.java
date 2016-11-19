package r2beat.model;

/**
 * Created by Angzhi on 11/11/2016.
 */
public class Setting {
    int left;
    int top;
    int bottom;
    int right;
    int reset;
    int difficulty;

    public Setting() {
        left = 81;
        top = 87;
        bottom = 79;
        right = 80;
        reset = 70;
        difficulty = 0;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getReset() {
        return reset;
    }

    public void setReset(int reset) {
        this.reset = reset;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
