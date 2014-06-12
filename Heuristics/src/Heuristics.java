
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;



/**
 *
 * @author BrynClayton
 */
public class Heuristics {
    static final int NumOfGenes = 10;
    static final float NumOfGenes_breed = 0.25f; //for every stack
    static final float NumOfGenes_mutate = 0.02f; //for every box
    static  public SecureRandom random;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList<Box> boxes = new LinkedList<>();
        random = new SecureRandom();
        try{
            
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            
            /* Read in Boxes From File */
            String line;
            while ((line = br.readLine()) != null) {
                String[] line_a = line.split(" ");
                int d,w,h;
                d = Integer.parseInt(line_a[0]);
                w = Integer.parseInt(line_a[1]);
                h = Integer.parseInt(line_a[2]);
                Box b = new Box(d,w,h);
                boxes.add(b);
            }
            br.close();
        
        /****** Inital Generation *****/    
        //Key is Hight of Stack
        TreeSet<Stack> GenePool = new TreeSet<>();
        
        
        for(int x=0;x<NumOfGenes;x++){
            HashMap<Box,Byte> boxMap = new HashMap<>(boxes.size());
            for(Box b : boxes){
                byte rot = (byte)random.nextInt(3);
                boxMap.put(b, rot);
            }
            GenePool.add(new Stack(boxMap));
        }
        
        //Breed and Mutate GenePool - Go Darwin
        while(true){
            int StackstoBreed = (int) Math.floor((double)(NumOfGenes * NumOfGenes_breed));
            //BREED
            Stack[] BS = GenePool.toArray(new Stack[0]);
            
            for(int x=0;x<(StackstoBreed*2);x+=2){
                Stack OffSpring = BS[x].Breed(BS[x+1]);
                GenePool.add(OffSpring);
            }
            for(int x=(StackstoBreed*2);x<BS.length;x++){
                BS[x].Mutate(NumOfGenes_mutate);
            }
            
        }
        
            
        }catch(IOException e){
           System.err.println(e.toString());
        }
    }
    
}
