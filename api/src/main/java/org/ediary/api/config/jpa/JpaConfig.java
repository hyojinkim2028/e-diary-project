package org.ediary.api.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.ediary.db")
@EnableJpaRepositories(basePackages = "org.ediary.db")
public class JpaConfig {
}
