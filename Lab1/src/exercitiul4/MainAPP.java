package exercitiul4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class MainAPP {
    public static void main(String[] args) {
        Random rand = new Random();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int nr1= rand.nextInt(30)+1;
        int nr2= rand.nextInt(30)+1;
        System.out.println("nr1: "+nr1+" nr2: "+nr2);
        int i;
        int cmmdc=1;

        for(i=1; i<=nr1; i++){
            if(nr1%i==0&&nr2%i==0){
                cmmdc=i;
            }
        }
        System.out.println("cmmdc="+cmmdc);
    }
}
