import java.util.ArrayList;

/************************************************
 * Created by Lance Schendt on 10/23/2016.
 * CSCE 478 Fall 2016 Homework 3
 ************************************************
 */
public class FreqTable {
//-Parameters------------------------------------
    private String name;                //attribute name
    private Integer id;                 //attribute position
    private ArrayList<String> classes;  //possible classifications
    private ArrayList<FreqRow> rows;    //data
    private ArrayList<Integer> totals;  //total for each classification
//-----------------------------------------------

//-Constructors----------------------------------
    public FreqTable () {
        this.classes = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.totals = new ArrayList<>();
    }
    public FreqTable (String name, int id) {
        this.name = name;
        this.id = id;
        this.classes = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.totals = new ArrayList<>();
    }
//-----------------------------------------------

//-Functions-------------------------------------

    /********************************************
     * Used to create the frequency table for a value
     * @param A - Attribute to create a table on
     * @param CL - List of possible classifications
     * @return - created table
     ********************************************
     */
    public FreqTable CreateFreqTable(Attribute A, ArrayList<String> CL) {
        int i;
        for (i = 0; i < A.getDomain().size(); i++) {
            FreqRow fr = new FreqRow(A.getDomain().get(i));
            fr.init(classes.size());
            this.rows.add(fr);
        }
        return this;
    }

    /********************************************
     * Used to fill out the frequency table
     * @param IS - the Instance Data Set to be ran
     * @return - the filled out frequency table
     ********************************************
     */
    public FreqTable FillFreqTable(Instance IS) {
        int i,j,k;
        //for each data set in the current instance do
        for (i = 0; i < IS.getData().size(); i++) {
            // for value in the domain of the current attribute
            for (j = 0; j < this.getRows().size(); j++) {

                //if instances attribute is each to domain value
                if (IS.getDataItem(i,this.getId()).equals(this.getRow(j).getValue())) {
                    //find the classification and add 1 to its count
                    for (k = 0; k < this.getClasses().size(); k++) {
                        if (IS.getClasses().get(i).equals(this.getClass(k))) {
                            this.getRow(j).addCount(k);
                        }
                    }
                }
            }
        }
        int total;
        for (i = 0; i < this.getClasses().size(); i++) {
            total = 0;
            for (j = 0; j < this.getRows().size(); j++) {
                total += this.getRow(j).getCount(i);
            }
            this.totals.add(total);
        }
        return this;
    }
//-----------------------------------------------

//-Adders / Getters / Setters -------------------

    public String getName() { return this.name; }
    public int getId() { return this.id; }

    public ArrayList<String> getClasses() { return this.classes; }
    public String getClass(int pos) { return this.classes.get(pos); }

    public ArrayList<FreqRow> getRows() { return this.rows; }
    public FreqRow getRow(int pos) { return this.rows.get(pos); }

    public ArrayList<Integer> getTotals() { return this.totals; }
    public int getTotal(int pos) { return this.totals.get(pos); }

}
