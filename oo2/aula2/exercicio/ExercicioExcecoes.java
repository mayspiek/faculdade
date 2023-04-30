import java.time.LocalDate; 
import java.time.format.DateTimeFormatter; 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.format.DateTimeParseException;
import java.io.InputStreamReader;

public class ExercicioExcecoes {
    DateTimeFormatter formato;
    public static void main(String[] args) {
        ExercicioExcecoes ee = new ExercicioExcecoes();
        ee.executar();
    }

    public void executar(){
        try {
            BufferedReader arqReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Informe o nome do arquivo de pedidos: ");
            processarArquivo(arqReader.readLine());
        }catch (ArqVazioException e1){    
            System.out.println("Erro:" + e1.getMessage());
        }catch (FileNotFoundException e2){    
            System.out.println("Erro: Arquivo não encontrado.");
        }catch (DateTimeParseException e3){
            System.out.println("Erro: O arquivo contém uma data inválida. Por favor verifique e execute novamente.");
        } catch (Exception e4) {//Tem que ser a última, por ser a mais genérica e capturar qualquer erro
            System.out.println("Ocorreu um erro inesperado. Entre em contato com o suporte.");
            //Para descobrir o tipo da exceção
            //System.out.println(e4);
        }   
        System.out.println("asd");     
    }

    public void processarArquivo(String arqEntrada) throws Exception{//throws FileNotFoundException, ArqVazioException, DateTimeParseException, Exception
        BufferedReader arq = new BufferedReader(new FileReader(arqEntrada));
        String linha;
        int qtLinhas = 0;
        formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while((linha = arq.readLine()) != null){
            qtLinhas++;
            String[] vetLinha = linha.split(";");
            System.out.println("Cliente: " + vetLinha[0] + " / Data limite entrega: " + calcularEntrega(vetLinha[1]));
        }
        arq.close();

        if (qtLinhas == 0) {
            throw new ArqVazioException();
        }
    }

    private String calcularEntrega(String dtPedido) throws DateTimeParseException{
        LocalDate minhaData = LocalDate.parse(dtPedido, formato);
        minhaData = minhaData.plusDays(7);
        return(minhaData.format(formato));   
    }
}