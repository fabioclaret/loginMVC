package br.com.fabioclaret.modularloginmvc.controller;

import android.content.ContentValues;
import android.content.Context;

import java.util.Collections;
import java.util.List;

import br.com.fabioclaret.modularloginmvc.datamodel.UsuarioDataModel;
import br.com.fabioclaret.modularloginmvc.datasource.AppDataBase;
import br.com.fabioclaret.modularloginmvc.model.Usuario;

public class UsuarioController extends AppDataBase implements iCRUD <Usuario>{
    ContentValues dadosDoUsuario;

    public UsuarioController(Context context) {
        super(context);
    }

    public boolean usuario(String userName){
        return chkUser(userName);
    }

    public boolean usuarioSenha(String userName , String password){
        return chkUserPassword(userName, password);
    }

    @Override
    public boolean incluir(Usuario object) {
        dadosDoUsuario = new ContentValues();
        dadosDoUsuario.put( UsuarioDataModel.NOME, object.getNome() );
        dadosDoUsuario.put( UsuarioDataModel.EMAIL, object.getEmail() );
        dadosDoUsuario.put( UsuarioDataModel.SENHA, object.getSenha() );

        return insert(UsuarioDataModel.TABELA, dadosDoUsuario);
    }

    @Override
    public boolean alterar(Usuario object) {
        return false;
    }
    @Override
    public boolean deletar(Usuario object) {
        return false;
    }

    @Override
    public List<Usuario> listar() {
        return Collections.emptyList();
    }

}
