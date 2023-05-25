package fr.sncf.d2d.shallenge;

import java.util.ArrayList;
import java.util.List;

public class PasswordsGenerator {
    private int counter = 0;

     public boolean isEmpty(){
        return this.counter> Math.pow(26,6);
     }

    public String generate(){
        if(this.isEmpty()){
            return null;
        }
       
    // int valInit = 97;
    // int valMax = 123;

       
            // for(int y = 0; y <3; y++){ 
             StringBuilder builder = new StringBuilder();
             int number = this.counter; //number=1
            for(int i=0; i<6; i++){
                int remain = number %26; // remain = 0%26 => 1 //2 remain= 0%26//3456 =>6x0

                char unicode = (char) ('a'+ remain); //'a'
       
                builder.append(unicode);//'z''b''a''a''a''a'
                number/=26;//1/26 =0
               
                
            }
          this.counter++;
            
       
        

   return builder.reverse().toString();
    // for (Integer i=valInit; i<valMax; i++){

    //     for(Integer j=valInit; j<valMax; j++){

    //         for(Integer k=valInit; k<valMax; k++){

    //              for(Integer l=valInit; l<valMax; l++){

    //                 for(Integer m=valInit; m<valMax; m++){

    //                         for(Integer n=valInit; n<valMax; n++){
    //                               StringBuilder builder = new StringBuilder();
    //                                 builder.append((char) i.intValue());
    //                                 builder.append((char) j.intValue());
    //                                 builder.append((char) k.intValue());
    //                                 builder.append((char) l.intValue());
    //                                 builder.append((char) m.intValue());
    //                                 builder.append((char) n.intValue());
    //                                 String result = builder.toString();
    //                                 System.out.println(result);
    //                                 //    String.fromCharCode(i,j);
    //                         }
                      
    //                 }
                       
    //              }
             
    //         }
           
        
        // }
 

    // }

    }
   
        // Hash hash = new Hash();
        // Hash.

}

