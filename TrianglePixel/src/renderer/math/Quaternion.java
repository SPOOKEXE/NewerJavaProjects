package renderer.math;

import java.io.Serializable;

// https://home.apache.org/~luc/commons-math-3.6-RC2-site/jacoco/org.apache.commons.math3.complex/Quaternion.java.html
// https://home.apache.org/~luc/commons-math-3.6-RC2-site/jacoco/org.apache.commons.math3.util/FastMath.html
// https://home.apache.org/~luc/commons-math-3.6-RC2-site/jacoco/org.apache.commons.math3.util/MathUtils.html
// https://home.apache.org/~luc/commons-math-3.6-RC2-site/jacoco/org.apache.commons.math3.util/Precision.html

public final class Quaternion implements Serializable {
	/** Identity quaternion. */
	public static final Quaternion IDENTITY = new Quaternion(1, 0, 0, 0);
	/** Zero quaternion. */
	public static final Quaternion ZERO = new Quaternion(0, 0, 0, 0);
	/** i */
	public static final Quaternion I = new Quaternion(0, 1, 0, 0);
	/** j */
	public static final Quaternion J = new Quaternion(0, 0, 1, 0);
	/** k */
	public static final Quaternion K = new Quaternion(0, 0, 0, 1);

	/** Serializable version identifier. */
	private static final long serialVersionUID = 20092012L;

	/** First component (scalar part). */
	private final double q0;
	/** Second component (first vector part). */
	private final double q1;
	/** Third component (second vector part). */
	private final double q2;
	/** Fourth component (third vector part). */
	private final double q3;

	public Quaternion(final double a, final double b, final double c, final double d) {
		this.q0 = a;
		this.q1 = b;
		this.q2 = c;
		this.q3 = d;
	}

	public Quaternion(final double scalar, final double[] v) {
		this.q0 = scalar;
		this.q1 = v[0];
		this.q2 = v[1];
		this.q3 = v[2];
	}
	
	public Quaternion(final double[] v) {
		this(0, v);
	}

	public Quaternion getConjugate() {
		return new Quaternion(q0, -q1, -q2, -q3);
	}

	public static Quaternion fromAxisAndAngle(Vector3 axis, double angle) {
		double s = (double) Math.sin(angle / 2);
		
		double w = (double) Math.cos(angle / 2);
		double x = axis.x * s;
		double y = axis.y * s;
		double z = axis.z * s;
		return new Quaternion(w, x, y, z);
	}
	
	public static Quaternion multiply(final Quaternion q1, final Quaternion q2) {
		// Components of the first quaternion.
		final double q1a = q1.getQ0();
		final double q1b = q1.getQ1();
		final double q1c = q1.getQ2();
		final double q1d = q1.getQ3();

		// Components of the second quaternion.
		final double q2a = q2.getQ0();
		final double q2b = q2.getQ1();
		final double q2c = q2.getQ2();
		final double q2d = q2.getQ3();

		// Components of the product.
		final double w = q1a * q2a - q1b * q2b - q1c * q2c - q1d * q2d;
		final double x = q1a * q2b + q1b * q2a + q1c * q2d - q1d * q2c;
		final double y = q1a * q2c - q1b * q2d + q1c * q2a + q1d * q2b;
		final double z = q1a * q2d + q1b * q2c - q1c * q2b + q1d * q2a;

		return new Quaternion(w, x, y, z);
	}

	public Quaternion multiply(final Quaternion q) {
		return multiply(this, q);
	}

	public static Quaternion add(final Quaternion q1, final Quaternion q2) {
		return new Quaternion(q1.getQ0() + q2.getQ0(), q1.getQ1() + q2.getQ1(), q1.getQ2() + q2.getQ2(), q1.getQ3() + q2.getQ3());
	}

	public Quaternion add(final Quaternion q) {
		return add(this, q);
	}

	public static Quaternion subtract(final Quaternion q1, final Quaternion q2) {
		return new Quaternion(q1.getQ0() - q2.getQ0(), q1.getQ1() - q2.getQ1(), q1.getQ2() - q2.getQ2(), q1.getQ3() - q2.getQ3());
	}

	public Quaternion subtract(final Quaternion q) {
		return subtract(this, q);
	}

	public static double dotProduct(final Quaternion q1, final Quaternion q2) {
		return q1.getQ0() * q2.getQ0() + q1.getQ1() * q2.getQ1() + q1.getQ2() * q2.getQ2() + q1.getQ3() * q2.getQ3();
	}

	public double dotProduct(final Quaternion q) {
		return dotProduct(this, q);
	}

	public double getNorm() {
		return Math.sqrt(q0 * q0 + q1 * q1 + q2 * q2 + q3 * q3);
	}

	public Quaternion normalize() {
		double norm = getNorm();
		if (norm < 0.00001) {
			norm = 0.00001;
		}
		return new Quaternion(q0 / norm, q1 / norm, q2 / norm, q3 / norm);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other instanceof Quaternion) {
			final Quaternion q = (Quaternion) other;
			return q0 == q.getQ0() &&
				q1 == q.getQ1() &&
				q2 == q.getQ2() &&
				q3 == q.getQ3();
		}

		return false;
	}

	@Override
	public int hashCode() {
		// "Effective Java" (second edition, p. 47).
		int result = 17;
		for (double comp : new double[] { q0, q1, q2, q3 }) {
			final int c = Double.hashCode(comp);
			result = 31 * result + c;
		}
		return result;
	}

	public boolean equals(final Quaternion q,
						  final double eps) {
		return MathUtility.equals(q0, q.getQ0(), eps) &&
				MathUtility.equals(q1, q.getQ1(), eps) &&
				MathUtility.equals(q2, q.getQ2(), eps) &&
				MathUtility.equals(q3, q.getQ3(), eps);
	}

	public boolean isUnitQuaternion(double eps) {
		return MathUtility.equals(getNorm(), 1d, eps);
	}

	public boolean isPureQuaternion(double eps) {
		return Math.abs(getQ0()) <= eps;
	}

	public Quaternion getPositivePolarForm() {
		if (getQ0() < 0) {
			final Quaternion unitQ = normalize();
			// The quaternion of rotation (normalized quaternion) q and -q
			// are equivalent (i.e. represent the same rotation).
			return new Quaternion(-unitQ.getQ0(),
								  -unitQ.getQ1(),
								  -unitQ.getQ2(),
								  -unitQ.getQ3());
		} else {
			return this.normalize();
		}
	}

	public Quaternion getInverse() {
		double squareNorm = q0 * q0 + q1 * q1 + q2 * q2 + q3 * q3;
		if (squareNorm < 0.000001) {
			squareNorm = 0.000001;
		}
		return new Quaternion(q0 / squareNorm, -q1 / squareNorm, -q2 / squareNorm, -q3 / squareNorm);
	}

	public double getQ0() {
		return q0;
	}

	public double getQ1() {
		return q1;
	}

	public double getQ2() {
		return q2;
	}

	public double getQ3() {
		return q3;
	}

	public double getScalarPart() {
		return getQ0();
	}

	public double[] getVectorPart() {
		return new double[] { getQ1(), getQ2(), getQ3() };
	}
	
	public float[] toMatrix() {
		float[] matrixs = new float[16];
		
		double w = this.q0;
		double x = this.q1;
		double y = this.q2;
		double z = this.q3;
		
		matrixs[3] = 0.0f;
		matrixs[7] = 0.0f;
		matrixs[11] = 0.0f;
		matrixs[12] = 0.0f;
		matrixs[13] = 0.0f;
		matrixs[14] = 0.0f;
		matrixs[15] = 1.0f;

		matrixs[0] = (float) (1.0f - (2.0f * ((y * y) + (z * z))));
		matrixs[1] = (float) (2.0f * ((x * y) - (z * w)));
		matrixs[2] = (float) (2.0f * ((x * z) + (y * w)));
		
		matrixs[4] = (float) (2.0f * ((x * y) + (z * w)));
		matrixs[5] = (float) (1.0f - (2.0f * ((x * x) + (z * z))));
		matrixs[6] = (float) (2.0f * ((y * z) - (x * w)));
		
		matrixs[8] = (float) (2.0f * ((x * z) - (y * w)));
		matrixs[9] = (float) (2.0f * ((y * z) + (x * w)));
		matrixs[10] = (float) (1.0f - (2.0f * ((x * x) + (y * y))));
		
		return matrixs;
	}

	public Quaternion multiply(final double alpha) {
		return new Quaternion(alpha * q0, alpha * q1, alpha * q2, alpha * q3);
	}

	@Override
	public String toString() {
		final String sp = " ";
		final StringBuilder s = new StringBuilder();
		s.append("[")
			.append(q0).append(sp)
			.append(q1).append(sp)
			.append(q2).append(sp)
			.append(q3)
			.append("]");
		return s.toString();
	}
	
}