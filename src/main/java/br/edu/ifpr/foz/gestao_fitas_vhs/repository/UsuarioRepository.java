package br.edu.ifpr.foz.gestao_fitas_vhs.repository;

import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
} 
