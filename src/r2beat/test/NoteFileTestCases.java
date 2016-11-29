package r2beat.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import r2beat.model.NoteFile;

public class NoteFileTestCases {

    NoteFile testNoteFile1;
    NoteFile testNoteFile2;
    NoteFile testNoteFile3;

    @Before
    public void setUp() {
        testNoteFile1 = new NoteFile(new File("notefiles/Newbie Melody.sm"));
        testNoteFile2 = new NoteFile(new File("notefiles/Tetris Theme A.sm"));
        testNoteFile3 = new NoteFile(new File("notefiles/I Burn.sm"));
    }

    @Test
    public void testNoteFile1() {
        assertEquals(new NoteFile(new File("notefiles/Newbie Melody.sm")), testNoteFile1);
    }

    @Test
    public void testNoteFile2() {
        assertEquals(new NoteFile(new File("notefiles/Tetris Theme A.sm")), testNoteFile2);
    }

    @Test
    public void testNoteFile3() {
        assertEquals(new NoteFile(new File("notefiles/I burn.sm")), testNoteFile2);
    }
}
