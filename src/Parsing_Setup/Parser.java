package Parsing_Setup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/***********************************************
 * CSCE 478 Fall 2016 Homework 3 - Naive Bayes.Bayes
 * Created by Lance Schendt on 10/26/2016.
 * Co-Author  Kevin Allen
 ***********************************************
 */
public class Parser {
//-Constructor-----------------------------------
    public Parser () {}
//-----------------------------------------------
//-Parsing_Setup.Format Parsing_Setup.Parser---------------------------------
    /********************************************
     * Parsing_Setup.Format Parsing_Setup.Parser.Parsing_Setup.Parser: parses out the format for creating an instance
     * @param format_file : format for the attributes
     * @return : Started and Formatted instance file
     ********************************************
     */
    public Format formatParser(String format_file) {
        Format format = new Format();
        try {
            File file = new File(format_file);
            Scanner scanner = new Scanner(file);
            String line;

            int id = 0;
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                String[] tokens = line.split(",");
                Attribute a = new Attribute(tokens[0], id);
                String[] values = tokens[1].split(" ");
                for (int i = 1; i < values.length; i++) {
                    a.addDomainValue(values[i]);
                }
                if (tokens[0].equals("class")) {
                    format.setClassification(a);
                } else if (tokens[0].equals("key")){
                    format.setKeyId(id);
                } else {
                    format.addAttribute(a);
                }
                id++;
            }
            for (int i = 0; i < format.getAttributes().size(); i++) {
                format.getAttribute(i).setId(i);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return format;
    }
//-----------------------------------------------
//-Parsing_Setup.Instance Parsing_Setup.Parser-------------------------------
    /********************************************
     * Parses Data Sets into an instance for later use
     * @param instance_file - file where instance is located at
     * @param format - previously parsed in format object
     * @param delim - delimiter for the instance file
     ********************************************
     */
    public Instance instanceParser(String instance_file, Format format, String delim) {
        Instance set = new Instance();
        try {
            File file = new File(instance_file);
            Scanner scanner = new Scanner(file);

            String line = "";
            while (scanner.hasNext()) {
                ArrayList<String> row = new ArrayList<>();
                line = scanner.nextLine();
                String[] tokens = line.split(delim);
                int i;
                for (i = 0; i < tokens.length; i++) {
                    if (i == format.getClassification().getID()) {
                        set.getLabels().add(tokens[i]);
                    } else if (null != format.getKeyId() && i == format.getKeyId()) {

                    } else {
                        row.add(tokens[i]);
                    }
                }
                set.addData(row);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return set;
    }
}
