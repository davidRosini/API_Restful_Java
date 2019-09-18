package com.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
@EntityScan(basePackages = {"com.api.model.entity"})
@EnableJpaRepositories(basePackages = {"com.api.repository", "com.api.repository"})
public class ApplicationBoot extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ApplicationBoot.class, args);
        try {
            Environment env = ctx.getEnvironment();

            String contextPath = env.getProperty("server.servlet.contextPath");
            String port = env.getProperty("server.port");

            log.info("\n\n *** \n\n"
                            + "\tAplicacao {} iniciada com sucesso!\n"
                            + "\tDisponivel nos enderecos:\n"
                            + "\tLocal: http://localhost:{}\n"
                            + "\tExterno: http://{}:{}\n"
                            + "\tSwagger Url: http://{}:{}\n"
                            + "\tLocal Swagger Url: http://localhost:{}\n"
                            + "\n *** \n\n",
                    env.getProperty("spring.application.name"),
                    port + contextPath,
                    InetAddress.getLocalHost().getHostAddress(),
                    port + contextPath,
                    InetAddress.getLocalHost().getHostAddress(),
                    port + contextPath + "swagger-ui.html",
                    port + contextPath + "swagger-ui.html");

        } catch (UnknownHostException e) {
            log.error("Falha ao executar aplicacao: {0}", e);
            ctx.close();
        }
    }
}
