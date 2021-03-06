package com.crud.democrud.ServicesTest;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.repositories.UsuarioRepository;
import com.crud.democrud.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static  org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioServiceTest {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void testGuardarUsuario(){
        UsuarioModel usuarioModel=new UsuarioModel("aquaman","aqua@gmail.com",99);
        UsuarioModel usuarioModelRegistrado = usuarioRepository.save(usuarioModel);
        assertNotNull(usuarioModelRegistrado);
    }

    @Test
    public void testBuscarUsuarioPorId(){
        Long idBuscado=16L;
        Optional<UsuarioModel> usuarioModelBuscado = usuarioRepository.findById(idBuscado);
        assertThat(usuarioModelBuscado.get().getId()).isEqualTo(idBuscado);
    }

    @Test
    public void testListarUsuarios(){
        List<UsuarioModel> usuarioModelList=(List<UsuarioModel>) usuarioRepository.findAll();
        assertThat(usuarioModelList).size().isGreaterThan(0);
    }

    @Test
    public void testEliminarUsuario(){
        Long idBuscado=19L;
        usuarioRepository.deleteById(idBuscado);
        Optional usuarioModel= usuarioRepository.findById(idBuscado);
        assertThat(usuarioModel).isEmpty();
    }

    @Test
    public void testBuscarPorEmail(){
        String email ="peipto@hotmail.com";
        List<UsuarioModel> usuarioModelList=(List<UsuarioModel>) usuarioRepository.findByEmail(email);
        assertNotNull(usuarioModelList);
    }

    @Test
    public void testEliminarPorEmail(){
        String email ="q@q.q";
        //UsuarioModel usuarioModel=new UsuarioModel("aquaman","aqua@gmail.com",99);
        List<UsuarioModel> usuarioModelList=(List<UsuarioModel>) usuarioRepository.findByEmail(email);
        for (UsuarioModel usuario : usuarioModelList) {
            usuarioRepository.deleteById(usuario.getId());
            }
        List<UsuarioModel> newUsuarioModelList=(List<UsuarioModel>) usuarioRepository.findByEmail(email);
        assertThat(newUsuarioModelList).size().isEqualTo(0);
    }
}
