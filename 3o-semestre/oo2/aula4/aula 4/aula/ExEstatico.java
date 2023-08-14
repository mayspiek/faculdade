public class ExEstatico {
    public static void main(String[] args) {

        Cliente c1 = new Cliente();
        c1.setNome("Gabriela");
        System.out.println("Quantidade de cliente = " + c1.qtdClientes);

        Cliente c2 = new Cliente();
        c2.setNome("Sofia");
        System.out.println("Quantidade de cliente = " + c2.qtdClientes);

        System.out.println("Quantidade de cliente = " + c1.qtdClientes);
        System.out.println("Quantidade de cliente = " + Cliente.qtdClientes);


    }
}
