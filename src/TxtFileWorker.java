
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class TxtFileWorker {
    public static String readFileName = "input.txt";

    public static String writeFileName = "output.txt";
    public static void read() throws IOException {
        BufferedReader br = null;

        try {

            File file = new File(readFileName);

            if(!file.exists()){
                file.createNewFile();
            }

            String line;
            br = new BufferedReader(new FileReader(readFileName));
            while((line = br.readLine()) != null){
                System.out.println(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            br.close();
        }
    }

    public static void write(String line) throws IOException {
        try{
            File file = new File(writeFileName);

            if(!file.exists()) {
                file.createNewFile();
            }

            PrintWriter pr = new PrintWriter(file);
            pr.println(line);
            pr.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
