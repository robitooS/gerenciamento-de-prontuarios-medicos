/**
 * @author higor.robinn on 19/01/2025.
 */

package br.org.santacasa.prontuario_api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class ProntuarioController {

    @GetMapping("/api/prontuario")
    public String teste () {
        return "Olá";
    }

    @GetMapping
    @ResponseBody
    public String teste2 () {
        return "Olá";
    }
}
