package com.company;

import com.vdurmont.emoji.*;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main (String args[]) throws Exception
    {
        Scanner reader = new Scanner(System.in); //get file to compile
        System.out.println("COMPILE JMOJI");
        System.out.println("ENTER FILE NAME");
        String file = reader.nextLine();

        JMoji worker = new JMoji(); //Initialize parser

        //Read file
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader( new FileInputStream(file), "UTF-8")))
        {
            String line;
            int lineCounter=1; //line counter for error detection
            while ((line = in.readLine()) != null)
            {
                String lineConvert = EmojiParser.parseToUnicode(line);
                worker.identifyLine(lineConvert, lineCounter);
                lineCounter=lineCounter+1;

            }
        }
    }
}

class JMoji
{
    public String finalCode;
    public ArrayList<String> identifiers;


    public JMoji()
    {
        finalCode = "";
        identifiers = new ArrayList<String>();
    }

    public void identifyLine(String arr, int lineCounter)
    {
        System.out.println(arr);
        String[] splitArr = arr.split("(?!^)"); //split by character
        for(int i = 0; i<splitArr.length; ++i)
        {
            splitArr[i] =EmojiParser.parseToAliases(splitArr[i]);
        }

        //Note: Some emojis consist of two or more characters, thus the nestled switch.
		 switch(splitArr[0]) {
			 case "": //System.out.println("Whitespace"); //Ignores whitespace
					break;
            case ":yin_yang:": parseBool(arr, lineCounter);
                     break;
            default:
                switch(EmojiParser.parseToAliases(splitArr[0]+splitArr[1]))
                {
                    case ":1234:":parseInt(splitArr);
                        break;
                    default:System.out.println("ERROR IN LINE "+lineCounter+". INVALID FIRST CHARACTER.");
                        break;
                }
                     break;
		 }
    }

    private void parseBool(String arr, int lineCounter)
    {
        System.out.println("Identified boolean.");
        String parsed = EmojiParser.parseToAliases(arr);
        String[] splitArr = parsed.split(":"); //split by character
        String identifier = ":"+splitArr[3]+":";
        identifiers.add(identifier);
        if(splitArr[5].equals("rainbow"))
        {
            if(splitArr[7].equals("white_circle"))
            {
                finalCode += "bool "+splitArr[3]+" = true;";
                System.out.println(finalCode);
            }
            else if(splitArr[7].equals("black_circle"))
            {
                finalCode += "bool "+splitArr[3]+" = false;";
                System.out.println(finalCode);
            }
            else
            {
                System.out.println("INCORRECT SYTNTAX AT LINE "+lineCounter);
                System.out.println("BOOLEAN MUST BE INITIALIZED TO ⚪ OR FALSE ⚫");
            }
        }
        else
        {
            System.out.println("INCORRECT SYTNTAX AT LINE "+lineCounter);
        }
    }

    private void parseInt(String[] arr)
    {

        System.out.println("Identified integer.");
    }

    //checks to make sure emoji used isn't a key
    private void checkIfKey(String testE)
    {

        switch(testE) {
            case "": //System.out.println("Whitespace"); //Ignores whitespace
                break;
            case ":yin_yang:":
                break;
            default:
                switch(EmojiParser.parseToAliases(testE))
                {
                    case ":1234:":
                        break;
                    default:System.out.println("ERROR IN LINE "+0000+". INVALID FIRST CHARACTER.");
                        break;
                }
                break;
        }
    }


}
