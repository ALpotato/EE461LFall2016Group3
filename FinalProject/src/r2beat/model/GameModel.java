package r2beat.model;

import java.text.DecimalFormat;

public class GameModel {

    //TODO: figure out if this should belong here
    private Song song;

    //FIXME:don't know what these are for, most likely will be placed in KeySetting class
    private int left = 65;
    private int down = 83;
    private int up = 75;//87 or 75
    private int right = 76;//68 or 76

    //FIXME:should be taken care of in JS
    /*BufferedImage receptors = null;
    BufferedImage leftArrow = null;
    BufferedImage downArrow = null;
    BufferedImage upArrow = null;
    BufferedImage rightArrow = null;

    Graphics bufferGraphics;
    Image image;*/

    private int life = 100;
    private double score = 0.0;
    private double maxScore = 0.0;
    private int perfect = 0;
    private int great = 0;
    private int good = 0;
    private int bad = 0;
    private int miss = 0;
    private int combo = 0;
    private int maxCombo = 0;
    private int judgement = 9;
    private boolean fail = false;
    private boolean songOver = false;

    //TODO: will need to ask player for it
    private String playerName = "Player 1";

    //TODO:what does this do?
    private DecimalFormat d = new DecimalFormat("#.##%");

    //TODO:this entire thing belongs in JS
    public void paint(Graphics g) {
        bufferGraphics.setFont(bufferGraphics.getFont().deriveFont(bufferGraphics.getFont().getSize() * 2F));
        bufferGraphics.clearRect(0, 0, 750, 1200);
        if (!songOver) {
            bufferGraphics.drawImage(receptors, 100, 150, this);
            int currentTime = (int) (System.currentTimeMillis() - song.getTrueTime());
            for (int i = 0; i < song.getNotes().getNotes().size(); i++) {
                Note n = song.getNotes().getNotes().get(i);
                if (n.getTime() - currentTime < -200) {
                    life -= 10;
                    score -= 2;
                    miss += 1;
                    combo = 0;
                    judgement = 4;
                    song.getNotes().getNotes().remove(n);
                } else if (n.getTime() - currentTime > 1000)
                    break;
                else {
                    if (n.getTrack() == 1)
                        bufferGraphics.drawImage(leftArrow, 100, (int) (150 + (n.getTime() - currentTime) * 1.8), this);
                    else if (n.getTrack() == 2)
                        bufferGraphics.drawImage(downArrow, 250, (int) (150 + (n.getTime() - currentTime) * 1.8), this);
                    else if (n.getTrack() == 3)
                        bufferGraphics.drawImage(upArrow, 400, (int) (150 + (n.getTime() - currentTime) * 1.8), this);
                    else if (n.getTrack() == 4)
                        bufferGraphics.drawImage(rightArrow, 550, (int) (150 + (n.getTime() - currentTime) * 1.8), this);
                }
            }
            if (judgement == 0) {
                bufferGraphics.drawString("Perfect!!", 300, 900);
            } else if (judgement == 1) {
                bufferGraphics.drawString("Great!", 300, 900);
            } else if (judgement == 2) {
                bufferGraphics.drawString("Good", 300, 900);
            } else if (judgement == 3) {
                bufferGraphics.drawString("Bad", 300, 900);
            } else if (judgement == 4) {
                bufferGraphics.drawString("Miss...", 300, 900);
            }
            bufferGraphics.setFont(bufferGraphics.getFont().deriveFont(bufferGraphics.getFont().getSize() * 0.5F));
            bufferGraphics.drawString("Combo: " + combo, 600, 1000);
            bufferGraphics.drawString("Life: " + life + "/200", 25, 25);
            bufferGraphics.drawString(d.format(score / maxScore), 25, 75);
            bufferGraphics.setFont(bufferGraphics.getFont().deriveFont(bufferGraphics.getFont().getSize() * 0.5F));
            bufferGraphics.drawString("Perfect: " + perfect, 25, 400);
            bufferGraphics.drawString("Great: " + great, 25, 450);
            bufferGraphics.drawString("Good: " + good, 25, 500);
            bufferGraphics.drawString("Bad: " + bad, 25, 550);
            bufferGraphics.drawString("Miss: " + miss, 25, 600);
            bufferGraphics.drawString(playerName, 25, 1000);
            bufferGraphics.setFont(bufferGraphics.getFont().deriveFont(bufferGraphics.getFont().getSize() * 2F));
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(image, 0, 0, this);
            frame(); //double buffer secondary method
        } else {
            bufferGraphics.drawString("Perfect: " + perfect, 100, 100);
            bufferGraphics.drawString("Great: " + great, 100, 200);
            bufferGraphics.drawString("Good: " + good, 100, 300);
            bufferGraphics.drawString("Bad: " + bad, 100, 400);
            bufferGraphics.drawString("Miss: " + miss, 100, 500);
            bufferGraphics.drawString("Max Combo: " + maxCombo, 100, 600);
            bufferGraphics.drawString("Score: " + d.format(score / maxScore), 100, 800);
            if (fail) {
                bufferGraphics.drawString("Grade: F", 100, 900);
            } else if (score == maxScore) {
                bufferGraphics.drawString("Grade: AAA", 100, 900);
            } else if (score >= maxScore * 0.99) {
                bufferGraphics.drawString("Grade: AA", 100, 900);
            } else if (score >= maxScore * 0.97) {
                bufferGraphics.drawString("Grade: A+", 100, 900);
            } else if (score >= maxScore * 0.92) {
                bufferGraphics.drawString("Grade: A", 100, 900);
            } else if (score >= maxScore * 0.90) {
                bufferGraphics.drawString("Grade: A-", 100, 900);
            } else if (score >= maxScore * 0.88) {
                bufferGraphics.drawString("Grade: B+", 100, 900);
            } else if (score >= maxScore * 0.82) {
                bufferGraphics.drawString("Grade: B", 100, 900);
            } else if (score >= maxScore * 0.80) {
                bufferGraphics.drawString("Grade: B-", 100, 900);
            } else if (score >= maxScore * 0.78) {
                bufferGraphics.drawString("Grade: C+", 100, 900);
            } else if (score >= maxScore * 0.72) {
                bufferGraphics.drawString("Grade: C", 100, 900);
            } else if (score >= maxScore * 0.70) {
                bufferGraphics.drawString("Grade: C-", 100, 900);
            } else if (score >= maxScore * 0.68) {
                bufferGraphics.drawString("Grade: D+", 100, 900);
            } else if (score >= maxScore * 0.62) {
                bufferGraphics.drawString("Grade: D", 100, 900);
            } else if (score >= maxScore * 0.60) {
                bufferGraphics.drawString("Grade: D-", 100, 900);
            } else {
                bufferGraphics.drawString("Grade: E", 100, 900);
            }
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(image, 0, 0, this);
        }
    }

    //FIXME: handled in JS
/*
    public void update(Graphics g) {
        paint(g);
    }
*/

    //FIXME: handled in JS
/*    public void frame() {
        while (System.currentTimeMillis() % 10 != 0) {
        }
        //100fps
        if (life <= 0 || song.getNotes().getNotes().size() == 0) {
            songOver = true;
            if (life <= 0) {
                fail = true;
            }
            song.getSongThread().end();
        }
        repaint();
    }*/

    //FIXME:should be handled in JS
/*    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == left)
            processKeyPress(1);

        if (e.getKeyCode() == down)
            processKeyPress(2);

        if (e.getKeyCode() == up)
            processKeyPress(3);

        if (e.getKeyCode() == right)
            processKeyPress(4);

    }*/

    //FIXME:should be handled in JS
/*    public void processKeyPress(int track) {
        int tapTime = (int) (System.currentTimeMillis() - song.getTrueTime());
        for (Note n : song.getNotes().getNotes()) {
            if (n.getTrack() == track) {
                int noteTime = n.getTime();
                if (Math.abs(noteTime - tapTime) < 50) {
                    life += 2;
                    if (life > 200)
                        life = 200;
                    score += 2;
                    perfect += 1;
                    combo += 1;
                    judgement = 0;
                    song.getNotes().getNotes().remove(n);
                } else if (Math.abs(noteTime - tapTime) < 100) {
                    life += 1;
                    if (life > 200)
                        life = 200;
                    score += 1;
                    great += 1;
                    combo += 1;
                    judgement = 1;
                    song.getNotes().getNotes().remove(n);
                } else if (Math.abs(noteTime - tapTime) < 150) {
                    good += 1;
                    combo = 0;
                    judgement = 2;
                    song.getNotes().getNotes().remove(n);
                } else if (Math.abs(noteTime - tapTime) < 200) {
                    life -= 5;
                    score -= 1;
                    bad += 1;
                    combo = 0;
                    judgement = 3;
                    song.getNotes().getNotes().remove(n);
                }
                if (combo > maxCombo) {
                    maxCombo = combo;
                }
                break;
            }
        }
    }*/

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
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

    public int getCombo() {
        return combo;
    }

    public void setCombo(int combo) {
        this.combo = combo;
    }

    public int getMaxCombo() {
        return maxCombo;
    }

    public void setMaxCombo(int maxCombo) {
        this.maxCombo = maxCombo;
    }

    public int getJudgement() {
        return judgement;
    }

    public void setJudgement(int judgement) {
        this.judgement = judgement;
    }

    public boolean isFail() {
        return fail;
    }

    public void setFail(boolean fail) {
        this.fail = fail;
    }

    public boolean isSongOver() {
        return songOver;
    }

    public void setSongOver(boolean songOver) {
        this.songOver = songOver;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
