import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

class SearchInFile
{
	public static void main(String[] arg) throws Exception
	{
		Scanner sobj=new Scanner(System.in);
		System.out.println("Enter the file ");
		String buffer;
		try
		{
			BufferedReader br=new BufferedReader(new FileReader(sobj.nextLine()));
			buffer=br.readLine();
			try
			{
				while(true)
				{
					System.out.println(buffer);
					buffer=br.readLine();
					if(buffer==null)
					{
						break;
					}
				}
			}
			catch(IOException e)
			{
				
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("FIle not found");
		}
		
	}
}