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
//-----------------------------------------------

//-Constructors----------------------------------
    public FreqTable () {
        this.classes = new ArrayList<>();
        this.rows = new ArrayList<>();
    }
    public FreqTable (String name, int id) {
        this.name = name;
        this.id = id;
        this.classes = new ArrayList<>();
        this.rows = new ArrayList<>();
    }
//-----------------------------------------------

//-Functions-------------------------------------
    public FreqTable CreateFreqTable(Attribute A, ArrayList<String> CL) {
        FreqTable ft = new FreqTable(A.getName(), A.getID());
        int i;
        for (i = 0; i < A.getDomain().size(); i++) {
            FreqRow fr = new FreqRow(A.getDomain().get(i));
            fr.init(classes.size());
            ft.addRow(fr);
        }
        return ft;
    }

    public FreqTable FillFreqTable(FreqTable FT, Instance IS) {
        int i,j,k;
        //for each data set in the current instance do
        for (i = 0; i < IS.getData().size(); i++) {
            // for value in the domain of the current attribute
            for (j = 0; j < FT.getRows().size(); j++) {

                //if instances attribute is each to domain value
                if (IS.getDataItem(i,FT.getId()).equals(FT.getRow(j).getValue())) {
                    //find the classification and add 1 to its count
                    for (k = 0; k < FT.getClasses().size(); k++) {
                        if (IS.getClasses().get(i).equals(FT.getClass(k))) {
                            FT.getRow(j).addCount(k);
                        }
                    }
                }
            }
        }
        return FT;
    }

//-Adders / Getters / Setters -------------------
    public void addRow(FreqRow fr) { this.rows.add(fr); }

    public String getName() { return this.name; }
    public int getId() { return this.id; }

    public ArrayList<String> getClasses() { return this.classes; }
    public String getClass(int pos) { return this.classes.get(pos); }

    public ArrayList<FreqRow> getRows() { return this.rows; }
    public FreqRow getRow(int pos) { return this.rows.get(pos); }

}
