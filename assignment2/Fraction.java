//Author: Jonathan Hassel
//Date: 1/25/17
//Class: COP3253 - Thrasher

public class Fraction
{
	private int numerator = 0;					// numerator (and keeps sign)
	private int denominator = 1;				// always stores positive value


//METHODS-----------------------------------------------------------------------------------------------------------
	public Fraction()
	{

	}

	public Fraction(int n, int d)
	{
		if(set(n,d) == false)
			set(0,1);
	}

	public boolean set(int n, int d)
	{
		if (d > 0)
		{
			numerator = n;
			denominator = d;
			return true;
		}
		else
			return false;
	}

	public String toString()
	{
		return (numerator + "/" + denominator);
	}

	public int getNumerator()
	{
		return numerator;
	}

	public int getDenominator()
	{
		return denominator;
	}

	public double decimal()
	{
		return (double)numerator / denominator;
	}

	public Fraction simplify()
	{	//making sure denominator is non-negative and non-zero
		this.check();

		Fraction result = new Fraction(this.numerator, this.denominator);
		int gcd;

		//making sure denominator is non-negative and non-zero
		result.check();

		if(this.numerator == 0)		//if numerator == 0, return 0/1
		{
			result.set(0,1);
			return result;
		}

		//finds gcd
		gcd = findGCD(this.numerator, this.denominator);

		//dividing numerator and denominator by gcd
		result.numerator /= gcd;
		result.denominator /= gcd;

		return result;
	}

	public Fraction add(Fraction f)
	{
		Fraction result = new Fraction(0,1);

		//adds the two fractions, then simplifies in the return statement
		result.numerator = (this.numerator * f.denominator) + (this.denominator * f.numerator);
		result.denominator = (this.denominator * f.denominator);

		return result.simplify();
	}

	public Fraction subtract(Fraction f)
	{
		Fraction result = new Fraction(0,1);

		//subtracts the two fractions, then simplifies in the return statement
		result.numerator = (this.numerator * f.denominator) - (this.denominator * f.numerator);
		result.denominator = (this.denominator * f.denominator);

		return result.simplify();
	}

	public Fraction multiply(Fraction f)
	{
		Fraction result = new Fraction(0,1);

		//multiplies the two fractions, then simplifies in the return statement
		result.numerator = (this.numerator * f.numerator);
		result.denominator = (this.denominator * f.denominator);

		return result.simplify();
	}

	public Fraction divide(Fraction f)
	{
		Fraction result = new Fraction(0,1);

		if(f.numerator == 0)		//if numerator is 0, return 0/1
			return result;

		//divides the two fractions, then simplifies in the return statement
		result.numerator = (this.numerator * f.denominator);
		result.denominator = (this.denominator * f.numerator);

		return result.simplify();
	}

	public void check()
	{	//checks if denominator is negative or 0, then fixes it
		if(this.denominator < 0)
		{
			this.denominator *= -1;
			this.numerator *= -1;
		}
		else if(this.numerator == 0 && this.denominator != 0)
			this.set(0,1);
	}

	public int findGCD(int a, int b)
	{	//euclydian method to find gcd
		a = Math.abs(a);

		if(a == 0)
			return b;

		while(b != 0)
		{
			if(a > b)
				a = a - b;
			else
				b = b - a;
		}
		return a;
	}
}
