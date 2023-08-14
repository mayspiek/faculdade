// import java.lang.reflect.Field;
// import java.lang.reflect.Method;
// import java.lang.reflect.Parameter;

public class ExReflection {
  // public static void main(String[] args) {
  //   try {
  //     Class c = Class.forName("Veiculo"); // se sei o nome da classe
  //     //Veiculo v = new Veiculo(); 
  //     //Class c = v.getClass(); // recebo o obj como param e n sei seu tipo
  //     System.out.println(c.getName());
  //     System.out.println("------ Atributos: ");
  //     Field campos[] = c.getDeclaredFields();
  //     for(Field f : campos){
  //       System.out.println(f.toString());
  //       System.out.println(f.getName() + " - " + f.getType());
  //     }
  //     System.out.println("------- Métodos: ");
  //     Method metodos[] = c.getDeclaredMethods();
  //     for(Method m : metodos) {
  //       System.out.println(m.toString());
  //       System.out.println(m.getName());
  //       Parameter param[] = m.getParameters();
  //       for(Parameter p : param){
  //         System.out.println(p.getParameterizedType());
  //         System.out.println(p.getName());;
  //       }

  //       if(m.getName().toString().equals("setAno")){
  //         m.invoke(c.newInstance(), 2020);
  //         System.out.println("Chamou o setAno");
  //       }
  //       System.out.println();
  //     }


  //   } catch (Throwable e) {
  //     // Throwable inclui Erros e Exceptions
  //       System.err.println(e);
  //   }
  // }
}