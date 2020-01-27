import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

class SearchInFile
{
	public static void main(String[] arg) throws Exception
	{
		Scanner sobj=new Scanner(System.in);
		System.out.println("Enter directory");
		String filename=sobj.nextLine();
		System.out.println("Enter word or substring to search");
		String substring=sobj.nextLine();		
		search(substring,filename);
	}
	
	public static void search(String substring,String filename) throws Exception
	{
		File[] filelist=allfilelist(src);
		if(filelist==null)
		{
			System.out.println("Wrong Directory");
			return;
		}
		String buffer=null;
		int no=0;
		try
		{
			BufferedReader br=new BufferedReader(new FileReader(filename));
			while((buffer=br.readLine())!=null)
			{
				no++;
				//System.out.println(buffer);
				if(buffer.contains(substring))
				{
					break;
					//System.out.println(buffer+"\nline no is = "+no);
			
				}
				
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FIle not found");
		}
		System.out.println(buffer+"\nline no is = "+no);
	}
	
	private File[] allFileList(String dirname)
	{
		File dir=new File(dirname);
		if(dir==null)
		{
			return null;
		}
		File[] list=dir.listFiles();
		return list;
	}
}