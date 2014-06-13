

                    
                                         COMP317-12A                  
                                         ASSIGNMENT 4                 
                                 BRYN CLAYTON & TIMOTHY DAWSON         
                                            README                    
                                            
                We have sed a Genetic Algorithm approach to this assignment.                          

   For this algorithm, we first read in all of the boxes, and save them into an arraylist.   
   We then save this list of boxes (including random rotations) as stacks, which are then 
                                   added to the Gene pool.

  Using the number of given boxes, we set a limit of (3n)^2 to the operations, and we start   
                                    our algorithm proper.

  From the random intial stacks, we sort by largest surface area (width*depth), we then go   
  through the process of checking the touching faces, if the check fails the box is passed
    over. From this, we then find the height of the finished stacks, and order them from 
                                      highest to lowest.

   Once this is done, we take a top percentage which are kept and are crossed over, by using     
   alternate rotation values from the two stacks being 'bred' together.Then, box by box for    
    the remaining stacks, we give a small percentage chance of mutating, or changing the 
 rotation value. all of these values are then added to a new gene pool, the lowermost heights 
 are removed, and the algorithm is run again, until the operation limit is met. at this point, 
                   we print out the values from the highest reaching stack.

             At this point, the program terminates and the algorithm is complete.

