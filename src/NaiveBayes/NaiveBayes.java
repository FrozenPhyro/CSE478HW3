package NaiveBayes;

import Parser.Attribute;
import Parser.Instance;

import java.util.ArrayList;
import java.util.HashMap;

/************************************************
 * Created by Lance Schendt on 10/23/2016.
 * CSCE 478 Fall Homework 3
 ************************************************
 */
public class NaiveBayes {
//-Parameters------------------------------------
    private Instance                training_set;
    private Instance                testing_set;
    private ArrayList<String>       classes;
    private HashMap<String,Integer> totals;
    private ArrayList<NBTable>      tables;
    private HashMap<String, Double> class_prob;
    private Double                  m;
//-----------------------------------------------
//-Constructor-----------------------------------
    public NaiveBayes (Instance training_set,
                       Instance testing_set,
                       Attribute classification,
                       double m) {
        this.training_set = training_set;
        this.testing_set = testing_set;
        this.classes = classification.getDomain();
        this.tables = new ArrayList<>();
        this.tables = new ArrayList<>();
    }
//-----------------------------------------------
//-Setup-----------------------------------------
    public void Setup(ArrayList<Attribute> a, ArrayList<String> cl) {
        int i;
        for (i = 0; i < a.size(); i++) {
            NBTable nbt = new NBTable(a.get(i), cl);
            this.tables.add(nbt);
        }
    }
//-----------------------------------------------
//-Algorithm-------------------------------------
    public void NaiveBayesLearn(Instance set) {
        int i,j;
        for (i = 0; i < this.tables.size(); i++) {
            this.tables.get(i).FrequencyCounter(set);
            this.tables.get(i).CalculateLikelihood();
        }
        for (i = 0; i < this.classes.size(); i++) {
            classProbCalc(this.classes.get(i));
        }
        for (i = 0; i < this.classes.size(); i++) {
            int count = 0;
            for (j = 0; j < set.getClasses().size(); j++) {
                if (this.classes.get(i).equals(set.getClassification(j))) {
                    count++;
                }
            }
            this.totals.put(this.classes.get(i), count);
        }
    }

    public String ClassifyNewInstance(ArrayList<String> x) {
        double max = 0.0;
        String max_class = "";
        int i;
        for (i = 0; i < this.classes.size(); i++) {
            double prob = likeProbCalc(x, this.classes.get(i)) * this.class_prob.get(this.classes.get(i));
            if (prob > max) {
                max = prob;
                max_class = this.classes.get(i);
            }
        }
        return max_class;
    }

    public ArrayList<String> Run(Instance set) {
        ArrayList<String> results = new ArrayList<>();
        int i;
        for (i = 0; i < set.getData().size(); i++) {
            String result = ClassifyNewInstance(set.getDataRow(i));
            results.add(result);
        }
        return results;
    }
//-----------------------------------------------
//-Probability Calculators-----------------------
    public double likeProbCalc(ArrayList<String> x, String c) {
        double value,total;
        double p = 0.0;
        double prob = 1.0;
        int count = 0;
        for (String i : x) {
            value = (double)this.tables.get(count).getCount(i, c);
            if (value == 0.0) {
                p = mEstimateCalc(this.totals.get(c), this.tables.get(count).getTotal(c), p, m);
            } else {
                total = (double) this.tables.get(count).getTotal(c);
                p = value / total;
            }
            prob = prob * p;
            count++;
        }
        return prob;
    }

    public void classProbCalc(String c) {
        double sum = 0.0;
        int i,j;
        for (i = 0; i < this.tables.size(); i++) {
            sum += this.tables.get(i).getClassProb(c);
        }
        this.class_prob.put(c, sum);
    }

    public double mEstimateCalc(int n, int nc, double p, double m) {
        double d;
        d = (nc + (m * p))/(n + m);
        return d;
    }
}
