import Parsing_Setup.*;
import Bayes.*;
import Testing_Printing.*;
import Testing_Printing.Error;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        Double p = 0.0;
        String output_object = "";
        String output_result = "";
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
                case "-oo" : { output_object = args[i+1]; break; }
                case "-or" : { output_result = args[i+1]; break; }
                case "-of" : { output_file = args[i+1]; break; }
                case "-m" : { m = Double.parseDouble(args[i+1]); break; }
            }
        }

        //-Create Parsed Objects-----------------
        Printing printer = new Printing();
        Error e = new Error();
        Parser parser = new Parser();

        Format format = parser.formatParser(format_file);
        Instance training = parser.instanceParser(training_file,format,delim);
        Instance testing = parser.instanceParser(testing_file,format,delim);

        //-Initialize Bayes----------------------
        Bayes bayes = new Bayes(format, training, m);
        bayes.NaiveBayesTrain(format, training);
        printer.PrintTables(output_object,bayes,format);

        //-Testing Bayes-------------------------
        int j; double k;
        try {
            File file = new File(output_file);
            FileWriter fw = new FileWriter(file);
            fw.append("m,p,lower,higher,error\n");
            for (j = 0; j < 5; j++) {
                bayes.setM(m+j);
                for (k = 0.0; k < 1; k = k + 0.05) {
                    bayes.setP(p+k);
                    //String result_file = new String(output_result);
                    //result_file = result_file + "_" + (j+m) + "_" + (k+p) + ".csv";
                    ArrayList<Result> results = new ArrayList<>();
                    for (i = 0; i < testing.getData().size(); i++) {
                        Result result = bayes.ClassifyNewInstance(testing.getDataRow(i));
                        results.add(result);
                    }
                    //printer.PrintResult(result_file,testing.getLabels(),results,format);
                    fw.append((m+j) + "," + (p+k) + ",");
                    Double error = e.EmpiricalError(testing.getLabels(),results);
                    ArrayList<Double> conf = e.ConfidenceIntervals(testing.getLabels().size(),error);
                    for (i = 0; i < conf.size(); i++) {
                        fw.append(conf.get(i) + ",");
                    }
                    fw.append(error + "\n");
                }
            }
            fw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
