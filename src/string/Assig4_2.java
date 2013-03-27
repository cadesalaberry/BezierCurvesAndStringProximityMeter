package string;

public class Assig4_2 {
	
	private static final String regexRound = "[eoacs]";
	private static final String regexRoundUp = "[dbh]";
	private static final String regexRoundDown = "[pqg]";
	private static final String regexThinUp = "[tifl]";
	private static final String regexThinDown = "[yj]";
	private static final String regexOblique = "[kwzxv]";
	private static final String regexStraight = "[rumn]";
	private static final String[][] groups = {};
	
	public static void main(String[] args) {
		
		if (args.length != 2) {
			System.err.println("Wrong number of arguments.");
			return;
		}
		
		MeasurableString first = new MeasurableString(args[0]);
		MeasurableString second = new MeasurableString(args[1]);
		
		System.out.println(first.distance(second));
		
	}
}