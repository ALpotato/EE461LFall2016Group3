package r2beat.servlets;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.*;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import r2beat.model.Score;
import r2beat.model.ScoreList;

import org.junit.Test;

public class LeaderBoardServletTest {
	
	private List<String> leaderBoard = new ArrayList<>();
	private List<String> songs = new ArrayList<>();
	private List<Score> scoreList = new ArrayList<>();
	private Score score = new Score("tester");
	
	@Before
	public void setUp() {
		score.setBad(1);
		score.setGood(1);
		score.setGreat(1);
		score.setMaxCombo(1);
		score.setMiss(1);
		score.setPerfect(1);
		score.setScore(1);
		scoreList.add(score);
	}
	
	@Test
	public void testDoGet() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		/**
		 * testing doGet
		 */
		when(request.getParameter("songIndex")).thenReturn("0");
		when(request.getAttribute("leaderBoard")).thenReturn(leaderBoard);
		when(request.getAttribute("songs")).thenReturn(songs);

		/**
		 * testing doPost
		 */
		when(request.getParameter("songIndex")).thenReturn("0");
		when(request.getParameter("bad")).thenReturn("1");
		when(request.getParameter("good")).thenReturn("1");
		when(request.getParameter("great")).thenReturn("1");
		when(request.getParameter("maxCombo")).thenReturn("1");
		when(request.getParameter("miss")).thenReturn("1");
		when(request.getParameter("perfect")).thenReturn("1");
		when(request.getParameter("score")).thenReturn("1");
		
		/**
		 * ensure the output is correct
		 */
		assertEquals(scoreList.get(0), score);
	}
}
