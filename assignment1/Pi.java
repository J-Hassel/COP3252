//Name: Jonathan Hassel
//Date: 1/18/17
import java.util.Scanner;

public class Pi
{
	public static void main(String[] args)
	{
		//scanner for input
		Scanner input = new Scanner(System.in);
		int numTerms;
		double pi = 0;

		System.out.printf("Compute how many terms of the series? ");	//asking user for input
		numTerms = input.nextInt();					//saving input to variable

		System.out.printf("terms\tPI approximation\n");

		//for loop for computations/printing
		for(int i = 0; i < numTerms; i++)
		{
			if(i % 2 == 0)
				pi += (4.0 / ((2 * i) + 1));
			else
				pi -= (4.0 / ((2 * i) + 1));
			//prints
			System.out.printf((i + 1) + "\t");
			System.out.printf("%.6f", pi);
			System.out.println();
		}
	}
}
