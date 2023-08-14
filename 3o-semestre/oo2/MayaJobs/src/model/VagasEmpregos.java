package model;

public class VagasEmpregos {
    private int cod_vaga;
    private String cargo;
    private String requisitos;
    private Float salario;
    private Empresas empresa;

    public int getCod_vaga() {
        return cod_vaga;
    }
    public void setCod_vaga(int cod_vaga) {
        this.cod_vaga = cod_vaga;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getRequisitos() {
        return requisitos;
    }
    public void setRequisitos(String Requisitos) {
        this.requisitos = Requisitos;
    }
    public Float getSalario() {
        return salario;
    }
    public void setSalario(Float salario) {
        this.salario = salario;
    }
    public Empresas getEmpresa() {
        return empresa;
    }
    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString(){
        return "\n----[VAGA]----"+ "\n- Código Vaga: " + this.cod_vaga + "\n- Cargo: " + this.cargo + "\n- Requisitos: " + this.requisitos + "\n- Salario: " + this.salario + "\n[EMPRESA]: " + this.empresa.toString() ;
    }

    
}
