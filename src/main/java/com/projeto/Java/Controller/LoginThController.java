package com.projeto.Java.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginThController {

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        String valor = (String) session.getAttribute("meuAtributo");
        model.addAttribute("valorSessao", valor);
        return "index";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam String valor, HttpSession session) {
        session.setAttribute("meuAtributo", valor);
        return "redirect:/";
    }
}


