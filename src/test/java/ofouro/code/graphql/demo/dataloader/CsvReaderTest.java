package ofouro.code.graphql.demo.dataloader;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import ofouro.code.graphql.demo.resolvers.InputConference;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CsvReaderTest {

    @Test
    public void testCsvValidData() throws Exception {
        List<InputConference> inputConferencess = CsvReader.loadObjectList(InputConference.class, "csv/conferences-test-data.csv");
        Assert.assertEquals(5, inputConferencess.size());
    }

    @Test(expected = UnrecognizedPropertyException.class)
    public void testInValidData() throws Exception {
        CsvReader.loadObjectList(InputConference.class, "csv/conferences-test-invalid-data.csv");
    }
}
