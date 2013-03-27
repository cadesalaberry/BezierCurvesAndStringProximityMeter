package string;

public class Assig4_2 {
	
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