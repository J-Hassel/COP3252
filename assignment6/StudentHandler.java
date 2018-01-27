//Name: Jonathan Hassel
//Date: 4/3/17
//Class: cop3252 - Thrasher

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentHandler extends Student
{
	private static ArrayList<Student> students;

	public static void main(String[] args)
	{
		int choice;
		Scanner input = new Scanner(System.in);
		StudentHandler handler = new StudentHandler();

		resetStudentCount();

		do
		{	//main menu loop
			System.out.println("1: Print out all loaded students");
			System.out.println("2: Add student");
			System.out.println("3: Clear students");
			System.out.println("4: Save students to file");
			System.out.println("5: Load students from file");
			System.out.println("6: Quit\n");

			System.out.print("Please input the number of your choice: ");

			try
			{	//gets menu selection
				choice = input.nextInt();
			}
			catch(InputMismatchException e)
			{	//clears non-integer input from scanner and sets choice to 0 to send error message
				input.nextLine();
				choice = 0;
			}

			switch(choice)
			{
				case 1:
					printAllStudents();
					break;

				case 2:
					input.nextLine();		//clearing input scanner
					addStudent(input);
					break;

				case 3:
					clearAllStudents();
					break;

				case 4:
					input.nextLine();		//clearing input scanner
					saveStudents(input);
					break;

				case 5:
					input.nextLine();		//clearing input scanner
					loadStudents(input);
					break;

				case 6:
					System.out.println("Goodbye!");
					break;

				default:
					System.out.println("Invalid choice, try again.\n\n\n");
					break;
			}

		}while(choice != 6);
	}

	public StudentHandler()
	{	//initializing member data
		students = new ArrayList<Student>();
	}

	public static void saveStudents(Scanner s)
	{
		String fileName;

		System.out.print("Please input the filename to save as: ");
		fileName = s.nextLine();

		File file = new File(fileName);

		try
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

			//saving all students as serialized objetcs
			for(Student element : students)
				out.writeObject(element);

			out.close();
		}
		catch(IOException e)
		{
			System.out.println("\n\nError reading from or writing to file. Returning to menu.\n\n");
			return;
		}
		System.out.println("\n");
	}

	public static void loadStudents(Scanner s)
	{
		String fileName;

		//clears student list and resets numStudents to 0
		students.clear();
		resetStudentCount();

		System.out.print("Please input the filename to load from: ");
		fileName = s.nextLine();

		File file = new File(fileName);

		try
		{	//throw exception if file does not exist
			if(!file.isFile())
				throw new FileNotFoundException();

			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

			while(true)
			{	//read in objects from file until EOFException is thrown
				Student temp = (Student) in.readObject();
				students.add(temp);
				temp.calcGrade();
				incrementStudentCount();
			}
		}
		catch(EOFException e)
		{	//while loop reached end of file
			System.out.println("\n");
			return;
		}
		catch(FileNotFoundException e)
		{	//if file does not exist
			System.out.println("\n\nFile not found. Returning to menu.\n\n");
			return;
		}
		catch(IOException e)
		{	//if there is an error with file input/output
			System.out.println("\n\nError reading from or writing to file. Returning to menu.\n\n");
			return;
		}
		catch(ClassNotFoundException e)
		{	//for wen user attempts to read from a file with no serialized objects
			System.out.println("\n\nError loading file. Returning to menu.\n\n");
			return;
		}
	}

	public static void addStudent(Scanner s)
	{
		Student student = new Student();
		String fname, lname;
		double hwGrade, tGrade;

		try
		{
			System.out.print("Please input a first name: ");
			fname = s.nextLine();

			System.out.print("\nPlease input a last name: ");
			lname = s.nextLine();

			student.setname(fname, lname);			//sets student name

			//loop for student homework grades
			System.out.print("\nPlease input student homework grades one at a time (negative value to finish): ");
			hwGrade = s.nextDouble();
			while(hwGrade >= 0)
			{
				student.addHW(hwGrade);
				System.out.print("\nPlease add another homework grade (negative value to finish): ");
				hwGrade = s.nextDouble();
			}


			//loop for student test grades
			System.out.print("\nPlease input student test grades one at a time (negative value to finish): ");
			tGrade = s.nextDouble();
			while(tGrade >= 0)
			{
				student.addTest(tGrade);
				System.out.print("\nPlease add another test grade (negative value to finish): ");
				tGrade = s.nextDouble();
			}

			students.add(student);		//adding student to the list
		}
		catch(InputMismatchException e)
		{	//for when wrong input is entered
			System.out.println("Invalid input, please try inputting the student again.");
			decrementStudentCount();		//decrements since count is incremented in constructor(when we set name)
			s.nextLine();		///clearing input
			addStudent(s);		//restarts funtion
		}
		System.out.println();
	}

	public static void printAllStudents()
	{
		//sorting
		Collections.sort(students);

		//pritning each student
		for(Student element : students)
			System.out.println(element.toString());

		System.out.println("\nPrinted " + getNumStudents() + " Student Records\n\n");
	}

	public static void clearAllStudents()
	{	//clears student list, sets student count to 0
		students.clear();
		resetStudentCount();
		System.out.println("\n");
	}
}
