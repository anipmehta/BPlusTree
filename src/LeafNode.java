import java.util.ArrayList;
import java.util.List;

/**
 * Created by anip on 11/11/17.
 */
public class LeafNode extends TreeNode {
    private ArrayList<KeyValue> data;
    private LeafNode left;
    private LeafNode right;
    private int min=0;
    private int max=0;
    public void LeafNode(int m){
        min = (int) Math.ceil(m/2);
        max = m-1;
        data = new ArrayList<KeyValue>();
        left = right = null;
    }
    public boolean search(double key){
        for (KeyValue d : data){
            if(d.getKey()==key){
                return true;
            }
        }
        return false;

    }
    public List<String> getValue(double key){
        for (KeyValue d : data){
            if(d.getKey()==key){
                return d.getValue();
            }
        }
        return null;
    }
    public boolean insert(KeyValue keyValue) {
        if (data.size() < max ) {
            data.add(keyValue);
            return true;
        } else {
            return false;
        }
    }
    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
    }
}
