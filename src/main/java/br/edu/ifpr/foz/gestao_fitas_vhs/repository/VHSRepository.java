package br.edu.ifpr.foz.gestao_fitas_vhs.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.Category;
import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.VHS;
import java.util.Optional;

@Repository
public interface VHSRepository extends JpaRepository<VHS, Long> {
    
    Optional<VHS> findByCodebar(Integer codebar);

    List<VHS> findByTittleContainingIgnoreCase(String tittle);

    @Query("SELECT DISTINCT v FROM VHS v LEFT JOIN FETCH v.categories")
    List<VHS> buscarTodasComCategorias();

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.vhsList")
    List<Category> buscarComFitas();

    
}
