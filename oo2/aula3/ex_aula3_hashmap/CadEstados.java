import java.util.HashMap;

public class CadEstados {
    public static void main(String[] args) {
        HashMap<String, Estado> estados = 
            new HashMap<String, Estado>();

        Estado e1 = new Estado();
        e1.setNome("Paraná");
        e1.setRegiao("Sul");
        estados.put("PR", e1);
        
        Estado e2 = new Estado();
        e2.setNome("São Paulo");
        e2.setRegiao("Sudeste");
        estados.put("SP", e2);

        Estado e3 = new Estado();
        e3.setNome("Rio Grande do Sul");
        e3.setRegiao("Sul");
        estados.put("RS", e3);

        System.out.println("Estados armazenados: " + estados);

        System.out.println("Verificando se um estado existe:");
        String pesquisado = "SP";
        if(estados.containsKey(pesquisado)){
            System.out.println(pesquisado + " existe no hashmap");
            System.out.println("Buscando a região do estado de " 
                + pesquisado);
            System.out.println(estados.get(pesquisado).getRegiao());
        }

        System.out.println("Percorrendo as chaves: ");
        for (String chave : estados.keySet()) {
            System.out.println(chave);
        }

        System.out.println("Percorrendo os valores: ");
        for (Estado item : estados.values()) {
            System.out.println(item.getNome());
        }        
    }
}
