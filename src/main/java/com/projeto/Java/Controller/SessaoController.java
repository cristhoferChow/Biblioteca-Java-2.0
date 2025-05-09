package com.projeto.Java.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessao")
public class SessaoController {

    private static final String ATRIBUTO_SESSAO = "meuAtributo";

    @GetMapping("/guardar/{valor}")
    public String guardarNaSessao(@PathVariable String valor, HttpSession session) {
        session.setAttribute(ATRIBUTO_SESSAO, valor);
        return "Valor '" + valor + "' guardado na sessão.";
    }

    @GetMapping("/obter")
    public String obterDaSessao(HttpSession session) {
        String valor = (String) session.getAttribute(ATRIBUTO_SESSAO);
        if (valor != null) {
            return "Valor obtido da sessão: " + valor;
        } else {
            return "Nenhum valor encontrado na sessão.";
        }
    }

    @GetMapping("/remover-atributo")
    public String removerAtributo(HttpSession session) {
        session.removeAttribute(ATRIBUTO_SESSAO);
        return "Atributo removido da sessão.";
    }

    @GetMapping("/remover")
    public String removerSessao(HttpSession session) {
        session.invalidate();
        return "Sessão invalidada com sucesso.";
    }
}
 