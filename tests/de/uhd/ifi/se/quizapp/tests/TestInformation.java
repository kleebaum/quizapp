package de.uhd.ifi.se.quizapp.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.Information;

public class TestInformation {

	// T23
	@Test
	public void testConstructorOfInformationWithEmptyNameUninitializedText() {
		Information information = new Information("", null);

		assertTrue(information instanceof Information);
		assertNotNull(information);

		assertEquals(information.getName(), "");
		assertNull(information.getText());

		assertTrue(information.getName().isEmpty());
	}

	// T29
	@Test
	public void testConstructorOfInformationWithInitializedNonEmptyIdEmptyNameText() {
		Information information = new Information(1, "", "");

		assertTrue(information instanceof Information);
		assertNotNull(information);

		assertNotNull(information.getInformationId());
		assertEquals(information.getName(), "");
		assertEquals(information.getText(), "");

		assertTrue(information.getName().isEmpty());
		assertTrue(information.getText().isEmpty());

		assertEquals(information.getInformationId(), 1);
	}

	// T27
	@Test
	public void testConstructorOfInformationWithInitializedNonEmptyIdUninitializedNameText() {
		Information information = new Information(1, null, null);

		assertTrue(information instanceof Information);
		assertNotNull(information);

		assertNotNull(information.getInformationId());
		assertNull(information.getName());
		assertNull(information.getText());

		assertEquals(information.getInformationId(), 1);
	}

	// T28
	@Test
	public void testConstructorOfInformationWithInitializedNonEemptyIdNameText() {
		Information information = new Information(1, "Obst und Gemüse", "Text über Obst und Gemüse");

		assertTrue(information instanceof Information);
		assertNotNull(information);

		assertNotNull(information.getInformationId());
		assertEquals(information.getName(), "Obst und Gemüse");
		assertEquals(information.getText(), "Text über Obst und Gemüse");

		assertEquals(information.getInformationId(), 1);
	}

	// T22
	@Test
	public void testConstructorOfInformationWithNonEmptyNameUninitializedText() {
		Information information = new Information("Obst und Gemüse", null);

		assertTrue(information instanceof Information);
		assertNotNull(information);

		assertEquals(information.getName(), "Obst und Gemüse");
		assertNull(information.getText());
	}

	// T26
	@Test
	public void testConstructorOfInformationWithUninitializedNameEmptyText() {
		Information information = new Information(null, "");

		assertTrue(information instanceof Information);
		assertNotNull(information);

		assertNull(information.getName());
		assertEquals(information.getText(), "");
		assertTrue(information.getText().isEmpty());
	}

	// T25
	@Test
	public void testConstructorOfInformationWithUninitializedNameNonEmptyText() {
		Information information = new Information(null, "Obst und Gemüse");

		assertTrue(information instanceof Information);
		assertNotNull(information);

		assertNull(information.getName());
		assertEquals(information.getText(), "Obst und Gemüse");
		assertFalse(information.getText().isEmpty());
	}

	// T24
	@Test
	public void testConstructorOfInformationWithUninitializedNameText() {
		Information information = new Information(null, null);

		assertTrue(information instanceof Information);
		assertNotNull(information);

		assertNull(information.getName());
		assertNull(information.getText());
	}
}
