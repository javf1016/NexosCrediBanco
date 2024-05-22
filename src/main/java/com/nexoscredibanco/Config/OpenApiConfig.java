package com.nexoscredibanco.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "NexosCrediBanco",
                version = "1.0.0",
                description = "Nexos"
        )
)
public class OpenApiConfig {

}
