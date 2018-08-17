package ofouro.code.graphql.demo.graphql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.jayway.jsonpath.PathNotFoundException;
import lombok.extern.slf4j.Slf4j;
import ofouro.code.graphql.demo.resolvers.InputPerson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static graphql.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphQLPersonTest extends GraphQLBaseTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;


    @Test
    public void findPersonEmptyFilter() throws IOException {

        final ObjectMapper mapper = new ObjectMapper();

        // Find the created person
        InputPerson person = new InputPerson();

        findPerson(person);
    }


    @Test(expected = PathNotFoundException.class)
    public void findPersonNonExisting() throws IOException {

        final ObjectMapper mapper = new ObjectMapper();

        // Find the created person
        InputPerson person = new InputPerson("Santa", null, "santa");

        GraphQLResponse findResponse = findPerson(person);

        // should trigger an exception
        findResponse.get("$.data.persons[0]");
    }


    @Test
    public void createAndFindPerson() throws IOException {


        InputPerson person = new InputPerson("Niek", "https://040code.github.io", "npalm");

        GraphQLResponse createResonse = super.createPerson(person);
        assertNotNull(createResonse);
        assertTrue(createResonse.isOk());
        assertNotNull(createResonse.get("$.data.addPerson.id"));


        // Find the created person
        final ObjectMapper mapper = new ObjectMapper();
        final ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("id", createResonse.get("$.data.addPerson.id"));

        GraphQLResponse findResponse = graphQLTestTemplate.perform("queries/find-person-by-id.graphql", rootNode);
        log.info(String.format("Response: %s", findResponse.getRawResponse().toString()));

        assertNotNull(findResponse);
        assertTrue(findResponse.isOk());
        assertEquals(person.getName(), findResponse.get("$.data.person.name"));

    }


    private GraphQLResponse findPerson(InputPerson filter) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();

        final ObjectNode filterNode = mapper.createObjectNode();
        filterNode.set("filter", mapper.convertValue(filter, JsonNode.class));

        GraphQLResponse findResponse = graphQLTestTemplate.perform("queries/find-person.graphql", filterNode);
        log.info(String.format("Response: %s", findResponse.getRawResponse().toString()));

        assertNotNull(findResponse);
        assertTrue(findResponse.isOk());

        return findResponse;
    }


}
