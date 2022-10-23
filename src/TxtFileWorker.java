
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtFileWorker {
    public static String writeFileName = "output.txt";
    public static List<String> read(String fileName) throws IOException {
        BufferedReader br = null;
        List<String> list = new ArrayList<>();
        try {

            File file = new File(fileName);

            if(!file.exists()){
                file.createNewFile();
            }

            String line;
            br = new BufferedReader(new FileReader(fileName));
            while((line = br.readLine()) != null){
                list.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            br.close();
            return list;

        }
    }

    public static void write(List<String> list,String fileName) throws IOException {
        try{
            File file = new File(fileName);

            if(!file.exists()) {
                file.createNewFile();
            }

            PrintWriter pr = new PrintWriter(file);
            for(int i = 0;i < list.size(); i++){
                String line = list.get(i);
                pr.println(line);
            }
            pr.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
