package Bayes;

import java.util.ArrayList;
import java.util.HashMap;
import Parsing_Setup.*;

/***********************************************
 * CSCE 478 Fall 2016 Homework 3 - Naive Bayes.Bayes
 * Created by Lance Schendt on 10/26/2016.
 * Co-Author  Kevin Allen
 ***********************************************
 */
public class Bayes {
//-Parameters for Bayes.Bayes--------------------------
    private Instance                training;
    private ArrayList<NBTable>      tables;
    private ArrayList<String>		labels;
    private HashMap<String,Integer> totals;
    private Double                  m;
    private Double                  p;
//-----------------------------------------------
//-Constructor-----------------------------------
    /********************************************
     * @param T - the training instance to work with
     * @param F - the format of the instance
     * @param m - the value of m that was chosen
     ********************************************
     */
    public Bayes(Format F, Instance T, Double m) {
        this.training = T;
        this.labels = new ArrayList<>(F.getLabels());
        this.tables = new ArrayList<>();
        for (int i = 0; i < F.getAttributes().size(); i++) {
            this.tables.add(new NBTable(F.getAttribute(i),F.getLabels()));
        }
        this.m = m;
        this.totals = new HashMap<>();
        for (int i = 0; i < F.getLabels().size(); i++) {
            this.totals.put(F.getLabels().get(i), 0);
        }
        this.p = 1.0;
    }
//-Naive Training--------------------------------
    /********************************************
     * @param F - format object to use to train
     * @param T - instance object to train from
     ********************************************
     */
    public void NaiveBayesTrain(Format F, Instance T) {
        //allocate space outside of for loop
        int h,j,k;
        String value,label;
        // for each sample in the training set
        for (h = 0; h < T.getData().size(); h++) {
            // for each attribute in the sample
            label = T.getLabel(h);
            for (k = 0; k < F.getAttributes().size(); k++) {
                // Get the values for accessing the table
                value = T.getDataValue(h,k);
                add(k,value,label);
                //System.out.println("add one to table " + k + " value " + value + " class " + label);
            }
            add(label);
        }
    }
//-----------------------------------------------
//-Classify New Parsing_Setup.Instance-------------------------
    /********************************************
     * @param X - the instance to be classified
     * @return the prediction of the algorithm
     ********************************************
     */
    public Result ClassifyNewInstance(ArrayList<String> X) {

        //variables for function use
        Result result = new Result();
        String prediction = "";
        String label = "";
        double max_prob = 0.0;
        double pcix, pci, pxci, pxkci;
        int i,k;

        //for each possible label for the sample do the following
        for (i = 0; i < this.labels.size(); i++) {
            //reset at 1.0 for a starting point
            pxci = 1.0;
            label = this.labels.get(i);

            //find the probability of label given the training data
            pci = (double) freq(label) / card(this.training);

            //find the combined probability of the given attributes
            //given a naive approach
            int count = 0;
            for (k = 0; k < X.size(); k++) {
                //find the probability of an individual attribute with the give value
                pxkci = (double) count(k,X.get(k),label) / freq(label);
                //System.out.println(pxkci);
                //if the probability is zero in act the following
                if (pxkci == 0.0) {
                    count++;
                    //System.out.println("Using m-Estimate");
                    double p = pEstimate(this.training.getData().size(), count);
                    pxkci = mEstimate(freq(label),count(k,X.get(k),label),this.m,p);
                    //System.out.println(pxkci);
                }
                //II section for the current attribute
                pxci = pxkci * pxci;
            }

            //perform the approximation of Bayes.Bayes algorithm
            pcix = pci * pxci;
            result.probs.add(pcix);

            //if the highest probability, set prediction each to class,
            //and set the max_prob found to compare against later.
            if (pcix > max_prob) {
                max_prob = pcix;
                prediction = this.labels.get(i);
            }
        }
        result.result = prediction;
        return result;
    }
//-----------------------------------------------
//-Utlities--------------------------------------

    /********************************************
     * freq - frequency of the label (class)
     * @param c - the label to check
     * @return the frequency at which it accrues
     ********************************************
     */
    public int freq(String c) {
        int freq = this.totals.get(c);
        return freq;
    }

    /********************************************
     * card - cardinality of the instance
     * @param T - the instance to get the card of
     * @return the cardinality of the instance (size)
     ********************************************
     */
    public int card(Instance T) {
        int card = T.getData().size();
        return card;
    }

    /********************************************
     * count - number of training instances that were of the label
     * @param k - the attribute table id
     * @param x - the value of the attribute in question
     * @param c - the label in question
     * @return the number of instances with that combination
     ********************************************
     */
    public int count(int k, String x, String c) {
        //access the required table
        NBTable nbt = this.tables.get(k);
        //get the count at the value of the attribute and the label in question
        int count = nbt.getCount(x,c);
        return count;
    }

    /********************************************
     * add(String c) - Add to the total for the label
     * @param c - the label to add to
     ********************************************
     */
    public void add(String c) {
        //Get the initial value
        int count = this.totals.get(c);
        count = count + 1;
        this.totals.put(c, count);
    }

    /********************************************
     * add(int,String,String) - add to the count for the combination
     * @param k - the id of the table to add to
     * @param x - the value of the attribute in question
     * @param c - the label in question
     ********************************************
     */
    public void add(int k, String x, String c) {
        //access the required table
        NBTable nbt = this.tables.get(k);
        //Get the initial value
        int count = nbt.getCount(x,c);
        count = count + 1;
        this.tables.get(k).setCount(x, c, count);
    }

    public double mEstimate(int n, int nc, double m, double p) {
        double pxkci = (nc + (m * p)) / (n + m);
        return pxkci;
    }

    public double pEstimate(int size, int count) {
        double p = (double) count / size;
        return p;
    }

    public ArrayList<NBTable> getTables() { return this.tables; }
    public void setM(double m) { this.m = m; }
    public void setP(double p) { this.p = p;}
//-----------------------------------------------

}
