import java.util.ArrayList;
import java.util.List;

/**
 * Created by anip on 11/11/17.
 */
public class InternalNode extends TreeNode{
    private ArrayList<Double> keys;
    private int max=0;
    private InternalNode leftChild;
    private InternalNode rightChild;
    private LeafNode leftLeaf;
    private LeafNode rightLeaf;
    private ArrayList<LeafNode> leafNodes;
    private ArrayList<InternalNode> childNodes;
    public void InternalNode(int m){
        keys = new ArrayList<Double>();
        max = m-1;
        leftChild = rightChild = null;
        leftLeaf = rightLeaf = null;
    }
    public boolean insert(double key){
        if(keys.size()<max){
            keys.add(key);
            return true;
        }
        else
            {return false;}
    }
    public List<String> search(double key){
        for(InternalNode node : childNodes){
            if(key>=node.keys.get(0) && key<=node.keys.get(keys.size()-1)){
                return searchLeaf(key);
            }
        }
        return null;
    }
    public List<String> searchLeaf(double key){
        for(LeafNode leafNode : leafNodes){
            if(leafNode.search(key)){
                return leafNode.getValue(key);
            }
        }
        return null;
    }

    public InternalNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(InternalNode leftChild) {
        this.leftChild = leftChild;
    }

    public InternalNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(InternalNode rightChild) {
        this.rightChild = rightChild;
    }

    public LeafNode getLeftLeaf() {
        return leftLeaf;
    }

    public void setLeftLeaf(LeafNode leftLeaf) {
        this.leftLeaf = leftLeaf;
    }

    public LeafNode getRightLeaf() {
        return rightLeaf;
    }

    public void setRightLeaf(LeafNode rightLeaf) {
        this.rightLeaf = rightLeaf;
    }

    public ArrayList<LeafNode> getLeafNodes() {
        return leafNodes;
    }

    public void setLeafNodes(ArrayList<LeafNode> leafNodes) {
        this.leafNodes = leafNodes;
    }

    public ArrayList<InternalNode> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(ArrayList<InternalNode> childNodes) {
        this.childNodes = childNodes;
    }
}
