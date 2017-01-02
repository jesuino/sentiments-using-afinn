package org.fxapps.sentiments.finn;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import javax.management.RuntimeErrorException;

class AFINNWords {
	
	private static final String AFINN_FILE = "/AFINN-111.txt";
	private static Map<String, Integer> wordsAndSentiments = getAFinnWords();
	
	private static Map<String, Integer> getAFinnWords() {
		try {
			URI AFINNURI = AFINNWords.class.getResource(AFINN_FILE).toURI();
			Path path = Paths.get(AFINNURI);
			return wordsAndSentiments = Files.lines(path)
				.map(l -> l.split("\\t"))
				.collect(Collectors.toMap(l -> l[0], l -> Integer.valueOf(l[1]))
			);
		} catch (Exception e) {
			throw new RuntimeErrorException(new Error(e), "Error loading AFINN words list.");
		}
	}
	
	public static Map<String, Integer> get() {
		return wordsAndSentiments;
	}
	

}