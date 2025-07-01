package br.edu.ifpr.foz.gestao_fitas_vhs.repository;

import org.springframework.stereotype.Repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    // Define any custom query methods if needed
    // For example:
    //List<Category> findByName(String categoryName);    

    
}
