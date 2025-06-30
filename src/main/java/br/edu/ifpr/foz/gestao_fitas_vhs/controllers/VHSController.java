package br.edu.ifpr.foz.gestao_fitas_vhs.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.Usuario;
import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.VHS;
import br.edu.ifpr.foz.gestao_fitas_vhs.services.VHSService;
import jakarta.servlet.http.HttpSession;
import br.edu.ifpr.foz.gestao_fitas_vhs.services.UsuarioService;
import java.util.List;

@Controller
@RequestMapping("/vhs")
public class VHSController {

    @Autowired
    VHSService vhsService;

    @Autowired
    UsuarioService usuarioService;

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
            return "redirect:/vhs-list";
        } else {
            model.addAttribute("erro", "Usuário ou senha inválidos.");
            return "login";
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


}
