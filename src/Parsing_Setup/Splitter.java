package Parsing_Setup;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Lance Schendt on 10/16/2016.
 */
public class Splitter {
    public static void main(String[] args) {
        String input_file = "";
        String test_set_file = "";
        String training_set_file = "";

        for (int i = 0; i < args.length; i=i+2) {
            switch (args[i]) {
                case "-if" : input_file = args[i+1]; break;
                case "-te" : test_set_file = args[i+1]; break;
                case "-tr" : training_set_file = args[i+1]; break;
            }
        }

        try {
            File file = new File(input_file);
            Scanner s = new Scanner(file);

            ArrayList<String> ib = new ArrayList<>();
            String line;
            while (s.hasNext()){
                line = s.nextLine();
                ib.add(line);
            }
            Random r = new Random();
            PrintWriter pw2 = new PrintWriter(training_set_file);
            int tb_size = ib.size() / 2;
            for (int i = 0; i < tb_size; i++) {
                int j = r.nextInt(ib.size());
                String removed = ib.remove(j);
                ib.add(removed);
                pw2.println(removed);
            }

            PrintWriter pw3 = new PrintWriter(test_set_file);
            for (String string : ib) {
                pw3.println(string);
            }


            pw2.close();
            pw3.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
