/**
 * @author higor.robinn on 27/02/2025.
 */

package br.org.santacasa.prontuario_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
public class AuthController {

    @ResponseBody
    @GetMapping("/login")
    public String teste() {
        return "teste";
    }
}
