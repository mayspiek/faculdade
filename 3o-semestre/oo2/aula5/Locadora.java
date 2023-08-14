import java.util.Scanner;

public class Locadora {
    public static void main(String[] args) {
        Locadora loc = new Locadora();
        loc.menuPrincipal();
    }

    public void menuPrincipal(){
        int opcao = -1;
        Scanner scanner = new Scanner(System.in);
        while(opcao != 0){
			System.out.println("\n---[MENU PRINCIPAL]----.");
			System.out.println("[1] Gerenciar Veiculos.");
			System.out.println("[2] Gerenciar Clientes.");
			System.out.println("[3] Gerenciar Clientes PJ.");
			System.out.println("[0] Sair.");

            try{
                opcao = Integer.parseInt(scanner.nextLine());
            }catch(Exception e){
                System.out.println("\nErro. Informe um número inteiro. " 
                    + e.getMessage());
            }

            switch (opcao) {
            case 1:
                GerenciadorVeiculo gv = new GerenciadorVeiculo();
                gv.menu();
                break;
            case 2:
                GerenciadorClientes gc = new GerenciadorClientes();
                gc.menu();
                break;
            case 3:
                GerenciadorClientePj gcpj = new GerenciadorClientePj();
                gcpj.menu();
            case 0:
                System.out.println("\nEncerrando....");
                break;
            default: 
                System.out.println("\nOpção inválida.");
                break;
            }
        }
        scanner.close();
    }    
}
