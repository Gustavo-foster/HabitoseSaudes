package com.example.habitossaude;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.habitossaude.model.Usuario;

@Dao
public interface UsuarioDao {

    @Insert
    void inserirUsuario(Usuario usuarios);

    // Método para buscar usuário por email e senha
    @Query("SELECT * FROM usuarios WHERE email =:email AND senha =:senha LIMIT 1")
    Usuario getUsuarioPorEmailESenha(String email, String senha);
}
