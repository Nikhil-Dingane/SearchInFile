import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

class SearchInFile extends Thread
{
	File[] filelist;
	
	public SearchInFile(String dirname)
	{
		allFileList(dirname);
	}
	
	
	public static void main(String[] arg) throws Exception
	{
		if(arg.length<2)
		{
			System.out.println("Invalid argument");
			System.exit(0);
		}
		
		SearchInFile obj=new SearchInFile(arg[0]);
		
		if(!obj.isFilesAvailable())
		{
			System.out.println("There is no files in given directory");
			System.exit(0);
		}
		
		for(int i=1;i<arg.length;i++)
		{
			obj.search(arg[i]);
		}
	}
	
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
	
	public void search(String substring) throws Exception
	{
		
		
		
		for(File file:filelist)
		{
			if(!getFileExtension(file).toUpperCase().equals("TXT"))
			{
				continue;
			}
			searchInFile(file,substring);
			
		}
			
	}
	
	public void searchInFile(File file,String substring) throws Exception
	{
		String buffer=null;
		BufferedReader br=new BufferedReader(new FileReader(file));
		int count=0;
		while((buffer=br.readLine())!=null)
		{
			
			//System.out.println(buffer);
			if(buffer.contains(substring))
			{
				count++;
				//System.out.println(buffer+"\nline no is = "+no);
		
			}
		}
		if(count>0)
		{
			System.out.println("\n");
			System.out.println("File name : "+file.getName());
			System.out.println(substring+": "+count+" times");
		}
	}
	
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