import java.io.*;

class Search
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
		
		int bunch=((filelist.length)/10);
		int start=0,end=0,i=0;
		
		System.out.println(filelist.length);
		for(File f:filelist)
		{
			System.out.println(i+" "+f.getName());
			i++;
		}
		
		while(start<=(filelist.length)-1)
		{
			if((start+9)<=filelist.length-1)
			{
				System.out.println();
				end=start+9;
				System.out.println(start +" "+ end);
			//	subarray=Arrays.copyOfRange(filelist,start,end);
			
				start=start+10;
			}
			else
			{
				end=filelist.length-1;
				System.out.println(start +" "+ end);
				//subarray=Arrays.copyOfRange(filelist,start,end);
				break;
			}
		}
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
