import java.util.ArrayList;

/************************************************
 * Created by Lance Schendt on 10/23/2016.
 * CSCE 478 Homework 3
 ************************************************
 */
public class LikelyHoodTable {
//-Parameters------------------------------------
    private String name;
    private Integer id;
    private FreqTable freq_table;
    private ArrayList<Double> value_probs;
    private ArrayList<Double> class_probs;
//-----------------------------------------------

//-Constructors----------------------------------
    public LikelyHoodTable (String name, int id, FreqTable ft) {
        this.name = name;
        this.id = id;
        this.freq_table = ft;
        this.value_probs = new ArrayList<>();
        this.class_probs = new ArrayList<>();
    }
//-----------------------------------------------

//-
}
