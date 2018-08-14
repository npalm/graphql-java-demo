package demo.graphql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import demo.resolvers.InputPerson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static graphql.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphQLPersonMutationTest {


    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    public void createPerson() throws IOException {

        InputPerson person = new InputPerson("Niek", "https://040code.github.io", "npalm");

        final ObjectMapper mapper = new ObjectMapper();
        final ObjectNode root = mapper.createObjectNode();
        root.set("person", mapper.convertValue(person, JsonNode.class));

        GraphQLResponse response = graphQLTestTemplate.perform("queries/create-person.graphql", root);
        assertNotNull(response);
        assertTrue(response.isOk());

        log.info("Response: %s", response.getRawResponse().getBody());
        assertNotNull(response.get("$.data.addPerson.id"));
    }


}
