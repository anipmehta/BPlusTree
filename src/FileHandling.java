import java.io.*;
import java.util.ArrayList;

/**
 * Created by anip on 17/11/17.
 */
public class FileHandling {
    private int m;
    private BPTree bpTree;
    private ArrayList<KeyValue> keyValues;
    private ArrayList<Double> keys;

    private ArrayList<RangedSearch> rangedSearches;

    public ArrayList<KeyValue> readFile(String name) throws IOException {
        FileInputStream fstream = new FileInputStream("../ADS/src/input2.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        int i=0;

//Read File Line By Line
        while ((strLine = br.readLine()) != null)   {
            // Print the content on the console

            if(i==0){
                bpTree = new BPTree(Integer.parseInt(strLine));
                this.setM(Integer.parseInt(strLine));
            }
            else {
                if(strLine.contains("Insert")){
                    int length = strLine.length();

                  String parsedString = strLine.substring(7,length-1);
                  String [] arr = parsedString.split(",");

                  KeyValue keyValue = new KeyValue();
                    keyValue.setKey(Double.parseDouble(arr[0]));
                    keyValue.setValue(arr[1]);

//                    System.out.println (keyValue.getKey());
                    bpTree.insert(keyValue);

                }
                else if(strLine.contains("Search")){
                    int length = strLine.length();
                    String parsedString = strLine.substring(7,length-1);
                                        System.out.println (parsedString);

                    if(parsedString.contains(",")){
                        String [] arr = parsedString.split(",");
                        bpTree.search(Double.parseDouble(arr[0]), Double.parseDouble(arr[1]));
//
                    }
                    else{
                        bpTree.search(Double.parseDouble(parsedString));
                    }

                }
            }
            i++;


        }

//Close the input stream
        br.close();
        return keyValues;
    }

    public void writeFile(ArrayList<String> outputs,String name) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        for(String string : outputs){
            bufferedWriter.write(string);
        }
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }
    public ArrayList<RangedSearch> getRangedSearches() {
        return rangedSearches;
    }

    public ArrayList<Double> getKeys() {
        return keys;
    }


    public BPTree getBpTree() {
        return bpTree;
    }

    public void setBpTree(BPTree bpTree) {
        this.bpTree = bpTree;
    }
}
