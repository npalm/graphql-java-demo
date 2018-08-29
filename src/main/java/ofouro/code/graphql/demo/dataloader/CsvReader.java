package ofouro.code.graphql.demo.dataloader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CsvReader {

    public static <T> List<T> loadObjectList(Class<T> type, String fileName) throws IOException {
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();

        CsvMapper mapper = new CsvMapper();
        File file = new ClassPathResource(fileName).getFile();
        MappingIterator<T> readValues = mapper.readerFor(type).with(bootstrapSchema).readValues(file);
        return readValues.readAll();
    }
}
