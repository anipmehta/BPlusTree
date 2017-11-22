/**
 * Created by anip on 11/11/17.
 */
public class BPTree {
private int order;
private TreeNode root;


    public BPTree(int m){
            order = m;
        }


    public StringBuffer search(Double min, Double max) {

        return root.find(min,max, new StringBuffer());
    }

    public void insert(KeyValue keyValue){
        if(root==null){
            TreeNode treeNode = new TreeNode(this.order);
            treeNode.setLeaf(true);
            treeNode.initialize();
            treeNode.insert(keyValue);
            root = treeNode;
        }
        else {

            root = root.insert(keyValue);
            //Always Returns the root node after every insertion

        }

    }
    public String[] search(Double key){
        return root.find(key);
    }

}
