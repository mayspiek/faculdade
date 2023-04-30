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
                    //this.consultar();
                    break;
                case 3:
                    //this.alterar();
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

    // public void consultar(){
    //     System.out.println("----[CONSULTA DE CLIENTES PJ]----");
    //     System.out.println("\nCódigo: ");
    //     int codigo = Integer.parseInt(scanner.nextLine());
    //     Veiculo v = daoPj.consultar(codigo);
    //     if(v != null){
    //         System.out.println("\n----[Dados do veículo]----");
    //         System.out.println("- Código: " + v.getCodigo());
    //         System.out.println("- Marca: " + v.getMarca());
    //         System.out.println("- Modelo: " + v.getModelo());
    //         System.out.println("- Chassi: " + v.getChassi());
    //         System.out.println("- Ano: " + v.getAno());
    //     }else{
    //         System.out.println("\nNão encontrado...");
    //     }
    // }

    
    // public void alterar(){
    //     System.out.println("----[ALTERAÇÃO DE CLIENTES PJ]----");
    //     System.out.println("Código: ");
    //     int codigo = Integer.parseInt(scanner.nextLine());
    //     Veiculo v = daoPj.consultar(codigo);
    //     if(v!=null){
    //         System.out.println("\n----[Dados do veículo]----");
    //         System.out.println("[Código: " + v.getCodigo() + "]");
            
    //         System.out.println("[Marca: " + v.getMarca() + "]");
    //         String marca = scanner.nextLine();
    //         if(marca.isEmpty()){
    //             v.setModelo(v.getModelo());
    //         } else{
    //             v.setModelo(marca);
    //         }
            
    //         System.out.println("[Modelo: " + v.getModelo() + "]");
    //         String modelo = scanner.nextLine();
    //         if(modelo.isEmpty()){
    //             v.setModelo(v.getModelo());
    //         } else {
    //             v.setModelo(modelo);
    //         }
            
    //         System.out.println("[Chassi: " + v.getChassi() + "]");
    //         String chassi = scanner.nextLine();
    //         if(chassi.isEmpty()){
    //             v.setChassi(v.getChassi());
    //         }else {
    //             v.setModelo(chassi);
    //         }
            
    //         System.out.println("[Ano: " + v.getAno() + "]");
    //         String ano = scanner.nextLine();
    //         if(ano.isEmpty()){
    //             v.setAno(v.getAno());
    //         } else {
    //             v.setAno(Integer.parseInt(ano));
    //         }
                      
    //         int qtdeAlterado = daoPj.alterar(v);
    //         if(qtdeAlterado > 0){
    //             System.out.println("\nAtualizado com sucesso.");
    //         }
    //     }else{
    //         System.out.println("\nNão encontrado...");
    //     }
    // }

}
