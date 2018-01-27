//Name: Jonathan Hassel
//Date: 1/18/17
import java.util.Scanner;

public class Reverse
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		long num, numReversed;

		//loop for continuous program (until 0 is entered)
		while(true)
		{
			System.out.printf("Please enter a long integer (0 to quit): ");	//asking user for input
			num = input.nextLong();						//getting input

			if(num == 0)
			{
				System.out.printf("\nGoodbye!\n");			//ends the program
				break;
			}

			numReversed = reverseDigits(num);
			System.out.printf("\nThe number reversed is: " + numReversed + "\n\n");
		}
	}

	//method to reverse the digits
	public static long reverseDigits(long input)
	{
		long numReversed = 0;

		while(input != 0)
		{
			numReversed *= 10;
			numReversed += (input % 10);
			input = input / 10;
		}

		return numReversed;
	}
}
