package renderer.math;

public class MathUtility {

	public static final double EPSILON;
	public static final double SAFE_MIN;
	private static final long EXPONENT_OFFSET = 1023l;
	
	private static final long HEX_40000000 = 0x40000000L; // 1073741824L
	private static final long MASK_30BITS = -1L - (HEX_40000000 -1); // 0xFFFFFFFFC0000000L;
	
	static {
		/*
		 *  This was previously expressed as = 0x1.0p-53;
		 *  However, OpenJDK (Sparc Solaris) cannot handle such small
		 *  constants: MATH-721
		 */
		EPSILON = Double.longBitsToDouble((EXPONENT_OFFSET - 53l) << 52);

		/*
		 * This was previously expressed as = 0x1.0p-1022;
		 * However, OpenJDK (Sparc Solaris) cannot handle such small
		 * constants: MATH-721
		 */
		SAFE_MIN = Double.longBitsToDouble((EXPONENT_OFFSET - 1022l) << 52);
	}

	
	private static double doubleHighPart(double d) {
		if (d > -SAFE_MIN && d < SAFE_MIN){
			return d; // These are un-normalised - don't try to convert
		}
			long xl = Double.doubleToRawLongBits(d); // can take raw bits because just gonna convert it back
		xl &= MASK_30BITS; // Drop low order bits
		return Double.longBitsToDouble(xl);
	}
	
	// https://home.apache.org/~luc/commons-math-3.6-RC2-site/jacoco/org.apache.commons.math3.util/Precision.java.html#L178
	public static boolean equals(float x, float y, float eps) {
		return equals(x, y, 1) || Math.abs(y - x) <= eps;
	}
	
	public static boolean equals(float x, float y) {
		return equals(x, y, 1);
	}
	
	public static boolean equals(double x, double y, double eps) {
		return equals(x, y, 1) || Math.abs(y - x) <= eps;
	}
	
	public static boolean equals(double x, double y) {
		return equals(x, y, 1);
	}
	
	public static double toRadians(double x) {
		if (Double.isInfinite(x) || x == 0.0) { // Matches +/- 0.0; return correct sign
			return x;
		}

		// These are PI/180 split into high and low order bits
		final double facta = 0.01745329052209854;
		final double factb = 1.997844754509471E-9;

		double xa = doubleHighPart(x);
		double xb = x - xa;

		double result = xb * factb + xb * facta + xa * factb + xa * facta;
		if (result == 0) {
			result *= x; // ensure correct sign if calculation underflows
		}
		return result;
	}
	
	public static double toDegrees(double x)
	{
		if (Double.isInfinite(x) || x == 0.0) { // Matches +/- 0.0; return correct sign
			return x;
		}

		// These are 180/PI split into high and low order bits
		final double facta = 57.2957763671875;
		final double factb = 3.145894820876798E-6;

		double xa = doubleHighPart(x);
		double xb = x - xa;

		return xb * factb + xb * facta + xa * factb + xa * facta;
	}

	
}
