package org.fxapps.sentiments.finn;

import static java.util.stream.Collectors.toMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class TextSentimentAnalyzer {

	private static final String WORD_SPLIT_REGEX = "\\W";

	private TextSentimentAnalyzer() {
	}
	
	public TextAnalisysResult analyze(Path file) {
		try {
			byte[] text = Files.readAllBytes(file);
			return analyze(new String(text, StandardCharsets.UTF_8));
		} catch (IOException e) {
			throw new Error("Error reading " + file, e);
		}
	}

	public static TextAnalisysResult analyze(URL url) {
		StringBuffer sb = new StringBuffer();
		try {
			URLConnection con = url.openConnection();
			InputStreamReader in = new InputStreamReader(con.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return analyze(sb.toString());
	}

	public static TextAnalisysResult analyze(String text) {
		Map<String, Integer> afinnWords = AFINNWords.get();
		String words[] = text.split(WORD_SPLIT_REGEX);
		AtomicInteger score = new AtomicInteger(0);
		Map<String, Integer> detectedWords = Stream.of(words)
				.filter(w -> afinnWords.containsKey(w))
				.peek(w -> score.addAndGet(afinnWords.get(w)))
				.collect(toMap(w -> w, afinnWords::get));
		double comparative = 0;
		if(words.length > 0) { 
			comparative = new Double(score.get()) / new Double(words.length);
		}
		return new TextAnalisysResult(text, score.get(), comparative, detectedWords);
	}
}
