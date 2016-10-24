package Parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/************************************************
 * Created by FrozenPhyro on 10/24/2016.
 * CSCE 478 Fall 2016 Homework
 ************************************************
 */
public class Parser {
//-Constructor-----------------------------------
    public Parser () {}
//-----------------------------------------------
//-Format Parser---------------------------------
    /********************************************
     * Format Parser.Parser: parses out the format for creating an instance
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
                String[] tokens = line.split(" ");
                Attribute a = new Attribute("Class", id);
                String[] values = tokens[1].split(",");
                for (int i = 0; i < values.length; i++) {
                    a.addDomainValue(values[i]);
                }
                format.setClassification(a);
                if (tokens[0].equals("Class")) {
                    format.setClassification(a);
                    id--;
                } else {
                    format.addAttribute(a);
                }
                id++;
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return format;
    }
//-----------------------------------------------
//-Instance Parser-------------------------------
    /********************************************
     * Parses Data Sets into an instance for later use
     * @param instance_file - file where instance is located at
     * @param format - previously parsed in format object
     * @param delim - delimiter for the instance file
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
                for (int i = 0; i < tokens.length; i++) {
                    if (i == format.getClassification().getID()) {
                        set.getClasses().add(tokens[i]);
                    } else {
                        row.add(tokens[i]);
                    }
                    set.addData(row);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return set;
    }
}
