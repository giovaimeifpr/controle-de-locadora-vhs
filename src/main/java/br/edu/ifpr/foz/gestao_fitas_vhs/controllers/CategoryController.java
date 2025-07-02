package br.edu.ifpr.foz.gestao_fitas_vhs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.Category;
import br.edu.ifpr.foz.gestao_fitas_vhs.services.CategoryService;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    
    @GetMapping("/categorias")
    public String CadastrarCategorias(Model model) {
        model.addAttribute("categorias", categoryService.findAll());
        return "categorias";
    }
    
    @PostMapping("/categorias")
    public String cadastrarCategoria(
            @RequestParam String name,
            Model model) {

        if (categoryService.buscarPorNome(name) != null) {
            model.addAttribute("erro", "Categoria já cadastrada.");
            return "categorias";
        }

        Category category = new Category();
        category.setCategoryName(name);

        categoryService.salvar(category);

        return "redirect:/categorias";
    }

    @GetMapping("/categorias_deletar/{id}")
    public String deletarCategoria(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean sucesso = categoryService.excluir(id);
        if (!sucesso) {
            redirectAttributes.addFlashAttribute("erro", "Categoria está associada a uma ou mais fitas e não pode ser deletada.");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Categoria deletada com sucesso.");
        }
        return "redirect:/categorias";  
    }

    @GetMapping("/categorias_editar/{id}")
    public String editarCategoria(@PathVariable Long id, Model model) {
        Category category = categoryService.buscarPorId(id);
        if (category != null) {
            model.addAttribute("categoria", category);
            return "categorias_editar"; 
        } else {
            model.addAttribute("erro", "Categoria não encontrada.");
            return "redirect:/categorias"; 
        }   
    }

    @PostMapping("/categorias_editar")
    public String atualizarCategoria(
            @RequestParam Long id,
            @RequestParam String name,
            Model model) {
        Category category = categoryService.buscarPorId(id);
        if (category != null) {
            category.setCategoryName(name);
            categoryService.salvar(category);
            return "redirect:/categorias"; 
        } else {
            model.addAttribute("erro", "Categoria não encontrada.");
            return "categorias_editar"; 
        }
    }
    
}
