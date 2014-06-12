
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;



/**
 *
 * @author BrynClayton
 */
public class Heuristics {
    static final int NumOfGenes = 20;
    static final float NumOfGenes_breed = 0.50f; //for every stack
    static final float NumOfGenes_mutate = 0.05f; //for every box
    static  public SecureRandom random;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Box> boxes = new ArrayList<>();
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
        //Sorted By Hight
        long StackLimit = (long) Math.pow(3*boxes.size(),2);
        System.err.println("StackLimit: "+StackLimit);
        List<Stack> GenePool = new ArrayList<>(boxes.size()+(boxes.size()/2));
        
        
        for(int x=0;x<NumOfGenes;x++){
            HashMap<Box,Byte> boxMap = new HashMap<>(boxes.size());
            for(Box b : boxes){
                byte rot = (byte)random.nextInt(3);
                boxMap.put(b, rot);
            }
            GenePool.add(new Stack(boxMap));
            StackLimit--;
        }
        
        //Breed and Mutate GenePool - Go Darwin
        int StackstoBreed = (int) Math.floor((double)(NumOfGenes * NumOfGenes_breed));
        while(StackLimit>0){
            //System.err.println("Breeding: "+StackstoBreed+" Pairs");
            //BREED
            Collections.sort(GenePool);
            Stack[] BS = GenePool.toArray(new Stack[0]);
            System.out.println("Max: "+BS[0].getHeight()+" Min: "+BS[BS.length-1].getHeight()+" Rem: "+StackLimit);
            for(int x=0;x<(StackstoBreed*2);x+=2){
              //  System.err.println(x+" and "+(x+1)+" share Genetic material" );
                Stack OffSpring = BS[x].Breed(BS[x+1]);
                GenePool.add(OffSpring);
                StackLimit--;
            }
            for(int x=(StackstoBreed*2);x<BS.length;x++){
                //System.err.println(x+" Mutated");
                BS[x].Mutate(NumOfGenes_mutate);
                StackLimit--;
            }
            
            Collections.sort(GenePool);
            GenePool = GenePool.subList(0, NumOfGenes);
            //System.gc();
        }
        
        Stack MaxStack = GenePool.get(0);
        System.out.println(MaxStack.toString());
        System.out.println("Height"+MaxStack.lastheight);
            
        }catch(IOException e){
           System.err.println(e.toString());
        }
    }
    
}
