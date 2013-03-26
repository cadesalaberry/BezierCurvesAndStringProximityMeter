package bezier;

/**
 * utility class for double-based control points
 * 
 * @author cadesalaberry
 * 
 */
class DPoint {
	double x, y;

	public DPoint(double dx, double dy) {
		x = dx;
		y = dy;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	// static method to parse a pair of coords
	public static DPoint parsePoint(String s) throws NumberFormatException {
		int i = s.indexOf(',');
		return new DPoint(Double.parseDouble(s.substring(0, i)),
				Double.parseDouble(s.substring(i + 1)));
	}
	
	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}
}