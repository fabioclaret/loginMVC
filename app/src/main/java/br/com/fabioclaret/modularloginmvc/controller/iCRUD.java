package br.com.fabioclaret.modularloginmvc.controller;

import java.util.List;

public interface iCRUD <T> {
    public boolean incluir(T object);
    public boolean alterar(T object);
    public boolean deletar(T object);
    public List<T> listar();
}
