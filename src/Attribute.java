import java.util.ArrayList;

/************************************************
 * Created by Lance Schendt on 10/21/2016.
 ************************************************
 */
public class Attribute {

// Parameters
    private String name;
    private int id;
    private ArrayList<String> domain;

// Constructors
    public Attribute() {
        this.domain = new ArrayList<>();
    }
    public Attribute(String name, int id) {
        this.name = name;
        this.id = id;
        this.domain = new ArrayList<>();
    }

// Getters
    public String getName() {
        return this.name;
    }
    public int getID() {
        return this.id;
    }
    public ArrayList<String> getDomain() {
        return this.domain;
    }

// Adders
    public void addDomain(ArrayList<String> domain) {
        for (String s : domain) {
            this.domain.add(s);
        }
    }
    public void addDomainValue(String value) {
        this.domain.add(value);
    }

}
