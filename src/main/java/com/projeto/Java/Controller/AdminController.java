package com.projeto.Java.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final String ATRIBUTO_ADMIN = "isAdmin";

    // Simulação de login: usuário = admin, senha = 123
    @GetMapping("/login")
    public String login(@RequestParam String usuario, @RequestParam String senha, HttpSession session) {
        if ("admin".equals(usuario) && "123".equals(senha)) {
            session.setAttribute(ATRIBUTO_ADMIN, true);
            return "Login de administrador realizado com sucesso.";
        } else {
            return "Usuário ou senha inválidos.";
        }
    }

    // Endpoint protegido: só acessa se estiver logado como admin
    @GetMapping("/painel")
    public String painelAdmin(HttpSession session) {
        Boolean autenticado = (Boolean) session.getAttribute(ATRIBUTO_ADMIN);
        if (Boolean.TRUE.equals(autenticado)) {
            return "Bem-vindo ao painel do administrador!";
        } else {
            return "Acesso negado. Faça login como administrador.";
        }
    }

    // Logout do administrador
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logout realizado com sucesso. Sessão encerrada.";
    }
}

