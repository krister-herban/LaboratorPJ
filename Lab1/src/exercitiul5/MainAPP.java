package exercitiul5;

import java.util.Random;

public class MainAPP {
    public static void main(String[] args) {
        Random rand = new Random();
        int nr=rand.nextInt(19)+1;

        System.out.println("nr: "+nr);

        int i=1;
        int j=0;
        int ver=0;
        for(int a=0; a<=7; a++){
            int next=i+j;
            j=i;
            i=next;
            if(nr==next){
                System.out.println("nr e din sirul Fibonacci");
                ver=1;
            }
        }
        if(ver==0){
            System.out.println("nr nu e din sirul Fibonacci");
        }
    }
}
