import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Aniversario {
    BufferedReader reader;
    //HashMap<String, ArrayList<Aniversariante>> data = new HashMap<String, ArrayList<Aniversariante>>();
    HashMap<String, ArrayList<String>> aniversariante;


    public static void main(String[] args) throws Exception{
        Aniversario a1 = new Aniversario();
        // a1 : objeto da propria classe
        a1.reader = new BufferedReader(new InputStreamReader(System.in));
        a1.aniversariante = new HashMap<String, ArrayList<String>>();
        // instanciado e visivel em qualquer lugar
        a1.menu();
    }

    public void menu() throws Exception{

        // try catch dentro do while 
        System.out.println("Sistema Assistência Técnica\n");
        String opcao = "";
        while(!opcao.equals("4")){
            System.out.println("------------");
            System.out.println("[1] - Cadastrar aniversariante: ");
            System.out.println("[2] - Exibir a lista de aniversários (data): ");
            System.out.println("[3] - Consultar data: ");
            System.out.println("[4] - Sair: ");

            opcao = this.reader.readLine(); 
            switch (opcao) {       

                case "1":
                    this.cadastrarAniversariante();
                    break;

                case "2":
                    this.listarDatas();
                    break;

                case "3":
                    this.consultarAniversariante();
                    break;

                case "4":
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção informada inválida.");
            }
        }
    }

    public void cadastrarAniversariante() throws Exception{
        System.out.println("Informe a data de aniversário:");
        System.out.println("Formato: [DD/mm]");
        String data = this.reader.readLine();
        System.out.println("Informe o nome:");
        String nome = this.reader.readLine();
        if(!this.aniversariante.containsKey(data)){
            this.aniversariante.put(data, new ArrayList<String>());
        }
        this.aniversariante.get(data).add(nome);
        
    }

    public void listarDatas() {
        System.out.println("Lista datas");
        for(String dataAniv : aniversariante.keySet()) {
            System.out.println(dataAniv);
        }
    }

    public void consultarAniversariante() throws Exception {
        System.out.println("Informe a data:");
        String dataConsulta = this.reader.readLine();
        // for (Aniversariante aniver : lista) {}
        if(this.aniversariante.containsKey(dataConsulta)){
            System.out.println(dataConsulta);
            ArrayList<String> arrayListNomes = this.aniversariante.get(dataConsulta);
            for (String nome : arrayListNomes) {
                System.out.println(nome);
            }
        } else {
            System.out.println("Não exste aniversariantes desse dia.");
        }

    }
}
