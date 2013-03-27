package string;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MeasurableString {

	public String string;
	public String[] words;

	public MeasurableString(String s) {
		this.string = s;
		this.words = stringToWords(s);
	}

	public int distance(String s2) {

		MeasurableString ms2 = new MeasurableString(s2);

		return this.distance(ms2);
	}

	public int distance(MeasurableString s2) {

		int distance = 0;

		// Checks that they have the same number of words.
		if (s2.words.length != words.length) {
			return Integer.MAX_VALUE;
		}

		for (int i = 0; i < s2.words.length; i++) {

			// Checks that the number of character is the same
			if (s2.words[i].length() != words[i].length()) {
				return Integer.MAX_VALUE;
			}

			distance += distanceWord(s2.words[i], words[i]);
		}

		return distance;
	}

	/**
	 * Gets rid of spaces by turning the string into an array of words.
	 * 
	 * @param s
	 * @return
	 */
	public static String[] stringToWords(String s) {

		ArrayList<String> strings = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(s);

		while (st.hasMoreTokens()) {
			strings.add(st.nextToken());
		}
		String[] toReturn = new String[strings.size()];
		return strings.toArray(toReturn);
	}

	/**
	 * Gets the distance between the two given words. Assumes the word are of
	 * same length.
	 * 
	 * @param w1
	 * @param w2
	 * @return
	 */
	private int distanceWord(String w1, String w2) {

		int distance = 0;

		for (int i = 0; i < w1.length(); i++) {
			distance += distanceLetter(w1.charAt(i), w2.charAt(i));
		}

		return distance;
	}

	private int distanceLetter(char c1, char c2) {

		return 0;
	}

}
