package ofouro.code.graphql.demo.service;

import ofouro.code.graphql.demo.model.Conference;
import ofouro.code.graphql.demo.model.Person;
import ofouro.code.graphql.demo.model.Talk;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.Type;

@Configuration
public class RepositoryConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer()
    {
        return RepositoryRestConfigurer.withConfig(config -> {
            config.exposeIdsFor(
                    entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new)
            );
        });
    }
}



