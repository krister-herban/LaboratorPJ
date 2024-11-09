package exercitiu1;

public class Parabola {
    double a, b, c;

    public Parabola() {}

    public Parabola(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double[] varf_parabole(){
        double x,y;
        x=-b/(2*a);
        y=-(b*b-4*a*c)/(4*a);
        return new double[]{x,y};
    }
    @Override
    public String toString() {
        return (a+"*x^2"+"+"+b+"*x"+"+"+c);
    }

    public double[] mij_doua_parabole(Parabola p){
        double[] this_parabola = this.varf_parabole();
        double[] p_parabola = p.varf_parabole();

        double x=(this_parabola[0]+p_parabola[0])/2;
        double y=(this_parabola[1]+p_parabola[1])/2;

        return new double[]{x,y};
    }

    public static double[] mij_doua_parabole_static(Parabola p1, Parabola p2){
        double[] p1_parabole = p1.varf_parabole();
        double[] p2_parabole = p2.varf_parabole();

        double x=(p1_parabole[0]+p2_parabole[0])/2;
        double y=(p1_parabole[1]+p2_parabole[1])/2;

        return new double[]{x,y};
    }

    public double distanta_varfuri(Parabola p) {
        double[] this_parabola = this.varf_parabole();
        double[] p_parabola = p.varf_parabole();

        double distance =Math.sqrt(Math.pow(this_parabola[0]-p_parabola[0], 2)+Math.pow(this_parabola[1]-p_parabola[1], 2));
        return distance;
    }

    public static double distanta_varfuri_static(Parabola p1, Parabola p2) {
        double[] p1_parabola = p1.varf_parabole();
        double[] p2_parabola = p2.varf_parabole();

        double distance = Math.sqrt(Math.pow(p1_parabola[0]-p2_parabola[0], 2)+Math.pow(p1_parabola[1]-p2_parabola[1], 2));
        return distance;
    }
}
