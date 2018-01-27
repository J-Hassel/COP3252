//Name: Jonathan Hassel
//Date: 4/3/17
//Class: cop3252 - Thrasher

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable, Comparable<Student>
{

	private ArrayList<Double> testGrades;
	private ArrayList<Double> hwGrades;

	//Should not be serialized(transient)
	private transient double grade = 0;

	private String fname, lname;

	private static int totalStudents = 0;

	//Get the number of students implemented at this time
	public static int getNumStudents()
	{
		return totalStudents;
	}

	//Resets the count of students
	public static void resetStudentCount()
	{
		totalStudents = 0;
	}

	//Increment number of students
	public static void incrementStudentCount()
	{
		totalStudents++;
	}

	//Decrement number of students
	public static void decrementStudentCount()
	{
		totalStudents--;
	}


	//default constructor
	public Student()
	{
		this("", "");
	}

	//Sets student first and last name based on parameters
	public Student(String f, String l)
	{
		testGrades = new ArrayList<Double>();
		hwGrades = new ArrayList<Double>();

		fname = new String(f);
		lname = new String(l);
		totalStudents++;
	}

	//Calculate final grade with tests and homeworks counting for
	//50% each
	//Returns true if there are values for both test and homework
	//False if either has zero entries
	public boolean calcGrade()
	{

		double testAvg = 0;
		double hwAvg = 0;
		boolean complete = true;

		for (Double d: testGrades)
			testAvg += d;

		for (Double d: hwGrades)
			hwAvg += d;

		if (testGrades.size() >= 1)
			testAvg /= testGrades.size();
		else
			complete = false;

		if (hwGrades.size() >= 1)
			hwAvg /= hwGrades.size();
		else
			complete = false;

		grade = (testAvg + hwAvg) / 2;

		return complete;
	}

	//Get final grade
	public double getGrade()
	{
		return grade;
	}

	//Get last name
	public String getLast()
	{
		return lname;
	}

	//Get first name
	public String getFirst()
	{
		return fname;
	}

	//Adds a new homework grade and calculates the new average
	public void addHW(double hw)
	{
		hwGrades.add(hw);
		calcGrade();
	}

	//Adds a new test grade and calculates the new average
	public void addTest(double test)
	{
		testGrades.add(test);
		calcGrade();
	}

	//override of toString to print out student information
	public String toString()
	{
		StringBuilder theString = new StringBuilder("");

		theString.append("First name: " + fname);
		theString.append("\nLast name: " + lname);
		theString.append("\nFinal Grade: " + grade + "\n");
		return theString.toString();
	}

	public void setname(String f, String l)
	{
		fname = f;
		lname = l;
	}



	//used to sort by last name, then by first name
	public int compareTo(Student s)
	{
		if(lname.equals(s.lname))
			return fname.compareTo(s.fname);
		else
			return lname.compareTo(s.lname);
	}
}
