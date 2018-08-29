package ofouro.code.graphql.demo.resolvers;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;

public class InputConverterTest {

    @Test
    public void testInputPersonNull() {
        Assert.assertNull(InputPerson.convert(null));
    }

    @Test
    public void testInputTalkNull() {
        Assert.assertNull(InputTalk.convert(null));
    }

    @Test
    public void testInputConferenceNull() {
        Assert.assertNull(InputConference.convert(null));
    }

    @Test
    public void testInputPageNull() {
        PageRequest pageRequest = InputPage.convert(null);
        Assert.assertEquals("Zero should be the default page.", 0, pageRequest.getPageNumber());
    }
}

