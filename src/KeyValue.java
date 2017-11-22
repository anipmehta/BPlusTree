
/**
 * Created by anip on 11/11/17.
 */

//A KeyValue Class for Storing keys and arrays of values
public class KeyValue{
    private double key;
    private String[] value;
    private int count =0;

    public double getKey() {
        return key;
    }

    public void setKey(double key) {
        this.key = key;
    }




    public void setValue(String value){
        if(this.value==null){
            this.value = new String[100];
        }
        this.value[0] = value;
        count++;
    }
    public void addValue(String value){
        this.value[count++] = value;
    }
    public String getValue(){
        return value[0];
    }
    public String[] getValues(){
        return value;
    }
}
