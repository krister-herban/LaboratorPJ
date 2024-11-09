package exercitiul3;

import java.util.Scanner;

public class MainAPP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("introduceti sirul: ");
        String s = sc.nextLine();
        System.out.print("introduceti subsirul: ");
        String sub = sc.nextLine();
        StringBuilder stringBuilder=new StringBuilder(s);
        System.out.print("introduceti pozitia: ");
        int i=sc.nextInt();
        stringBuilder.insert(i,sub);
        System.out.println(stringBuilder.toString());
    }
}
