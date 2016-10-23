import java.util.ArrayList;

/************************************************
 * Created by Lance Schendt on 10/23/2016.
 * CSCE 478 Fall 2016 Homework 3
 ************************************************
 */
public class FreqRow {
//-Parameters Section----------------------------
    private String value;               // attribute value
    private ArrayList<Integer> counts;  // number of instances with this value
//-----------------------------------------------

//-Constructors----------------------------------
    public FreqRow() {
        this.counts = new ArrayList<>();
    }
    public FreqRow(String value) {
        this.value = value;
        this.counts = new ArrayList<>();
    }
//-----------------------------------------------

//-Intializer------------------------------------
    public void init(int num_of_classes) {
        for (int i = 0; i < num_of_classes; i++) {
            this.counts.add(0);
        }
    }
//-----------------------------------------------

//-Getters / Adders------------------------------
    public String getValue() { return this.value; }
    public Integer getCount(int pos) { return this.counts.get(pos); }
    public ArrayList<Integer> getCounts() { return this.counts; }

    public void addCount(int pos) {
        this.counts.set(pos, this.counts.get(pos) + 1);
    }
//-----------------------------------------------
}
