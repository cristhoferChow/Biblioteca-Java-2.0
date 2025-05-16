package com.projeto.Java.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class LoginSessao{

    private static final String ATRIBUTO_ADMIN = "isAdmin";

    @GetMapping("/login")
    public String exibirLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String processarLogin(@RequestParam String usuario,
                                 @RequestParam String senha,
                                 HttpSession session,
                                 Model model) {
        if ("admin".equals(usuario) && "123".equals(senha)) {
            session.setAttribute(ATRIBUTO_ADMIN, true);
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("erro", "Usuário ou senha inválidos.");
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        Boolean autenticado = (Boolean) session.getAttribute(ATRIBUTO_ADMIN);
        if (Boolean.TRUE.equals(autenticado)) {
            return "dashboard";
        } else {
            return "acesso-negado";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}

