package Lexus.Score.dev.Controller;

import Lexus.Score.dev.Dto.AuthenticationDto;
import Lexus.Score.dev.Dto.LoginResponseDto;
import Lexus.Score.dev.Dto.RegisterDto;
import Lexus.Score.dev.Entity.User.UserEntity;
import Lexus.Score.dev.Infra.Security.TokenService;
import Lexus.Score.dev.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto request) {
        //Verificando se tem usuário com esse login
        Optional<UserEntity> user = this.userRepository.findByLogin(request.login());
        // Se existir irei criar o user com a senha criptografada e tentar autenticar
        if (user != null ) {
            var usernamePassword = new UsernamePasswordAuthenticationToken(request.login(), request.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = this.tokenService.generateToken((UserEntity) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDto(user.get().getUsername(),token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto request) {
        if(this.userRepository.findByLogin(request.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
        UserEntity user = new UserEntity(request.login(),encryptedPassword,request.role());
        this.userRepository.save(user);
        return ResponseEntity.ok().build();
    }

}
