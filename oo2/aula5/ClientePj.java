public class ClientePj {
    private int codigoClientePj;
    private String nome, cnpj;
    private EnderecoPj enderecoPj;

    public int getCodigoClientePj() {
        return codigoClientePj;
    }

    public void setCodigoClientePj(int codigoCliente) {
        this.codigoClientePj = codigoCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public EnderecoPj getEnderecoPj() {
        return enderecoPj;
    }

    public void setEnderecoPj(EnderecoPj enderecoPj) {
        this.enderecoPj = enderecoPj;
    }

}
