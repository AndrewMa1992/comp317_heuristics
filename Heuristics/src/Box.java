/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BrynClayton
 */
public class Box {
    static final byte rot_o = 0, rot_h = 1, rot_v = 2;
    int depth, width, height;
    
    public Box(int depth, int width, int height){
        this.depth = depth;
        this.width = width;
        this.height = height;
    }
    
    @Override
    public String toString (){
        return String.valueOf(this.depth)+" "+ String.valueOf(this.width)+" "+String.valueOf(this.height);
    }
     
    public boolean checkifStacks(final byte thisrot, Box prev, final byte prot){
        //44x39 on 44x44
        if(prev.getWidth(prot) > this.getWidth(thisrot) && prev.getDepth(prot) > this.getDepth(thisrot)){ 
            return true;
        }
        if(prev.getWidth(prot) > this.getDepth(thisrot) && prev.getDepth(prot) > this.getWidth(thisrot)){
            return true;
        }
        return false;
    }
    
    public int getDepth(final byte rot){
        if(rot == Box.rot_o || rot == Box.rot_h) return this.depth;
        if(rot == Box.rot_v) return this.height;
        return -1;
    }
    
    public int getHeight(final byte rot){
        if(rot == Box.rot_o) return this.height;
        if(rot == Box.rot_h) return this.width;
        if(rot == Box.rot_v) return this.depth;
        return -1;
    } 
    
    public int getWidth(final byte rot){
        if(rot == Box.rot_o || rot == Box.rot_v) return this.width;
        if(rot == Box.rot_h) return this.height;
        return -1;
    }
    
}
