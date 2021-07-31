package de.uhd.ifi.se.quizapp.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import de.uhd.ifi.se.quizapp.controller.StudentServlet;

public class TestStudentServlet extends Mockito {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// T30
	@Test
	public void TestStudentServletConstructor() {
		StudentServlet servlet = new StudentServlet();
		assertTrue(servlet instanceof StudentServlet);
	}

	// T53
	@Test
	public void TestHandleLoginOfStudentServletWithBothUsernameAndPasswordNull() {
		StudentServlet servlet = new StudentServlet();
		assertTrue(servlet instanceof StudentServlet);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);

		when(request.getParameter("username")).thenReturn(null);
		when(request.getParameter("password")).thenReturn(null);
		request = servlet.handleLogin(request, session);

		when(request.getAttribute("message")).thenReturn("Login war nicht erfolgreich.");
		assertEquals(request.getAttribute("message"), "Login war nicht erfolgreich.");
	}

	// T55
	@Test
	public void TestHandleLoginOfStudentServletWithPasswordNotNullAndNotCorrectNegativeTest() {
		StudentServlet servlet = new StudentServlet();
		assertTrue(servlet instanceof StudentServlet);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);

		when(request.getParameter("username")).thenReturn("admin");
		when(request.getParameter("password")).thenReturn("123");
		request = servlet.handleLogin(request, session);

		when(request.getAttribute("message")).thenReturn("Passwort nicht korrekt.");
		assertEquals(request.getAttribute("message"), "Passwort nicht korrekt.");
	}

}
