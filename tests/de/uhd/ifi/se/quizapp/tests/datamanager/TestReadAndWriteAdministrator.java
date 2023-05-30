package de.uhd.ifi.se.quizapp.tests.datamanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.Administrator;
import de.uhd.ifi.se.quizapp.model.DataManager;

public class TestReadAndWriteAdministrator {

	private DataManager dataManager;

	@Before
	public void setUp() {
		dataManager = new DataManager();
	}

	@Test
	public void testGetAdminByUsernameWithUsernameInTheDatabase() {
		String username = "admin";
		Administrator administrator = dataManager.getAdministrator(username);
		assertNotNull(administrator);
		assertEquals(administrator.getUsername(), username);
	}

	@Test
	public void testInsertAdministratorInvalid() {
		Administrator admin = new Administrator();
		assertFalse(dataManager.insertAdministrator(admin));

		admin.setUsername("admin");
		assertFalse(dataManager.insertAdministrator(admin));

		admin.setFirstname("Ralph");
		assertFalse(dataManager.insertAdministrator(admin));

		admin.setLastname("Reed");
		assertFalse(dataManager.insertAdministrator(admin));
	}

	@Test
	public void testInsertAdministratorAlreadyExisting() {
		Administrator admin = new Administrator("admin", "Ralph", "Reed", "secret");
		assertFalse(dataManager.insertAdministrator(admin));
	}
}