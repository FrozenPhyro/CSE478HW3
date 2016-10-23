import java.util.ArrayList;

/************************************************
 * Created by Lance Schendt on 10/21/2016.
 ************************************************
 */
public class Instance {
// Parameters
    private ArrayList<ArrayList<String>> data;


// Constructor
    public Instance () {
        this.data = new ArrayList<>();
    }


// Adders
    public void addData(String[] data) {
        ArrayList<String> row = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            row.add(data[i]);
        }
        this.data.add(row);
    }

    public void addData(ArrayList<String> data) {
        this.data.add(data);
    }


// Getters
    public String getDataItem(int i, int j) {
        return this.data.get(i).get(j);
    }

    public ArrayList<String> getDataRow(int i) {
        return this.data.get(i);
    }


}
