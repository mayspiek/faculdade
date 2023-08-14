import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorVeiculo {
    Scanner scanner;
    DaoVeiculo daoVeiculo;

    public GerenciadorVeiculo(){
        scanner = new Scanner(System.in);
        daoVeiculo = new DaoVeiculo();
    }

    public void menu(){
        int opcao = -1;
        while(opcao != 0){;
			System.out.println("\n----[GERENCIAMENTO DE VEÍCULOS]----");
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
        Veiculo v = new Veiculo();
        System.out.println("\n----[CADASTRO DE VEÍCULOS]----");
        System.out.println("Marca:");
        v.setMarca(scanner.nextLine());
        System.out.println("Modelo:");
        v.setModelo(scanner.nextLine());
        System.out.println("Chassi:");
        v.setChassi(scanner.nextLine());
        System.out.println("Ano:");
        v.setAno(Integer.parseInt(scanner.nextLine()));

        //passa o objeto para a camada model
        boolean inserido = daoVeiculo.inserir(v);
        if(inserido){
            System.out.println("\nInserido com sucesso.");
        }
    }

    public void listarTodos(){
        ArrayList<Veiculo> veiculos = daoVeiculo.buscarTodos();
        System.out.println("----[LISTA DE VEÍCULOS]----");
        for(Veiculo v : veiculos){
            System.out.println("Código: " + v.getCodigo()
            + ", Marca: " + v.getMarca()
            + ", Modelo: " + v.getModelo()
            + ", Chassi: " + v.getChassi()
            + ", Ano: " + v.getAno());
        }
    }

    public void excluir(){
        System.out.println("----[EXCLUSÃO DE VEÍCULO]----");
        System.out.println("\nCódigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        int qtde = daoVeiculo.excluir(codigo);
        if(qtde > 0){
            System.out.println("\nExcluído com sucesso.");
        }else{
            System.out.println("\nNão encontrado...");
        }
    }

    public void consultar(){
        System.out.println("----[CONSULTA DE VEICULO]----");
        System.out.println("\nCódigo: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        Veiculo v = daoVeiculo.consultar(codigo);
        if(v != null){
            System.out.println("\n----[Dados do veículo]----");
            System.out.println("- Código: " + v.getCodigo());
            System.out.println("- Marca: " + v.getMarca());
            System.out.println("- Modelo: " + v.getModelo());
            System.out.println("- Chassi: " + v.getChassi());
            System.out.println("- Ano: " + v.getAno());
        }else{
            System.out.println("\nNão encontrado...");
        }
    }

    
    public void alterar(){
        System.out.println("----[ALTERAÇÃO DE VEÍCULO]----");
        System.out.println("Código: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        Veiculo v = daoVeiculo.consultar(codigo);
        if(v!=null){
            System.out.println("\n----[Dados do veículo]----");
            System.out.println("[Código: " + v.getCodigo() + "]");
            
            System.out.println("[Marca: " + v.getMarca() + "]");
            String marca = scanner.nextLine();
            if(marca.isEmpty()){
                v.setModelo(v.getModelo());
            } else{
                v.setModelo(marca);
            }
            
            System.out.println("[Modelo: " + v.getModelo() + "]");
            String modelo = scanner.nextLine();
            if(modelo.isEmpty()){
                v.setModelo(v.getModelo());
            } else {
                v.setModelo(modelo);
            }
            
            System.out.println("[Chassi: " + v.getChassi() + "]");
            String chassi = scanner.nextLine();
            if(chassi.isEmpty()){
                v.setChassi(v.getChassi());
            }else {
                v.setModelo(chassi);
            }
            
            System.out.println("[Ano: " + v.getAno() + "]");
            String ano = scanner.nextLine();
            if(ano.isEmpty()){
                v.setAno(v.getAno());
            } else {
                v.setAno(Integer.parseInt(ano));
            }
                      
            int qtdeAlterado = daoVeiculo.alterar(v);
            if(qtdeAlterado > 0){
                System.out.println("\nAtualizado com sucesso.");
            }
        }else{
            System.out.println("\nNão encontrado...");
        }
    }

}
