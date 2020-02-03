import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime; 

class FileInsert
{
	File outputfile;
	FileOutputStream fos;
	
	public static void main(String arg[]) throws Exception
	{
		FileInsert obj=new FileInsert();
		obj.createOutputFile();
		String str="krishagni";
		
		byte buffer[]=str.getBytes();
		
		long endTime;
		long startTime;
		
		startTime=System.nanoTime();
		 
		for(int i=0;i<=1000;i++)
		{
			buffer=(new String(str+i)).getBytes();
			obj.fos.write(buffer, 0, buffer.length);
		}
		
		endTime=System.nanoTime();
		
		System.out.println("Required time for insertion in file is : "+ (endTime-startTime)/1000000000+"seconds");
		
	}
	
	private void createOutputFile() throws Exception
	{
		String filename;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		
		filename="output_"+dtf.format(now)+".txt";
		outputfile=new File(filename);
		if(outputfile.createNewFile())
		{
			fos = new FileOutputStream(outputfile); 
		}
		else
		{
			System.out.println("Unable to crea new file");
		}
	}
}