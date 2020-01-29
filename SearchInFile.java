import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

class SearchInFile
{
	
	// This array will store the list of all files of specified directory
	File[] filelist;
	
	// This HashMap is used to store words and their frequecy
	HashMap<String,Integer> worddictionary;

	// Default constructor
	// This will fill the files in array and will store all the words in HashMap with the frequecy 0.
	public SearchInFile(String arg[])
	{
		allFileList(arg[0]);
		worddictionary=new HashMap<String,Integer>();
		setWords(arg);
	}
	
	//This is helper method to add words in HashMap with value 0
	public void setWords(String arg[])
	{
		for(int i=1;i<arg.length;i++)
		{
			worddictionary.put(arg[i],0);
		}
	}
	
	// Entry point function
	// This function will accept the command line arguments from user and 
	//create object of SearchInFile class which will initialize all things and 
	//then it will call search() method
	public static void main(String[] arg) throws Exception
	{
		if(arg.length<2)
		{
			System.out.println("Invalid argument");
			System.exit(0);
		}
		
		SearchInFile obj=new SearchInFile(arg);
		
		if(!obj.isFilesAvailable())
		{
			System.out.println("There is no files in given directory");
			System.exit(0);
		}
		
		long endTime;
		long startTime;
		
		startTime=System.nanoTime();
		
		obj.search();
		
		endTime=System.nanoTime();
		
		System.out.println("Required time for searching is : "+ (endTime-startTime)/1000000000+" seconds");
	}
	
	
	// This is the helper method to check wheather there are files available or not in specified directory
	public boolean isFilesAvailable()
	{
		if(filelist==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	//This method is used search the words within the files of directory
	// This method will get the a file from the FileList array one by one and
	// If the file is text file then it will passed to searchInFile() method
	public void search() throws Exception
	{	
		
		for(File file:filelist)
		{
			if(!getFileExtension(file).toUpperCase().equals("TXT"))
			{
				continue;
			}
			
			setWordCountToZero();
			
			searchInFile(file);
		}
			
	}
	
	// This method will accept the filename and will read the file line by line
	// For each line it will also iterate the HashMap and will check that wheather the words are present the line or not
	// If the words are present then there assocating value will increased by one for each occurance.
	public void searchInFile(File file) throws Exception
	{
		String buffer=null;
		BufferedReader br=new BufferedReader(new FileReader(file));
		
		while((buffer=br.readLine())!=null)
		{
			for(Map.Entry<String,Integer> entry : worddictionary.entrySet())
			{
				if(buffer.contains(entry.getKey()))
				{
					worddictionary.put(entry.getKey(),entry.getValue()+1);
				}
			}
		}
		
		displayWordCount(file.getName());
	}
	
	// This method is helper method of searchInFile() method and will be called to display frequecy of words
	public void displayWordCount(String fileName)
	{
		for(Map.Entry<String,Integer> entry : worddictionary.entrySet())
		{
			System.out.println("\nFile name : "+fileName);
			System.out.println(entry.getKey()+" : "+entry.getValue()+" times ");
		}
	}
	
	// This method will set assocative values of all the words to 0
	public void setWordCountToZero()
	{
		for(Map.Entry<String,Integer> entry : worddictionary.entrySet())
		{
			worddictionary.put(entry.getKey(),0);
		}
	}
	
	// This method will return extention of given file
	private static String getFileExtension(File file) 
	{
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
		{
			return fileName.substring(fileName.lastIndexOf(".")+1);
		}
        else
		{
			return "";
		}
    }
	
	// This method will fill the array filelist with files of specified directory.
	private void allFileList(String dirname)
	{
		File dir=new File(dirname);
		if(dir==null)
		{
			return;
		}
		
		filelist=dir.listFiles();
		
		if(filelist==null)
		{
			System.out.println("Wrong Directory");
		}
	}
}