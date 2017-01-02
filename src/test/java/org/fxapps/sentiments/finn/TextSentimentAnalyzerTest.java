package org.fxapps.sentiments.finn;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TextSentimentAnalyzerTest {
	
	public void afinnWordTest(){
		int sadScore = AFINNWords.get().get("sad");
		int happyScore = AFINNWords.get().get("happy");
		int abandonScore = AFINNWords.get().get("abandon");
		int zealousScore = AFINNWords.get().get("zealous");
		assertEquals(-2, sadScore);
		assertEquals(3, happyScore);
		assertEquals(-2, abandonScore);
		assertEquals(2, zealousScore);
	}
	
	@Test
	public void positiveInputTest() {
		String text = "I am happy.";
		TextAnalisysResult result = TextSentimentAnalyzer.analyze(text);
		assertEquals(3, result.getScore());
		assertEquals(1, result.getDetectedWords().size());
	}
	
	@Test
	public void negativeInputTest() {
		String text = "I am sad.";
		TextAnalisysResult result = TextSentimentAnalyzer.analyze(text);
		assertEquals(-2, result.getScore());
		assertEquals(1, result.getDetectedWords().size());
	}
	
	@Test
	public void mixedInputTest() {
		String text = "I am sad and I am happy.";
		TextAnalisysResult result = TextSentimentAnalyzer.analyze(text);
		assertEquals(1, result.getScore());
		assertEquals(2, result.getDetectedWords().size());
	}

}
