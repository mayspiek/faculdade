import java.util.HashMap;

public class Dias {
    public static void main(String[] args) {
        HashMap<String, String> diasSemana = 
            new HashMap<String, String>();
        
        diasSemana.put("SEG", "Segunda-feira");
        diasSemana.put("TER", "Terça-feira");
        diasSemana.put("QUA", "Quarta-feira");
        diasSemana.put("QUI", "Quinta-feira");
        diasSemana.put("SEX", "Sexta-feira");
        diasSemana.put("SAB", "Sábado");
        diasSemana.put("DOM", "Domingo");
        
        System.out.println("Mostrando os pares armazenados:");
        System.out.println(diasSemana);

        System.out.println("Verificando se um dia existe:");
        System.out.println("QUA existe? " 
            + diasSemana.containsKey("QUA"));
        System.out.println("ABC existe? " 
            + diasSemana.containsKey("ABC"));
        
        System.out.println("Pegando um item a partir da chave:");
        System.out.println(diasSemana.get("TER"));

        System.out.println("Quantidade de pares: "
            + diasSemana.size());

        System.out.println("Removendo um item... ");
        String removido = diasSemana.remove("TER");
        System.out.println("O removido foi " + removido);
        System.out.println("Quantidade de pares: "
            + diasSemana.size());

        System.out.println("Percorrendo as chaves: ");
        for (String chave : diasSemana.keySet()) {
            System.out.println(chave);
        }

        System.out.println("Percorrendo os valores: ");
        for (String item : diasSemana.values()) {
            System.out.println(item);
        }

        System.out.println("Removendo todos os pares...");
        diasSemana.clear();
        System.out.println("Quantidade de pares: "
            + diasSemana.size());
    }
    
}