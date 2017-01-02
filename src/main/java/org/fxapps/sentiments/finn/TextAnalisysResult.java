package org.fxapps.sentiments.finn;

import java.util.Map;

public class TextAnalisysResult {

	private String text;
	private int score;
	private double comparative;
	private Map<String, Integer> detectedWords;
	
	public TextAnalisysResult(String text, int score, double comparative,
			Map<String, Integer> detectedWords) {
		super();
		this.text = text;
		this.score = score;
		this.comparative = comparative;
		this.detectedWords = detectedWords;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getComparative() {
		return comparative;
	}

	public void setComparative(double comparative) {
		this.comparative = comparative;
	}

	public Map<String, Integer> getDetectedWords() {
		return detectedWords;
	}

	public void setDetectedWords(Map<String, Integer> detectedWords) {
		this.detectedWords = detectedWords;
	}

	@Override
	public String toString() {
		return "TextAnalisysResult [text=" + text + ", score=" + score
				+ ", comparative=" + comparative + ", detectedWords="
				+ detectedWords + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(comparative);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((detectedWords == null) ? 0 : detectedWords.hashCode());
		result = prime * result + score;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TextAnalisysResult other = (TextAnalisysResult) obj;
		if (Double.doubleToLongBits(comparative) != Double
				.doubleToLongBits(other.comparative))
			return false;
		if (detectedWords == null) {
			if (other.detectedWords != null)
				return false;
		} else if (!detectedWords.equals(other.detectedWords))
			return false;
		if (score != other.score)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	
	

}
