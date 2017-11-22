import java.util.ArrayList;

/**
 * Created by anip on 11/11/17.
 */
public class TreeNode {
    private double[] keys;
    private TreeNode[] children;
    private KeyValue[] keyValues;
    private TreeNode previous;
    private TreeNode parent;
    private int leafCount;
    private int childCount;
    private int m;
    private TreeNode next;
    private boolean isLeaf = false;
    private boolean isRoot = false;
    public void addChildren(TreeNode treeNode) {
        children[childCount] = treeNode;
        childCount++;
    }

    public TreeNode(int m) {
        this.m = m;
    }
    //For Assigning Memory to a Node whether it is a leaf or non leaf node
    public void initialize() {
        if (this.isLeaf()) {
            leafCount = 0;
            KeyValue keyValue = new KeyValue();
            keyValue.setKey(-1000);
            keyValue.setValue("");
            keyValues = new KeyValue[m];
            for (int i = 0; i < keyValues.length; i++) {
                keyValues[i] = keyValue;
            }
        } else {
            childCount = 0;
            keys = new double[m];
//            childCount=1;
            for (int i = 0; i < keys.length; i++) {
                keys[i] = -1000;
            }
            children = new TreeNode[m+1];
        }
        parent = previous = next = null;
    }

    //Adding KeyValue to Leaf At Correct Position before splitting
    public void updateLeaf(KeyValue keyValue){
        for (int i = 0; i < keyValues.length; i++) {
            if (keyValues[i].getKey() == -1000) {
                keyValues[i] = keyValue;
                leafCount++;
                break;

            } else if (keyValue.getKey() == keyValues[i].getKey() && keyValues[i].getKey() != -1000) {
                keyValues[i].addValue(keyValue.getValue());
                break;
            } else if (keyValue.getKey() < keyValues[i].getKey() && keyValues[i].getKey() != -1000) {
                shift(i, keyValues);
                keyValues[i] = keyValue;
                leafCount++;
//                    check();
                break;
            }

        }
    }


    //This Function is called everytime there is an Insert and is a Recursive Function and always returns the root of tree after insertion
    public TreeNode insert(KeyValue keyValue) {
        if (isLeaf() && notFull()) {
            for (int i = 0; i < keyValues.length; i++) {
                if (keyValues[i].getKey() == -1000) {
                    keyValues[i] = keyValue;
                    leafCount++;
                    break;

                } else if (keyValue.getKey() == keyValues[i].getKey() && keyValues[i].getKey() != -1000) {
                    keyValues[i].addValue(keyValue.getValue());
                    break;
                } else if (keyValue.getKey() < keyValues[i].getKey() && keyValues[i].getKey() != -1000) {
                    shift(i, keyValues);
                    keyValues[i] = keyValue;
                    leafCount++;
//                    check();
                    break;
                }

            }
            TreeNode temp = this;
            while(true){
                if(temp.parent==null)
                break;
                temp = temp.getParent();
            }
            return temp;
        }

         else if (isLeaf() && !notFull()) {
            updateLeaf(keyValue);
            //Splitting the Leaf Node after adding
           TreeNode parent = splitLeaf(keyValue);

           return check(parent,keyValue);

        }
        ///Inserting Into InternalNode
        else if (!isLeaf()) {
//            TreeNode treeNode = search(keyValue);
            return search(keyValue).insert(keyValue);
        }
        return this;
    }
    public TreeNode check(TreeNode parent, KeyValue keyValue){
        if(parent.parent==null && !parent.notFull()){
            return parent.splitInternalNode(keyValue);
        }
        else if(parent.parent==null&& parent.notFull()){
            return parent;
        }
        if(!parent.notFull()){
            TreeNode gp = parent.splitInternalNode(keyValue);
            return check(gp,keyValue);
        }
        return check(parent.parent,keyValue);
    }

    public boolean notFull() {
        if (isLeaf())
            return leafCount < m - 1;
        else
            return childCount <= m;
    }

    //Used for creating space in keys arrays for a particular keyValue in Leaf
    public void shift(int i, KeyValue[] keyValues) {
        int length = keyValues.length - 1;
        int last = length;
        while (length >= 0) {
            if (keyValues[length].getKey() != 1000) {
                last = length + 1;
                break;
            }
            length--;
        }
        for (int k = last - 1; k > i; k--) {
            keyValues[k] = keyValues[k - 1];
        }
    }

    //Used for creating space in keys arrays for a particular index

    public void shift(int i, TreeNode parent) {

        for (int k = parent.keys.length-1; k > i; k--) {
            parent.keys[k] = parent.keys[k - 1];
            parent.children[k+1] = parent.children[k];
        }
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    public TreeNode getPrevious() {
        return previous;
    }

    public void setPrevious(TreeNode previous) {
        this.previous = previous;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getNext() {
        return next;
    }

    public void setNext(TreeNode next) {
        this.next = next;
    }

    //Splits the two internal node and returns the parent of the split node
    public TreeNode splitInternalNode(KeyValue keyValue){
        int half = 0;
        if (m % 2 == 0) {
            half = m / 2;
        } else {
            half = (int) Math.floor(m/2);
        }



        TreeNode treeNode = new TreeNode(m);
        treeNode.initialize();
        //int index=0;

            for(int k=half+1,r=0;k<m;k++,r++){
                children[k].setParent(treeNode);
                treeNode.children[r] = children[k];
                treeNode.childCount++;
//                System.out.println("child"+children[k].keys[0]);
                if(k==m-1){
                 treeNode.children[r+1] = children[k+1];
                    children[k+1].setParent(treeNode);

                    if(children[k+1].isLeaf()){
                        children[k+1] = new TreeNode(m);
                        children[k+1].setLeaf(true);
                        children[k+1].initialize();
                    }
                    else{
                        children[k+1] = new TreeNode(m);
                        children[k+1].setLeaf(false);
                        children[k+1].initialize();
                    }

                    treeNode.childCount++;
                }
                if(children[k].isLeaf()){
                    children[k] = new TreeNode(m);
                    children[k].setLeaf(true);
                    children[k].initialize();
                }
                else{
                    children[k] = new TreeNode(m);
                    children[k].setLeaf(false);
                    children[k].initialize();
                }
                childCount--;

            }


//        treeNode.children[m-1]=children[]
        for (int i = half+1,j=0; i < m; i++,j++) {
            treeNode.keys[j] = keys[i];
            keys[i] = -1000;


//            children[i].setParent(treeNode);

//            treeNode.addChildren(children[i+1]);
            if(i==half && children[i].isLeaf() && treeNode.children[0].isLeaf()){
            }
//            if(children[i].isLeaf()){
//                children[i] = new TreeNode(m);
//                children[i].setLeaf(true);
//                children[i].initialize();
//            }
//            else{
//                children[i] = new TreeNode(m);
//                children[i].setLeaf(false);
//                children[i].initialize();
//            }

//            children[i+1]=null;



//            childCount--;
        }
        double temp = keys[half];
        keys[half] = -1000;
        childCount--;
        //Split Value is stored in temp

        TreeNode parent;
        if(this.parent==null){
            parent = new TreeNode(m);
            parent.setLeaf(false);
            parent.initialize();
            parent.addChildren(this);
            parent.addChildren(treeNode);
            parent.keys[0] = temp;

        }
        else {
            parent = this.getParent();
            addToParent(parent, temp, treeNode);
        }
        this.setParent(parent);
        treeNode.setParent(parent);
            return parent;
        }



    //Just add Double key to the parent before splitting
    private void addToParent(TreeNode parent, double temp, TreeNode treeNode) {
        for(int i=0;i<parent.keys.length;i++){
                if(temp<parent.keys[i] && parent.keys[i]!=-1000){
                    shift(i,parent);
                    parent.keys[i] = temp;
                    parent.children[i+1] = treeNode;
                    parent.childCount++;

                    break;
                }
                else if(parent.keys[i]==-1000){
                    parent.keys[i]=temp;
//                parent.children[i-1].next = treeNode;
                    parent.children[i+1] = treeNode;
                    parent.childCount++;
                    break;
                }
            }
    }


    //Splits the leaf Node and return the parent of the the nodes after splitting it into two leaf nodes
    public TreeNode splitLeaf(KeyValue keyValue) {
        int half = 0;
        if (m % 2 == 0) {
            half = m / 2;
        } else {
            half = (int) Math.floor(m/2);
        }

        TreeNode treeNode = new TreeNode(m);
        treeNode.setLeaf(true);
        treeNode.initialize();
        for (int i = half,j=0; i < m ; i++,j++) {
            treeNode.keyValues[j] = keyValues[i];
            treeNode.leafCount++;
            this.leafCount--;
            keyValues[i] = defaultKeyValue();
        }
        treeNode.setPrevious(this);
        treeNode.setNext(this.next);
        this.setNext(treeNode);
        TreeNode parent;
        if(this.parent==null) {
            parent = new TreeNode(m);
            parent.initialize();
            parent.addChildren(this);
            parent.addChildren(treeNode);
            parent.keys[0] = treeNode.keyValues[0].getKey();
            treeNode.setPrevious(this);
            this.setNext(treeNode);
        }
        else {
            parent = this.parent;

                for (int i = 0; i < parent.keys.length; i++) {
                    if (treeNode.keyValues[0].getKey() < parent.keys[i] && parent.keys[i] != -1000) {
                        shift(i, parent);
                        parent.keys[i] = treeNode.keyValues[0].getKey();
                        parent.children[i + 1] = treeNode;
                        parent.childCount++;

                        break;
                    } else if (parent.keys[i] == -1000) {
                        parent.keys[i] = treeNode.keyValues[0].getKey();
                        parent.children[i + 1] = treeNode;
                        parent.childCount++;
                        break;
                    }
                }
            }





        this.setParent(parent);
        treeNode.setParent(parent);
        return parent;
    }


    //Searches Recursively to find the positon to insert the KeyValue
    //Always return a leaf node

    public TreeNode search(KeyValue keyValue) {
        if (isLeaf()) {
            return this;
        } else {
            int k = 0;
            for (int i = 0; i < keys.length; i++) {
                if (keyValue.getKey() == keys[i]) {
                    k=i;
                    break;

                } else if (keyValue.getKey() <= keys[i] && keys[i] != 1000) {
                    k = i;
                    break;
                }
                else if (keys[i] == -1000) {
//                    children[i].search(keyValue);
                    k = i;
                    break;

                }

            }

            return children[k].search(keyValue);

        }

    }
    public String[] find(Double key) {
        if (isLeaf()) {
            for (int i = 0; i < keyValues.length; i++) {
                if (key == keyValues[i].getKey()) {
                    //Same Key adding values to array
                    return keyValues[i].getValues();
                }
            }
            return null;
        } else {
            int k = 0;
            for (int i = 0; i < keys.length; i++) {
                if (key == keys[i]) {
                    k=i+1;
                    break;

                } else if (key < keys[i] && keys[i] != 1000) {
                    k = i;
                    break;
                } else if (keys[i] == -1000) {

                    k = i;
                    break;

                }

            }
            return children[k].find(key);

        }

    }

    //Searches for a ranged query and return the ouptut in StringBuffer with all key Value Pair
    public StringBuffer find(Double min, Double max,StringBuffer str) {
        if (isLeaf()) {
           //Iterating on leaves double linked list

                    return traverseList(this,0,min,max);

        } else {
            int k = 0;
            for (int i = 0; i < keys.length; i++) {
                if (min == keys[i]) {
                    k=i+1;
                    break;

                } else if (min <= keys[i] && keys[i] != 1000) {
                    k = i;
                    break;
                }

            }
            return children[k].find(min,max,str);

        }

    }
    //Traverses the Doubly Linked List of leaves for ranged search
    private StringBuffer traverseList(TreeNode treeNode, int index, Double min, Double max) {
        StringBuffer str = new StringBuffer();
        int in=0;
        boolean flag=false;
        while(treeNode!=null){
            for(int k=index;k<treeNode.keyValues.length;k++){
                if(treeNode.keyValues[k].getKey()!=-1000 && treeNode.keyValues[k].getKey()>=min && treeNode.keyValues[k].getKey()<=max) {
                    if(str.length()!=0) {
                        str.append(",");
                    }
                    ArrayList<String> add= new ArrayList<String>();
                            //Getting all the values there for this key
                            String [] arr= treeNode.find(treeNode.keyValues[k].getKey());
                            if(arr!=null) {
                                for (int i = 0; i < arr.length; i++) {
                                    if (arr[i] != null) {
                                        str.append("(");
                                        str.append(treeNode.keyValues[k].getKey());
                                        str.append(",");
                                        str.append(arr[i]);
                                        str.append(")");
                                    } else break;
                                }


                            }
                    else {
                                str.append("(");
                                str.append(treeNode.keyValues[k].getKey());
                                str.append(",");
                                str.append(treeNode.keyValues[k].getValue());
                                str.append(")");
                            }





                }
                if(treeNode.keyValues[k].getKey()>max){
                    flag = true;
                    break;
                }
            }
            if(flag){
                break;
            }
            treeNode = treeNode.next;
            index = 0;
        }
        return str;
    }
    //Default Value for Key = -1000;
    //Default Value for Value = "";
    public KeyValue defaultKeyValue(){
        KeyValue keyValue = new KeyValue();
        keyValue.setKey(-1000);
        keyValue.setValue("");
        return keyValue;
    }
}

