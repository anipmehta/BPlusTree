import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by anip on 14/11/17.
 */
public class Main {
    private static ArrayList<KeyValue> keyValues;
    public static void main(String[] args) throws IOException {
//        FileHandling fileHandling = new FileHandling();
//        fileHandling.readFile("input.txt");
//        BPTree bpTree  = fileHandling.getBpTree();
        BPTree bpTree = new BPTree(4);
        KeyValue keyValue = new KeyValue();
        keyValue.setKey(3.5);
        keyValue.setValue("Value 3.5");
        KeyValue keyValue2 = new KeyValue();
        keyValue2.setKey(5.0);
        keyValue2.setValue("Value 5");
        KeyValue keyValue3 = new KeyValue();
        keyValue3.setKey(2.0);
        keyValue3.setValue("Value 2.0");
        KeyValue keyValue4= new KeyValue();
        keyValue4.setKey(0.0);
        keyValue4.setValue("Value 0.0");
        KeyValue keyValue5  = new KeyValue();
        keyValue5.setKey(10.0);
        keyValue5.setValue("Value 10.0");
        KeyValue keyValue6  = new KeyValue();
        keyValue6.setKey(11.0);
        keyValue6.setValue("Value 11.0");
        KeyValue keyValue7  = new KeyValue();
        keyValue7.setKey(12.0);
        keyValue7.setValue("Value 12.0");
        KeyValue keyValue8  = new KeyValue();
        keyValue8.setKey(6.0);
        keyValue8.setValue("Value 6.0");
        KeyValue keyValue9 = new KeyValue();
        keyValue9.setKey(7.0);
        keyValue9.setValue("Value 7.0");
        KeyValue keyValue10 = new KeyValue();
        keyValue10.setKey(15.0);
        keyValue10.setValue("Value 15.0");
        KeyValue keyValue11 = new KeyValue();
        keyValue11.setKey(13.0);
        keyValue11.setValue("Value 13.0");
        KeyValue keyValue12 = new KeyValue();
        keyValue12.setKey(14.0);
        keyValue12.setValue("Value 14");


        bpTree.insert(keyValue);
        bpTree.insert(keyValue2);
        bpTree.insert(keyValue3);
        bpTree.insert(keyValue4);
        bpTree.insert(keyValue5);
        bpTree.insert(keyValue6);
        bpTree.insert(keyValue7);
        bpTree.insert(keyValue8);
        bpTree.insert(keyValue9);
        bpTree.insert(keyValue10);
        bpTree.insert(keyValue11);
        bpTree.insert(keyValue12);
//        String [] arr = bpTree.search(3.71);
        bpTree.insert(keyValue);

    }
}
