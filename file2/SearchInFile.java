import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

class SearchInFile extends Thread
{
	static File[] filelist;
	
	public static void main(String arg[]) throws Exception
	{
		if(arg.length<2)
		{
			System.out.println("Invalid argument");
			System.exit(0);
		}
		
		allFileList(arg[0]);
		
		if(filelist==null)
		{
			System.out.println("There is no files in given directory");
			System.exit(0);
		}
		
		File subarray[];
		Search obj;
		Thread tobj;
		
		int bunch=((filelist.length)/10);
		int start=0,end=0;
		
		long endTime;
		long startTime;
		
		startTime=System.nanoTime();
		
		while(start<=(filelist.length)-1)
		{
			if((start+9)<=filelist.length-1)
			{
				end=start+9;
				System.out.println(start +" "+ end);
				subarray=Arrays.copyOfRange(filelist,start,end+1);
				obj=new Search(arg,subarray);
				tobj=new Thread(obj);
				tobj.start();
				//tobj.join();
				start=start+10;
			}
			else
			{
				end=filelist.length-1;
				System.out.println(start +" "+ end);
				subarray=Arrays.copyOfRange(filelist,start,end+1);
				obj=new Search(arg,subarray);
				tobj=new Thread(obj);
				tobj.start();
				//tobj.join();
				break;
			}
		}
		endTime=System.nanoTime();
		
		System.out.println("\n\nRequired time for searching is : "+ (endTime-startTime)/1000000000+" seconds");
		
	}
	private static void allFileList(String dirname)
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

class Search extends Thread
{
	File[] filelist;
	HashMap<String,Integer> worddictionary;
	
	public void run()
	{
		try
		{
			search();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public Search(String arg[],File filelist[])
	{
		//allFileList(arg[0]);
		this.filelist=filelist;
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
			System.out.println("\nFile name : "+fileName+"\n"+entry.getKey()+" : "+entry.getValue()+" times ");
			//System.out.println();
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