import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LancaExcecoes {
    public static void main(String[] args) throws Exception {
        LancaExcecoes le = new LancaExcecoes();
        le.lerArquivo();
    }

    public void lerArquivo() throws FileNotFoundException, IOException{
        String arqEntrada = "arquivo.txt";
        BufferedReader arq = new BufferedReader(new FileReader(arqEntrada));
        String linha;
        while((linha = arq.readLine()) != null){
            System.out.println(linha + "\n");
        }
        arq.close();
    }
    
}