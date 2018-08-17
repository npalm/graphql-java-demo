package ofouro.code.graphql.demo.graphql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.jayway.jsonpath.PathNotFoundException;
import lombok.extern.slf4j.Slf4j;
import ofouro.code.graphql.demo.resolvers.InputTalk;
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
public class GraphQLTalkTest extends GraphQLBaseTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;


    @Test
    public void findTelkEmptyFilter() throws IOException {

        final ObjectMapper mapper = new ObjectMapper();

        // Find the created person
        InputTalk talk = new InputTalk();

        findTalk(talk);
    }


    @Test(expected = PathNotFoundException.class)
    public void findPersonNonExisting() throws IOException {

        final ObjectMapper mapper = new ObjectMapper();

        // Find the created person
        InputTalk talk = new InputTalk("Bladiebla", "nothing to mention");

        GraphQLResponse findResponse = findTalk(talk);

        // should trigger an exception
        findResponse.get("$.data.talk[0]");
    }


    @Test
    public void createAndFindTalk() throws IOException {

        final ObjectMapper mapper = new ObjectMapper();

        InputTalk talk = new InputTalk("GraphQL", "will follow soon.");

        GraphQLResponse createResonse = super.createTalk(talk);

        assertNotNull(createResonse);
        assertTrue(createResonse.isOk());
        assertNotNull(createResonse.get("$.data.addTalk.id"));


        // Find the created person
        final ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("id", createResonse.get("$.data.addTalk.id"));

        GraphQLResponse findResponse = graphQLTestTemplate.perform("queries/find-talk-by-id.graphql", rootNode);
        log.info(String.format("Response: %s", findResponse.getRawResponse().toString()));

        assertNotNull(findResponse);
        assertTrue(findResponse.isOk());
        assertEquals(talk.getTitle(), findResponse.get("$.data.talk.title"));

    }

    private GraphQLResponse findTalk(InputTalk filter) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();

        final ObjectNode filterNode = mapper.createObjectNode();
        filterNode.set("filter", mapper.convertValue(filter, JsonNode.class));

        GraphQLResponse findResponse = graphQLTestTemplate.perform("queries/find-talk.graphql", filterNode);
        log.info(String.format("Response: %s", findResponse.getRawResponse().toString()));

        assertNotNull(findResponse);
        assertTrue(findResponse.isOk());

        return findResponse;
    }


}
