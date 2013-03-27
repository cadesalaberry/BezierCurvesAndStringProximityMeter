package bezier;

import java.util.ArrayList;

public class BezierHelper {

	public static final int IMAGESIZE = Assig4_1.IMAGESIZE;
	public static final int ERROR_MAX = IMAGESIZE / 2;
	private static int doesNotMatter = 0;

	/**
	 * Parses the points, and replaces every middle points by its associated
	 * control points.
	 * 
	 * @param t
	 * @param ctrlPoints
	 * @return newCtrlPoints
	 */
	public static ArrayList<DPoint> interleavePointsQuadratic(double t,
			ArrayList<DPoint> ctrlPoints) {

		// Stops interleaving points after a 50% failure
		if (doesNotMatter > ERROR_MAX) {
			return ctrlPoints;
		}

		int size = ctrlPoints.size();

		// Parses the points one by one starting from the end.
		for (int i = size - 2; i > 0; i -= 2) {

			DPoint p0 = ctrlPoints.get(i - 1);
			DPoint p1 = ctrlPoints.remove(i);
			DPoint p2 = ctrlPoints.get(i);

			// Checks that it is worth adding points
			if (distanceMatters(p0, p2)) {
				ctrlPoints.addAll(i, getNewPointsQuadratic(t, p0, p1, p2));
			} else {
				doesNotMatter++;
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
	public static ArrayList<DPoint> getNewPointsQuadratic(double t, DPoint p0,
			DPoint p1, DPoint p2) {

		// Uses Bernstain polynomial to find interleaving points.
		DPoint p11 = new DPoint((1 - t) * p0.x + t * p1.x, (1 - t) * p0.y + t
				* p1.y);
		DPoint p21 = new DPoint((1 - t) * p1.x + t * p2.x, (1 - t) * p1.y + t
				* p2.y);
		DPoint p22 = new DPoint((1 - t) * p11.x + t * p21.x, (1 - t) * p11.y
				+ t * p21.y);

		ArrayList<DPoint> newCtrlPoints = new ArrayList<DPoint>();

		// Adds the points in order
		newCtrlPoints.add(p11);
		newCtrlPoints.add(p22);
		newCtrlPoints.add(p21);

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
}
