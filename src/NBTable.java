import java.util.ArrayList;
import java.util.HashMap;

/************************************************
 * Created by Lance Schendt on 10/24/2016.
 * CSCE 478 Fall 2016 Homework 3
 ************************************************
 */
public class NBTable {
/************************************************
 * Class makes up the frequency table and
 * likelyhood table.  Uses ArrayLists for frequency
 * table section for easy incrementing.  HashMaps
 * for the likelyhood table for faster look ups
 ************************************************
 */
    private String  name;
    private Integer id;
//-Frequency Table-------------------------------
    private ArrayList<ArrayList<Integer>>   counts;
    private ArrayList<String>               domain;
    private ArrayList<String>               classes;
    private ArrayList<Integer>              totals;
//-----------------------------------------------
//-Likelyhood Table------------------------------
    private Integer                         amount;
    private HashMap<String,Double>          class_prob;
    private HashMap<String,Double>          value_prob;
//-----------------------------------------------
//-Constructors----------------------------------
    /********************************************
     * Naive Bayes Table Creators
     * @param a - Attribute table is based one
     * @param cl - list of possible classifications
     ********************************************
     */
    public NBTable (Attribute a, ArrayList<String> cl) {
        int i, j;
        this.name = a.getName();
        this.id = a.getID();
        //-Frequency Table Setup-----------------
            this.counts = new ArrayList<>();
            for (i = 0; i < a.getDomain().size(); i++) {
                ArrayList<Integer> row = new ArrayList<>();
                for (j = 0; j < cl.size(); j++) {
                    row.add(0);
                }
                this.counts.add(row);
            }
            this.domain = new ArrayList<>(a.getDomain());
            this.classes = new ArrayList<>(cl);
            this.totals = new ArrayList<>();
            for (i = 0; i < cl.size(); i++) {
                this.totals.add(0);
            }
        //-Likelyhood Table Setup-----------------
            this.class_prob = new HashMap<>();
            this.amount = 0;
            for (i = 0; i < cl.size(); i++) {
                this.class_prob.put(cl.get(i),0.0);
            }
            this.value_prob = new HashMap<>();
            for (i = 0; i < a.getDomain().size(); i++) {
                this.value_prob.put(a.getDomain().get(i),0.0);
            }
    }
    public NBTable (Attribute a, String[] cl) {
        int i, j;
        this.name = a.getName();
        this.id = a.getID();
        //-Frequency Table Setup-----------------
            this.counts = new ArrayList<>();
            for (i = 0; i < a.getDomain().size(); i++) {
                ArrayList<Integer> row = new ArrayList<>();
                for (j = 0; j < cl.length; j++) {
                    row.add(0);
                }
                this.counts.add(row);
            }
            this.domain = new ArrayList<>(a.getDomain());
            this.classes = new ArrayList<>();
            this.totals = new ArrayList<>();
            for (i = 0; i < cl.length; i++) {
                this.classes.add(cl[i]);
                this.totals.add(0);
            }
        //-Likelyhood Table Setup----------------
            this.amount = 0;
            this.class_prob = new HashMap<>();
            for (i = 0; i < cl.length; i++) {
                this.class_prob.put(cl[i], 0.0);
            }
            this.value_prob = new HashMap<>();
            for (i = 0; i < a.getDomain().size(); i++) {
                this.value_prob.put(a.getDomain().get(i), 0.0);
            }
    }
//-----------------------------------------------
//-Counters \ Calculators------------------------

    /********************************************
     * Fills the frequency part of the table out
     * @param i - the training set
     *******************************************
     */
    public void FrequencyCounter(Instance i) {
        int a,b,c;
        for (a = 0; a < i.getData().size(); a++) {
            //-individual counts----------------
            for (b = 0; b < this.domain.size(); b++) {
                if (i.getDataItem(a, this.id).equals(this.domain.get(b))) {
                    for (c = 0; c < this.classes.size(); c++) {
                        if (i.getClasses().get(a).equals(this.classes.get(c))) {
                            addOne(b, c);
                        }
                    }
                }
            }
            //-totals for classes----------------
            for (b = 0; b < this.classes.size(); b++) {
                if (i.getClasses().get(a).equals(this.classes.get(b))) {
                    this.totals.set(b,this.totals.get(b) + 1);
                }
            }
        }
        //-number of instances in training set---
        this.amount = a;
    }

    /********************************************
     * Used to calculate the probabilities in
     * the likelihood table.
     ********************************************
     */
    public void CalculateLikelihood() {
        int c,d,sum;
        //-value likelihood----------------------
        for (d = 0; d < this.domain.size(); d++) {
            sum = 0;
            for (c = 0; c < this.classes.size(); c++) {
                sum += getCount(d, c);
            }
            double value = (double)sum/this.amount;
            this.value_prob.put(this.domain.get(c), value);
        }
        //-class likelihood----------------------
        for (c = 0; c < this.classes.size(); c++) {
            sum = 0;
            for (d = 0; d < this.domain.size(); d++) {
                sum += getCount(d, c);
            }
            double value = (double)sum/this.amount;
            this.class_prob.put(this.classes.get(c), value);
        }
    }

//-----------------------------------------------
//-Adders----------------------------------------
    public void addOne(int d, int c) {
        this.counts.get(d).set(c,this.counts.get(d).get(c) + 1);
    }

//-Getters---------------------------------------
    public int getCount(int d, int c) {
        return this.counts.get(d).get(c);
    }
}
