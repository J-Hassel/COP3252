//Name: Jonathan Hassel
//Date: 1/18/17
import java.util.Scanner;
import java.util.Random;

public class DiceStats
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		Random randNum = new Random();
		int numDie, numRolls;
		int[] frequency;

		System.out.printf("How many dice will constitute one roll? ");
		numDie = input.nextInt();					//numbers of die input

		System.out.printf("How many rolls? ");
		numRolls = input.nextInt();					//number of rolls input

		//initializing array to max sum rolled
		frequency = new int[6 * numDie];

		//for loop rolling die, then summing them up and incrementing the slot corresponding to the sum
		for(int roll = 0; roll < numRolls; roll++)
		{
			int sum = 0;						//sum resets every iteration of the main loop

			//loop that rolls all the die, then sums them up
			for(int i = 0; i < numDie; i++)
				sum += (1 + randNum.nextInt(6));

			//increments the slot that represents the sum of the rolls (slot 1 = die summed up to 2, etc...)
			frequency[sum - 1]++;
		}

		System.out.printf("Sum\t# of times\tPercentage\n");

		//prints all the data
		for(int i = numDie - 1; i < frequency.length; i++)
		{
			//calculates the percent for each roll
			double pct = (frequency[i] / (double)numRolls) * 100;

			System.out.print(i + 1 + "\t" + frequency[i] + "\t\t");
			System.out.printf("%.2f", pct);
			System.out.println(" %");
		}
	}
}
