//Author: Jonathan Hassel
//Date: 1/25/17
//Class: COP3253 - Thrasher

import java.lang.String;

public class IntegerSet
{
	public boolean[] intSet;


//METHODS-----------------------------------------------------------------------------------------------------------
	IntegerSet()
	{	//initializing the boolean aray to size 100
		intSet = new boolean[101];
	}

	public IntegerSet union(IntegerSet iSet)
	{
		IntegerSet set = new IntegerSet();

		//sets this.intSet[i] to true for all elements in iSet that are different from 'this'
		for(int i = 0; i < 101; i++)
		{
			if(this.intSet[i] == true || iSet.intSet[i] == true)
				set.intSet[i] = true;
		}
		return set;
	}

	public IntegerSet intersection(IntegerSet iSet)
	{
		IntegerSet set = new IntegerSet();

		//sets element to true if it is included in both sets, and false otherwise
		for(int i = 0; i < 101; i++)
		{
			if(this.intSet[i] == true && iSet.intSet[i] == true)
				set.intSet[i] = true;
			else
				set.intSet[i] = false;
		}
		return set;
	}

	public IntegerSet insertElement(int data)
	{	//sets element to true
		this.intSet[data] = true;

		return this;
	}

	public IntegerSet deleteElement(int data)
	{	//sets element to 0
		this.intSet[data] = false;

		return this;
	}

	public boolean isEqualTo(IntegerSet iSet)
	{	//to keep count of how many elements are the same, if j = 100, then arrays are identical
		int j = 0;

		//compares all elements in each array
		for(int i = 0; i < 101; i++)
		{
			if(this.intSet[i] == iSet.intSet[i])
				j++;
		}

		if(j == 101)
			return true;

		return false;
	}

	public String toString()
	{
		String newString = "", emptySet = "---";

		//printing each element that is turned  on
		for(int i = 0; i < 101; i++)
		{
			if(this.intSet[i] == true)
				newString += (i + " ");
		}

		//if nothing was added to newString, return emptySet
		if(newString == "")
			return emptySet;

		return newString;
	}
}
