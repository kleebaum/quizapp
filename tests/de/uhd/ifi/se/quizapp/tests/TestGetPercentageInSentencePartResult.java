package de.uhd.ifi.se.quizapp.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import de.uhd.ifi.se.quizapp.model.sentencepartexercise.Sentence;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartExercise;
import de.uhd.ifi.se.quizapp.model.sentencepartexercise.SentencePartResult;

/*
 * UAK1 Aufgabe ohne Sätze (NumSentence = 0)
 *  AKS1 Aufgabe enthält Sätze (NumSentence > 0)
 * 	AkS1.1 1 Satz
 * 	AkS1.2 mehrere Sätze
 * 
AKR1: Es gibt nur 1 Satzteil

AKR2: Es gibt mehrere Satzteile

 

AKL1: Alle Sätze wurden falsch gelöst.

AKL2: Die Sätze wurden teilweise falsch gelöst.

AKL3: Alle Sätze wurden richtig gelöst.
 * 
 * AKS1.1	AKR1	AKL1	0
 * AKS1.1	AKR1	AKL3	1
 * AKS1.1	AKR2	AKL1	0
 * AKS1.1	AKR2	AKL2	0
 * AKS1.1	AKR2	AKL3	1
 * UAK						-1
 * 
 * AKS1.2	AKR1	AKL1	0
 * AKS1.2	AKR1	AKL3	1
 * AKS1.2	AKR2	AKL1	0
 * AKS1.2	AKR2	AKL2	0
 * AKS1.2	AKR2	AKL3	1
 * 
 * 
 */

public class TestGetPercentageInSentencePartResult {

	@Test
	public void testUAk() {
		SentencePartResult result = new SentencePartResult();
		SentencePartExercise exercise = null;

		result.setExercise(exercise);

		assertTrue(result.getPercentage() == -1);
	}

	@Test
	public void testAk11_AKR1_AKL1() {
		SentencePartResult result = new SentencePartResult();
		SentencePartExercise exercise = new SentencePartExercise();

		ArrayList<String> sentenceParts = new ArrayList<String>();
		sentenceParts.add("1");

		Sentence sentence = new Sentence(sentenceParts);
		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		sentences.add(sentence);

		exercise.setSentences(sentences);

		result.setExercise(exercise);

		sentenceParts.remove(0);
		sentences.remove(0);
		result.setSentences(sentences);

		assertTrue(result.getPercentage() == 0);
	}

	@Test
	public void testAk11_AKR1_AKL3() {
		SentencePartResult result = new SentencePartResult();
		SentencePartExercise exercise = new SentencePartExercise();

		ArrayList<String> sentenceParts = new ArrayList<String>();
		sentenceParts.add("1");

		Sentence sentence = new Sentence(sentenceParts);
		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		sentences.add(sentence);

		exercise.setSentences(sentences);

		result.setExercise(exercise);
		result.setSentences(sentences);

		assertTrue(result.getPercentage() == 1);
	}

	@Test
	public void testAk11_AKR2_AKL1() {
		SentencePartResult result = new SentencePartResult();
		SentencePartExercise exercise = new SentencePartExercise();

		ArrayList<String> sentenceParts = new ArrayList<String>();
		sentenceParts.add("1");
		sentenceParts.add("2");
		sentenceParts.add("3");

		Sentence sentence = new Sentence(sentenceParts);
		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		sentences.add(sentence);

		exercise.setSentences(sentences);

		result.setExercise(exercise);

		ArrayList<String> sentencePartsResult = new ArrayList<String>();
		sentenceParts.add("3");
		sentenceParts.add("1");
		sentenceParts.add("2");

		Sentence sentenceResult = new Sentence(sentencePartsResult);
		ArrayList<Sentence> sentencesResult = new ArrayList<Sentence>();
		sentencesResult.add(sentenceResult);

		result.setSentences(sentencesResult);

		assertTrue(result.getPercentage() == 0);
	}

	@Test
	public void testAk11_AKR2_AKL2() {
		SentencePartResult result = new SentencePartResult();
		SentencePartExercise exercise = new SentencePartExercise();

		ArrayList<String> sentenceParts = new ArrayList<String>();
		sentenceParts.add("1");
		sentenceParts.add("2");
		sentenceParts.add("3");

		Sentence sentence = new Sentence(sentenceParts);
		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		sentences.add(sentence);

		exercise.setSentences(sentences);

		result.setExercise(exercise);

		ArrayList<String> sentencePartsResult = new ArrayList<String>();
		sentenceParts.add("1"); // Richtiges Satzteil
		sentenceParts.add("3");
		sentenceParts.add("2");

		Sentence sentenceResult = new Sentence(sentencePartsResult);
		ArrayList<Sentence> sentencesResult = new ArrayList<Sentence>();
		sentencesResult.add(sentenceResult);

		result.setSentences(sentencesResult);

		assertTrue(result.getPercentage() == 0);
	}

	@Test
	public void testAk11_AKR2_AKL3() {
		SentencePartResult result = new SentencePartResult();
		SentencePartExercise exercise = new SentencePartExercise();

		ArrayList<String> sentenceParts = new ArrayList<String>();
		sentenceParts.add("1");
		sentenceParts.add("2");
		sentenceParts.add("3");

		Sentence sentence = new Sentence(sentenceParts);
		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		sentences.add(sentence);

		exercise.setSentences(sentences);

		result.setExercise(exercise);

		result.setSentences(sentences);

		assertTrue(result.getPercentage() == 1);
	}

	// AKS1.2 AKR1 AKL1 0
	@Test
	public void testAk12_AKR1_AKL1() {
		SentencePartResult result = new SentencePartResult();
		SentencePartExercise exercise = new SentencePartExercise();

		ArrayList<String> sentenceParts = new ArrayList<String>();
		sentenceParts.add("1");

		Sentence sentence = new Sentence(sentenceParts);

		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		sentences.add(sentence);
		sentences.add(sentence);
		sentences.add(sentence);

		exercise.setSentences(sentences);

		ArrayList<Sentence> sentencesResult = new ArrayList<Sentence>(); // EmptyResult

		result.setExercise(exercise);

		result.setSentences(sentencesResult);

		assertTrue(result.getPercentage() == 0);
	}

	// AKS1.2 AKR1 AKL3 1
	@Test
	public void testAk12_AKR1_AKL3() {
		SentencePartResult result = new SentencePartResult();
		SentencePartExercise exercise = new SentencePartExercise();

		Sentence sentence = new Sentence(Arrays.asList("1"));
		Sentence sentence2 = new Sentence(Arrays.asList("2"));
		Sentence sentence3 = new Sentence(Arrays.asList("3"));

		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		sentences.add(sentence);
		sentences.add(sentence2);
		sentences.add(sentence3);

		exercise.setSentences(sentences);

		result.setExercise(exercise);

		result.setSentences(sentences);

		assertTrue(result.getPercentage() == 1);
	}

	// AKS1.2 AKR2 AKL1 0
	@Test
	public void testAk12_AKR2_AKL1() {
		SentencePartResult result = new SentencePartResult();
		SentencePartExercise exercise = new SentencePartExercise();

		Sentence sentence = new Sentence(Arrays.asList("1", "2"));
		Sentence sentence2 = new Sentence(Arrays.asList("3", "4"));
		Sentence sentence3 = new Sentence(Arrays.asList("5", "6"));

		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		sentences.add(sentence);
		sentences.add(sentence2);
		sentences.add(sentence3);

		exercise.setSentences(sentences);

		result.setExercise(exercise);

		Sentence sentenceResult = new Sentence(Arrays.asList("6", "4"));
		Sentence sentence2Result = new Sentence(Arrays.asList("1", "3"));
		Sentence sentence3Result = new Sentence(Arrays.asList("5", "2"));

		ArrayList<Sentence> sentencesResult = new ArrayList<Sentence>();
		sentences.add(sentenceResult);
		sentences.add(sentence2Result);
		sentences.add(sentence3Result);

		result.setSentences(sentencesResult);

		assertTrue(result.getPercentage() == 0);
	}

	// AKS1.2 AKR2 AKL2 0.5
	@Test
	public void testAk12_AKR2_AKL2() {
		SentencePartResult result = new SentencePartResult();
		SentencePartExercise exercise = new SentencePartExercise();

		Sentence sentence = new Sentence(Arrays.asList("1", "2"));
		Sentence sentence2 = new Sentence(Arrays.asList("3", "4"));
		Sentence sentence3 = new Sentence(Arrays.asList("5", "6"));
		Sentence sentence4 = new Sentence(Arrays.asList("7", "8"));

		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		sentences.add(sentence);
		sentences.add(sentence2);
		sentences.add(sentence3);
		sentences.add(sentence4);

		exercise.setSentences(sentences);

		result.setExercise(exercise);

		Sentence sentenceResult = new Sentence(Arrays.asList("1", "2")); // richtig
		Sentence sentence2Result = new Sentence(Arrays.asList("3", "4")); // richtig
		Sentence sentence3Result = new Sentence(Arrays.asList("7", "5")); // falsch
		Sentence sentence4Result = new Sentence(Arrays.asList("8", "6")); // falsch

		ArrayList<Sentence> sentencesResult = new ArrayList<Sentence>();
		sentencesResult.add(sentenceResult);
		sentencesResult.add(sentence2Result);
		sentencesResult.add(sentence3Result);
		sentencesResult.add(sentence4Result);

		result.setSentences(sentencesResult);

		assertTrue(result.getPercentage() == 0.5);
	}

	// AKS1.2 AKR2 AKL3 1
	@Test
	public void testAk12_AKR2_AKL3() {
		SentencePartResult result = new SentencePartResult();
		SentencePartExercise exercise = new SentencePartExercise();

		Sentence sentence = new Sentence(Arrays.asList("1", "2"));
		Sentence sentence2 = new Sentence(Arrays.asList("3", "4"));
		Sentence sentence3 = new Sentence(Arrays.asList("5", "6"));
		Sentence sentence4 = new Sentence(Arrays.asList("7", "8"));

		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		sentences.add(sentence);
		sentences.add(sentence2);
		sentences.add(sentence3);
		sentences.add(sentence4);

		exercise.setSentences(sentences);

		result.setExercise(exercise);

		result.setSentences(sentences);

		assertTrue(result.getPercentage() == 1);
	}
}
