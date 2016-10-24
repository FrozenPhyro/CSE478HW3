package Parser;

import java.util.ArrayList;

/************************************************
 * Created by Lance Schendt on 10/24/2016.
 * CSCE 478 Fall 2016 Homework 3
 ************************************************
 */
public class Format {
//-Parameters------------------------------------
    private Attribute               classification;
    private ArrayList<Attribute>    attributes;
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
//-----------------------------------------------
}
