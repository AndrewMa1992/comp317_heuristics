
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BrynClayton
 */
public class Stack implements Comparable{
    
    int lastheight;
    HashMap<Box,Byte> listOfBoxes;
    ArrayList<Box> StackedBoxes;
    
    public Stack(HashMap <Box,Byte> map){
        this.listOfBoxes = map;
        this.lastheight = 0;
        stackBoxes();
    }
    
    public Integer getHeight(){
        return lastheight;
    }
    
    private void findHeight(List<Box> Boxes){
        
        int height = 0;
        for(Box b: Boxes)
            height += b.getHeight(listOfBoxes.get(b));
        this.lastheight = height;
    }
            
    private void stackBoxes(){
        int area;
        TreeMap<Integer,Box> sortedBoxes = new TreeMap<>();
        StackedBoxes = new ArrayList();
        
        for(Box b: this.listOfBoxes.keySet()){
            area = b.getDepth(this.listOfBoxes.get(b))* b.getWidth(this.listOfBoxes.get(b));
            sortedBoxes.put(area, b);
        }
        
        //Add First Box
        StackedBoxes.add(sortedBoxes.pollLastEntry().getValue());
        while(sortedBoxes.size()>0){
           Box nextB = sortedBoxes.remove(sortedBoxes.lastKey()); //getBiggest
           Box prevB = StackedBoxes.get(StackedBoxes.size()-1);
           byte thisrot = listOfBoxes.get(nextB);
           byte prot = listOfBoxes.get(prevB);
           if(nextB.checkifStacks(thisrot, prevB, prot)){
               StackedBoxes.add(nextB);
               //System.out.println(nextB.getWidth(thisrot) +"x"+ nextB.getDepth(thisrot) +" on "+ prevB.getWidth(prot) +"x"+ prevB.getDepth(prot));
           }
        }
            
        
        //System.out.println("STACKED");
        //System.err.println(this.toString());
        findHeight(StackedBoxes);
}
    
    public Stack Breed(Stack mStack){
        HashMap<Box,Byte> Boxes = new HashMap<>();
        int boxn = 0;
        for(Box b : this.listOfBoxes.keySet()){
            if((boxn%2)==0)
                Boxes.put(b, this.listOfBoxes.get(b));
            else
                Boxes.put(b, mStack.listOfBoxes.get(b));
            boxn++;
        }
        
        return new Stack(Boxes);
    }
    
    
    public void Mutate(float rate){
        boolean Mutation = false;
        for(Box b : this.listOfBoxes.keySet()){
            if(Heuristics.random.nextFloat()<rate){
                //System.err.println("Mutating");
                this.listOfBoxes.put(b, (byte)Heuristics.random.nextInt(3));
                Mutation=true;
            }
        }
        if(Mutation)
            stackBoxes();
        
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int x=0;x<StackedBoxes.size();x++){
            Box b = StackedBoxes.get(x);
            byte rot = this.listOfBoxes.get(b);
            str.append(b.getWidth(rot) +" "+ b.getDepth(rot)).append("\n");
        }
        return str.toString();
    }
    
    @Override
    public int compareTo(Object OtherStack) throws ClassCastException {
        if (!(OtherStack instanceof Stack))
            throw new ClassCastException("Expected Stack Object."); 
        return ((Stack)OtherStack).getHeight()-this.lastheight;    
    }
            
}
