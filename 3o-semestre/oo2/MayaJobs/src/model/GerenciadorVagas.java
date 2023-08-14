package model;

import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorVagas {
    Scanner scanner;
    DaoVagas daoVagas;

    public GerenciadorVagas() {
        scanner = new Scanner(System.in);
        daoVagas = new DaoVagas();
    }

    public static void main(String[] args) {
        try {
            GerenciadorVagas gv = new GerenciadorVagas();

            gv.menu();

        } catch (Exception e) {

        }
    }

    public void menu() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n\n----[GERENCIADOR DE VAGAS]----");
            System.out.println("[1] - Cadastrar vagas.");
            System.out.println("[2] - Listar todas as vagas.");
            System.out.println("[3] - Alterar vagas.");
            System.out.println("[4] - Consultar vagas.");
            System.out.println("[5] - Excluir.");
            System.out.println("[0] - Sair.");

            try {
                op = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("\n\nInforme um numero valido.");
            }

            switch (op) {
                case 1:
                    this.cadastrar();
                    break;
                case 2:
                    this.listarTodos();
                    break;
                case 3:
                    this.alterar();
                    break;
                case 4:
                    this.consultar();
                    break;
                case 5:
                    this.excluir();
                    break;
                default:
                    break;
            }
        }
    }

    public void cadastrar() {
        VagasEmpregos vagasEmp = new VagasEmpregos();

        System.out.println("\n\n----[CADASTRO DE VAGAS]----");
        System.out.println("Cargo: ");
        vagasEmp.setCargo(scanner.nextLine());
        System.out.println("Requisitos: ");
        vagasEmp.setRequisitos(scanner.nextLine());
        System.out.println("Salario: ");
        vagasEmp.setSalario(Float.parseFloat(scanner.nextLine()));

        Empresas emp = new Empresas();
        System.out.println("\n[EMPRESA]");
        System.out.println("Nome da empresa: ");
        emp.setNome(scanner.nextLine());
        System.out.println("Descrição: ");
        emp.setDescricao(scanner.nextLine());
        System.out.println("Quantos funcionários trabalham nessa empresa?: ");
        emp.setQtdeFuncionarios(Integer.parseInt(scanner.nextLine()));

        vagasEmp.setEmpresa(emp);

        boolean inserido = daoVagas.inserirBanco(vagasEmp);

        if (inserido) {
            System.out.println("\nInserido com sucesso.");
        }
    }

    private void listarTodos() {
        ArrayList<VagasEmpregos> resultado = daoVagas.buscarTodos();

        System.out.println("\n\n----[VAGAS CADASTRADAS]----");
        for (VagasEmpregos ve : resultado) {
            System.out.println(ve.toString());
        }
    }

    private void consultar(){
        System.out.println("\n\n----[CONSULTAR VAGAS]----");
        System.out.println("Informe o codigo da vaga: ");
        int cod_vaga = Integer.parseInt(scanner.nextLine());

        VagasEmpregos ve = daoVagas.consultar(cod_vaga);

        if (ve != null) {
            System.out.println(ve.toString());
        } else {
            System.out.println("\nVaga não encontrada.");
        }
    }

    private void alterar(){
        System.out.println("\n\n----[ALTERAR VAGAS]----");
        System.out.println("Informe o codigo da vaga: ");
        int cod_vaga = Integer.parseInt(scanner.nextLine());

        VagasEmpregos ve = daoVagas.consultar(cod_vaga);
        if(ve != null){
            System.out.println("\n\n[Dados da Vaga]");
            System.out.println("Código: [" + ve.getCod_vaga() + "]");

            System.out.println("Cargo: [" + ve.getCargo() + "]");
            String cargo = scanner.nextLine().trim();
            if(!cargo.isEmpty()){
                ve.setCargo(cargo);
            } else {
                System.out.println("Cargo não alterado.");
            }

            System.out.println("Requisitos: [" + ve.getRequisitos() + "]");
            String requisitos = scanner.nextLine().trim();
            if(!requisitos.isEmpty()){
                ve.setRequisitos(requisitos);
            } else {
                System.out.println("Requisitos não alterado.");
            }
            System.out.println("Salario: [" + ve.getSalario() + "]");
            String salario = scanner.nextLine().trim();
            if(!salario.isEmpty()){
                ve.setSalario(Float.parseFloat(salario));
            } else {
                System.out.println("Salario não alterado.");
            }
            System.out.println("\n\n[Dados da Empresa]");
            System.out.println("Nome: [" + ve.getEmpresa().getNome() + "]");
            String nome = scanner.nextLine().trim();
            if(!nome.isEmpty()){
                ve.getEmpresa().setNome(nome);
            } else {
                System.out.println("Nome não alterado.");
            }

            System.out.println("Descrição: [" + ve.getEmpresa().getDescricao() + "]");
            String descricao = scanner.nextLine().trim();
            if(!descricao.isEmpty()){
                ve.getEmpresa().setDescricao(descricao);
            } else {
                System.out.println("Descrição não alterado.");
            }

            System.out.println("Quantidade de funcionarios: [" + ve.getEmpresa().getQtdeFuncionarios() + "]");
            String qtdeFuncionarios = scanner.nextLine().trim();
            if(!qtdeFuncionarios.isEmpty()){
                ve.getEmpresa().setQtdeFuncionarios(Integer.parseInt(qtdeFuncionarios));
            } else {
                System.out.println("Quantidade de funcionarios não alterado.");
            }

            int qtdeAlterado = daoVagas.alterar(ve);
            if(qtdeAlterado > 0){
                System.out.println("\nAlterado com sucesso.");
            }
           
        } else {
            System.out.println("\nVaga não encontrada.");
        }
    }

    private void excluir(){
        System.out.println("\n\n----[EXCLUIR VAGAS]----");
        System.out.println("Informe o codigo da vaga: ");
        int cod_vaga = Integer.parseInt(scanner.nextLine());

        int qtde = daoVagas.excluir(cod_vaga);

        if(qtde > 0){
            System.out.println("\nExcluido com sucesso.");
        } else {
            System.out.println("\nVaga não encontrada.");
        }
    }
}
