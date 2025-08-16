package fiap.adj.fase3.tech_challenge_hospital.application.configs.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailService implements UserDetailsService {

    // Classe para definir como será o acesso aos dados dos usuários
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("usuario", "senha", new ArrayList<>());
    }
}
