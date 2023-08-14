package model;

import java.sql.*;
import java.util.ArrayList;

public class DaoVagas {
    private Connection conn;
    private Statement st;

    private void conectar() {
        try {
            this.conn = GerenciadorConexao.pegarConexao();
            st = conn.createStatement();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void desconectar() {
        try {
            st.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexao: "
                    + e.getMessage());
        }
    }

    public boolean inserirBanco(VagasEmpregos ve) {
        boolean resultado = false;
        try {
            this.conectar();
            PreparedStatement pst = conn.prepareStatement(
                    "INSERT INTO tb_vagas(cod_vaga, cargo, requisitos, salario) VALUES (NULL, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, ve.getCargo());
            pst.setString(2, ve.getRequisitos());
            pst.setFloat(3, ve.getSalario());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            int idVaga = 0;

            if (rs.next()) {
                idVaga = rs.getInt(1);
                PreparedStatement pstEmp = conn.prepareStatement(
                        "INSERT INTO tb_empresas(cod_emp, cod_vaga, nome, descricao, qtdeFuncionarios) VALUES (NULL, ?, ?, ?, ?)");
                
                
                pstEmp.setInt(1, idVaga);
                pstEmp.setString(2, ve.getEmpresa().getNome());
                pstEmp.setString(3, ve.getEmpresa().getDescricao());
                pstEmp.setInt(4, ve.getEmpresa().getQtdeFuncionarios());
                pstEmp.executeUpdate();
                resultado = true;
            }

        } catch (SQLException e) {
            System.out.println("Erro: "+ e.getMessage());
        } finally {
            this.desconectar();
        }
        return resultado;
    }

    public ArrayList<VagasEmpregos> buscarTodos() {
        ArrayList<VagasEmpregos> resultados = new ArrayList<>();
        try {
            this.conectar();
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM tb_vagas as v, tb_empresas as e where v.cod_vaga =  e.cod_vaga order by v.cod_vaga;");

            while (rs.next()) {
                VagasEmpregos vagas = new VagasEmpregos();
                vagas.setCod_vaga(rs.getInt("cod_vaga"));
                vagas.setCargo(rs.getString("cargo"));
                vagas.setRequisitos(rs.getString("requisitos"));
                vagas.setSalario(rs.getFloat("salario"));

                Empresas emp = new Empresas();
                emp.setCodigo_emp(rs.getInt("cod_emp"));
                emp.setNome(rs.getString("nome"));
                emp.setDescricao(rs.getString("descricao"));
                emp.setQtdeFuncionarios(rs.getInt("qtdeFuncionarios"));

                vagas.setEmpresa(emp);
                resultados.add(vagas);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            this.desconectar();
        }

        return resultados;
    }

    public VagasEmpregos consultar(int cod){
        VagasEmpregos vagas = new VagasEmpregos();
        try {
            this.conectar();
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM tb_vagas as v, tb_empresas as e where v.cod_vaga =  e.cod_vaga and v.cod_vaga = ?;");
            
            pst.setInt(1, cod);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                vagas.setCod_vaga(rs.getInt("cod_vaga"));
                vagas.setCargo(rs.getString("cargo"));
                vagas.setRequisitos(rs.getString("requisitos"));
                vagas.setSalario(rs.getFloat("salario"));

                Empresas emp = new Empresas();
                emp.setCodigo_emp(rs.getInt("cod_emp"));
                emp.setNome(rs.getString("nome"));
                emp.setDescricao(rs.getString("descricao"));
                emp.setQtdeFuncionarios(rs.getInt("qtdeFuncionarios"));

                vagas.setEmpresa(emp);
            }
    
        } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
        } finally {
            this.desconectar();
        }

        return vagas;
    }

    public int alterar(VagasEmpregos vagas){
        int qtde = 0;
        try {
            this.conectar();
            PreparedStatement pst = conn.prepareStatement("UPDATE tb_vagas SET cargo = ?, requisitos = ?, salario = ? WHERE cod_vaga = ?;");

            pst.setString(1, vagas.getCargo());
            pst.setString(2, vagas.getRequisitos());
            pst.setFloat(3, vagas.getSalario());
            pst.setInt(4, vagas.getCod_vaga());
            pst.executeUpdate();

            qtde = pst.getUpdateCount();
            if(qtde > 0){
                PreparedStatement pstEmp = conn.prepareStatement("UPDATE tb_empresas SET nome = ?, descricao = ?, qtdeFuncionarios = ? WHERE cod_vaga = ?;");

                pstEmp.setString(1, vagas.getEmpresa().getNome());
                pstEmp.setString(2, vagas.getEmpresa().getDescricao());
                pstEmp.setInt(3, vagas.getEmpresa().getQtdeFuncionarios());
                pstEmp.setInt(4, vagas.getCod_vaga());
                pstEmp.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return qtde;
    }

    public int excluir(int cod){
        int qtde = 0;
        try {
            this.conectar();
            //excluir empresa primeiro por causa da constraint
            PreparedStatement pstEmp = conn.prepareStatement("DELETE FROM tb_empresas WHERE cod_vaga = ?;");

            pstEmp.setInt(1, cod);
            pstEmp.executeUpdate();

            PreparedStatement pst = conn.prepareStatement("DELETE FROM tb_vagas WHERE cod_vaga = ?;");

            pst.setInt(1, cod);
            pst.executeUpdate();


            qtde = pst.getUpdateCount();
        } catch (Exception e) {
            System.out.println("Erro ao excluir: " + e.getMessage()); 
        } finally {
            this.desconectar();
        }
        return qtde;
    }
    
    // método que confere no banco caso o nome da empresa já existe e dessa forma, adiciona a vaga na mesma empresa
    public ArrayList<VagasEmpregos> buscarTodosFiltro(String campo, String filtro){
        ArrayList<VagasEmpregos> resultados = new ArrayList<>();
        
        if(!campo.equals("cargo") && !campo.equals("nome") && !campo.equals("requisitos")){
            return resultados;
        }
        
        try {
            this.conectar();
            ResultSet rs = st.executeQuery(
               "SELECT * FROM tb_vagas as v join tb_empresas as e WHERE v.cod_vaga = e.cod_vaga AND "
                    + campo + " LIKE '%" + filtro 
                    + "%' ORDER BY cargo;");
            
            System.out.println(campo);
            System.out.println(filtro);
                    
            while(rs.next()){
                VagasEmpregos vEmp = new VagasEmpregos();
                vEmp.setCod_vaga(rs.getInt("cod_vaga"));
                vEmp.setCargo(rs.getString("cargo"));
                vEmp.setRequisitos(rs.getString("requisitos"));
                vEmp.setSalario(rs.getFloat("salario"));
                
                Empresas emp = new Empresas();
                emp.setCodigo_emp(rs.getInt("cod_emp"));
                emp.setNome(rs.getString("nome"));
                emp.setQtdeFuncionarios(rs.getInt("qtdeFuncionarios"));
                emp.setDescricao(rs.getString("descricao"));
                
                vEmp.setEmpresa(emp);
                
                resultados.add(vEmp);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }finally{
            this.desconectar();
        }
        return resultados;
    }
    
}
