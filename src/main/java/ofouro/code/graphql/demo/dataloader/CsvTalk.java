package ofouro.code.graphql.demo.dataloader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ofouro.code.graphql.demo.model.Conference;
import ofouro.code.graphql.demo.model.Person;
import ofouro.code.graphql.demo.model.Talk;
import ofouro.code.graphql.demo.service.ConferenceService;
import ofouro.code.graphql.demo.service.PersonService;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsvTalk {

    private String title;
    private String summary;
    private String conferences;
    private String speakers;

    public Talk convert(ConferenceService conferenceService, PersonService personService) {
        List<Conference> conferencesList = new ArrayList<>();
        for (String name : StringUtils.split(conferences, ",")) {
            conferencesList.addAll(conferenceService.find(new Conference(name, null, null)));
        }

        List<Person> speakersList = new ArrayList<>();
        for (String name : StringUtils.split(speakers, ",")) {
            speakersList.addAll(personService.find(new Person(name, null, null, null)));
        }

        return new Talk(this.title, this.summary, speakersList, conferencesList);

    }


}
