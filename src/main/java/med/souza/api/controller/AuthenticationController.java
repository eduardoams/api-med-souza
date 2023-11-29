package med.souza.api.controller;

import jakarta.validation.Valid;
import med.souza.api.domain.authentication.AuthenticationData;
import med.souza.api.domain.user.User;
import med.souza.api.infra.security.DataTokenJWT;
import med.souza.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DataTokenJWT> login(@RequestBody @Valid AuthenticationData data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication auth = manager.authenticate(authToken);

        var tokenJWT = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }
}
