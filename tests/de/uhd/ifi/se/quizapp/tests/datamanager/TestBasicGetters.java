package de.uhd.ifi.se.quizapp.tests.datamanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.DataManager;

public class TestBasicGetters {

	private DataManager dataManager;

	@Before
	public void setUp() {
		dataManager = new DataManager();
	}

	@Test
	public void testGetDbName() {
		assertEquals("org.sqlite.JDBC", dataManager.getDbName());
	}

	@Test
	public void testGetDbURL() {
		assertTrue(dataManager.getDbURL().contains("jdbc:sqlite:"));
		assertTrue(dataManager.getDbURL().contains("heieducation.sqlite"));
	}

	@Test
	public void testGetDbCredentials() {
		assertEquals("", dataManager.getDbUserName());
		assertEquals("", dataManager.getDbPassword());
	}

}
