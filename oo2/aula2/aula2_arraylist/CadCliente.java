import java.util.ArrayList;
import java.util.Iterator;

public class CadCliente {
    public static void main(String[] args) {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        Cliente c1 = new Cliente();
        c1.setNome("Humberto");
        c1.setEmail("asd@asd.com");
        c1.setCpf("123456");
        lista.add(c1);

        Cliente c2 = new Cliente();
        c2.setNome("Ana");
        c2.setEmail("ana@asd.com");
        c2.setCpf("123123123");
        lista.add(c2);

        Cliente c3 = new Cliente();
        c3.setNome("Pedro");
        c3.setEmail("pedro@asd.com");
        c3.setCpf("432234");
        lista.add(c3);

        System.out.println("Percorrendo a lista:");
        Iterator<Cliente> it = lista.iterator();
        //lista.iterator() devolve um ponteiro para o inicio da lista
        while(it.hasNext()){
            Cliente cli = it.next(); //pega o próximo elemento
            System.out.println("Cliente: " + cli.getNome()
                + " CPF: " + cli.getCpf());
        }

        //remover um item da lista
        Cliente cliRemover = new Cliente();
        cliRemover.setCpf("123456");
        Iterator<Cliente> itRemover = lista.iterator();
        while(itRemover.hasNext()){
            if(itRemover.next().getCpf().equals(cliRemover.getCpf())){
                System.out.println("Removendo cliente");
                itRemover.remove();
            }
        }

        //percorrendo com foreach
        for (Cliente cli : lista) {
            System.out.println(cli.getNome());
        }

        //para remover elemento da lista é recomendado usar 
        //o iterator ao invés do foreach 
        // for (Cliente cli : lista) {
        //     System.out.println(cli.getNome());
        //     if(cli.getCpf().equals("123123123")){
        //         lista.remove(cli);
        //     }
        // }


    }
}
