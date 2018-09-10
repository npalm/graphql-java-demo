package ofouro.code.graphql.demo.dataloader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CsvReader {

    public static <T> List<T> loadObjectList(Class<T> type, String fileName) throws IOException {
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();

        CsvMapper mapper = new CsvMapper();
        InputStream is = new ClassPathResource(fileName).getInputStream();
        MappingIterator<T> readValues = mapper.readerFor(type).with(bootstrapSchema).readValues(is);
        return readValues.readAll();
    }
}
