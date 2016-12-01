package r2beat.servlets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion.Setting;

public class SettingServletTest {
	
	private User user = new User("tester", "http://");

	@Test
	public void testLeft() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		UserService userService = mock(UserService.class);
		/**
		 * testing doPost
		 */
		when(request.getParameter("cancel")).thenReturn(null);
		when(userService.getCurrentUser()).thenReturn(user);
		when(request.getParameter("left")).thenReturn("Q");
		
		/**
		 * ensure the output is correct
		 */
		assertEquals(request.getParameter("left"), "Q");
	}
	
	@Test
	public void testDown() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		UserService userService = mock(UserService.class);
		/**
		 * testing doPost
		 */
		when(request.getParameter("cancel")).thenReturn(null);
		when(userService.getCurrentUser()).thenReturn(user);
		when(request.getParameter("down")).thenReturn("W");
		
		/**
		 * ensure the output is correct
		 */
		assertEquals(request.getParameter("down"), "W");
	}
	
	@Test
	public void testUp() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		UserService userService = mock(UserService.class);
		/**
		 * testing doPost
		 */
		when(request.getParameter("cancel")).thenReturn(null);
		when(userService.getCurrentUser()).thenReturn(user);
		when(request.getParameter("up")).thenReturn("O");
		
		/**
		 * ensure the output is correct
		 */
		assertEquals(request.getParameter("up"), "O");
	}
	
	@Test
	public void testRight() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		UserService userService = mock(UserService.class);
		/**
		 * testing doPost
		 */
		when(request.getParameter("cancel")).thenReturn(null);
		when(userService.getCurrentUser()).thenReturn(user);
		when(request.getParameter("left")).thenReturn("Q");
		
		/**
		 * ensure the output is correct
		 */
		assertEquals(request.getParameter("right"), "P");
	}

}
