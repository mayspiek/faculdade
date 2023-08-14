/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.table.AbstractTableModel;
import model.VagasEmpregos;
import java.util.ArrayList;
/**
 *
 * @author mayara
 */
public class MayaJobsTableModel extends AbstractTableModel {
    public static final int COL_COD_VAGA = 0;
    public static final int COL_CARGO = 1;
    public static final int COL_REQUISITOS = 2;
    public static final int COL_SALARIO = 3;
    public static final int COL_COD_EMP = 4;
    public static final int COL_NOME = 5;
    public static final int COL_QTDE_FUNC = 6;
    public static final int COL_DESCRICAO = 7;
    
    public ArrayList<VagasEmpregos> listaVagas;
     
    public MayaJobsTableModel(ArrayList<VagasEmpregos> vagasEmp){
        this.listaVagas = vagasEmp;
    }
    @Override
    public Object getValueAt(int linha, int coluna){
        VagasEmpregos vagas = listaVagas.get(linha);
        
        Object conteudo = "";
        if(coluna == COL_COD_VAGA){conteudo = vagas.getCod_vaga();}
        if(coluna == COL_CARGO){conteudo = vagas.getCargo();}
        if(coluna == COL_REQUISITOS){conteudo = vagas.getRequisitos();}
        if(coluna == COL_SALARIO){conteudo = vagas.getSalario();}
        if(coluna == COL_COD_EMP){conteudo = vagas.getEmpresa().getCodigo_emp();}
        if(coluna == COL_NOME){conteudo = vagas.getEmpresa().getNome();}
        if(coluna == COL_QTDE_FUNC){conteudo = vagas.getEmpresa().getQtdeFuncionarios();}
        if(coluna == COL_DESCRICAO){conteudo = vagas.getEmpresa().getDescricao();
        }
        
        return conteudo;
    }
    
    @Override
    public int getColumnCount(){
        return 8;
    }
    
    @Override
    public int getRowCount(){
        return listaVagas.size();
    }
    
    @Override
    public String getColumnName(int coluna){
        String nome = "";
        if(coluna == COL_COD_VAGA){nome = "Cód Vaga";}
        if(coluna == COL_CARGO){nome = "Cargo";}
        if(coluna == COL_REQUISITOS){nome = "Requisitos";}
        if(coluna == COL_SALARIO){nome = "Salário";}
        if(coluna == COL_COD_EMP){nome = "Cód Emp";}
        if(coluna == COL_NOME){nome = "Nome";}
        if(coluna == COL_QTDE_FUNC){nome = "Qtd Func";}
        if(coluna == COL_DESCRICAO){nome = "Descrição";}

        return nome;
    }    
}
