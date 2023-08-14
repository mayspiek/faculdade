import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorClientePj {
    Scanner scanner;
    DaoClientePj daoPj;

    public GerenciadorClientePj(){
        scanner = new Scanner(System.in);
        daoPj = new DaoClientePj();
    }

    public void menu(){
        int opcao = -1;
        while(opcao != 0){;
			System.out.println("\n----[GERENCIAMENTO DE CLIENTES PJ]----");
			System.out.println("[1] Cadastrar.");
			System.out.println("[2] Consultar.");
			System.out.println("[3] Alterar.");
			System.out.println("[4] Excluir.");
			System.out.println("[5] Listar todos.");
			System.out.println("[0] Voltar ao menu anterior.");
            System.out.println("\nSelecione uma opção: ");

            try{
                opcao = Integer.parseInt(scanner.nextLine());
            }catch(Exception e){
                System.out.println("\nErro... informe um numero inteiro. " 
                    + e.getMessage());
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
                    
                    break;
                }
        }
    }
    
    public void cadastrar(){
        ClientePj cpj = new ClientePj();
        System.out.println("\n----[CADASTRO DE CLIENTES PJ]----");
        System.out.println("Nome:");
        cpj.setNome(scanner.nextLine());
        System.out.println("CNPJ:");
        cpj.setCnpj(scanner.nextLine());

        EnderecoPj endPj = new EnderecoPj();
        System.out.println("\n----[Endereço]----");
        System.out.println("Rua:");
        endPj.setRua(scanner.nextLine());
        System.out.println("Bairro:");
        endPj.setBairro(scanner.nextLine());
        System.out.println("Número:");
        endPj.setNumero(Integer.parseInt(scanner.nextLine()));
        System.out.println("CEP: ");
        endPj.setCep(scanner.nextLine());

        cpj.setEnderecoPj(endPj);
        
        boolean inserido = daoPj.inserir(cpj);
        if(inserido){
            System.out.println("\nInserido com sucesso.");
        }
    }

    public void listarTodos(){
        ArrayList<ClientePj> cliPJ = daoPj.buscarTodos();
        System.out.println("----[LISTA DE CLIENTES PJ]----");
        for(ClientePj cpj : cliPJ){
            System.out.println("Código: " + cpj.getCodigoClientePj()
            + ", Nome: " + cpj.getNome()
            + ", CNPJ: " + cpj.getCnpj()
            + ", Rua: " + cpj.getEnderecoPj().getRua() 
            + ", Numero: " + cpj.getEnderecoPj().getNumero()
            + ", Bairro: " + cpj.getEnderecoPj().getBairro()
            + ", CEP: " + cpj.getEnderecoPj().getCep());
        }
    }

    public void excluir(){
        System.out.println("----[EXCLUSÃO DE CLIENTES PJ]----");
        System.out.println("\nCódigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        int qtde = daoPj.excluir(codigo);
        if(qtde > 0){
            System.out.println("\nExcluído com sucesso.");
        }else{
            System.out.println("\nNão encontrado...");
        }
    }

    public void consultar(){
        System.out.println("----[CONSULTA DE CLIENTES PJ]----");
        System.out.println("\nCódigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        ClientePj cPj = daoPj.consultar(codigo);
        if(cPj != null){
            System.out.println("\n----[Dados do Cliente]----");
            System.out.println("- Código: " + cPj.getCodigoClientePj());
            System.out.println("- Nome: " + cPj.getNome());
            System.out.println("- CNPJ: " + cPj.getCnpj());

            System.out.println("----[Dados do endereço do Cliente PJ]----");
            System.out.println("- Rua: " + cPj.getEnderecoPj().getRua());
            System.out.println("- Numero: " + cPj.getEnderecoPj().getNumero());
            System.out.println("- Bairro: " + cPj.getEnderecoPj().getBairro());
            System.out.println("- CEP: " + cPj.getEnderecoPj().getCep());
        }else{
            System.out.println("\nNão encontrado...");
        }
    }

    
    public void alterar(){
        System.out.println("----[ALTERAÇÃO DE CLIENTES PJ]----");
        System.out.println("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        ClientePj cPj = daoPj.consultar(codigo);
        if(cPj!=null){
            System.out.println("\n----[Dados do Cliente PJ]----");
            System.out.println("[Código: " + cPj.getCodigoClientePj() + "]");
            
            System.out.println("[Nome: " + cPj.getNome() + "]");
            String nome = scanner.nextLine();
            if(nome.isEmpty()){
                cPj.setNome(cPj.getNome());
            } else{
                cPj.setNome(nome);
            }
            
            System.out.println("[CNPJ: " + cPj.getCnpj() + "]");
            String cnpj = scanner.nextLine();
            if(cnpj.isEmpty()){
                cPj.setCnpj(cPj.getCnpj());
            } else {
                cPj.setCnpj(cnpj);
            }
            
            System.out.println("----[Dados do endereço do Cliente PJ]----");
            System.out.println("[Rua: " + cPj.getEnderecoPj().getRua() + "]");
            String rua = scanner.nextLine();
            if(rua.isEmpty()){
                cPj.getEnderecoPj().setRua(cPj.getEnderecoPj().getRua());
            }else {
                cPj.getEnderecoPj().setRua(rua);
            }
            
            System.out.println("[Numero: " + cPj.getEnderecoPj().getNumero() + "]");
            String numero = scanner.nextLine();
            if(numero.isEmpty()){
                cPj.getEnderecoPj().setNumero(cPj.getEnderecoPj().getNumero());
            } else {
                cPj.getEnderecoPj().setNumero(Integer.parseInt(numero));
            }

            System.out.println("[Bairro: " + cPj.getEnderecoPj().getNumero() + "]");
            String bairro = scanner.nextLine();
            if(bairro.isEmpty()){
                cPj.getEnderecoPj().setBairro(cPj.getEnderecoPj().getBairro());
            } else {
                cPj.getEnderecoPj().setBairro(bairro);
            }

            System.out.println("[CEP: " + cPj.getEnderecoPj().getCep() + "]");
            String cep = scanner.nextLine();
            if(cep.isEmpty()){
                cPj.getEnderecoPj().setCep(cPj.getEnderecoPj().getCep());
            } else {
                cPj.getEnderecoPj().setCep(cep);
            }
                      
            int qtdeAlterado = daoPj.alterar(cPj);
            if(qtdeAlterado > 0){
                System.out.println("\nAtualizado com sucesso.");
            }
        }else{
            System.out.println("\nNão encontrado...");
        }
    }

}
