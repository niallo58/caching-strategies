package cachingstrategies.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Autowired
    private TypeResolver typeResolver;

    /**
     * Swagger config
     *
     * @return
     */

    @Bean
    public Docket bookApi() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Mobacar CDS Unidas Atomic").description("Interactions with Unidas")
                .termsOfServiceUrl("http://mobacar.com/cds").license("Apache License Version 2.0")
                .licenseUrl("https://mobacar.com/cdslicense").version("1.0").build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration("validatorUrl", // url
                "list", // docExpansion => none | list
                "alpha", // apiSorter => alpha
                "schema", // defaultModelRendering => schema
                false, // enableJsonEditor => true | false
                true); // showRequestHeaders => true | false
    }
}