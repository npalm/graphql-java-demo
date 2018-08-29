package ofouro.code.graphql.demo;

import lombok.extern.slf4j.Slf4j;
import ofouro.code.graphql.demo.dataloader.CsvReader;
import ofouro.code.graphql.demo.dataloader.CsvTalk;
import ofouro.code.graphql.demo.resolvers.InputConference;
import ofouro.code.graphql.demo.resolvers.InputPerson;
import ofouro.code.graphql.demo.service.ConferenceService;
import ofouro.code.graphql.demo.service.PersonService;
import ofouro.code.graphql.demo.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


@Slf4j
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {


    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private PersonService personService;

    @Autowired
    private TalkService talkService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        try {
            List<InputConference> inputConferencess = CsvReader.loadObjectList(InputConference.class, "csv/conferences.csv");
            for (InputConference inputConference : inputConferencess) {
                conferenceService.save(InputConference.convert(inputConference));
            }

            List<InputPerson> speakers = CsvReader.loadObjectList(InputPerson.class, "csv/speakers.csv");
            for (InputPerson speaker : speakers) {
                personService.save(InputPerson.convert(speaker));
            }

            List<CsvTalk> talks = CsvReader.loadObjectList(CsvTalk.class, "csv/talks.csv");
            for (CsvTalk talk : talks) {
                talkService.save(talk.convert(conferenceService, personService));
            }
        } catch (IOException e) {
            log.error("Error loading data: " + e.getMessage());
        }
    }

}