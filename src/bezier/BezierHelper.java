package bezier;

import java.util.ArrayList;

public class BezierHelper {

	public static ArrayList<DPoint> getPoints(double t,
			ArrayList<DPoint> initialControlPoints) {
		
		// Gets the support points from the initial points
		ArrayList<DPoint> temp = new ArrayList<>();
		temp.addAll(initialControlPoints);
		// Limits to 300 points
		for (int i = 0; i <= 60; i++) {
			System.out.println("Adding points @" + i);
			
			DPoint p = getPointFromt(t, temp);
			
			int j = 0;
			while (temp.get(j).getX() < p.getX()) {j++;}
			temp.add(j+1, p);
		}
		return temp;
	}
	
	public static ArrayList<DPoint> getPoints(
			ArrayList<DPoint> initialControlPoints) {
		
		// Gets t from the control points.
		double step = 0;
		for (int i = 0; i < initialControlPoints.size() - 1; i++) {
			step += getDistance(initialControlPoints.get(i),
					initialControlPoints.get(i + 1));
		}
		step = 1 / step;
		System.out.println("step: " + step);
		// Gets the support points from the initial points
		ArrayList<DPoint> temp = new ArrayList<>();

		for (double t = 0; t <= 1; t = t + step) {
			System.out.println("Adding points with t=" + t);
			temp.add(getPointFromt(t, initialControlPoints));
		}
		return temp;
	}

	/**
	 * Computes the distance between the two given points.
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	private static double getDistance(DPoint p1, DPoint p2) {
		return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2)
				+ Math.pow(p1.getY() - p2.getY(), 2));
	}

	/**
	 * Gets the point with coordinates calculated from the t parameter, and the
	 * initial control points given.
	 * 
	 * @param t
	 * @param ctrlPoints
	 * @return
	 */
	public static DPoint getPointFromt(double t, ArrayList<DPoint> ctrlPoints) {

		int totalPoints = ctrlPoints.size() - 1;
		double dx = 0, dy = 0;

		for (int i = 0; i <= totalPoints; i++) {

			double b = Bernstain(i, totalPoints, t);

			dx += ctrlPoints.get(i).getX() * b;
			dy += ctrlPoints.get(i).getY() * b;
		}
		return new DPoint(dx, dy);
	}

	/**
	 * Computes the Bernstain polynomial of degree n.
	 * 
	 * @param i
	 * @param n
	 * @param t
	 * @return
	 */
	public static double Bernstain(int i, int n, double t) {
		if (n < i) {
			System.err.println("This Bernstain polynomial does not exist.");
			return -1;
		}
		return factorial(n) / (factorial(i) * factorial(n - i))
				* Math.pow(t, i) * Math.pow(1 - t, n - i);
	}

	/**
	 * Computes the factorial up to the given level. Note that no efforts have
	 * been made to try optimize the functions yet.
	 * 
	 * @param level
	 * @return
	 */
	private static long factorial(int level) {
		
		//System.out.println("fact(" + level + ")");
		
		if (level == 0 || level == 1) {
			return 1;
		} else {
			return level * factorial(level - 1);
		}
	}
}
