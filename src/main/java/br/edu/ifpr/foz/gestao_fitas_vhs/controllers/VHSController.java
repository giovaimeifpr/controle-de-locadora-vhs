package br.edu.ifpr.foz.gestao_fitas_vhs.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.TapeStatus;
import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.Usuario;
import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.VHS;
import br.edu.ifpr.foz.gestao_fitas_vhs.services.VHSService;
import jakarta.servlet.http.HttpSession;
import br.edu.ifpr.foz.gestao_fitas_vhs.services.UsuarioService;
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
    UsuarioService usuarioService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String findAll(Model model) {
        
        List<VHS> listaVHS = vhsService.findAll();
        model.addAttribute("listaVHS", listaVHS);
        return "vhs-list";         
    }

     @GetMapping("/login")
    public String telaLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String senha,
            HttpSession session,
            Model model) {

        Usuario usuario = usuarioService.autenticar(email, senha);

        if (usuario != null) {
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/vhs";
        } else {
            model.addAttribute("erro", "Usuário ou senha inválidos.");
            return "redirect:/login";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/cadastro")
    public String telaCadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha,
            Model model) {

        if (usuarioService.findByEmail(email) != null) {
            model.addAttribute("erro", "E-mail já cadastrado.");
            return "cadastro";
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        usuarioService.salvarUsuario(usuario);

        return "redirect:/login";
    }    

    @GetMapping("/cadastro_fitas")
    public String telaCadastroFitas(Model model) {
        model.addAttribute("categorias", categoryService.findAll());
        return "cadastro_fitas";
    }

    @PostMapping("/cadastro_fitas")
    public String cadastrarFitas(
            @RequestParam String title,
            @RequestParam String director,
            @RequestParam String imageUrl,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate registrationDate,
            @RequestParam TapeStatus status,
            @RequestParam List<Long> categories, // IDs das categorias
            Model model) {

        if (vhsService.findByTittle(title) != null) {
            model.addAttribute("erro", "Fita já cadastrada.");
            return "cadastro_fitas";
        }

        VHS vhs = new VHS();
        vhs.setTitle(title);
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

        return "redirect:/vhs/categorias";
    }
    
    @GetMapping("/debug")
    public String debug(Model model) {
        List<VHS> fitas = vhsService.findAll();
        for (VHS v : fitas) {
            System.out.println("Fita: " + v.getTitle());
            System.out.println("Categorias:");
            for (Category c : v.getCategories()) {
                System.out.println(" - " + c.getCategoryName());
            }
        }
        model.addAttribute("listaVHS", fitas);
        return "vhs-list";
    }

}
