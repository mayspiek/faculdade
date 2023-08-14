import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListCores {
    public static void main(String[] args) {
        String[] vetCores = { "MAGENTA", "RED", "WHITE", "BLUE", "CYAN" };
        ArrayList<String> listaCores = new ArrayList<String>();

        System.out.println("Vetor: percorrer vetor com contador");
        for(int i = 0; i < vetCores.length; i++){
            System.out.println(vetCores[i]);
        }

        System.out.println("Vetor: percorrer vetor com foreach");
        for(String cor : vetCores){//para cada cor no vetor de cores
            System.out.println(cor);
            listaCores.add(cor);
        }

        System.out.println("ArrayList: percorendo com contador");
        for(int count = 0; count < listaCores.size(); count++){
            System.out.println(listaCores.get(count));
        }

        System.out.println("ArrayList: percorrendo com Iterator");
        Iterator<String> it = listaCores.iterator(); 
        while(it.hasNext()){
            System.out.println(it.next());
        }
                
        System.out.println("ArrayList: percorrendo com foreach");
        for(String cor : listaCores){
            System.out.println(cor);
        }    
           
    }

}
