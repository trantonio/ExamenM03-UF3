package Example.AguirreAntonio.v4;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Accidentes  implements Comparable<Accidentes> {


    String nombre;
    String diaSem;
    int hora;
    String causa;
    static ArrayList<Accidentes> accidentes= new ArrayList<>();



    public Accidentes(String[] linea) {
        //Comprobamos que todos los datos que necesitamos esten llenos
        if(linea[2].isEmpty()||linea[8].isEmpty()||linea[11].isEmpty()||linea[13].isEmpty()){
            this.nombre = "Sin Nombre";
            this.diaSem = "Sin dia";
            this.hora = 0;
            this.causa = "Sin causa";
        }else {
            this.nombre = linea[2];
            this.diaSem = linea[8];
            this.hora = parseInt(linea[11]);
            this.causa = linea[13];

        }
        accidentes.add(this);
    }

    public String getNombre() {
        return nombre;
    }
    public String getDiaSem() {
        return diaSem;
    }
    public int getHora() {
        return hora;
    }

    public String getCausa() {
        return causa;
    }

    @Override
    public String toString() {
        return "AguirreAntonio.v4.Accidentes{" +
                "nombre='" + nombre + '\'' +
                ", diaSem='" + diaSem + '\'' +
                ", hora=" + hora +
                ", causa='" + causa + '\'' +
                '}';
    }

    @Override
    public int compareTo(Accidentes o) {
        if( o.nombre.compareTo(this.nombre)>0){
            return -1;
        }else if ( o.nombre.compareTo(this.nombre)== 0){
        return 0;
        }
        return 1;
    }
//    @Override
//    public int compareTo(AguirreAntonio.v4.Accidentes o) {
//        return nombre.compareTo(o.nombre);
//    }
}
