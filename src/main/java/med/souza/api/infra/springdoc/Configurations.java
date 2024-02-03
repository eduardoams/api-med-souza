package med.souza.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {

    @Bean
    public OpenAPI customOpenAPI() {
        //Segurança
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        //Informações
        String title = "API Med Souza";
        String description = "API Rest da aplicação Med Souza, contendo as funcionalidades de CRUD de médicos e de pacientes, além de agendamento e cancelamento de consultas";

        Contact contact = new Contact()
                .name("Desenvolvedor")
                .email("eduardoa.ms14@gmail.com");

        Info info = new Info()
                .title(title)
                .description(description)
                .contact(contact);

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearer-key", securityScheme))
                .info(info);
    }
}
