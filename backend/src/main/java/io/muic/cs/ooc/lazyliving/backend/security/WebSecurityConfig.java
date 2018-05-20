package io.muic.cs.ooc.lazyliving.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{

    private EntryConfig entryConfig;
    private FailAuthenticate failAuthenticate;
    private SuccessAuthenticate successAuthenticate;

    @Autowired
    public WebSecurityConfig(EntryConfig entryConfig, FailAuthenticate failAuthenticate, SuccessAuthenticate successAuthenticate){
        this.entryConfig = entryConfig;
        this.failAuthenticate = failAuthenticate;
        this.successAuthenticate = successAuthenticate;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/logout", "/users/register",
                        "/register","/checkUsernameIsOk","/checkEmailIsOk").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin().loginProcessingUrl("/login")
                .successHandler(successAuthenticate).failureHandler(failAuthenticate)
                .and()
                .exceptionHandling().authenticationEntryPoint(entryConfig)
                .and()
                .cors()
                .and()
                .logout().logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .permitAll();

        http
                .csrf()
                .disable();
    }

    @Bean
    public BCryptPasswordEncoder PasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public WebMvcConfigurer CORSConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowCredentials(true)
                        .allowedMethods("*")
                        .allowedHeaders("Authorization", "Content-Type","X-Amz-Date","Authorization","X-Api-Key","X-Amz-Security-Token","X-XSRF-TOKEN","Access-Control-Allow-Headers")
//                        .allowedOrigins("http://localhost:3000");
                        .allowedOrigins("*");
            }
        };
    }

}
