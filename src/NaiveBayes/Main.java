package NaiveBayes;

import Parser.*;

/**
 * Created by FrozenPhyro on 10/25/2016.
 */
public class Main {
    public static void main(String[] args){
        String format_file = "";
        String training_file = "";
        String testing_file = "";
        String delim = "";
        String output_object = "";
        String output_file = "";

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
            }
        }

        Parser parser = new Parser();
        Format format = parser.formatParser(format_file);
        Instance train = parser.instanceParser(training_file, format, delim);
        Instance test = parser.instanceParser(testing_file, format, delim);

        NaiveBayes nb = new NaiveBayes(train, test, format.getClassification());

    }
}
