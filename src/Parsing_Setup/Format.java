package Parsing_Setup;

import Parsing_Setup.Attribute;

import java.util.ArrayList;

/***********************************************
 * CSCE 478 Fall 2016 Homework 3 - Naive Bayes.Bayes
 * Created by Lance Schendt on 10/26/2016.
 * Co-Author  Kevin Allen
 ***********************************************
 */
public class Format {
//-Parameters------------------------------------
    private Attribute classification;
    private ArrayList<Attribute>    attributes;
    private Integer                 key_id;
//-----------------------------------------------
//-Constructor-----------------------------------
    public Format () {
        this.attributes = new ArrayList<>();
    }
//-----------------------------------------------
//-Getters---------------------------------------
    public Attribute getClassification() {
        return this.classification;
    }
    public ArrayList<String> getLabels() {
        return this.classification.getDomain();
    }
    public Attribute getAttribute(int i) {
        return this.attributes.get(i);
    }
    public ArrayList<Attribute> getAttributes() {
        return this.attributes;
    }
//-----------------------------------------------
//-Adders \ Setters------------------------------
    public void addAttribute(Attribute a) {
        this.attributes.add(a);
    }
    public void setClassification(Attribute a) {
        this.classification = a;
    }
    public void setKeyId(int i) {
        this.key_id = i;
    }
//-----------------------------------------------
//-Getters---------------------------------------
    public Integer getKeyId() {
        return this.key_id;
    }

}
