//Name: Jonathan Hassel
//Date: 2/20/17
//Class: COP3252 - Thrasher
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Arrays;
import java.util.Scanner;

public class FileViewer
{

	public static void main(String[] args)
	{
		if(args.length == 0)	//display info on current directory
			information(".");
		else if(args.length == 1)
		{
			if("-i".equals(args[0]))	//information
				information(".");
			else
				printUsage();
		}
		else if(args.length == 2)
		{
			if("-i".equals(args[0]))	//information
				information(args[1]);
			else if("-v".equals(args[0]))	//view
				view(args[1]);
			else
				printUsage();
		}
		else if(args.length == 3)
		{
			if("-c".equals(args[0]))	//copy
				copy(args[1], args[2]);
			else if("-d".equals(args[0]))	//differences
				differences(args[1], args[2]);
			else
				printUsage();
		}
		else
			printUsage();
	}//end main




//--METHODS------------------------------------------------------------------------------------------------------------
	public static void information(String name)
	{
		try
		{
			File file = new File(name);
			SimpleDateFormat date = new SimpleDateFormat("EEE MMM dd hh:mm:ss");

			if(file.isFile())	//if it is a file, print path, executable, size and date last modified
			{
				System.out.println("\nFile Path: " + file.getAbsolutePath());
				System.out.println("Is executable? " + file.canExecute());
				System.out.println("Size: " + file.length() + " bytes");
				System.out.println("Last Modified Date: " + date.format(file.lastModified()) + "\n");
			}
			else if(file.isDirectory())		//if it is a directory, print the contents
			{
				System.out.println("Size\tFilename");
				System.out.println();
				listDirectory(name);
			}
			else	//file doesnt exist
				throw new FileNotFoundException();
		}
		catch(FileNotFoundException e)
		{	//catches the exception thrown when the file does not exist
			System.out.println("Error. Invalid File.");
		}
	}


	public static void listDirectory(String directoryName)
	{	//creates File array of all files/directories in the directory
		File directory = new File(directoryName);
		File[] files = directory.listFiles();

		//sorts files by size and prints them out
		Arrays.sort(files, new FileSizeCompare());
		for(File file : files)
		{
			if(file.isFile())
				System.out.println(file.length() + "\t" + file.getName());
			else
				System.out.println("*\t" + file.getName());
		}
	}


	public static void view(String name)
	{
		try
		{	//creating a new file
			File file = new File(name);
			if(file.isFile())
			{	//if it is a file, print all the contents to the screen
				Scanner fileIn = new Scanner(new File(name));

				while(fileIn.hasNextLine())
					System.out.println(fileIn.nextLine());
			}
			else	//file doesnt exist
				throw new FileNotFoundException();
		}
		catch(FileNotFoundException e)
		{	//catches the exception thrown when the file does not exist
			System.out.println("File \"" + name + "\" not found.");
		}
	}


	public static void copy(String source, String destination)
	{
		try
		{
			File file1 = new File(source);
			File file2 = new File(destination);

			if(file1.isFile() && !file2.isFile())	//file1 must be a file and file2 must not exist to copy
			{	//input stream from file source, output stream to file destination
				FileInputStream fin = new FileInputStream(new File(source));
				FileOutputStream fout = new FileOutputStream(new File(destination));

				//reads a byte from input stream into r, then writes it to output stream while !EOF
				int r;
				while((r = fin.read()) != -1)
					fout.write((byte)r);

				fout.flush();
				fout.close();

				System.out.println("File \"" + source + "\" was copied to file \"" + destination + "\" successfully.");
			}
			else if(!file1.isFile())	//file1 does not exist
				throw new FileNotFoundException();
			else if(file2.isFile())		//file2 already exists
				System.out.println("File \"" + destination + "\" already exists.");
		}
		catch(FileNotFoundException e)
		{	//catches the exception thrown when the file does not exist
			System.out.println("File \"" + source + "\" not found.");
		}
		catch(IOException e)
		{	//catches the IOException
			System.out.println(e.getMessage());
		}
	}


	public static void differences(String file1, String file2)
	{
		try
		{
			File f1 = new File(file1);
			File f2 = new File(file2);

			if(!f1.isFile() || !f2.isFile())
				throw new FileNotFoundException();

			if(file1.equals(file2))		//if the same files are being compared, print they are the same
				System.out.println("The two files \"" + file1 + "\" and \"" + file2 + "\" are the same.");
			else
			{	//creating two byte arrays, and filling them with the file contents
				byte[] F1 = Files.readAllBytes(f1.toPath());
				byte[] F2 = Files.readAllBytes(f2.toPath());

				if(Arrays.equals(F1, F2))	//if the two byte arrays are equal, then the files have no differences
					System.out.println("The two files \"" + file1 + "\" and \"" + file2 + "\" are the same.");
				else	//else they are not the same
					System.out.println("The two files \"" + file1 + "\" and \"" + file2 + "\" are not the same.");
			}

		}
		catch(FileNotFoundException e)
		{	//catches the exception thrown when one or more of the files does not exist
			System.out.println("Error. One or more of the files provided do not exist.");
		}
		catch(IOException e)
		{	//catches the IOException
			System.out.println(e.getMessage());
		}
	}


	public static void printUsage()
	{	//prints usage message
		System.out.println("Usage: java -jar hw4.jar [-i [<file>|<directory>] | -v <file> | -c <sourceFile> <destFile> | -d <file1> <file2>]");
	}


}//end FileViewer class


//for comparing file sizes
class FileSizeCompare implements Comparator<File>
{
	public int compare(File f1, File f2)
	{
		return ((int)f1.length() - (int)f2.length());
	}
}
