package r2beat.model;

/**
 *  Song titles to sort the score lists
 */
public enum Song {
    ExtraTone_Pirates(0),
    FREEDOM_DiVE(1),
    Megalovania(2),
    SMB_Main_Theme(3);

    private final int index;
    Song (int index) {
        this.index = index;
    }
}
