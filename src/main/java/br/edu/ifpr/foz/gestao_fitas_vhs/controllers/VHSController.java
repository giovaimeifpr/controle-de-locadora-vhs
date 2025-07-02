package br.edu.ifpr.foz.gestao_fitas_vhs.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.TapeStatus;
import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.VHS;
import br.edu.ifpr.foz.gestao_fitas_vhs.services.VHSService;
import br.edu.ifpr.foz.gestao_fitas_vhs.services.CategoryService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.Category;

@Controller
@RequestMapping("/vhs")
public class VHSController {

    @Autowired
    VHSService vhsService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String findAll(@RequestParam(required = false) String titulo, Model model) {
        List<VHS> listaVHS;

        if (titulo != null && !titulo.isBlank()) {
            listaVHS = vhsService.findByTittle(titulo);
        } else {
            listaVHS = vhsService.findAll();
        }

        model.addAttribute("listaVHS", listaVHS);
        return "vhs-list";
    }   
        

    @GetMapping("/cadastro_fitas")
    public String telaCadastroFitas(Model model) {
        model.addAttribute("categorias", categoryService.findAll());
        return "cadastro_fitas";
    }

    @PostMapping("/cadastro_fitas")
    public String cadastrarFitas(
            @RequestParam Integer codebar,
            @RequestParam String tittle,
            @RequestParam String director,
            @RequestParam String imageUrl,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate registrationDate,
            @RequestParam TapeStatus status,
            @RequestParam List<Long> categories, // IDs das categorias
            Model model) {

        if (vhsService.findByCodeBar(codebar) != null) {
            model.addAttribute("erro", "Fita já cadastrada com esse código de barras.");
            return "cadastro_fitas";
        }

        VHS vhs = new VHS();
        vhs.setCodebar(codebar);
        vhs.setTittle(tittle);
        vhs.setDirector(director);
        vhs.setImageUrl(imageUrl);
        vhs.setRegistrationDate(registrationDate);
        vhs.setStatus(status);

        // Recupera as categorias pelo ID e adiciona ao Set
        Set<Category> categoriaSet = new HashSet<>(categoryService.buscarPorIds(categories));
        vhs.setCategories(categoriaSet);

        vhsService.salvarFita(vhs);

        return "redirect:/vhs";
    }   
    

    @GetMapping("/editar/{id}")
    public String telaEditar(@PathVariable Long id, Model model) {
        VHS fita = vhsService.findById(id);
        model.addAttribute("fita", fita);
        model.addAttribute("categorias", categoryService.findAll());
        model.addAttribute("statuses", TapeStatus.values());
        return "editar_fita";
    }

    @PostMapping("/editar")
    public String editarFita(
        @RequestParam Long id,
        @RequestParam Integer codebar,
        @RequestParam String tittle,
        @RequestParam String director,
        @RequestParam String imageUrl,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate registrationDate,
        @RequestParam TapeStatus status,
        @RequestParam List<Long> categories) {

        VHS fita = vhsService.findById(id);
        fita.setCodebar(codebar);
        fita.setTittle(tittle);
        fita.setDirector(director);
        fita.setImageUrl(imageUrl);
        fita.setRegistrationDate(registrationDate);
        fita.setStatus(status);

        Set<Category> categoriaSet = new HashSet<>(categoryService.buscarPorIds(categories));
        fita.setCategories(categoriaSet);

        vhsService.salvarFita(fita);

        return "redirect:/vhs";
    }

    @GetMapping("/excluir/{id}")
    public String excluirFita(@PathVariable Long id) {
        VHS fita = vhsService.findById(id);
        if (fita != null) {
            vhsService.deletarFita(id);
        }
        return "redirect:/vhs";
    }    

}
