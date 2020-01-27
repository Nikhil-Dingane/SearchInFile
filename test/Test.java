import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.channels.FileChannel;
import java.io.*;

class Test
{
	public static void main(String arg[]) throws Exception
	{
		copyFileUsingJava7Files(new File("file1.txt"),new File("file3.txt"));
	}
	private static void copyFileUsingJava7Files(File source, File dest) throws IOException
	{
		Files.copy(source.toPath(), dest.toPath());
		dest=new File("file2.txt");
		Files.copy(source.toPath(),dest.toPath());
	}
	private static void copyFileUsingChannel(File source, File dest) throws IOException
	{
		FileChannel sourceChannel = null;
		FileChannel destChannel = null;
		try 
		{
			sourceChannel = new FileInputStream(source).getChannel();
			destChannel = new FileOutputStream(dest,true).getChannel();
			destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		}
		finally
		{
			sourceChannel.close();
			destChannel.close();
		}
	}

}

