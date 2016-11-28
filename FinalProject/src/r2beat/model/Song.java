package r2beat.model;

/**
 * Song titles to sort the score lists
 * additional songs should be added in here
 */
public enum Song {
    NEWBIE_MELODY(1, "Newbie Melody"),
    TETRIS_THEME_A(2, "Tetris Theme A"),
    I_BURN(3, "I Burn"),
    SMB_MAIN_THEME(4, "SMB Main Theme"),
    NOT_AS_IT_SEEMS(5, "Not As It Seems"),
    ONE_WINGED_ANGEL(6, "One Winged Angel"),
    INTERSECT_THUNDERBOLT(7, "Intersect Thunderbolt"),
    MEGALOVANIA(8, "Megalovania"),
    EXTRATONE_PIRATES(9, "Extratone Pirates");

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
