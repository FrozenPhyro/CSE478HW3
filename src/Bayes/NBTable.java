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
public class NBTable {
//-Parameters for the Bayes.Bayes.NBTable--------------------
    // First key is attribute value, and second key is label
    private HashMap<String,HashMap<String,Integer>> counts;
//-----------------------------------------------
//-Constructor-----------------------------------
    /********************************************
     * @param A - attribute to build the table on
     * @param CL - list of possible labels
     * Output: Blank Bayes.Bayes.NBTable object
     ********************************************
     */
    public NBTable (Attribute A, ArrayList<String> CL) {
        //initialize the storage variable
        this.counts = new HashMap<>();

        //allocate space for variables outside of for loops
        HashMap<String,Integer> hm;
        int i,k;

        //for each domain value k of the attribute
        for (k = 0; k < A.getDomain().size(); k++) {
            //initialize a new HashMap
            hm = new HashMap<>();

            //for each possible label i
            for (i = 0; i < CL.size(); i++) {
                //put a blank count in hm at the label
                hm.put(CL.get(i), 0);
            }

            //put the new HashMap in counts at the value k
            this.counts.put(A.getDomainValue(k), hm);
        }
    }
//-----------------------------------------------
//-Getter----------------------------------------
    /********************************************
     * @param k - domain value of attribute
     * @param l - target label
     * @return the number of training instances with that value set
     ********************************************
     */
    public int getCount(String k, String l) {
        int count = this.counts.get(k).get(l);
        return count;
    }
//-----------------------------------------------
//-Adder-----------------------------------------
    /********************************************
     * @param k - domain value
     * @param l - target label
     * @param i - amount to increase
     ********************************************
     */
    public void setCount(String k, String l, int i) {
        this.counts.get(k).put(l, i);
    }
}
