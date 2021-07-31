package de.uhd.ifi.se.quizapp.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.BooleanStatement;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceExercise;
import de.uhd.ifi.se.quizapp.model.twochoiceexercise.TwoChoiceResult;

/*
 * AKA: Two-choice-exercise with and without boolean statement
 * AKR: Boolean statements can be right or wrong
 * AKL: Solution of a boolean statement
 * 
 * AKA1: Two-choice-exercise without boolean statement (NumStatement = 0)
 * AKA2: Two-choice-exercise with boolean statement  (NumStatement > 0)
 * 		AKA2.1: Two-choice-exercise with one boolean statement (NumStatement = 1)
 * 		AKA2.2: Two-choice-exercise with more than one boolean statement (NumStatement > 1)
 * 
 * AKR1: All boolean statements of a two-choice-exercise are false.
 * AKR2: Boolean of a two-choice-exercise are both false or true.
 * AKR3: All boolean statements of a two-choice-exercise are true.
 * 
 * AKL1: All boolean statements of a two-choice-exercise were solved incorrectly (getPercentage returns 0).
 * AKL2: Some boolean statements of a two-choice-exercise were solved correctly, others not.
 * AKL3: All boolean statements of a two-choice-exercise were solved correctly (getPercentage returns 1).
 * 
 * UAK: Two-choice-exercise is incorrectly initialized.
 * 
 * Nr	| AKA		| AKR		| AKL		| Expected Output
 * ----------------------------------------------------------
 * 1 	| AKA1		| -			| -			| 0
 * 2	| AKA2.1	| AKR1		| AKL1		| 0
 * 3	| AKA2.1	| AKR1		| AKL3		| 1
 * 4	| AKA2.1	| AKR3		| AKL1		| 0
 * 5	| AKA2.1	| AKR3		| AKL3		| 1
 * 6	| AKA2.2	| AKR1		| AKL1		| 0
 * 7	| AKA2.2	| AKR1		| AKL2		| numberOfCorrectStatements / this.getBooleanStatements().size()
 * 8	| AKA2.2	| AKR1		| AKL3		| 1
 * 9	| AKA2.2	| AKR2		| AKL1		| 0
 * 10	| AKA2.2	| AKR2		| AKL2		| numberOfCorrectStatements / this.getBooleanStatements().size()
 * 11	| AKA2.2	| AKR2		| AKL3		| 1
 * 12	| AKA2.2	| AKR3		| AKL1		| 0
 * 13	| AKA2.2	| AKR3		| AKL2		| numberOfCorrectStatements / this.getBooleanStatements().size()
 * 14	| AKA2.2	| AKR3		| AKL3		| 1
 * 15	| UAK		| -			| -			| -1
 * 
 */

public class TestGetPercentageInTwoChoiceResult {

	@Test
	public void testUAK() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = null;

		result.setExercise(exercise);

		assertTrue(result.getPercentage() == -1);
	}

	@Test
	public void testUAK0() {
		TwoChoiceResult result = new TwoChoiceResult();
		SentencePartExercise exercise = new SentencePartExercise();

		result.setExercise(exercise);

		assertTrue(result.getPercentage() == -1);
	}

	@Test
	public void testAKA1() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();

		result.setExercise(exercise);

		assertTrue(result.getPercentage() == 0);
	}

	@Test
	public void testAKA21_AKR1_AKL1() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", false));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", true));

		assertTrue(result.getPercentage() == 0);
	}

	@Test
	public void testAKA21_AKR1_AKL3() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", false));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", false));

		assertEquals(result.getPercentage(), 1, 0.0f);
	}

	@Test
	public void testAKA21_AKR3_AKL1() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", false));

		assertTrue(result.getPercentage() == 0);
	}

	@Test
	public void testAKA21_AKR3_AKL3() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", true));

		assertTrue(result.getPercentage() == 1);
	}

	@Test
	public void testAKA22_AKR1_AKL1() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", false));
		exercise.getBooleanStatements().add(new BooleanStatement("B", false));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", true));
		result.getBooleanStatements().add(new BooleanStatement("B", true));

		assertTrue(result.getPercentage() == 0);
	}

	@Test
	public void testAKA22_AKR1_AKL2() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", false));
		exercise.getBooleanStatements().add(new BooleanStatement("B", false));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", false));
		result.getBooleanStatements().add(new BooleanStatement("B", true));

		assertTrue(Math.abs(result.getPercentage() - 0.5) < 0.0000001);
	}

	@Test
	public void testAKA22_AKR1_AKL3() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", false));
		exercise.getBooleanStatements().add(new BooleanStatement("B", false));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", false));
		result.getBooleanStatements().add(new BooleanStatement("B", false));

		assertTrue(result.getPercentage() == 1.0);
	}

	@Test
	public void testAKA22_AKR2_AKL1() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", false));
		exercise.getBooleanStatements().add(new BooleanStatement("B", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", true));
		result.getBooleanStatements().add(new BooleanStatement("B", false));

		assertTrue(result.getPercentage() == 0);
	}

	@Test
	public void testAKA22_AKR2_AKL2() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", false));
		exercise.getBooleanStatements().add(new BooleanStatement("B", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", false));
		result.getBooleanStatements().add(new BooleanStatement("B", false));

		assertTrue(Math.abs(result.getPercentage() - 0.5) < 0.0000001);
	}

	@Test
	public void testAKA22_AKR2_AKL3() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", false));
		exercise.getBooleanStatements().add(new BooleanStatement("B", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", false));
		result.getBooleanStatements().add(new BooleanStatement("B", true));

		assertTrue(result.getPercentage() == 1);
	}

	@Test
	public void testAKA22_AKR3_AKL1() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", true));
		exercise.getBooleanStatements().add(new BooleanStatement("B", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", false));
		result.getBooleanStatements().add(new BooleanStatement("B", false));

		assertTrue(result.getPercentage() == 0);
	}

	@Test
	public void testAKA22_AKR3_AKL2() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", true));
		exercise.getBooleanStatements().add(new BooleanStatement("B", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", false));
		result.getBooleanStatements().add(new BooleanStatement("B", true));

		assertTrue(Math.abs(result.getPercentage() - 0.5) < 0.0000001);
	}

	@Test
	public void testAKA22_AKR3_AKL3() {
		TwoChoiceResult result = new TwoChoiceResult();
		TwoChoiceExercise exercise = new TwoChoiceExercise();
		exercise.getBooleanStatements().add(new BooleanStatement("A", true));
		exercise.getBooleanStatements().add(new BooleanStatement("B", true));

		result.setExercise(exercise);
		result.getBooleanStatements().add(new BooleanStatement("A", true));
		result.getBooleanStatements().add(new BooleanStatement("B", true));

		assertTrue(result.getPercentage() == 1);
	}

}
