package Parsing_Setup;

import java.util.ArrayList;

/***********************************************
 * CSCE 478 Fall 2016 Homework 3 - Naive Bayes.Bayes
 * Created by Lance Schendt on 10/26/2016.
 * Co-Author  Kevin Allen
 ***********************************************
 */
public class Instance {
//-Parameters------------------------------------
    private ArrayList<ArrayList<String>> data;
    private ArrayList<String> classes;
//-----------------------------------------------
//-Constructor-----------------------------------
    public Instance () {
        this.data = new ArrayList<>();
        this.classes = new ArrayList<>();
    }
//-----------------------------------------------
//-Adders----------------------------------------
    /********************************************
     * Adds data and classification
     * @param data - The data being added
     ********************************************
     */
    public void addData(String[] data) {
        ArrayList<String> row = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            row.add(data[i]);
        }
        this.data.add(row);
    }
    public void addData(ArrayList<String> data) {
        ArrayList<String> row = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            row.add(data.get(i));
        }
        this.data.add(row);
    }
//-----------------------------------------------
//-Getters---------------------------------------
    public String getDataValue(int i, int j) {
        return this.data.get(i).get(j);
    }

    public ArrayList<String> getDataRow(int i) {
        return this.data.get(i);
    }

    public ArrayList<ArrayList<String>> getData() {
        return this.data;
    }

    public String getLabel(int i) {
        return this.classes.get(i);
    }

    public ArrayList<String> getLabels() {
        return this.classes;
    }
//-----------------------------------------------

}
