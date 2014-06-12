
import java.util.HashMap;
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
    
    public Stack(HashMap <Box,Byte> map){
        this.listOfBoxes = map;
        this.lastheight = 0;
    }
    
    public Integer getHeight(){
        return lastheight;
    }
    
    private void findHeight(){
        
        int height = 0;
        for(Box b: this.listOfBoxes.keySet())
            height += b.getHeight(listOfBoxes.get(b));
        this.lastheight = height;
    }
            
    public void stackBoxes(){
        int area;
        TreeMap<Integer,Box> sortedBoxes = new TreeMap<>();
        for(Box b: this.listOfBoxes.keySet()){
            area = b.getDepth(this.listOfBoxes.get(b))* b.getWidth(this.listOfBoxes.get(b));
            sortedBoxes.put(area, b);
        }
        Box[] SB = sortedBoxes.descendingMap().values().toArray(new Box[0]);
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
        for(Box b : this.listOfBoxes.keySet()){
            if(Heuristics.random.nextFloat()<rate){
                this.listOfBoxes.put(b, (byte)Heuristics.random.nextInt(3));
            }
        }
    }
    
    
    
    @Override
    public int compareTo(Object OtherStack) throws ClassCastException {
        if (!(OtherStack instanceof Stack))
            throw new ClassCastException("Expected Stack Object."); 
        return this.lastheight- ((Stack)OtherStack).getHeight();    
    }
            
}
