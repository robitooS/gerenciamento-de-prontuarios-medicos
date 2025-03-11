/**
 * @author higor.robinn on 01/03/2025.
 */

package br.org.santacasa.prontuario_api.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.err.println("Erro de autenticação: " + authException.getMessage());

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Erro: Acesso não autorizado");
    }
}
