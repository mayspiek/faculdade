import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorClientes {
    Scanner scanner;
    DaoCliente daoCliente;

    public GerenciadorClientes() {
        scanner = new Scanner(System.in);
        daoCliente = new DaoCliente();
        
    }
    
    public void menu(){
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n----[GERENCIAMENTO DE CLIENTES]----");
			System.out.println("[1] Cadastrar.");
			System.out.println("[2] Consultar.");
			System.out.println("[3] Alterar.");
			System.out.println("[4] Excluir.");
			System.out.println("[5] Listar todos.");
			System.out.println("[0] Voltar ao menu anterior.");
            System.out.println("\nSelecione uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Informe um número inteiro. " + e.getMessage());
            }

            switch (opcao) {
                case 1:
                    this.cadastrar();
                    break;
                case 2: 
                    this.consultar();
                    break;
                    case 3:
                    this.alterar();
                    break;
                case 4:
                    this.excluir();
                    break;
                case 5:
                    this.listarTodos();
                    break;
                case 0:
                    System.out.println("\nEncerrando.");
                    break;
            }
        }
    }

    public void cadastrar(){
        Cliente c = new Cliente();
        System.out.println("\n----[CADASTRO DE CLIENTES]----");
        System.out.println("Nome: ");
        c.setNome(scanner.nextLine());
        System.out.println("Idade: ");
        c.setIdade(Integer.parseInt(scanner.nextLine()));
        System.out.println("Endereço:");
        c.setEndereco(scanner.nextLine());
        System.out.println("E-mail: ");
        c.setEmail(scanner.nextLine());

        boolean inserido = daoCliente.inserir(c);
        if(inserido){
            System.out.println("\nInserido com sucesso!");
        }
    }

    public void listarTodos() {
        ArrayList<Cliente> clientes = daoCliente.buscarTodos();
        System.out.println("----[LITSA DE CLIENTES]----");
        for(Cliente c : clientes){
            System.out.println("Código " + c.getCodigo()
            + ", Nome: " + c.getNome()
            + ", Idade: " + c.getIdade()
            + ", Endereço: " + c.getEndereco()
            + ", E-mail: " + c.getEmail());
        }
    }

    public void excluir(){
        System.out.println("----[EXCLUSÃO DE CLIENTE]----");
        System.out.println("\nCódigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        int qtde = daoCliente.excluir(codigo);
        if(qtde > 0){
            System.out.println("\nExcluído com sucesso.");
        }else{
            System.out.println("\nNão encontrado...");
        }
    }

    public void consultar(){
        System.out.println("----[CONSULTA DE CLIENTES]----");
        System.out.println("\nCódigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        Cliente c = daoCliente.consultar(codigo);
        if(c != null){
            System.out.println("\n----[Dados do cliente]----");
            System.out.println("- Codigo: " + c.getCodigo());
            System.out.println("- Nome: " + c.getNome());
            System.out.println("- Idade: " + c.getIdade());
            System.out.println("- Endereço: " + c.getEndereco());
            System.out.println("- E-mail: " + c.getEmail());
        }else{
            System.out.println("\nNão encontrado...");
        }
    }

    public void alterar(){
        System.out.println("----[ALTERAÇÃO DE CLIENTE]----");
        System.out.println("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        Cliente c = daoCliente.consultar(codigo);
        if(c!=null){
            System.out.println("\n----[Dados do cliente]----");
            System.out.println("[Código: " + c.getCodigo() + "]");
            
            System.out.println("[Nome: " + c.getNome() + "]");
            String nome = scanner.nextLine();
            if(nome.isEmpty()){
                c.setNome(c.getNome());
            } else{
                c.setNome(nome);
            }
            
            System.out.println("[Idade: " + c.getIdade() + "]");
            String idade = scanner.nextLine();
            if(idade.isEmpty()){
                c.setIdade(c.getIdade());
            } else {
                c.setIdade(Integer.parseInt(idade));

            }
            
            System.out.println("[Endereco: " + c.getEndereco() + "]");
            String endereco = scanner.nextLine();
            if(endereco.isEmpty()){
                c.setEndereco(c.getEndereco());
            } else{
                c.setEndereco(endereco);
            }
            
            System.out.println("[E-mail: " + c.getEmail() + "]");
            String email = scanner.nextLine();
            if(email.isEmpty()){
                c.setEmail(c.getEmail());
            } else{
                c.setEmail(email);
            }
                      
            int qtdeAlterado = daoCliente.alterar(c);
            if(qtdeAlterado > 0){
                System.out.println("\nAtualizado com sucesso.");
            }
        }else{
            System.out.println("\nNão encontrado...");
        }
    }

    public String isVazio(String par){
        if(par.equals("") || par.equals(null)){
            System.out.println("Entrada vazia. Digite novamente.");
        }
        return par;
    }
}
