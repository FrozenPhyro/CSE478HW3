package Testing_Printing;

import Bayes.*;
import Parsing_Setup.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/***********************************************
 * CSCE 478 Fall 2016 Homework 3 - Naive Bayes.Bayes
 * Created by Lance Schendt on 10/26/2016.
 * Co-Author  Kevin Allen
 ***********************************************
 */
public class Printing {

    public void PrintTables(String object_file,
                            Bayes bayes,
                            Format format) {
        File file = new File(object_file);
        try {
            FileWriter fw = new FileWriter(file);
            int i,j,h;
            for (i = 0; i < bayes.getTables().size(); i++) {

                String title_bar = " ";
                for (h = 0; h < format.getLabels().size(); h++) {
                    title_bar = title_bar + "," + format.getLabel(h);
                }
                title_bar = title_bar + "\n";
                fw.append(title_bar);
                for (j = 0; j < format.getAttribute(i).getDomain().size(); j++) {
                    String line = format.getAttribute(i).getDomainValue(j);
                    for (h = 0; h < format.getLabels().size(); h++) {
                        line = line + "," + bayes.count(i,format.getAttribute(i).getDomainValue(j),format.getLabel(h));
                    }
                    line = line + "\n";
                    fw.append(line);
                }
            }
            fw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void PrintResult(String result_file,
                            ArrayList<String> correct,
                            ArrayList<Result> results,
                            Format format) {
        File file = new File(result_file);
        try {
            FileWriter fw = new FileWriter(file);
            int i,j;
            fw.append(" ");
            for (i = 0; i < format.getLabels().size(); i++) {
                fw.append("," + format.getLabel(i));
            } fw.append("\n");

            for (i = 0; i < results.size(); i++) {
                for (j = 0; j < format.getLabels().size(); j++) {
                    if (j == 0) {
                        fw.append(results.get(i).probs.get(j).toString());
                    } else {
                        fw.append("," + results.get(i).probs.get(j).toString());
                    }
                }
                fw.append("\n");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
