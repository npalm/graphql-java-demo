package ofouro.code.graphql.demo.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import lombok.extern.slf4j.Slf4j;
import ofouro.code.graphql.demo.resolvers.InputComment;
import ofouro.code.graphql.demo.resolvers.InputPerson;
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
public class GraphQLCommentTest extends GraphQLBaseTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;


    @Test
    public void findComments() throws IOException {
        GraphQLResponse findResponse = graphQLTestTemplate.perform("queries/find-comment.graphql", null);
        log.info(String.format("Response: %s", findResponse.getRawResponse().toString()));

        assertNotNull(findResponse);
        assertTrue(findResponse.isOk());
    }


    @Test
    public void createAndFInd() throws IOException {

        // create talk
        InputTalk inputTalk = new InputTalk("Some talk", "empty");
        GraphQLResponse talkCreateResponse = createTalk(inputTalk);
        String talkId = talkCreateResponse.get("$.data.addTalk.id");

        // create author
        InputPerson inputPerson = new InputPerson("commenter", "noblog", "nogit");
        GraphQLResponse personCreateResponse = createPerson(inputPerson);
        String personId = personCreateResponse.get("$.data.addPerson.id");


        final ObjectMapper mapper = new ObjectMapper();

        InputComment inputComment = new InputComment(Long.valueOf(talkId), Long.valueOf(personId), "test");

        GraphQLResponse createResonse = super.createComment(inputComment);

        assertNotNull(createResonse);
        assertTrue(createResonse.isOk());
        assertNotNull(createResonse.get("$.data.addComment.id"));

        // Find the created person
        final ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("id", createResonse.get("$.data.addComment.id"));

        GraphQLResponse findResponse = graphQLTestTemplate.perform("queries/find-comment-by-id.graphql", rootNode);
        log.info(String.format("Response: %s", findResponse.getRawResponse().toString()));

        assertNotNull(findResponse);
        assertTrue(findResponse.isOk());
        assertEquals(inputComment.getComment(), findResponse.get("$.data.comment.comment"));

    }


}
