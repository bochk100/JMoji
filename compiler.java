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
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) 
		{
			String line;
			while ((line = br.readLine()) != null) 
			{
				System.out.println(line);
				identifyLine(line);
			}
		}
	}
	
	public void identifyLine(String line)
	{
		 switch (string) {
            case "":  monthString = "January";
                     break;

            default: monthString = "Invalid month";
                     break;
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
}