package com.Veridia.CidadeVeridiaOficial.controllers;

import com.Veridia.CidadeVeridiaOficial.dtos.UsuarioDTO;
import com.Veridia.CidadeVeridiaOficial.model.Usuario;
import com.Veridia.CidadeVeridiaOficial.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuario") //endpoint


public class UsuarioController {

    @Autowired
    UsuarioRepository repository; // instanciando com autowired (instancia sem burocracia)
    // MÉTODOS PARA BUSCA //
    @GetMapping
    public ResponseEntity getAll(){ //ResponseEntity
        List<Usuario> listUsuarios = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listUsuarios);}

    @GetMapping("/{id}")
    public ResponseEntity getByid(@PathVariable(value = "id") UUID id) {
        Optional usuario = repository.findById(id);
        if(usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.FOUND).body("Não existe usuário com esse ID");}
        return ResponseEntity.status(HttpStatus.FOUND).body(usuario.get());}

    @GetMapping("/email/{email}")
    public ResponseEntity getByEmail(@PathVariable(value = "email") String email) {
        Optional usuario = repository.findByEmail(email);
        if(usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.FOUND).body("Não existe usuário com esse email");}
        return ResponseEntity.status(HttpStatus.FOUND).body(usuario.get());}

    @GetMapping("/nome/{fullName}")
    public ResponseEntity getByfullName(@PathVariable(value = "fullName") String fullName) {
        Optional usuario = repository.findByfullName(fullName);
        if(usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.FOUND).body("Não existe usuário com esse nome");}
        return ResponseEntity.status(HttpStatus.FOUND).body(usuario.get());}

    @GetMapping("/role/{role}")
    public ResponseEntity getByrole(@PathVariable(value = "role") Usuario.Role role) {
        List<Usuario> usuarios = repository.findByrole(role);
        if(usuarios.isEmpty()){
            return ResponseEntity.status(HttpStatus.FOUND).body("Não existe ninguém cadastrado!");}
        return ResponseEntity.ok(usuarios);}

     // MÉTODOS PARA ADICIONAR//
    @PostMapping
    public ResponseEntity save(@RequestBody UsuarioDTO dto){
        var usuario = new Usuario();
        BeanUtils.copyProperties(dto,usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));}

    //MÉTODOS PARA DELETAR//
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") UUID id){
            Optional<Usuario> usuario = repository.findById(id);
            if(usuario.isEmpty()){
                return ResponseEntity.status(HttpStatus.FOUND).body("Não existe usuário com esse ID");}
            repository.delete(usuario.get());
            return ResponseEntity.status(HttpStatus.OK).body("Usuario Deletado");
    }
    //MÉTODOS PARA ATUALIZAR//
    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable(value = "id") UUID id, @RequestBody UsuarioDTO dto){
        Optional<Usuario> usuario = repository.findById(id);
        if(usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.FOUND).body("Não existe usuário com esse ID");}
        var usuarioModel = usuario.get();
        BeanUtils.copyProperties(dto, usuarioModel);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuarioModel));
    }

    @PutMapping("/nome/{fullName}")
    public ResponseEntity updateByName(@PathVariable(value = "fullName") String fullName, @RequestBody UsuarioDTO dto){
        Optional<Usuario> usuario = repository.findByfullName(fullName);
        if(usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.FOUND).body("Não existe usuário com esse Nome");}
        var usuarioModel = usuario.get();
        BeanUtils.copyProperties(dto, usuarioModel);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuarioModel));
    }

    @PutMapping("/email/{email}")
    public ResponseEntity updateByEmail(@PathVariable(value = "email") String email, @RequestBody UsuarioDTO dto){
        Optional<Usuario> usuario = repository.findByEmail(email);
        if(usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.FOUND).body("Não existe usuário com esse email");}
        var usuarioModel = usuario.get();
        BeanUtils.copyProperties(dto, usuarioModel);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuarioModel));
    }
}
