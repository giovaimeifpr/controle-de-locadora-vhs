package br.edu.ifpr.foz.gestao_fitas_vhs.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import java.util.List;
import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.VHS;

@Repository
public interface VHSRepository extends JpaRepository<VHS, Long> {
    // public List<VHS> findByTitulo(String titulo); 
    // public List<VHS> findByDiretor(String diretor); 
    // public Integer countByDisponivelTrue(); 

    // @Query("SELECT v FROM VHS v WHERE v.titulo = :titulo")
    // public List<VHS> buscarPorTitulo(@Param("titulo") String titulo);

}
