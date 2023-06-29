import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ExReflection {

    public static void main(String[] args) {
        try {
            Class c = Class.forName("Veiculo");//se sei o nome da classe    
            // Veiculo v = new Veiculo();
            // Class c2 = v.getClass(); //se recebo um objeto como 
            //                         //param e não sei o seu tipo
            System.out.println(c.getName());
            System.out.println("-->Atributos:");
            Field fields[] = c.getDeclaredFields();
            for(Field f : fields){
                System.out.println(f.toString());
                System.out.println(f.getName() 
                    + " - " + f.getType());
            }

            System.out.println("-->Métodos:");
            Method methods[] = c.getDeclaredMethods();
            for (Method m : methods) {
                System.out.println(m.toString());
                System.out.println(m.getName());
                Parameter parameters[] = m.getParameters();
                for (Parameter p : parameters) {
                    System.out.println(p.getParameterizedType());
                    System.out.println(p.getName()); //o nome do
                    //param só é armazenado no .class se for 
                    //compilado com -parameters
                }

                if(m.getName().toString().equals("setAno")){
                    m.invoke(c.newInstance(), 2020);
                }
            }
            

        } catch (Throwable e) {
            //Throwable inclui Erros e Exceptions
            System.err.println(e);
        }
    }
}