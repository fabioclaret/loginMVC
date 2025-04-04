package br.com.fabioclaret.modularloginmvc.datamodel;

import android.util.Log;

public class UsuarioDataModel {
    /*
    Modelo objeto relacional
    Toda classe DataModel tem essa estrutura?
    1 - Atributo nome da tabela
    2 - Atributos um para cada campo da tabela
    3 - Variavel com Query (consulta) para criar a tabela no DB
    4 - Metodo para gerar o script da criacao da tabela
     */

    // 1
    public static final String TABELA = "usuarios";

    // 2
    public static final String ID     = "id";
    public static final String NOME   = "nome";
    public static final String EMAIL   = "email";
    public static final String SENHA   = "senha";

    // 3
    public static String consultaCriarTabela = "";

    // 4
    public static String criarTabela(){
        consultaCriarTabela += "CREATE TABLE " + TABELA + "(";
        consultaCriarTabela += ID    + " integer primary key autoincrement, ";
        consultaCriarTabela += NOME  + " text, ";
        consultaCriarTabela += EMAIL + " text,";
        consultaCriarTabela += SENHA + " text";
        consultaCriarTabela += ")";

        Log.i("TAG", "criarTabela: " + consultaCriarTabela);

        return consultaCriarTabela;
    }


}
