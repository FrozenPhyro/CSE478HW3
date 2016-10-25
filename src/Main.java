import NaiveBayes.*;
import Parser.*;

import java.util.ArrayList;

/************************************************
 * Created by Lance Schendt on 10/24/2016.
 * CSCE 478 Fall 2016 Homework 3
 ************************************************
 */
public class Main {
    public static void main(String[] args){
        String format_file = "";
        String training_file = "";
        String testing_file = "";
        String delim = "";
        String output_object = "";
        String output_results = "";
        String output_file = "";
        double m = 0.0;

        int i;
        for (i = 0; i < args.length; i = i+2) {
            switch (args[i]) {
                case "-f" : { format_file = args[i+1]; break; }
                case "-tr" : { training_file = args[i+1]; break; }
                case "-te" : { testing_file = args[i+1]; break; }
                case "-d" : {
                    if (args[i+1].equals("comma")) {
                        delim = ",";
                    } else {
                        delim = " ";
                    }
                    break;
                }
                case "-oo" : { output_object = args[i+1]; break; }
                case "-of" : { output_file = args[i+1]; break; }
                case "-m" : { m = Double.parseDouble(args[i+1]); break; }
            }
        }
        Parser parser = new Parser();
        Format format = parser.formatParser(format_file);
        Instance train = parser.instanceParser(training_file, format, delim);
        Instance test = parser.instanceParser(testing_file, format, delim);

        NaiveBayes nb = new NaiveBayes(train, test, format.getClassification(), m);
        nb.Setup(format.getAttributes(), format.getClassification().getDomain());
        nb.NaiveBayesLearn(train);

        if (!output_object.equals("")) {
            //TODO: Object Printer
        }
        ArrayList<String> results = nb.Run(train);
        if (!output_file.equals("")) {
            //TODO: Write to file
        }
    }
}
