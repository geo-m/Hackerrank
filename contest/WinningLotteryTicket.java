import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.Map.Entry;

/* 
*  Problem Description : https://www.hackerrank.com/contests/hackerrank-hiring-contest/challenges/winning-lottery-ticket
*  Score : 40/40
*/ 
public class Solution {

    public static void main(String[] args) {
        
        /* Get number of tickets */
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        String ticket = new String();
        
        /* Since we are only interested about all numbers 0-9 
         * appearing at least once, we only need to keep track
         * of which numbers exists in every ticket. For that
         * purpose we transform the ticket string to an int ( could be short)
         * where the bit value at position i marks the existence of number i
        */
        int[] freq =  new int[(int)n];
        
        /* Tickets with the same number flags will be put in the same bucket,
         * so that a match with a bucket automatically means +(bucket entry count) results
         */
        Map<Integer,Long> m = new HashMap<>();
       
        Long total = 0l;
        Long tmp;
        
        for(int tickets_i = 0; tickets_i < n; tickets_i++){
          
            ticket = in.next();
            for(int i = 0 ; i < 10 ; i++){
                if(ticket.indexOf((char)(i+48))!=-1){
                    freq[tickets_i] = freq[tickets_i] | ( 1 << i );
                    
                }
            }
            
            /* To check if all numbers exist in a ticket combination,
             * we do a bitwise OR operation between the two ticket frequency
             * integers. If all numbers exist in the combination, then the resulting
             * number of the OR operation is 1023 ( bit 1 for positions 0-9 )
             */
             
            for(Entry<Integer,Long> e : m.entrySet()){
                if((freq[tickets_i] | e.getKey()) == 1023){
                    total+=e.getValue();
                }
            }
            
            tmp = m.get(freq[tickets_i]);
            if(tmp == null){
                tmp = 1l;
            } else {
                tmp++;
            }
            m.put(freq[tickets_i],tmp);
            
        }
        System.out.println(total);
        in.close();
    }
}
