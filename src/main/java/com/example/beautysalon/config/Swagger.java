package com.example.beautysalon.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Beauty salon",
                description = "Some long and useful description",
                version = "v1",
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")),
        security = { @SecurityRequirement(name = "SESSION") })
@SecurityScheme(
        type = SecuritySchemeType.APIKEY,
        name = "SESSION",
        in = SecuritySchemeIn.COOKIE)
public class Swagger {
}
