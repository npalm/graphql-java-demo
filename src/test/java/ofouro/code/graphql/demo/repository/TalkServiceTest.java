package ofouro.code.graphql.demo.repository;

import graphql.Assert;
import ofouro.code.graphql.demo.model.Talk;
import ofouro.code.graphql.demo.service.TalkRepository;
import ofouro.code.graphql.demo.service.TalkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TalkServiceTest {

    @Autowired
    private TalkService talkService;

    @Autowired
    private TalkRepository talkRepository;

    private Talk aTalk = new Talk("GraphQL is Cool", "summary", new ArrayList<>(), new ArrayList<>());

    @Test
    public void findNull() {
        List<Talk> result = talkService.find(null);
        Assert.assertNotNull(result);
    }

    @Test
    public void shouldFindTalkLikeNameMiddle() throws Exception {
        checkFindTalk("L is Coo");
    }

    @Test
    public void shouldFindTalkLikeNameUPPER() throws Exception {
        checkFindTalk("IS COOL");
    }

    @Test
    public void shouldNotFind() throws Exception {
        checkNotFindTalk("BLAAT");
    }

    private Iterable<Talk> setupAndFind(String title) {
        // Arrange
        talkRepository.save(aTalk);

        // Act
        return talkService.find(new Talk(title, null, null, null));
    }

    private void checkFindTalk(String title) {
        // Arrange && Act
        Iterable<Talk> findTalk = setupAndFind(title);

        // Assert
        assertThat(aTalk, equalTo(findTalk.iterator().next()));
    }

    private void checkNotFindTalk(String title) {
        // Arrange && Act
        Iterable<Talk> findTalk = setupAndFind(title);

        // Assert
        assertThat(Boolean.FALSE, equalTo(findTalk.iterator().hasNext()));
    }

}