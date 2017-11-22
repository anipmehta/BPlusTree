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

    private ArrayList<String> output;

    public ArrayList<KeyValue> readFile(String name) throws IOException {
        FileInputStream fstream = new FileInputStream(name);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;

        int i=0;

//Read File Line By Line
        while ((strLine = br.readLine()) != null)   {


            if(i==0){
                bpTree = new BPTree(Integer.parseInt(strLine));
                this.setM(Integer.parseInt(strLine));
                output = new ArrayList<String>();
            }
            //Running insert query on Tree
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


                //Running Search Queries on Tree

                else if(strLine.contains("Search")){
                    int length = strLine.length();
                    String parsedString = strLine.substring(7,length-1);
//                                        System.out.println (parsedString);
                    BPTree old= bpTree;

                    if(parsedString.contains(",")){
                        String [] arr = parsedString.split(",");
                        StringBuffer str = bpTree.search(Double.parseDouble(arr[0]), Double.parseDouble(arr[1]));
                        bpTree= old;
                        if(str!=null)
                        output.add(str.toString());

//
                    }

                    //Running Range Queries on Tree

                    else{
                          String[] out = bpTree.search(Double.parseDouble(parsedString));
                        StringBuffer outputString = new StringBuffer();
                        bpTree= old;
                        if(out==null){
                            outputString.append("Null");
                        }
                        else{
                            if(out!=null){
                                for(int k=0;k<out.length;k++){
                                    if(out[k]==null){
                                        break;
                                    }

                                    else if(out[k+1]==null){
                                        outputString.append(out[k] + "");
                                    }
                                    else{

                                        outputString.append(out[k]+",");
                                    }
//                                System.out.println(s);


                                }

                            }
                        }

                        output.add(outputString.toString());


                    }

                }
            }
            i++;


        }

//Close the input stream
        br.close();
        return keyValues;
    }

    public void writeFile(String filename) throws IOException {

        //Writing Output to file stored in an ArrayList output
        File file = new File("output_file.txt");
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        for(String string : output){
//            string.charAt(string.length()-1) = "";
            bufferedWriter.write(string);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }



    public BPTree getBpTree() {
        return bpTree;
    }

    public void setBpTree(BPTree bpTree) {
        this.bpTree = bpTree;
    }
}
