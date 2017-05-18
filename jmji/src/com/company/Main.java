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
        code+="}\n}";
        try{
            PrintWriter writer = new PrintWriter("C:\\Users\\Sophie\\Desktop\\JMoji\\jmji\\src\\com\\company\\emojicode.java", "UTF-8");
            writer.println(code);
            writer.close();
        } catch (IOException e) {
            // do something
        }

        emojicode.main(args);

    }
}

class JMoji
{
    public String finalCode;
    public ArrayList<idCollector> identifiers;


    public JMoji()
    {
        finalCode = "package com.company;\npublic class emojicode {\npublic static void main (String args[])\n{\n";
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
        System.out.println(parsed);

        boolean needID = true;
        for(int id=0;id<identifiers.size();++id)
        {
            if(splitArr[1].equals(identifiers.get(id).getName())) {
                if (splitArr[3].equals("rainbow")) {
                    if (identifiers.get(id).getType().equals("boolean")) {
                        reassignBool(splitArr, lineCounter);
                        needID = false;
                        break;
                    }
                    else if (identifiers.get(id).getType().equals("integer")) {
                        reassignInt(splitArr, lineCounter);
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
                //primitves
                case "yin_yang":
                    parseBool(splitArr, lineCounter);
                    break;
                case "1234":
                    parseInt(splitArr, lineCounter);
                    break;
                //Method declarations
                // case "void":

                //other functions
                case "printer":
                    printOut(splitArr, lineCounter);
                    break;
                default:
                    System.out.println("ERROR IN LINE " + lineCounter + ". INVALID FIRST CHARACTER.\n");
                    break;
            }
        }
    }


    //methods for primitives
    private void parseBool(String[] splitArr, int lineCounter)
    {
        System.out.println("Identified boolean.");

        String identifier = splitArr[3]; //should be second
        identifiers.add(new idCollector(identifier, "boolean"));
        if(splitArr[5].equals("rainbow"))
        {
            if(splitArr[7].equals("white_circle"))
            {
                finalCode += "boolean "+splitArr[3]+" = true;\n";
                System.out.println(finalCode);
            }
            else if(splitArr[7].equals("black_circle"))
            {
                finalCode += "boolean "+splitArr[3]+" = false;\n";
                System.out.println(finalCode);
            }
            else
            {
                System.out.println("INCORRECT SYNTAX AT LINE "+lineCounter);
                System.out.println("BOOLEAN MUST BE INITIALIZED TO TRUE ⚪ OR FALSE ⚫");
            }
        }
        else
        {
            System.out.println("INCORRECT SYNTAX AT LINE "+lineCounter);
        }
    }
    private void parseInt(String[] splitArr, int lineCounter)
    {

        System.out.println("Identified integer.");
        String identifier = splitArr[3]; //should be second
        identifiers.add(new idCollector(identifier, "integer"));
        if(splitArr[5].equals("rainbow"))
        {
            // Only has integers 1 through 9 because emojis only go from 0 through 9
            if(splitArr[6].equals("0"))
            {
                finalCode += "int "+splitArr[3]+" = 0;\n";
                System.out.println(finalCode);
            }
            if(splitArr[6].equals("1"))
            {
                finalCode += "int "+splitArr[3]+" = 1;\n";
                System.out.println(finalCode);
            }
            else if(splitArr[6].equals("2"))
            {
                finalCode += "int "+splitArr[3]+" = 2;\n";
                System.out.println(finalCode);
            }
            else if(splitArr[6].equals("3"))
            {
                finalCode += "int "+splitArr[3]+" = 3;\n";
                System.out.println(finalCode);
            }
            else if(splitArr[6].equals("4"))
            {
                finalCode += "int "+splitArr[3]+" = 4;\n";
                System.out.println(finalCode);
            }
            else if(splitArr[6].equals("5"))
            {
                finalCode += "int "+splitArr[3]+" = 5;\n";
                System.out.println(finalCode);
            }
            else if(splitArr[6].equals("6"))
            {
                finalCode += "int "+splitArr[3]+" = 6;\n";
                System.out.println(finalCode);
            }
            else if(splitArr[6].equals("7"))
            {
                finalCode += "int "+splitArr[3]+" = 7;\n";
                System.out.println(finalCode);
            }
            else if(splitArr[6].equals("8"))
            {
                finalCode += "int "+splitArr[3]+" = 8;\n";
                System.out.println(finalCode);
            }
            else if(splitArr[6].equals("9"))
            {
                finalCode += "int "+splitArr[3]+" = 9;\n";
                System.out.println(finalCode);
            }
            else
            {
                System.out.println("INCORRECT SYNTAX AT LINE "+lineCounter);
                System.out.println("INT MUST BE SET TO 0 THROUGH 9\n");
            }
        }
        else
        {
            System.out.println("INCORRECT SYNTAX AT LINE "+lineCounter);
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
                System.out.println("INCORRECT SYNTAX AT LINE "+lineCounter);
                System.out.println("BOOLEAN MUST BE INITIALIZED TO TRUE ⚪ OR FALSE ⚫");
            }
        }
        else
        {
            System.out.println("INCORRECT SYNTAX AT LINE "+lineCounter);
        }
    }
    private void reassignInt(String[] arr, int lineCounter)
    {
        if(arr[3].equals("rainbow"))
        {
            if(arr[4].equals("0"))
            {
                finalCode += arr[1]+" = 0;\n";
                System.out.println(finalCode);
            }
            else if(arr[4].equals("1"))
            {
                finalCode += arr[1]+" = 1;\n";
                System.out.println(finalCode);
            }
            else if(arr[4].equals("2"))
            {
                finalCode += arr[1]+" = 2;\n";
                System.out.println(finalCode);
            }
            else if(arr[4].equals("3"))
            {
                finalCode += arr[1]+" = 3;\n";
                System.out.println(finalCode);
            }
            else if(arr[4].equals("4"))
            {
                finalCode += arr[1]+" = 4;\n";
                System.out.println(finalCode);
            }
            else if(arr[4].equals("5"))
            {
                finalCode += arr[1]+" = 5;\n";
                System.out.println(finalCode);
            }
            else if(arr[4].equals("6"))
            {
                finalCode += arr[1]+" = 6;\n";
                System.out.println(finalCode);
            }
            else if(arr[4].equals("7"))
            {
                finalCode += arr[1]+" = 7;\n";
                System.out.println(finalCode);
            }
            else if(arr[4].equals("8"))
            {
                finalCode += arr[1]+" = 8;\n";
                System.out.println(finalCode);
            }
            else if(arr[4].equals("9"))
            {
                finalCode += arr[1]+" = 9;\n";
                System.out.println(finalCode);
            }
            else
            {
                System.out.println("INCORRECT SYNTAX AT LINE "+lineCounter);
                System.out.println("INT MUST BE SET TO 0 THROUGH 9\n");
            }
        }
        else
        {
            System.out.println("INCORRECT SYNTAX AT LINE "+lineCounter);
        }
    }

    //System.out.println
    public void printOut(String[] arr, int lineCounter)
    {
        if(arr.length>=3) {
            finalCode += "System.out.println(" + arr[3] + ");\n";
        }
        else
        {
            System.out.println("ERROR AT LINE "+lineCounter);
        }
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
                    default:System.out.println("ERROR IN LINE "+0000+". INVALID FIRST CHARACTER.\n");
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
