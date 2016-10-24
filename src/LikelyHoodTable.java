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

    private int total;
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

//-Calculators-----------------------------------
    public LikelyHoodTable calculateClassProbs() {
        int i;
        double d;
        for (i = 0; i < this.freq_table.getTotals().size(); i++) {
            d = (double)this.freq_table.getTotal(i)/this.total;
            this.class_probs.add(d);
        }
        return this;
    }

    public LikelyHoodTable calculateValueProbs() {
        int i,j,sum;
        double d;
        for (i = 0; i < this.freq_table.getRows().size(); i++) {
            sum = 0;
            for (j = 0; j < this.freq_table.getClasses().size(); j++) {
                sum += this.freq_table.getRow(i).getCount(j);
            }
            d = (double)sum/this.total;
            this.value_probs.add(d);
        }
        return this;
    }

//-Table Filler----------------------------------
    public LikelyHoodTable tableFiller() {
        this.setTotal();
        this.calculateClassProbs();
        this.calculateValueProbs();
        return this;
    }


    public void setTotal() {
        int total = 0; int i;
        for (i = 0; i < this.freq_table.getTotals().size(); i++) {
            total += this.freq_table.getTotal(i);
        }
        this.total = total;
    }
//-----------------------------------------------

//-Getters---------------------------------------
    public FreqTable getFreqTable() { return this.freq_table; }

    public ArrayList<Double> getClassProbs() { return this.class_probs; }
    public double getClassProb(int pos) { return this.class_probs.get(pos); }

    public ArrayList<Double> getValueProbs() { return this.value_probs; }
    public double getValueProb(int pos) { return this.value_probs.get(pos); }
}
