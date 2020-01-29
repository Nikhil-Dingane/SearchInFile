import java.util.Scanner;
import java.util.*;
import java.io.File;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  
import java.io.IOException;
import java.nio.file.Files;
import java.nio.channels.FileChannel;

class TextFileMaker
{
	
	// Characterstics of class
	
	// Array of file to which will store text files in it 
	File[] filelist;
	
	//  Output file 
	File outputfile;
	
	//  To write the stream on the file
	FileOutputStream fos;
	
	// To read the stream from the file
	FileInputStream fis;
	
	
	// Default constructor
	// This will create array of file which are used create new file and to create outpur file
	public TextFileMaker() throws Exception
	{
		listAllFiles("/home/admin1/books");
		
		createOutputFile();
		
		if(outputfile.createNewFile())
		{
			fos = new FileOutputStream(outputfile); 
		}
		else
		{
			System.out.println("Unable to crea new file");
		}
	}
	
	// This is the helper method called by the default constructor and will fill the array of file with text files
	private void listAllFiles(String dirname)
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
			return;
		}
	}
	
	// This is the helper method of class which gets called by the default constructor to create output file
	private void createOutputFile()
	{
		String filename;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		
		filename="output_"+dtf.format(now)+".txt";
		outputfile=new File(filename);
	}
	
	// This is the method wich actually create output file 
	public void filemaker(int newfilesize) throws Exception
	{		
		for(int i=0;(i<filelist.length)&&(outputfile.length()<(newfilesize*1000000));i++)
		{
			copyFile(filelist[i],newfilesize);
			if(i==filelist.length-1)
			{
				i=-1;
			}
		}	
		
		System.out.println("File successfully created as :"+outputfile.getAbsolutePath());
		
	}
	
	// This is the helper method of the filemaker function this copy the data of provided file into output file
	public void copyFile(File file,int newfilesize) throws Exception
	{
		byte[] buffer=new  byte[1024];
		int length;
		
		fis=new FileInputStream(file);
		while(((length=fis.read(buffer))>0)&&(outputfile.length()<(newfilesize*1000000)))
		{
			fos.write(buffer, 0, length);
		}
		fis.close();
	}
	
	
	// This is the main method which consist the code to test class is working fine or not
	public static void main(String arg[]) throws Exception
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter size of text file to be created(in MB) : ");
		int newfilesize=Integer.parseInt(br.readLine());
		TextFileMaker obj=new TextFileMaker();
		obj.filemaker(newfilesize);
	}

}