package br.edu.ifpr.foz.gestao_fitas_vhs.filters;

import java.io.IOException;
import org.springframework.stereotype.Component;
import jakarta.servlet.Filter;

import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AutenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        boolean acessoLiberado = 
            uri.startsWith("/vhs/login") || 
            uri.startsWith("/vhs/cadastro") ||
            uri.startsWith("/css") ||
            uri.startsWith("/js") ||
            uri.startsWith("/images");

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");

        if (usuario != null || acessoLiberado) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + "/vhs/login");
        }
    }
}
