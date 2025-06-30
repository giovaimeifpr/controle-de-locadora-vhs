package br.edu.ifpr.foz.gestao_fitas_vhs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.Usuario;
import br.edu.ifpr.foz.gestao_fitas_vhs.repository.UsuarioRepository;


@Service
public class UsuarioService {
    
    @Autowired 
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void salvarUsuario(Usuario usuario) {
        String senhaCriptografada = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        usuarioRepository.save(usuario);
    }

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && encoder.matches(senha, usuario.getSenha())) {
            return usuario;
        }
        return null;
    }

    public Usuario findByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }
}
