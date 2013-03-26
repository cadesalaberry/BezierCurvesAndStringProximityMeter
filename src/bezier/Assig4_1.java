package bezier;

import java.awt.image.*;
import java.awt.Graphics2D;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.*;

// Template for assignment 4
public class Assig4_1 {
	public static final int IMAGESIZE = 1024;

	public static ArrayList<DPoint> ctrlPoints = new ArrayList<>();
	public static double t;

	public static void main(String[] args) {
		try {

			readArguments(args);

			// create an image for output. We don't need colour, so greyscale or
			// even binary is ok.
			BufferedImage img = new BufferedImage(IMAGESIZE, IMAGESIZE,
					BufferedImage.TYPE_BYTE_GRAY);

			ArrayList<DPoint> allPoints = BezierHelper.getPoints(t,ctrlPoints);
			
			System.out.println(allPoints);
			drawPointsOnImage(allPoints, img.createGraphics());

			saveImage(img);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void drawPointsOnImage(ArrayList<DPoint> points, Graphics2D g) {

		int x1 = (int) (IMAGESIZE * points.get(0).getX());
		int y1 = (int) (IMAGESIZE * points.get(0).getY());

		for (DPoint p : points) {

			int x2 = (int) (IMAGESIZE * p.getX());
			int y2 = (int) (IMAGESIZE * p.getY());

			g.drawLine(x1, y1, x2, y2);

			x1 = x2;
			y1 = y2;
		}
	}

	public static void readArguments(String[] args) {

		// first arg is the t value
		t = Double.parseDouble(args[0]);

		// Adds the first endpoint at (0,1/2).
		ctrlPoints.add(new DPoint(0.0, 0.5));

		for (int i = 1; i < args.length; i++) {
			ctrlPoints.add(DPoint.parsePoint(args[i]));
		}

		// Adds the endpoint at (1,1/2).
		ctrlPoints.add(new DPoint(1.0, 0.5));
	}

	public static void saveImage(BufferedImage img) throws IOException {

		// write out the image
		File outputfile = new File("outputimage.png");
		ImageIO.write(img, "png", outputfile);
	}

}