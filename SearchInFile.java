import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

class SearchInFile extends Thread
{
	File[] filelist;
	HashMap<String,Integer> worddictionary;
	
	public SearchInFile(String arg[])
	{
		allFileList(arg[0]);
		worddictionary=new HashMap<String,Integer>();
		setWords(arg);
	}
	
	public void setWords(String arg[])
	{
		for(int i=1;i<arg.length;i++)
		{
			worddictionary.put(arg[i],0);
		}
	}
	
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
		obj.search();
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
	
	public void displayWordCount(String fileName)
	{
		for(Map.Entry<String,Integer> entry : worddictionary.entrySet())
		{
			System.out.println("\nFile name : "+fileName);
			System.out.println(entry.getKey()+" : "+entry.getValue()+" times ");
		}
	}
	
	public void setWordCountToZero()
	{
		for(Map.Entry<String,Integer> entry : worddictionary.entrySet())
		{
			worddictionary.put(entry.getKey(),0);
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