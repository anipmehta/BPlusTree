import java.util.ArrayList;

/**
 * Created by anip on 11/11/17.
 */
public class LeafNode{
    private ArrayList<KeyValue> data;
    private LeafNode left;
    private LeafNode right;
    private int min=0;
    private int max=0;
    public LeafNode(int m){
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
    public String getValue(double key){
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
//    public TreeNode split(LeafNode leafNode, int m){
//        int half = 0;
//        if(leafNode.data.size()%2==0){
//            half = leafNode.data.size();
//        }
//        else {
//            half = leafNode.data.size()/2+1;
//        }
//        LeafNode leafNode1 = new LeafNode(m);
//
//        for(int i = leafNode.data.size()-half;i>0;i--){
//            leafNode1.data.add(leafNode.data.remove(i));
//        }
//        InternalNode internalNode = new InternalNode(m);
////        internalNode.addChildNodes(leafNode);
////        internalNode.addChildNodes(leafNode1);
//        return ;
//    }
//    public int getMin() {
//        return min;
//    }
    public int getMax() {
        return max;
    }
}
