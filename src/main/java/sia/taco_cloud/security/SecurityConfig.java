package sia.taco_cloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sia.taco_cloud.User;
import sia.taco_cloud.data.UserRepository;

// Идентификация — это утверждение вашей личности,
// аутентификация проверяет это утверждение,
// а авторизация определяет, к каким действиям или ресурсам вы можете получить доступ после установления вашей личности.
@Configuration
public class SecurityConfig {

    @Bean
    // Инструмент шифрования паролей, пароли хранятся в БД в зашифрованном виде, этот бин указывает тип шифрования
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*

    @Bean
    // Создаёт бин (служба хранения учётных записей) с готовыми пользователями из готовой реализации USER и хранит в памяти
    // Готовых пользователей помещаем в List.
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        List<UserDetails> usersList = new ArrayList<>();
        usersList.add(new User(
                "buzz", encoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
        ));
        usersList.add(new User(
                "woody", encoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
        ));

        return new InMemoryUserDetailsManager(usersList);
    }

     */

    @Bean
    // Служба хранения учётных записей, которая читает информацию из репозитория
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) return user;

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/design", "/orders").hasRole("USER")
                                .requestMatchers("/", "/**").permitAll()
                ).formLogin(form ->
                        form
                                .loginPage("/login")
                                .defaultSuccessUrl("/design"))
                //.csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
