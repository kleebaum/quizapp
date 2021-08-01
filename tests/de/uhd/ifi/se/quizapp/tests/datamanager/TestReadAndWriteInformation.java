package de.uhd.ifi.se.quizapp.tests.datamanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Information;

public class TestReadAndWriteInformation {

	private DataManager dataManager;

	@Before
	public void setUp() {
		dataManager = new DataManager();
	}

	@Test
	public void testGetInformation() {
		List<Information> informationList = dataManager.getInformation();
		assertTrue(informationList.size() > 0);
	}

	@Test
	public void testGetInformationById() {
		int existingId = dataManager.getInformation().get(0).getInformationId();
		Information information = dataManager.getInformation(existingId);
		assertNotNull(information);
		assertEquals(information.getInformationId(), existingId);
	}

	@Test
	public void testGetInformationByIdNonExisting() {
		Information information = dataManager.getInformation(42000);
		assertNull(information);
	}

	@Test
	public void testInsertInformationWithInformationNotNull() {
		Information information = new Information("Obst", "Obst Obst Obst");
		assertTrue(dataManager.insertInformation(information));
	}

	@Test
	public void testInsertInformationWithUninitializedInformation() {
		assertFalse(dataManager.insertInformation(null));
	}

	@Test
	public void testUpdateInformationWithInformationNotNull() {
		int existingId = dataManager.getInformation().get(0).getInformationId();
		Information information = new Information(existingId, "Obst", "Obst Obst Obst");
		assertTrue(dataManager.updateInformation(information));
	}

	@Test
	public void testUpdateInformationWithUninitializedInformation() {
		Information information = new Information(0, null, null);
		assertFalse(dataManager.updateInformation(information));
	}

	@Test
	public void testDeleteInformation() {
		int existingId = dataManager.getInformation().get(0).getInformationId();
		Information information = new Information(existingId, "Obst", "Obst Obst Obst");
		assertTrue(dataManager.deleteInformation(information));
		dataManager.insertInformation(information);
	}
}