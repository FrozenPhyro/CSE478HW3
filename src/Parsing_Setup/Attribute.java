package Parsing_Setup;

import java.util.ArrayList;

/***********************************************
 * CSCE 478 Fall 2016 Homework 3 - Naive Bayes.Bayes
 * Created by Lance Schendt on 10/26/2016.
 * Co-Author  Kevin Allen
 ***********************************************
 */
public class Attribute {
//-Parameters------------------------------------
    private String name;
    private int id;
    private ArrayList<String> domain;
//-----------------------------------------------
//-Constructors----------------------------------
    public Attribute(String name, int id) {
        this.name = name;
        this.id = id;
        this.domain = new ArrayList<>();
    }
//-----------------------------------------------
//-Getters---------------------------------------
    public int getID() {
        return this.id;
    }
    public ArrayList<String> getDomain() {
        return this.domain;
    }
//-----------------------------------------------
//-Adders----------------------------------------
    public void addDomainValue(String value) {
        this.domain.add(value);
    }

    public void setId(int i) { this.id = i; }

    public String getDomainValue(int k) {
        String value = this.getDomain().get(k);
        return value;
    }
//-----------------------------------------------

}
