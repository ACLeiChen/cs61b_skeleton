import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.math.*;

public class TestPlanet {
	public static void main(String[] args) {
		Planet kobe1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0,"kevin.gif");
		Planet kobe2 = new Planet(4.0, 5.0, 3.0, 4.0, 5.0,"ball.gif");
		double pairwise_force;
		pairwise_force = kobe1.calcForceExertedBy(kobe2);
		checkEquals(kobe1.calcForceExertedBy(kobe2), 6.67e-11, "pairwise force: calcForceExertedBy()", 0.01);
	}

	 /**
     *  Checks whether or not two Doubles are equal and prints the result.
     *
     *  @param  expected    Expected double
     *  @param  actual      Double received
     *  @param  label   Label for the 'test' case
     *  @param  eps     Tolerance for the double comparison.
     */
    private static void checkEquals(double actual, double expected, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }
}