//May need to reference https://github.com/vdurmont/emoji-java

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class compiler {
	
	public static void main (String args[]) throws Exception
	{
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("COMPILE JMOJI");
		System.out.println("ENTER FILE NAME");
		
		String file = reader.nextLine();
		System.out.println(file);
		JMoji worker = new JMoji();
		
		try (BufferedReader in = new BufferedReader(
				new InputStreamReader( new FileInputStream(file), "UTF-8"));) 
		{
			String line;
			int lineCounter=0;
			while ((line = in.readLine()) != null) 
			{
				System.out.println(line);
				byte[] b = line.getBytes("UTF-8");		
				System.out.println(b[0]);
				
				worker.identifyLine(b, lineCounter);
				line+=1;
			}
		}
	}
	
}

class JMoji
{
	public JMoji()
	{
		
	}
	
	public void identifyLine(byte[] b, int lineCounter)
	{
		 /*switch(b[0]) {
			 case "": //System.out.println("Whitespace"); //Ignores whitespace
					break;
            case "U+1F522": parseInt(b);
                     break;
			case "U+262F": parseInt(b);
                     break;
            default: System.out.println("ERROR IN LINE "+lineCounter); 
                     break;
		
		 }*/
		
	}

	private void parseBool(byte[] b)
	{
		System.out.println("Identified boolean.");
	}	
	
	private void parseInt(byte[] b)
	{
	
		System.out.println("Identified integer.");
	}

	
	
	
	
	
	
	
	}
