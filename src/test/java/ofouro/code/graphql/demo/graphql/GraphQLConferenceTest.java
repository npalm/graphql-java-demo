package ofouro.code.graphql.demo.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import lombok.extern.slf4j.Slf4j;
import ofouro.code.graphql.demo.resolvers.InputConference;
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
public class GraphQLConferenceTest extends GraphQLBaseTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;


    @Test
    public void findConferences() throws IOException {
        GraphQLResponse findResponse = graphQLTestTemplate.perform("queries/find-conferences.graphql", null);
        log.info(String.format("Response: %s", findResponse.getRawResponse().toString()));

        assertNotNull(findResponse);
        assertTrue(findResponse.isOk());
    }


    @Test
    public void createAndFInd() throws IOException {

        final ObjectMapper mapper = new ObjectMapper();

        InputConference inputConference = new InputConference("Nextbuild 2018", "Eindhoven");

        GraphQLResponse createResonse = super.createConference(inputConference);

        assertNotNull(createResonse);
        assertTrue(createResonse.isOk());
        assertNotNull(createResonse.get("$.data.addConference.id"));

        // Find the created person
        final ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("id", createResonse.get("$.data.addConference.id"));

        GraphQLResponse findResponse = graphQLTestTemplate.perform("queries/find-conference-by-id.graphql", rootNode);
        log.info(String.format("Response: %s", findResponse.getRawResponse().toString()));

        assertNotNull(findResponse);
        assertTrue(findResponse.isOk());
        assertEquals(inputConference.getName(), findResponse.get("$.data.conference.name"));

    }

    @Test
    public void test() throws IOException {

        InputConference inputConference = new InputConference("For Nerds", "A Dark City");
        GraphQLResponse conferenceCreateResponse = createConference(inputConference);
        String conferenceId = conferenceCreateResponse.get("$.data.addConference.id");

        InputTalk inputTalk = new InputTalk("Agile is Dead", "Title is obvious.");
        GraphQLResponse talkCreateResponse = createTalk(inputTalk);
        String talkId = talkCreateResponse.get("$.data.addTalk.id");

        InputPerson inputPerson = new InputPerson("speakerX", "noblog", "nogit");
        GraphQLResponse personCreateResponse = createPerson(inputPerson);
        String speakerId = personCreateResponse.get("$.data.addPerson.id");

        // Find the created person
        final ObjectMapper mapper = new ObjectMapper();
        final ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("conferenceId", conferenceId);
        rootNode.put("talkId", talkId);

        GraphQLResponse responseTalkToConf = graphQLTestTemplate.perform("queries/create-conference-talk-connection.graphql", rootNode);
        log.info(String.format("Response: %s", responseTalkToConf.getRawResponse().toString()));

        assertEquals(inputConference.getName(), responseTalkToConf.get("$.data.addTalkToConference.name"));
        assertEquals(inputTalk.getTitle(), responseTalkToConf.get("$.data.addTalkToConference.talks[0].title"));

        // Find the created person
        final ObjectNode rootNode2 = mapper.createObjectNode();
        rootNode2.put("talkId", talkId);
        rootNode2.put("speakerId", speakerId);

        GraphQLResponse responseSpeakerToTalk = graphQLTestTemplate.perform("queries/create-talk-speaker-connection.graphql", rootNode2);
        log.info(String.format("Response: %s", responseSpeakerToTalk.getRawResponse().toString()));
        assertEquals(inputTalk.getTitle(), responseSpeakerToTalk.get("$.data.addSpeakerToTalk.title"));
        assertEquals(inputPerson.getName(), responseSpeakerToTalk.get("$.data.addSpeakerToTalk.speakers[0].name"));


    }


}
