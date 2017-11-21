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
    private String[] ranged;
    private int cnt;
    //    public boolean insert(KeyValue keyValue){
//        return true;
//    }
    public void addChildren(TreeNode treeNode) {
        children[childCount] = treeNode;
        childCount++;
    }

    public TreeNode(int m) {
        this.m = m;
    }

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
    public void updateLeaf(KeyValue keyValue){
        for (int i = 0; i < keyValues.length; i++) {
            if (keyValues[i].getKey() == -1000) {
                System.out.println("np" + 0);
                keyValues[i] = keyValue;
                leafCount++;
                break;

            } else if (keyValue.getKey() == keyValues[i].getKey() && keyValues[i].getKey() != -1000) {
                System.out.println("np" + 1);
                System.out.println("Found Duplicate");
                keyValues[i].addValue(keyValue.getValue());
                break;
            } else if (keyValue.getKey() < keyValues[i].getKey() && keyValues[i].getKey() != -1000) {
                System.out.println("np" + 2);
                shift(i, keyValues);
                keyValues[i] = keyValue;
                leafCount++;
//                    check();
                break;
            }

        }
    }

    public TreeNode insert(KeyValue keyValue) {
        if (isLeaf() && notFull()) {
            System.out.println("breaking" + keyValues);
            for (int i = 0; i < keyValues.length; i++) {
                System.out.println("key"+keyValues[0].getKey());
                if (keyValues[i].getKey() == -1000) {
                    System.out.println("np" + 0);
                    keyValues[i] = keyValue;
                    leafCount++;
                    break;

                } else if (keyValue.getKey() == keyValues[i].getKey() && keyValues[i].getKey() != -1000) {
                    System.out.println("np" + 1);
                    System.out.println("Found Duplicate");
                    keyValues[i].addValue(keyValue.getValue());
                    break;
                } else if (keyValue.getKey() < keyValues[i].getKey() && keyValues[i].getKey() != -1000) {
                    System.out.println("np" + 2);
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

//    private TreeNode addToParent(KeyValue keyValue) {
//        if(this.parent==null){
//            return this;
//        }
//        if(this.parent.parent==null){
//            return parent;
//        }
//        else if(parent.notFull()){
//            for(int i=0;i<parent.keys.length;i++){
//                if(keyValue.getKey()<parent.keys[i] && parent.keys[i]!=-1000){
//                    shift(i,parent);
//                    parent.keys[i] = keyValue.getKey();
//                    parent.children[i+1] = this;
//                    parent.childCount++;
//
//                    break;
//                }
//                else if(parent.keys[i]==-1000){
////                parent.keys[i] = treeNode.keyValues[0].getKey();
////                parent.addChildren(treeNode);
//                    parent.keys[i]=keyValue.getKey();
//                    System.out.println("index"+i);
////                parent.children[i-1].next = treeNode;
//                    parent.children[i+1] = this;
//                    parent.childCount++;
//                    break;
//                }
//            }
//            return parent;
//
//        }
//        else if(!parent.notFull()){
//           return splitInternalNode(keyValue).search(keyValue).insert(keyValue);
//        }
//        return this;
//
    public boolean notFull() {
        if (isLeaf())
            return leafCount < m - 1;
        else
            return childCount <= m;
    }


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

    public void shift(int i, TreeNode parent) {

        for (int k = parent.keys.length-1; k > i; k--) {
            System.out.println(k);
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
        System.out.println("Pivot Value"+temp);

//        for(int k=1;k<treeNode.childCount;k++){
//            treeNode.keys[k-1] = treeNode.keys[k];
//            treeNode.keys[k]=-1000;
//        }
        this.setParent(parent);
        treeNode.setParent(parent);

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

            for (int i = 0; i < parent.keys.length; i++) {
                if (treeNode.keys[0] < parent.keys[i] && parent.keys[i] != -1000) {
                    shift(i, parent);
                    parent.keys[i] = treeNode.keys[0];
                    parent.children[i + 1] = treeNode;
                    parent.childCount++;

                    break;
                } else if (parent.keys[i] == -1000) {
//                parent.keys[i] = treeNode.keyValues[0].getKey();
//                parent.addChildren(treeNode);
                    parent.keys[i] = treeNode.keys[0];
                    System.out.println("index" + i);
//                parent.children[i-1].next = treeNode;
                    parent.children[i + 1] = treeNode;
                    parent.childCount++;
                    break;
                }
            }
        }
            return parent;
        }



//        parent.addChildren(this);



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
//                parent.keys[i] = treeNode.keyValues[0].getKey();
//                parent.addChildren(treeNode);
                    parent.keys[i]=temp;
                    System.out.println("index"+i);
//                parent.children[i-1].next = treeNode;
                    parent.children[i+1] = treeNode;
                    parent.childCount++;
                    break;
                }
            }
    }

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
//                parent.keys[i] = treeNode.keyValues[0].getKey();
//                parent.addChildren(treeNode);
                        parent.keys[i] = treeNode.keyValues[0].getKey();
                        parent.children[i + 1] = treeNode;
                        parent.childCount++;
                        break;
                    }
                }
            }





//        parent.keys[parent.childCount-2]=treeNode.keyValues[0].getKey();
        this.setParent(parent);
        treeNode.setParent(parent);
        return parent;

//            leafNode1.data.add(leafNode.data.remove(i));
//        }
    }

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
//                    children[i + 1].search(keyValue);
                    k = i;
                    break;
//                    return children[i].search(keyValue);
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
                    System.out.println("Found");
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
//                    children[i + 1].search(keyValue);
                    k = i;
                    break;
//                    return children[i].search(keyValue);
                } else if (keys[i] == -1000) {
//                    children[i].search(keyValue);
                    k = i;
                    break;

                }

            }
            return children[k].find(key);

        }

    }
    public String[] find(Double min, Double max) {
       if(ranged==null){
           ranged = new String[1000];
           cnt=0;
       }
        if (isLeaf()) {
            for (int i = 0; i < keyValues.length; i++) {
                if (min >=keyValues[i].getKey()) {
//                    ranged[cnt]=keyValues[i].getValues();

                    return traverseList(this,i,max);
                }
            }
            return null;
        } else {
            int k = 0;
            for (int i = 0; i < keys.length; i++) {
                if (min == keys[i]) {
                    k=i+1;
                    break;

                } else if (min < keys[i] && keys[i] != 1000) {
//                    children[i + 1].search(keyValue);
                    k = i;
                    break;
//                    return children[i].search(keyValue);
                } else if (keys[i] == -1000) {
//                    children[i].search(keyValue);
                    k = i;
                    break;

                }

            }
            return children[k].find(min);

        }

    }

    private String[] traverseList(TreeNode treeNode, int index, Double max) {
        String[] arr = new String[1000];
        int in=0;
        boolean flag=false;
        while(treeNode.next!=null){
            for(int k=index;k<treeNode.keyValues.length;k++){
                arr[in] = treeNode.keyValues[k].getValue();
                if(treeNode.keyValues[k].getKey()>max){
                    flag = true;
                    break;
                }
            }
            if(flag){
                break;
            }
            treeNode = treeNode.next;
        }
        return arr;
    }

    public KeyValue defaultKeyValue(){
        KeyValue keyValue = new KeyValue();
        keyValue.setKey(-1000);
        keyValue.setValue("");
        return keyValue;
    }
}

