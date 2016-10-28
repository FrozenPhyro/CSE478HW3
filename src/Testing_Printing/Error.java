package Testing_Printing;

import Bayes.Result;

import java.util.ArrayList;

/***********************************************
 * CSCE 478 Fall 2016 Homework 3 - Naive Bayes.Bayes
 * Created by Lance Schendt on 10/26/2016.
 * Co-Author  Kevin Allen
 ***********************************************
 */
public class Error {

    public double
    EmpiricalError(ArrayList<String> correct,
                   ArrayList<Result> results) {
        int sum = 0;
        int i;
        for (i = 0; i < correct.size(); i++) {
            String c = correct.get(i);
            String r = results.get(i).result;
            if (!c.equals(r)) {
                sum++;
            }
        }
        double error = (double) sum / correct.size();
        return error;
    }

    public ArrayList<Double>
    ConfidenceIntervals(int size, double error) {
        ArrayList<Double> interval = new ArrayList<>();

        double z = 0.0;
        if (error <= 0.50) { z = 0.67; }
        else if (error > 0.50 && error <= 0.68) { z = 1.00; }
        else if (error > 0.68 && error <= 0.80) { z = 1.28; }
        else if (error > 0.80 && error <= 0.90) { z = 1.64; }
        else if (error > 0.90 && error <= 0.95) { z = 1.96; }
        else if (error > 0.95 && error <= 0.98) { z = 2.23; }
        else if (error > 0.98 && error <= 0.99) { z = 2.58; }

        double inner = (error) * (1 - error) / size;
        double sqrt = Math.sqrt(inner);

        interval.add(error - z * sqrt);
        interval.add(error + z * sqrt);
        return interval;
    }
}
