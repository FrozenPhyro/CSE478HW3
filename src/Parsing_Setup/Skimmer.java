package Parsing_Setup;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by FrozenPhyro on 10/27/2016.
 */
public class Skimmer {
    public static void main(String[] args) {
        String input_file = args[0];
        String output_file = args[1];

        try {
            File input = new File(input_file);
            File output = new File(output_file);

            Scanner scanner = new Scanner(input);
            PrintWriter fw = new PrintWriter(output, "UTF8");

            String line;
            String new_line;
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                new_line = "";
                String[] tokens = line.split(" ");
                int i;
                for (i = 0; i < tokens.length; i++) {
                    if (!tokens[i].isEmpty()) {
                        if (i == tokens.length-1) {
                            new_line = new_line + tokens[i];
                        } else {
                            new_line = new_line + tokens[i] + " ";
                        }
                    }
                }
                fw.println(new_line);
            }

            fw.close();
            scanner.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
