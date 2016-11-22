package r2beat.model;

/**
 * Song titles to sort the score lists
 * additional songs should be added in here
 */
public enum Song {
    EXTRATONE_PIRATES(0, "Extratone Pirates"),
    FREEDOM_DIVE(1, "FREEDOM DiVE"),
    I_BURN(2, "I Burn"),
    INTERSECT_THUNDERBOLT(3, "Intersect Thunderbolt"),
    MEGALOVANIA(4, "Megalovania"),
    NEWBIE_MELODY(5, "Newbie Melody"),
    NOT_AS_IT_SEEMS(6, "Not As It Seems"),
    ONE_WINGED_ANGEL(7, "One Winged Angel"),
    SMBMAINTHEME(8, "SMB Main Theme"),
    TETRIS_THEME_A(9, "Tetris Theme A");

    private final int index;
    private final String songName;

    Song(int index, String songName) {
        this.index = index;
        this.songName = songName;
    }

    public int getIndex() {
        return index;
    }

    public String getSongName() {
        return songName;
    }
}
