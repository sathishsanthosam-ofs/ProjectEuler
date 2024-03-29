package com.project.euler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Arbitrary-precision fractions, utilizing BigIntegers for numerator and denominator. Fraction is always kept in lowest
 * terms. Fraction is immutable, and guaranteed not to have a null numerator or denominator. Denominator will always be
 * positive (so sign is carried by numerator, and a zero-denominator is impossible).
 */
public final class BigFraction extends Number
        implements Comparable<BigFraction>
{
    private static final long serialVersionUID = 1L; // because Number is Serializable

    private final BigInteger numerator;

    private final BigInteger denominator;

    public final static BigFraction ZERO = new BigFraction(BigInteger.ZERO, BigInteger.ONE, true);

    public final static BigFraction ONE = new BigFraction(BigInteger.ONE, BigInteger.ONE, true);

    public final static BigFraction TWO = new BigFraction(new BigInteger("2"), BigInteger.ONE, true);

    /**
     * Constructs a BigFraction with given numerator and denominator. Fraction will be reduced to lowest terms. If
     * fraction is negative, negative sign will be carried on numerator, regardless of how the values were passed in.
     */
    public BigFraction(BigInteger numerator, BigInteger denominator)
    {
        if (numerator == null)
        {
            throw new IllegalArgumentException("Numerator is null");
        }
        if (denominator == null)
        {
            throw new IllegalArgumentException("Denominator is null");
        }
        if (denominator.equals(BigInteger.ZERO))
        {
            throw new ArithmeticException("Divide by zero.");
        }

        // only numerator should be negative.
        if (denominator.signum() < 0)
        {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }

        // create a reduced fraction
        final BigInteger gcd = numerator.gcd(denominator);
        this.numerator = numerator.divide(gcd);
        this.denominator = denominator.divide(gcd);
    }

    /**
     * Constructs a BigFraction from a whole number.
     */
    public BigFraction(final BigInteger numerator)
    {
        this(numerator, BigInteger.ONE, true);
    }

    public BigFraction(final long numerator, final long denominator)
    {
        this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    public BigFraction(final long numerator)
    {
        this(BigInteger.valueOf(numerator), BigInteger.ONE, true);
    }

    /**
     * Constructs a BigFraction from a floating-point number.
     * 
     * Warning: round-off error in IEEE floating point numbers can result in answers that are unexpected. For example,
     * System.out.println(new BigFraction(1.1)) will print: 2476979795053773/2251799813685248
     * 
     * This is because 1.1 cannot be expressed exactly in binary form. The given fraction is exactly equal to the
     * internal representation of the double-precision floating-point number. (Which, for 1.1, is: (-1)^0 * 2^0 * (1 +
     * 0x199999999999aL / 0x10000000000000L).)
     * 
     * NOTE: In many cases, BigFraction(Double.toString(d)) may give a result closer to what the user expects.
     */
    public BigFraction(final double d)
    {
        if (Double.isInfinite(d))
        {
            throw new IllegalArgumentException("double val is infinite");
        }
        if (Double.isNaN(d))
        {
            throw new IllegalArgumentException("double val is NaN");
        }

        // special case - math below won't work right for 0.0 or -0.0
        if (d == 0)
        {
            numerator = BigInteger.ZERO;
            denominator = BigInteger.ONE;
            return;
        }

        final long bits = Double.doubleToLongBits(d);
        final int sign = (int) (bits >> 63) & 0x1;
        final int exponent = ((int) (bits >> 52) & 0x7ff) - 0x3ff;
        final long mantissa = bits & 0xfffffffffffffL;

        // number is (-1)^sign * 2^(exponent) * 1.mantissa
        BigInteger tmpNumerator = BigInteger.valueOf(sign == 0 ? 1 : -1);
        BigInteger tmpDenominator = BigInteger.ONE;

        // use shortcut: 2^x == 1 << x. if x is negative, shift the denominator
        if (exponent >= 0)
        {
            tmpNumerator = tmpNumerator.multiply(BigInteger.ONE.shiftLeft(exponent));
        }
        else
        {
            tmpDenominator = tmpDenominator.multiply(BigInteger.ONE.shiftLeft(-exponent));
        }

        // 1.mantissa == 1 + mantissa/2^52 == (2^52 + mantissa)/2^52
        tmpDenominator = tmpDenominator.multiply(BigInteger.valueOf(0x10000000000000L));
        tmpNumerator = tmpNumerator.multiply(BigInteger.valueOf(0x10000000000000L + mantissa));

        final BigInteger gcd = tmpNumerator.gcd(tmpDenominator);
        numerator = tmpNumerator.divide(gcd);
        denominator = tmpDenominator.divide(gcd);
    }

    /**
     * Constructs a BigFraction from two floating-point numbers.
     * 
     * Warning: round-off error in IEEE floating point numbers can result in answers that are unexpected. See
     * BigFraction(double) for more information.
     * 
     * NOTE: In many cases, BigFraction(Double.toString(numerator) + "/" + Double.toString(denominator)) may give a
     * result closer to what the user expects.
     */
    public BigFraction(final double numerator, final double denominator)
    {
        if (denominator == 0)
        {
            throw new ArithmeticException("Divide by zero.");
        }

        final BigFraction tmp = new BigFraction(numerator).divide(new BigFraction(denominator));
        this.numerator = tmp.numerator;
        this.denominator = tmp.denominator;
    }

    /**
     * Constructs a new BigFraction from the given BigDecimal object.
     */
    public BigFraction(final BigDecimal d)
    {
        this(d.scale() < 0 ? d.unscaledValue().multiply(BigInteger.TEN.pow(-d.scale())) : d.unscaledValue(),
                d.scale() < 0 ? BigInteger.ONE : BigInteger.TEN.pow(d.scale()));
    }

    public BigFraction(final BigDecimal numerator, final BigDecimal denominator)
    {
        if (denominator.equals(BigDecimal.ZERO))
        {
            throw new ArithmeticException("Divide by zero.");
        }

        final BigFraction tmp = new BigFraction(numerator).divide(new BigFraction(denominator));
        this.numerator = tmp.numerator;
        this.denominator = tmp.denominator;
    }

    /**
     * Constructs a BigFraction from a String. Expected format is numerator/denominator, but /denominator part is
     * optional. Either numerator or denominator may be a floating- point decimal number, which in the same format as a
     * parameter to the <code>BigDecimal(String)</code> constructor.
     * 
     * @throws NumberFormatException if the string cannot be properly parsed.
     */
    public BigFraction(final String s)
    {
        final int slashPos = s.indexOf('/');
        if (slashPos < 0)
        {
            final BigFraction res = new BigFraction(new BigDecimal(s));
            this.numerator = res.numerator;
            this.denominator = res.denominator;
        }
        else
        {
            final BigDecimal num = new BigDecimal(s.substring(0, slashPos));
            final BigDecimal den = new BigDecimal(s.substring(slashPos + 1, s.length()));
            final BigFraction res = new BigFraction(num, den);
            this.numerator = res.numerator;
            this.denominator = res.denominator;
        }
    }

    /**
     * Returns this + f.
     */
    public BigFraction add(final BigFraction f)
    {
        if (f == null)
        {
            throw new IllegalArgumentException("Null argument");
        }

        // n1/d1 + n2/d2 = (n1*d2 + d1*n2)/(d1*d2)
        return new BigFraction(numerator.multiply(f.denominator).add(denominator.multiply(f.numerator)),
                denominator.multiply(f.denominator));
    }

    /**
     * Returns this + b.
     */
    public BigFraction add(final BigInteger b)
    {
        if (b == null)
        {
            throw new IllegalArgumentException("Null argument");
        }

        // n1/d1 + n2 = (n1 + d1*n2)/d1
        return new BigFraction(numerator.add(denominator.multiply(b)), denominator, true);
    }

    /**
     * Returns this + n.
     */
    public BigFraction add(final long n)
    {
        return add(BigInteger.valueOf(n));
    }

    /**
     * Returns this - f.
     */
    public BigFraction subtract(final BigFraction f)
    {
        if (f == null)
        {
            throw new IllegalArgumentException("Null argument");
        }

        return new BigFraction(numerator.multiply(f.denominator).subtract(denominator.multiply(f.numerator)),
                denominator.multiply(f.denominator));
    }

    /**
     * Returns this - b.
     */
    public BigFraction subtract(final BigInteger b)
    {
        if (b == null)
        {
            throw new IllegalArgumentException("Null argument");
        }

        return new BigFraction(numerator.subtract(denominator.multiply(b)), denominator, true);
    }

    /**
     * Returns this - n.
     */
    public BigFraction subtract(final long n)
    {
        return subtract(BigInteger.valueOf(n));
    }

    /**
     * Returns this * f.
     */
    public BigFraction multiply(final BigFraction f)
    {
        if (f == null)
        {
            throw new IllegalArgumentException("Null argument");
        }

        return new BigFraction(numerator.multiply(f.numerator), denominator.multiply(f.denominator));
    }

    /**
     * Returns this * b.
     */
    public BigFraction multiply(final BigInteger b)
    {
        if (b == null)
        {
            throw new IllegalArgumentException("Null argument");
        }

        return new BigFraction(numerator.multiply(b), denominator);
    }

    /**
     * Returns this * n.
     */
    public BigFraction multiply(final long n)
    {
        return multiply(BigInteger.valueOf(n));
    }

    /**
     * Returns this / f.
     */
    public BigFraction divide(final BigFraction f)
    {
        if (f == null)
        {
            throw new IllegalArgumentException("Null argument");
        }

        if (f.numerator.equals(BigInteger.ZERO))
        {
            throw new ArithmeticException("Divide by zero");
        }

        return new BigFraction(numerator.multiply(f.denominator), denominator.multiply(f.numerator));
    }

    /**
     * Returns this / b.
     */
    public BigFraction divide(final BigInteger b)
    {
        if (b == null)
        {
            throw new IllegalArgumentException("Null argument");
        }

        if (b.equals(BigInteger.ZERO))
        {
            throw new ArithmeticException("Divide by zero");
        }

        return new BigFraction(numerator, denominator.multiply(b));
    }

    /**
     * Returns this / n.
     */
    public BigFraction divide(final long n)
    {
        return divide(BigInteger.valueOf(n));
    }

    /**
     * Returns this^exponent.
     */
    public BigFraction pow(final int exponent)
    {
        if (exponent == 0)
        {
            return BigFraction.ONE;
        }
        else if (exponent == 1)
        {
            return this;
        }
        else if (exponent < 0)
        {
            return new BigFraction(denominator.pow(-exponent), numerator.pow(-exponent), true);
        }
        else
        {
            return new BigFraction(numerator.pow(exponent), denominator.pow(exponent), true);
        }
    }

    /**
     * Returns 1/this.
     */
    public BigFraction reciprocal()
    {
        if (this.numerator.equals(BigInteger.ZERO))
        {
            throw new ArithmeticException("Divide by zero");
        }

        return new BigFraction(denominator, numerator, true);
    }

    /**
     * Returns the complement of this fraction, which is equal to 1 - this. Useful for probabilities/statistics.
     */
    public BigFraction complement()
    {
        return new BigFraction(denominator.subtract(numerator), denominator, true);
    }

    /**
     * Returns -this.
     */
    public BigFraction negate()
    {
        return new BigFraction(numerator.negate(), denominator, true);
    }

    /**
     * Returns -1, 0, or 1, representing the sign of this fraction.
     */
    public int signum()
    {
        return numerator.signum();
    }

    /**
     * Returns the absolute value of this.
     */
    public BigFraction abs()
    {
        return (signum() < 0 ? negate() : this);
    }

    /**
     * Returns a string representation of this, in the form numerator/denominator.
     */
    public String toString()
    {
        return numerator.toString() + "/" + denominator.toString();
    }

    /**
     * Returns if this object is equal to another object.
     */
    public boolean equals(final Object o)
    {
        if (!(o instanceof BigFraction))
        {
            return false;
        }

        final BigFraction f = (BigFraction) o;
        return numerator.equals(f.numerator) && denominator.equals(f.denominator);
    }

    /**
     * Returns a hash code for this object.
     */
    public int hashCode()
    {
        // using the method generated by Eclipse, but streamlined a bit..
        return (31 + numerator.hashCode()) * 31 + denominator.hashCode();
    }

    /**
     * Returns a negative, zero, or positive number, indicating if this object is less than, equal to, or greater than
     * f, respectively.
     */
    public int compareTo(final BigFraction f)
    {
        if (f == null)
        {
            throw new IllegalArgumentException("Null argument");
        }

        // easy case: this and f have different signs
        if (signum() != f.signum())
        {
            return signum() - f.signum();
        }

        // next easy case: this and f have the same denominator
        if (denominator.equals(f.denominator))
        {
            return numerator.compareTo(f.numerator);
        }

        // not an easy case, so first make the denominators equal then compare the numerators
        return numerator.multiply(f.denominator).compareTo(denominator.multiply(f.numerator));
    }

    /**
     * Returns the smaller of this and f.
     */
    public BigFraction min(final BigFraction f)
    {
        if (f == null)
        {
            throw new IllegalArgumentException("Null argument");
        }

        return (this.compareTo(f) <= 0 ? this : f);
    }

    /**
     * Returns the maximum of this and f.
     */
    public BigFraction max(final BigFraction f)
    {
        if (f == null)
        {
            throw new IllegalArgumentException("Null argument");
        }

        return (this.compareTo(f) >= 0 ? this : f);
    }

    /**
     * Returns a positive BigFraction, greater than or equal to zero, and less than one.
     */
    public static BigFraction random()
    {
        return new BigFraction(Math.random());
    }

    public final BigInteger getNumerator()
    {
        return numerator;
    }

    public final BigInteger getDenominator()
    {
        return denominator;
    }

    // implementation of Number class. may cause overflow.
    public byte byteValue()
    {
        return (byte) Math.max(Byte.MIN_VALUE, Math.min(Byte.MAX_VALUE, longValue()));
    }

    public short shortValue()
    {
        return (short) Math.max(Short.MIN_VALUE, Math.min(Short.MAX_VALUE, longValue()));
    }

    public int intValue()
    {
        return (int) Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, longValue()));
    }

    public long longValue()
    {
        return Math.round(doubleValue());
    }

    public float floatValue()
    {
        return (float) doubleValue();
    }

    public double doubleValue()
    {
        return toBigDecimal(18).doubleValue();
    }

    /**
     * Returns a BigDecimal representation of this fraction. If possible, the returned value will be exactly equal to
     * the fraction. If not, the BigDecimal will have a scale large enough to hold the same number of significant
     * figures as both numerator and denominator, or the equivalent of a double-precision number, whichever is more.
     */
    public BigDecimal toBigDecimal()
    {
        // Implementation note: A fraction can be represented exactly in base-10 iff its
        // denominator is of the form 2^a * 5^b, where a and b are nonnegative integers.
        // (In other words, if there are no prime factors of the denominator except for
        // 2 and 5, or if the denominator is 1). So to determine if this denominator is
        // of this form, continually divide by 2 to get the number of 2's, and then
        // continually divide by 5 to get the number of 5's. Afterward, if the denominator
        // is 1 then there are no other prime factors.

        // Note: number of 2's is given by the number of trailing 0 bits in the number
        final int twos = denominator.getLowestSetBit();
        BigInteger tmpDen = denominator.shiftRight(twos); // x / 2^n === x >> n

        final BigInteger FIVE = BigInteger.valueOf(5);
        int fives = 0;
        BigInteger[] divMod = null;

        // while(tmpDen % 5 == 0) { fives++; tmpDen /= 5; }
        while (BigInteger.ZERO.equals((divMod = tmpDen.divideAndRemainder(FIVE))[1]))
        {
            fives++;
            tmpDen = divMod[0];
        }

        if (BigInteger.ONE.equals(tmpDen))
        {
            // This fraction will terminate in base 10, so it can be represented exactly as
            // a BigDecimal. We would now like to make the fraction of the form
            // unscaled / 10^scale. We know that 2^x * 5^x = 10^x, and our denominator is
            // in the form 2^twos * 5^fives. So use max(twos, fives) as the scale, and
            // multiply the numerator and deminator by the appropriate number of 2's or 5's
            // such that the denominator is of the form 2^scale * 5^scale. (Of course, we
            // only have to actually multiply the numerator, since all we need for the
            // BigDecimal constructor is the scale.
            BigInteger unscaled = numerator;
            final int scale = Math.max(twos, fives);

            if (twos < fives)
            {
                unscaled = unscaled.shiftLeft(fives - twos); // x * 2^n === x << n
            }
            else if (fives < twos)
            {
                unscaled = unscaled.multiply(FIVE.pow(twos - fives));
            }

            return new BigDecimal(unscaled, scale);
        }

        // else: this number will repeat infinitely in base-10. So try to figure out
        // a good number of significant digits. Start with the number of digits required
        // to represent the numerator and denominator in base-10, which is given by
        // bitLength / log[2](10). (bitLenth is the number of digits in base-2).
        final double LG10 = 3.321928094887362; // Precomputed ln(10)/ln(2), a.k.a. log[2](10)
        int precision = Math.max(numerator.bitLength(), denominator.bitLength());
        precision = (int) Math.ceil(precision / LG10);

        // If the precision is less than 18 digits, use 18 digits so that the number
        // will be at least as accurate as a cast to a double. For example, with
        // the fraction 1/3, precision will be 1, giving a result of 0.3. This is
        // quite a bit different from what a user would expect.
        if (precision < 18)
        {
            precision = 18;
        }

        return toBigDecimal(precision);
    }

    /**
     * Returns a BigDecimal representation of this fraction, with a given precision.
     * 
     * @param precision the number of significant figures to be used in the result.
     */
    public BigDecimal toBigDecimal(final int precision)
    {
        return new BigDecimal(numerator).divide(new BigDecimal(denominator), new MathContext(precision,
                RoundingMode.HALF_EVEN));
    }

    // --------------------------------------------------------------------------
    // PRIVATE FUNCTIONS
    // --------------------------------------------------------------------------

    /**
     * Private constructor, used when you can be certain that the fraction is already in lowest terms. No check is done
     * to reduce numerator/denominator. A check is still done to maintain a positive denominator.
     * 
     * @param throwaway unused variable, only here to signal to the compiler that this constructor should be used.
     */
    private BigFraction(final BigInteger numerator, final BigInteger denominator, final boolean throwaway)
    {
        if (denominator.signum() < 0)
        {
            this.numerator = numerator.negate();
            this.denominator = denominator.negate();
        }
        else
        {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

}