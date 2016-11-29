package r2beat.model;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class NoteFileTestCases {

	NoteFile testNoteFile1;
	NoteFile testNoteFile2;
	NoteFile testNoteFile3;
	NoteFile testNoteFile4;
	NoteFile testNoteFile5;
	NoteFile testNoteFile6;
	NoteFile testNoteFile7;
	NoteFile testNoteFile8;
	NoteFile testNoteFile9;

	@Before
	public void setUp() {
		testNoteFile1 = new NoteFile(new File("notefiles/Newbie Melody.sm"));
		testNoteFile2 = new NoteFile(new File("notefiles/Tetris Theme A.sm"));
		testNoteFile3 = new NoteFile(new File("notefiles/I Burn.sm"));
		testNoteFile4 = new NoteFile(new File("notefiles/SMB Main Theme.sm"));
		testNoteFile5 = new NoteFile(new File("notefiles/Not As It Seems.sm"));
		testNoteFile6 = new NoteFile(new File("notefiles/One Winged Angel.sm"));
		testNoteFile7 = new NoteFile(new File("notefiles/Intersect Thunderbolt.sm"));
		testNoteFile8 = new NoteFile(new File("notefiles/Megalovania.sm"));
		testNoteFile9 = new NoteFile(new File("notefiles/Extratone Pirates.sm"));
	}

	@Test
	public void testNoteFile1() {
		boolean flag = true;
		NoteFile compareNoteFile = new NoteFile(new File("notefiles/Newbie Melody.sm"));
		for (int i = 0; i < compareNoteFile.getNotes().size(); i += 1){
			if(compareNoteFile.getNotes().get(i).getColumn() != testNoteFile1.getNotes().get(i).getColumn()){
				flag = false;
			}
		}
		assertTrue(flag);
	}

	@Test
	public void testNoteFile2() {
		assertEquals(new NoteFile(new File("notefiles/Tetris Theme A.sm")).getNotes(), testNoteFile2.getNotes());
	}

	@Test
	public void testNoteFile3() {
		assertEquals(new NoteFile(new File("notefiles/I burn.sm")), testNoteFile3);
	}
	
	@Test
	public void testNoteFile4() {
		assertEquals(new NoteFile(new File("notefiles/SMB Main Theme.sm")), testNoteFile4);
	}

	@Test
	public void testNoteFile5() {
		assertEquals(new NoteFile(new File("notefiles/Not As It Seems.sm")), testNoteFile5);
	}

	@Test
	public void testNoteFile6() {
		assertEquals(new NoteFile(new File("notefiles/One Winged Angel.sm")), testNoteFile6);
	}
	
	@Test
	public void testNoteFile7() {
		assertEquals(new NoteFile(new File("notefiles/Intersect Thunderbolt.sm")), testNoteFile7);
	}

	@Test
	public void testNoteFile8() {
		assertEquals(new NoteFile(new File("notefiles/Megalovania.sm")), testNoteFile8);
	}

	@Test
	public void testNoteFile9() {
		assertEquals(new NoteFile(new File("notefiles/Extratone Pirates.sm")), testNoteFile9);
	}
}
