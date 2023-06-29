import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CRUD_Generico {
    public static void main(String[] args) throws Exception{
        DaoGenerico dao = new DaoGenerico();
        
        //Teste inserir
        // Veiculo v = new Veiculo();
        // v.setAno(2020);
        // v.setChassi("123.123.123");
        // v.setMarca("Ford");
        // v.setModelo("Ka");
        // dao.inserir(v);

        DateTimeFormatter formatoData = 
            DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // String dataInformada = "10/06/1940";
        // Cidade c = new Cidade();
        // c.setNome("Foz");
        // c.setAltitude(272.5f);
        // c.setPopulacao(265000);
        // c.setDataFundacao(
        //     LocalDate.parse(dataInformada, formatoData));
        // dao.inserir(c);

        //Teste consulta
        // Cidade c1 = (Cidade) dao.consultar(
        //         new Cidade(), "id", 16);
        // System.out.println("O nome da cidade é: "
        //     + c1.getNome());

        // Veiculo v1 = (Veiculo) dao.consultar(
        //     new Veiculo(), "codigo", 50);
        // System.out.println("O veiculo é:" + v1.getModelo());

        //teste buscar todos
        // ArrayList<Object> itens = dao.buscarTodos(new Veiculo());
        // for (Object o : itens) {
        //     Veiculo v = (Veiculo) o;
        //     System.out.println("Veiculo: " + v.getModelo()
        //         + "  -  Marca: " + v.getMarca());
        // }

        // ArrayList<Object> itens = dao.buscarTodos(new Cidade());
        // for (Object o : itens) {
        //     Cidade c = (Cidade) o;
        //     System.out.println("Cidade: " + c.getNome()
        //         + "  -  Data Fund: " 
        //         + c.getDataFundacao().format(formatoData));
        // }
        
        // ArrayList<Object> itens = dao.buscarTodosFiltro(
        //     new Veiculo(), "marca", "Honda");
        // for (Object o : itens) {
        //     Veiculo v = (Veiculo) o;
        //     System.out.println("Veiculo: " + v.getModelo()
        //         + "  -  Marca: " + v.getMarca());
        // }

        //teste excluir
        int qtde = dao.excluir(new Veiculo(), "codigo", 61);
        System.out.println("Qtde excluida: " + qtde);
    }
}
