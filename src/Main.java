import Parsing_Setup.*;
import Bayes.*;

import java.util.ArrayList;

/**
 * Created by FrozenPhyro on 10/27/2016.
 */
public class Main {
    public static void main(String[] args) {
        String format_file = "";
        String training_file = "";
        String testing_file = "";
        String delim = "";
        Double m = 1.0;
        String output_file = "";

        //-Parse Inputs--------------------------
        int i;
        for (i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-f" : { format_file = args[i+1]; break; }
                case "-tr" : { training_file = args[i+1]; break; }
                case "-te" : { testing_file = args[i+1]; break; }
                case "-d" : {
                    if (args[i + 1].equals("space")) {
                        delim = " ";
                    } else {
                        delim = args[i + 1];
                    }
                    break; }
                case "-of" : { output_file = args[i+1]; break; }
                case "-m" : { m = Double.parseDouble(args[i+1]); break; }
            }
        }

        //-Create Parsed Objects-----------------
        Parser parser = new Parser();
        Format format = parser.formatParser(format_file);
        Instance training = parser.instanceParser(training_file,format,delim);
        Instance testing = parser.instanceParser(testing_file,format,delim);

        //-Initialize Bayes----------------------
        Bayes bayes = new Bayes(format, training, m);
        bayes.NaiveBayesTrain(format, training);

        //-Run Testing Set-----------------------
        ArrayList<String> output = new ArrayList<>();
        for (m = 1.0; m < 10; m = m + 0.1) {
            bayes.setM(m);
            int count = 0;
            for (i = 0; i < testing.getData().size(); i++) {
                ArrayList<String> sample = testing.getDataRow(i);
                String result = bayes.ClassifyNewInstance(sample);
                if (result.equals(testing.getLabel(i))) {
                    count++;
                }
            }
            System.out.println((double) count / i);
        }
    }
}
