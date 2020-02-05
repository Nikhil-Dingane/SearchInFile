import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime; 

class FileTextUpdate
{
	File outputfile;
	File tempfile;
	FileOutputStream fos;
	BufferedReader br;
	
	public static void main(String arg[]) throws Exception
	{
		FileTextUpdate obj=new FileTextUpdate();
		obj.createOutputFile();
		

		obj.writeWord("nikhil",12);
		obj.writeWord("sri",34);
		obj.writeWord("new",55);
		obj.update("new");
		obj.update("nikhil");
		obj.update("sri");
	}
	
	public void update(String word) throws Exception
	{
		br=new BufferedReader(new FileReader(outputfile));
		String line;
		String str[];
		createTempFile();
		fos=new FileOutputStream(tempfile);
		byte buffer[];
		while((line=br.readLine())!=null)
		{
			
			if(line.contains(word))
			{
				str=line.split(" ");
				int i=Integer.parseInt(str[1]);
				i+=1;
				line=str[0]+" "+i;
			}
			System.out.println(line);
			buffer=(line+System.lineSeparator()).getBytes();
			this.fos.write(buffer, 0, buffer.length);
		}
		br.close();
		outputfile.delete();
		tempfile.renameTo(outputfile);
	}
	
	public void createTempFile() throws Exception
	{
		tempfile=new File("temp.txt");
		tempfile.createNewFile();
	}
	
	public void writeWord(String str,int cnt) throws Exception
	{
		byte buffer[];
		buffer=(str+" "+cnt+System.lineSeparator()).getBytes();
		this.fos.write(buffer, 0, buffer.length);
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
			System.out.println("Unable to create new file");
		}
	}
}