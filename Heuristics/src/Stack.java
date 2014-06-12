
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
public class Stack {
    
    int lastheight;
    TreeMap<Box,Byte> listOfBoxes;
    
    public Stack(TreeMap <Box,Byte> map){
        this.listOfBoxes = map;
        this.lastheight = 0;
    }
    
    private void findHeight(){
        
        int height = 0;
        for(Box b: this.listOfBoxes.keySet())
            height += b.getHeight(listOfBoxes.get(b));
        this.lastheight = height;
    }
            
    public int stackBoxes(){
        
    }
            
}
