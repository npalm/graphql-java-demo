package demo;

import com.oembedler.moon.graphql.boot.SchemaParserDictionary;
import demo.model.Person;
import demo.model.Talk;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //NOSONAR
    }


    @Bean
    public SchemaParserDictionary schemaParserDictionary() {
        return new SchemaParserDictionary()
                .dictionary(Person.class, Talk.class);
    }
}
