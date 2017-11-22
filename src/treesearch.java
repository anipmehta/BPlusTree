import java.io.IOException;


/**
 * Created by anip on 14/11/17.
 */
public class treesearch {
    public static void main(String[] args) throws IOException {
        FileHandling fileHandling = new FileHandling();
        String input="input.txt";
        //Default Value for Key = -1000;
        //Default Value for Value = "";
        //So Please Don't insert -1000 as key
        if(args.length>0)
        {
        	System.out.println(args[0]);
            input = args[0];
        }
        fileHandling.readFile(input);
        BPTree bpTree  = fileHandling.getBpTree();
        fileHandling.writeFile(args[0]);

    }
}
