import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoClientePj {
    private Connection conn;
    private Statement st;

    private void conectar() {
        try {
            this.conn = GerenciadorConexao.pegarConexao();
            st = conn.createStatement();
        } catch (ClassNotFoundException e1) {
            System.out.println(e1.getMessage());
        } catch (SQLException e2) {
            System.out.println(e2.getMessage());
        }
    }

    private void desconectar() {
        try {
            st.close();
            conn.close();
        } catch (SQLException e2) {
            System.out.println("Erro: " + e2.getMessage());
        }
    }

    public boolean inserir(ClientePj cPj) {
        boolean resultado = false;
        try {
            this.conectar();
            PreparedStatement pst = conn.prepareStatement("INSERT INTO tb_clientes_pj (cod_cli_pj, nome, cnpj)"
                    + "VALUES (NULL, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, cPj.getNome());
            pst.setString(2, cPj.getCnpj());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            int idCliente = 0;
            if (rs.next()) {
                idCliente = rs.getInt(1);
                PreparedStatement pstEnd = conn
                        .prepareStatement("INSERT INTO tb_enderecos_pj (cod_end, cod_cli_pj, rua, numero, bairro, cep)"
                                + "VALUES (NULL, ?, ?, ?, ?, ?);");
                pstEnd.setInt(1, idCliente);
                pstEnd.setString(2, cPj.getEnderecoPj().getRua());
                pstEnd.setInt(3, cPj.getEnderecoPj().getNumero());
                pstEnd.setString(4, cPj.getEnderecoPj().getBairro());
                pstEnd.setString(5, cPj.getEnderecoPj().getCep());

                pstEnd.executeUpdate();
                resultado = true;
            }

        } catch (Exception e) {
            System.out.println("Erro ao inserir registro: "
                    + e.getMessage());
        } finally {
            this.desconectar();
        }
        return resultado;
    }

    public ArrayList<ClientePj> buscarTodos() {
        ArrayList<ClientePj> resultados = new ArrayList<ClientePj>();

        try {
            this.conectar();
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM tb_clientes_pj as c, tb_enderecos_pj as e WHERE c.cod_cli_pj = e.cod_cli_pj ORDER BY c.nome;");
            while(rs.next()) {
                ClientePj cPj = new ClientePj();
                cPj.setCodigoClientePj(rs.getInt("cod_cli_pj"));
                cPj.setNome(rs.getString("nome"));
                cPj.setCnpj(rs.getString("cnpj"));

                EnderecoPj endPj = new EnderecoPj();
                endPj.setRua(rs.getString("rua"));
                endPj.setNumero(rs.getInt("numero"));
                endPj.setBairro(rs.getString("bairro"));
                endPj.setCep(rs.getString("cep"));

                cPj.setEnderecoPj(endPj);
                resultados.add(cPj);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return resultados;
    }

    public int excluir(int cod) {
        int qtde = 0;
        try {
            this.conectar();
            PreparedStatement pstEnd = conn.prepareStatement("DELETE FROM tb_enderecos_pj WHERE cod_cli_pj = ?");
            pstEnd.setInt(1, cod);
            pstEnd.executeUpdate();

            PreparedStatement pstCli = conn.prepareStatement(
                "DELETE FROM tb_clientes_pj WHERE cod_cli_pj = ? ");
            pstCli.setInt(1, cod);
            pstCli.executeUpdate();

            qtde = pstCli.getUpdateCount();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return qtde;
    }

    public ClientePj consultar(int cod){
        ClientePj cPj = null;
        EnderecoPj endPj = null;
        try {
            this.conectar();
            ResultSet rs = st.executeQuery("SELECT * FROM tb_clientes_pj as c, tb_enderecos_pj as e WHERE c.cod_cli_pj = e.cod_cli_pj AND c.cod_cli_pj = " + cod + ";");
            if(rs.next()){
                cPj = new ClientePj();
                cPj.setCodigoClientePj(rs.getInt("cod_cli_pj"));
                cPj.setNome(rs.getString("nome"));
                cPj.setCnpj(rs.getString("cnpj"));

                endPj = new EnderecoPj();
                endPj.setCodigo(rs.getInt("cod_end"));
                endPj.setRua(rs.getString("rua"));
                endPj.setNumero(rs.getInt("numero"));
                endPj.setBairro(rs.getString("bairro"));
                endPj.setCep(rs.getString("cep"));

                cPj.setEnderecoPj(endPj);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally{
            this.desconectar();
        }
        return cPj;
    }  
    
    public int alterar(ClientePj cPj){
        int qtde = 0;
        try {
            this.conectar();
            PreparedStatement pstCli = conn.prepareStatement("UPDATE tb_clientes_pj SET nome = ?, cnpj = ? WHERE cod_cli_pj = ?;");
            pstCli.setString(1, cPj.getNome());
            pstCli.setString(2, cPj.getCnpj());
            pstCli.setInt(3, cPj.getCodigoClientePj());
  
            PreparedStatement pstEnd = conn.prepareStatement("UPDATE tb_enderecos_pj SET rua = ?, numero = ?, bairro = ?, cep = ? WHERE cod_cli_pj = ?;");
            pstEnd.setString(1, cPj.getEnderecoPj().getRua());
            pstEnd.setInt(2, cPj.getEnderecoPj().getNumero());
            pstEnd.setString(3, cPj.getEnderecoPj().getBairro());
            pstEnd.setString(4, cPj.getEnderecoPj().getCep());

            qtde = pstCli.getUpdateCount();
           
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally{
            this.desconectar();
        }
        return qtde;  
    }

}
