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

        //right now this only outputs a file, but does nothing more.
        String code = worker.getFinalCode();
        try{
            PrintWriter writer = new PrintWriter("emojicode.java", "UTF-8");
            writer.println(code);
            writer.close();
        } catch (IOException e) {
            // do something
        }

    }
}

class JMoji
{
    public String finalCode;
    public ArrayList<idCollector> identifiers;


    public JMoji()
    {
        finalCode = "";
        identifiers = new ArrayList<idCollector>();
    }

    public String getFinalCode()
    {
        return finalCode;
    }

    public void identifyLine(String arr, int lineCounter)
    {
        System.out.println(arr);
        String parsed = EmojiParser.parseToAliases(arr); //create string of names
        String[] splitArr = parsed.split(":"); //split by colon identifier

        boolean needID = true;
        for(int id=0;id<identifiers.size();++id)
        {
            if(splitArr[1].equals(identifiers.get(id).getName()))
            {
                if(splitArr[3].equals("rainbow")) {
                    if (identifiers.get(id).getType().equals("boolean")) {
                        reassignBool(splitArr, lineCounter);
                        needID = false;
                        break;
                    }
                }
            }
        }

        if(needID == true) {
            switch (splitArr[1]) {
                case "": //System.out.println("Whitespace"); //Ignores whitespace
                    break;
                case "yin_yang":
                    parseBool(splitArr, lineCounter);
                    break;
                case "1234":
                    parseInt(splitArr);
                    break;
                default:
                    System.out.println("ERROR IN LINE " + lineCounter + ". INVALID FIRST CHARACTER.");
                    break;
            }
        }
    }

    private void parseBool(String[] splitArr, int lineCounter)
    {
        System.out.println("Identified boolean.");

        String identifier = splitArr[3]; //should be second
        identifiers.add(new idCollector(identifier, "boolean"));
        if(splitArr[5].equals("rainbow"))
        {
            if(splitArr[7].equals("white_circle"))
            {
                finalCode += "bool "+splitArr[3]+" = true;\n";
                System.out.println(finalCode);
            }
            else if(splitArr[7].equals("black_circle"))
            {
                finalCode += "bool "+splitArr[3]+" = false;\n";
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

    private void reassignBool(String[] arr, int lineCounter)
    {
        if(arr[3].equals("rainbow"))
        {
            if(arr[5].equals("white_circle"))
            {
                finalCode += arr[1]+" = true;\n";
                System.out.println(finalCode);
            }
            else if(arr[5].equals("black_circle"))
            {
                finalCode += arr[1]+" = false;\n";
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

class idCollector
{
    String name;
    String type;
    public idCollector(String nameIn, String typeIn)
    {
        name = nameIn;
        type = typeIn;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }
}
