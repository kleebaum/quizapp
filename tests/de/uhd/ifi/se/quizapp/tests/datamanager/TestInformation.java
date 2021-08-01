package de.uhd.ifi.se.quizapp.tests.datamanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Information;

public class TestInformation {

	private DataManager dataManager;

	@Before
	public void setUp() {
		dataManager = new DataManager();
	}

	@Test
	public void testGetInformation() {
		List<Information> informationList = new ArrayList<Information>();
		informationList = dataManager.getInformation();
		assertTrue(informationList.size() > 0);
	}

	@Test
	public void testGetInformationByID() {
		Information information = dataManager.getInformation(1);
		assertNotNull(information);
		assertEquals(information.getInformationId(), 1);
	}

	@Test
	public void testInsertInformationOfDataManagerWithInformationNotNull() {
		Information information = new Information("Obst", "Obst Obst Obst");
		assertTrue(dataManager.insertInformation(information));
	}

	@Test
	public void testInsertInformationOfDataManagerWithUninitializedInformation() {
		assertFalse(dataManager.insertInformation(null));
	}

	@Test
	public void testUpdateInformationOfDataManagerWithInformationNotNull() {
		Information information = new Information(1, "Obst", "Obst Obst Obst");
		assertTrue(dataManager.updateInformation(information));
	}

	@Test
	public void testUpdateInformationOfDataManagerWithUninitializedInformation() {
		Information information = new Information(0, null, null);
		assertFalse(dataManager.updateInformation(information));
	}
}