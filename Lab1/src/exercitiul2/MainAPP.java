package exercitiul2;

import java.io.*;

public class MainAPP {
    public static void main(String[] args) throws IOException {
        String nume_fisi="in.txt";
        String nume_fiso="out.txt";
        BufferedReader br=new BufferedReader(new FileReader(nume_fisi));
        BufferedWriter bw=new BufferedWriter(new FileWriter(nume_fiso));
        int[] vector=new int[6];
        int i=0;
        String linie;
        while((linie=br.readLine())!=null){
            vector[i]=Integer.parseInt(linie);
            i++;
        }
        int s=0;
        int min=100;
        int max=0;
        System.out.print("vectorul este: ");
        for(int j=0;j<vector.length;j++){
            System.out.print(" "+vector[j]);
            s=s+vector[j];
            if(vector[j]<min){min=vector[j];}
            if(vector[j]>max){max=vector[j];}
        }
        System.out.println();
        System.out.println("suma numerelor: "+s);
        int ma=s/6;
        System.out.println("media aritmetica: "+ma);
        System.out.println("minimul: "+min);
        System.out.println("maximul: "+max);
        bw.write("suma numerelor: "+s+"\n"+"media aritmetica: "+ma+"\n"+"minimul: "+min+"\n"+"maximul: "+max);
        br.close();
        bw.close();
    }
}
