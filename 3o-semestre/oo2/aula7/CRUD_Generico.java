import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CRUD_Generico {
  public static void main(String[] args) throws Exception {
    DaoGenerico dao = new DaoGenerico();
    // dao.inserir(new Veiculo());
    // Veiculo v = new Veiculo();
    // v.setModelo("GOL");
    // v.setMarca("VOLKS");
    // v.setChassi("85565657");
    // v.setAno(2020);
    // dao.inserir(v);
    // DateTimeFormatter formatData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    // String dataInformada = "10/06/1940";
    // Cidade c = new Cidade();
    // c.setNome("Foz");
    // c.setAltitude(3554.5f);
    // c.setPopulacao(253000);
    // c.setDataFundacao(LocalDate.parse(dataInformada, formatData));
    // dao.inserir(c);
    // Cidade c1 = (Cidade) dao.consultar(new Cidade(), "id", 2);
    // System.out.println("O nome da cidade é: " + c1.getNome());

    // Veiculo v1 = (Veiculo) dao.consultar(new Veiculo(), "codigo", 1);
    // System.out.println("O veículo é: " + v1.getModelo());

    ArrayList<Object> itens = dao.buscarTodos(new Veiculo());
    for (Object o : itens) {
      Veiculo v = (Veiculo) o;
      System.out.println("Veiculo: " + v.getModelo() + "Marca: " + v.getMarca());
    }

  }
}
