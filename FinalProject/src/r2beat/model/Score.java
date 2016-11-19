package r2beat.model;

public class Score {

    private double score;
    private int perfect;
    private int great;
    private int good;
    private int bad;
    private int miss;
    private int maxCombo;

    public Score() {
        score = 0.0;
        perfect = 0;
        great = 0;
        good = 0;
        bad = 0;
        miss = 0;
        maxCombo = 0;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getPerfect() {
        return perfect;
    }

    public void setPerfect(int perfect) {
        this.perfect = perfect;
    }

    public int getGreat() {
        return great;
    }

    public void setGreat(int great) {
        this.great = great;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getBad() {
        return bad;
    }

    public void setBad(int bad) {
        this.bad = bad;
    }

    public int getMiss() {
        return miss;
    }

    public void setMiss(int miss) {
        this.miss = miss;
    }

    public int getMaxCombo() {
        return maxCombo;
    }

    public void setMaxCombo(int maxCombo) {
        this.maxCombo = maxCombo;
    }
}
