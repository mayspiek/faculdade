import java.time.LocalDate;

public class Cidade {
  private int id;
  private LocalDate dataFundacao;
  private int populacao;
  private String nome;
  private float altitude;
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public LocalDate getDataFundacao() {
    return dataFundacao;
  }
  public void setDataFundacao(LocalDate dataFundacao) {
    this.dataFundacao = dataFundacao;
  }
  public int getPopulacao() {
    return populacao;
  }
  public void setPopulacao(int populacao) {
    this.populacao = populacao;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public float getAltitude() {
    return altitude;
  }
  public void setAltitude(float altitude) {
    this.altitude = altitude;
  }

  
}
