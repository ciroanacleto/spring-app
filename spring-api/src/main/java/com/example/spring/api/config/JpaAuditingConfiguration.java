package com.example.spring.api.config;

import com.example.spring.api.model.Usuario;
import com.example.spring.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Bean
    public AuditorAware<Usuario> auditorProvider() {
        return () ->  this.usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
    }
}
