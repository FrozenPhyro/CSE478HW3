import java.util.ArrayList;

/************************************************
 * Created by Lance Schendt on 10/21/2016.
 ************************************************
 */
public class Instance {
// Parameters
    private ArrayList<ArrayList<String>> data;
    private ArrayList<String> classes;


// Constructor
    public Instance () {
        this.data = new ArrayList<>();
        this.classes = new ArrayList<>();
    }


// Adders

    /********************************************
     * Adds data and classification
     * @param pos - position in the data for the classification
     * @param data - The data being added
     ********************************************
     */
    public void addData(int pos, String[] data) {
        ArrayList<String> row = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            if (i == pos) {
                this.classes.add(data[i]);
            } else {
                row.add(data[i]);
            }
        }
        this.data.add(row);
    }
    public void addData(int pos, ArrayList<String> data) {
        ArrayList<String> row = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (i == pos) {
                this.classes.add(data.get(i));
            } else {
                row.add(data.get(i));
            }
        }
    }


// Getters
    public String getDataItem(int i, int j) {
        return this.data.get(i).get(j);
    }

    public ArrayList<String> getDataRow(int i) {
        return this.data.get(i);
    }

    public ArrayList<ArrayList<String>> getData() { return this.data; }

    public String getClassification(int i) { return this.classes.get(i); }

    public ArrayList<String> getClasses() { return this.classes; }

}
