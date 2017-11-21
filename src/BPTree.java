import com.sun.source.tree.Tree;
import sun.awt.SunHints;

import java.util.ArrayList;

/**
 * Created by anip on 11/11/17.
 */
public class BPTree {
private int order;
private TreeNode root;
private LeafNode leafNode;


    public BPTree(int m){
            order = m;
//            root = new LeafNode(m);
        }
    public void consumeFile(ArrayList<KeyValue> keyValues){
        for(KeyValue keyValue : keyValues){
            System.out.println(keyValues.size());
            this.insert(keyValue);
        }
    }
    public ArrayList<String> runQuery(ArrayList<Double> searches, ArrayList<RangedSearch> rangedSearches){
        ArrayList<String> outputs = new ArrayList<>();
        return outputs;
    }

//    private String search(Double key) {
//        return null;
//    }

    public String[] search(Double min, Double max) {

        return root.find(min,max);
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
//            if(!root.isLeaf()){
////                root = root.search(keyValue);
//                root.insert(keyValue);
////                while (root.getParent()!=null){
////                    root = root.getParent();
////                }
//            }
             root = root.insert(keyValue);
            System.out.print(root);
//            while(root.getParent()!=null){
//                root = root.getParent();
//            }
        }


//        leafNode.insert(keyValue);
//        if(!root.insert(keyValue)){
//            TreeNode treeNode = new InternalNode(order);
//            treeNode.insert(keyValue);
//            treeNode.addChildren(root);---
//            root = treeNode;
//        }
    }
    public String[] search(Double key){
        return root.find(key);
    }

}
