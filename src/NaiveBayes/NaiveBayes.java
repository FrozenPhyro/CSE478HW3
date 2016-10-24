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
    private Instance training_set;
    private Instance            testing_set;
    private ArrayList<String>   classes;
    private ArrayList<NBTable>  tables;
    private HashMap<String, Double> class_prob;
//-----------------------------------------------
//-Constructor-----------------------------------
    public NaiveBayes (Instance training_set,
                       Instance testing_set,
                       Attribute classification) {
        this.training_set = training_set;
        this.testing_set = testing_set;
        this.classes = classification.getDomain();
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
        int i;
        for (i = 0; i < this.tables.size(); i++) {
            this.tables.get(i).FrequencyCounter(set);
            this.tables.get(i).CalculateLikelihood();
        }
    }

    public String ClassifyNewInstance(ArrayList<String> x) {
        double max = 0.0;
        String max_class = "";
        int i;
        for (i = 0; i < this.classes.size(); i++) {
            double prob = likeProbCalc(x, this.classes.get(i)) * classProbCalc(this.classes.get(i));
            if (prob > max) {
                max = prob;
                max_class = this.classes.get(i);
            }
        }
        return max_class;
    }
//-----------------------------------------------
//-Probability Calculators-----------------------
    public double likeProbCalc(ArrayList<String> x, String c) {
        double value,total,p;
        double prob = 1.0;
        int count = 0;
        for (String i : x) {
            value = (double)this.tables.get(count).getCount(i, c);
            total = (double)this.tables.get(count).getTotal(c);
            p = value/total;
            prob = prob * p;
            count++;
        }
        return prob;
    }

    public double classProbCalc(String c) {
        double sum = 0.0;
        int i,j;
        for (i = 0; i < this.tables.size(); i++) {
            sum += this.tables.get(i).getClassProb(c);
        }
        return sum;
    }
}
