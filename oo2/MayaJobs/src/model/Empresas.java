package model;

public class Empresas {
    private int codigo_emp;
    private String nome;
    private String descricao;
    private int qtdeFuncionarios;
    
    public int getCodigo_emp() {
        return codigo_emp;
    }
    public void setCodigo_emp(int codigo_emp) {
        this.codigo_emp = codigo_emp;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public int getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }
    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    @Override
    public String toString(){
        return "\n- Nome: " + this.nome + "\n- Descrição: " + this.descricao + "\n- Quantidade de funcionários: " + this.qtdeFuncionarios;
    }
    
}