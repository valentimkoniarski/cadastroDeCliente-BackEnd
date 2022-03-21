package com.example.cadastrodecliente.config.security;

import com.example.cadastrodecliente.model.Usuario;
import com.example.cadastrodecliente.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Classe Responsavel em indicar para o Spring qual a logica de autenticação
// Mostra para o Spring que tipo de login é utilizado para o usuario acessar

@Service
public class AutenticaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);

        if (usuario.isPresent()) {
            return usuario.get();
        }

        throw new UsernameNotFoundException("Dados Inválidos");

    }

}
