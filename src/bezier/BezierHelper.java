package bezier;

import java.util.ArrayList;

public class BezierHelper {

	public static final int IMAGESIZE = Assig4_1.IMAGESIZE;
	public static final int ERROR_MAX = IMAGESIZE / 2;
	private static int doesNotMatter = 0;
	private static double t = 0;

	/**
	 * Parses the points, and replaces every middle points by its associated
	 * control points according to the quadratic formula.
	 * 
	 * @param t
	 * @param ctrlPoints
	 * @return newCtrlPoints
	 */
	public static ArrayList<DPoint> interleavePointsQuadratic(double tin,
			ArrayList<DPoint> ctrlPoints) {

		// Stops interleaving points after a 50% failure
		if (doesNotMatter > ERROR_MAX) {
			return ctrlPoints;
		}
		t = tin;
		int size = ctrlPoints.size();

		// Parses the points one by one starting from the end.
		for (int i = size - 2; i > 0; i -= 2) {

			DPoint p0 = ctrlPoints.get(i - 1);
			DPoint p1 = ctrlPoints.remove(i);
			DPoint p2 = ctrlPoints.get(i);

			// Checks that it is worth adding points
			if (distanceMatters(p0, p2)) {
				ctrlPoints.addAll(i, getNewPointsQuadratic(p0, p1, p2));
			} else {
				doesNotMatter++;
				ctrlPoints.add(i, p1);
			}
		}
		return ctrlPoints;
	}

	/**
	 * Parses the points, and replaces every middle points by its associated
	 * control points according to the quadratic formula.
	 * 
	 * @param t
	 * @param ctrlPoints
	 * @return newCtrlPoints
	 */
	public static ArrayList<DPoint> interleavePointsCubic(double tin,
			ArrayList<DPoint> ctrlPoints) {

		// Stops interleaving points after a 50% failure
		if (doesNotMatter > ERROR_MAX) {
			return ctrlPoints;
		}

		t = tin;
		int size = ctrlPoints.size();

		// Parses the points one by one starting from the end.
		for (int i = size - 3; i > 0; i -= 3) {

			DPoint p0 = ctrlPoints.get(i - 1);
			DPoint p1 = ctrlPoints.remove(i);
			DPoint p2 = ctrlPoints.remove(i);
			DPoint p3 = ctrlPoints.get(i);

			// Checks that it is worth adding points
			if (distanceMatters(p0, p3)) {
				ctrlPoints.addAll(i, getNewPointsCubic(p0, p1, p2, p3));
			} else {
				doesNotMatter++;
				ctrlPoints.add(i, p2);
				ctrlPoints.add(i, p1);
			}
		}
		return ctrlPoints;
	}

	/**
	 * Gets the new control points from the three given.
	 * 
	 * @param t
	 * @param ctrlPoints
	 * @return
	 */
	private static ArrayList<DPoint> getNewPointsQuadratic(DPoint p0,
			DPoint p1, DPoint p2) {

		// Uses Bernstain polynomial to find interleaving points.
		DPoint p11 = getMeMyPoint(p1, p0);
		DPoint p21 = getMeMyPoint(p2, p1);
		DPoint p22 = getMeMyPoint(p21, p11);

		ArrayList<DPoint> newCtrlPoints = new ArrayList<DPoint>();

		// Adds the points in order
		newCtrlPoints.add(p11);
		newCtrlPoints.add(p22);
		newCtrlPoints.add(p21);

		return newCtrlPoints;
	}

	/**
	 * Gets the new control points from the four given.
	 * 
	 * @param t
	 * @param ctrlPoints
	 * @return
	 */
	private static ArrayList<DPoint> getNewPointsCubic(DPoint p0, DPoint p1,
			DPoint p2, DPoint p3) {

		// Uses Bernstain polynomial to find interleaving points.
		DPoint p11 = getMeMyPoint(p1, p0);
		DPoint p21 = getMeMyPoint(p2, p1);
		DPoint p31 = getMeMyPoint(p3, p2);
		DPoint p22 = getMeMyPoint(p21, p11);
		DPoint p32 = getMeMyPoint(p31, p21);
		DPoint p33 = getMeMyPoint(p32, p22);

		ArrayList<DPoint> newCtrlPoints = new ArrayList<DPoint>();

		// Adds the points in order
		newCtrlPoints.add(p11);
		newCtrlPoints.add(p22);
		newCtrlPoints.add(p33);
		newCtrlPoints.add(p32);
		newCtrlPoints.add(p31);

		return newCtrlPoints;
	}

	/**
	 * Checks if the points between p0 and p1 will actually make a difference on
	 * the picture.
	 * 
	 * @param p0
	 * @param p1
	 * @return
	 */
	private static boolean distanceMatters(DPoint p0, DPoint p1) {

		boolean xSaturate = Math.abs(p0.x * IMAGESIZE - p1.x * IMAGESIZE) < 1;
		boolean ySaturate = Math.abs(p0.y * IMAGESIZE - p1.y * IMAGESIZE) < 1;

		return !(xSaturate && ySaturate);
	}

	private static DPoint getMeMyPoint(DPoint afterT, DPoint afterParenthesis) {
		return new DPoint(t * afterT.x + (1 - t) * afterParenthesis.x, t
				* afterT.y + (1 - t) * afterParenthesis.y);
	}
}
