package r2beat.model;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class NoteFileTestCases {

	NoteFile testNoteFile;
	int[] preset = { 3, 2, 3, 2, 1, 2, 4, 4, 3, 2, 3, 2, 1, 2, 4, 3, 2, 1, 2, 1, 2, 4, 4, 3, 2, 3, 2, 1, 2, 4, 3, 2, 1,
			2, 2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 1, 4, 2, 4, 2, 3, 1, 3, 1, 4, 2, 4, 2, 3, 1, 3, 4, 4, 2, 4, 2, 3, 1, 3, 1,
			4, 2, 4, 2, 3, 1, 3, 4, 2, 4, 4, 3, 2, 3, 2, 1, 2, 4, 3, 2, 1, 2, 1, 2, 3, 3, 2, 3, 2, 1, 2, 4, 4, 3, 2, 3,
			2, 1, 2, 4, 3, 2, 1, 2, 1, 2, 4, 4, 3, 2, 3, 2, 1, 2, 4, 3, 2, 1, 2, 2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 1, 4, 2,
			4, 2, 3, 1, 3, 1, 4, 2, 4, 2, 3, 1, 3, 4, 4, 2, 4, 2, 3, 1, 3, 1, 4, 2, 4, 2, 3, 1, 3, 4, 2, 4, 4, 3, 2, 3,
			2, 1, 2, 4, 3, 2, 1, 2, 1, 2, 3, 3, 2, 3, 2, 1, 2, 4, 4, 3, 2, 3, 2, 1, 2, 4, 3, 2, 1, 2, 1, 2, 4, 4, 3, 2,
			3, 2, 1, 2, 4, 3, 2, 1, 2, 2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 1, 4, 2, 4, 2, 3, 1, 3, 1, 4, 2, 4, 2, 3, 1, 3, 4,
			4, 2, 4, 2, 3, 1, 3, 1, 4, 2, 4, 2, 3, 1, 3, 4, 2, 4, 4, 3, 2, 3, 2, 1, 2, 4, 3, 2, 1, 2, 1, 2, 3 };

	/**
	 * preparing file to be tested
	 */
	@Before
	public void setUp() {
		testNoteFile = new NoteFile(new File("war/notefiles/Newbie Melody.sm"));
	}

	/**
	 * Test for the file being created
	 */
	@Test
	public void creationTest() {
		boolean flag = false;
		if (testNoteFile.getNotes().get(0) != null)
			flag = true;
		assertTrue(flag);
	}

	/**
	 * Test for the same file being created every time the system is creating
	 * same song
	 */
	@Test
	public void comparisonTest() {
		boolean flag = true;
		NoteFile compareNoteFile = new NoteFile(new File("war/notefiles/Newbie Melody.sm"));
		for (int i = 0; i < compareNoteFile.getNotes().size(); i += 1) {
			if (compareNoteFile.getNotes().get(i).getColumn() != testNoteFile.getNotes().get(i).getColumn()) {
				flag = false;
			}
		}
		assertTrue(flag);
	}

	/**
	 * Test for the file that reproduce the correct output
	 */
	@Test
	public void eqaulTest() {
		boolean flag = true;
		for (int i = 0; i < testNoteFile.getNotes().size(); i += 1) {
			if (testNoteFile.getNotes().get(i).getColumn() != preset[i])
				flag = false;
		}
		assertTrue(flag);
	}
}
