package br.edu.ifpr.foz.gestao_fitas_vhs.services;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.Category;
import br.edu.ifpr.foz.gestao_fitas_vhs.repository.CategoryRepository;
 

@Service
public class CategoryService {
    
    @Autowired
    CategoryRepository categoryRepository;
    
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    
    public List<Category> buscarPorIds(Collection<Long> ids) {
        return categoryRepository.findAllById(ids);
    }

    public Category buscarPorNome(String nome) {
        return categoryRepository.findAll().stream()
                .filter(c -> c.getCategoryName().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public Category buscarPorId(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
    public Category salvar(Category category) {
        return categoryRepository.save(category);   
    }
    public void excluir(Long id) {
        categoryRepository.deleteById(id);
    }
   


}
