package exercitiul3;

import java.util.Scanner;

public class MainAPP {
    public static void main(String[] args) {
        Scanner s =new Scanner(System.in);
        System.out.println("Dati numarul: ");
        int n = s.nextInt();
        int i;
        int j=0;
        System.out.print("Divizori: ");
        for(i=1; i<=n; i++){
            if(n%i==0){
                System.out.print(i+" ");
                j++;
            }
        }
        if(j==0)
            System.out.print("nu sunt divizori");
    }
}
