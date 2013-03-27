package string;

public class DiffMatrix {

	private static final String round = "saeoc", straight = "rumn";
	private static final String oblique = "kwzxv";
	private static final String roundUp = "dbh";
	private static final String thinUp = "i1tfl";
	private static final String thinDown = "yj";
	private static final String roundDown = "pqg";
	private static final String bigRound = "098365";
	private static final String bigOblique = "247";
	private static final String punctuation = ".,-";
	
	

	private static final String acceptedChars = "abcdefghijklmnopqrstuvwxyz0123456789.,-";
	private static final String[] groups = {bigOblique, bigRound, round, straight, oblique, roundUp,
			thinUp, thinDown, roundDown, punctuation };

	public static int getDistance(char c1, char c2) {

		if (!acceptedChars.contains("" + c1)
				|| !acceptedChars.contains("" + c2)) {

			System.err.println("Illegal Character Exception");
			return Integer.MAX_VALUE;
		}

		int first = getVirtualMapIndex(c1);
		int second = getVirtualMapIndex(c2);

		return delta(first, second);
	}

	private static int getVirtualMapIndex(char c) {

		int id = getGroup(c);

		if (id < 0) {
			return -1;
		}

		int pos = groups[id].indexOf(c);

		return id * 1000 + pos;
	}

	private static int getGroup(char c) {

		for (int i = 0; i < groups.length; i++) {

			if (groups[i].contains("" + c)) {
				return i;
			}
		}
		return -1;
	}

	private static int delta(int first, int second) {

		return Math.abs(first - second);
	}
}
