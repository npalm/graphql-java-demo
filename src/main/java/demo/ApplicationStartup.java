package demo;

import demo.model.Conference;
import demo.model.Person;
import demo.model.Talk;
import demo.service.ConferenceRepository;
import demo.service.PersonRepository;
import demo.service.TalkRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TalkRepository talkRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;


    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        String[][] speakersInput = {
                {"Niek", "npalm", "https://code040.github.io"},
                {"Josh","joshlong","https://spring.io/team/jlong"},
                {"Maarten","",""},
                {"Gert","aval",""},
                {"Arjen", "poutsma", ""},
                {"Todd", "", ""},
                {"Martin", "", ""},
                {"Jez", "", ""}
        };

        String[][] talksInput = {
                {"Niek", "Code the Next Build", "Nextbuild 2017"},
                {"Niek", "GraphQL - The Next API Language", "Nextbuild 2017,GeeCon 2017"},
                {"Arjen,Josh", "Reactive Spring", "Nextbuild 2017"},
                {"Maarten,Gert", "Clojure: alien technology coming to a runtime near you", "Nextbuild 2017"},
                {"Todd", "Making it count, Quality is not optional", "GeeCon 2017"},
                {"Todd,Martin", "What was the design process behind the fastest Messaging System", "GeeCon 2017"},
                {"Jez", "Software Seven Deadly Wasters", "GeeCon 2017"}
        };

        String[][] conferencesInput = {{"Nextbuild 2017", "Eindhoven"},
                {"GeeCon 2017", "Prague"}};

        for(String[] speaker : speakersInput) {
            Person person = new Person();
            person.setName(speaker[0]);
            person.setGithubAccount(speaker[1]);
            person.setBlog(speaker[2]);
            personRepository.save(person);
        }

        for (String[] conferenceInput : conferencesInput) {
            Conference conference = new Conference();
            conference.setName(conferenceInput[0]);
            conference.setCity(conferenceInput[1]);
            conferenceRepository.save(conference);
        }

        for(String[] talkInput : talksInput ) {
            Talk talk = new Talk();
            talk.setTitle(talkInput[1]);
            String[] speakers = StringUtils.split(talkInput[0], ",");
            for(String speakerInput : speakers) {
                Person speaker = personRepository.findByName(speakerInput);
                talk.getSpeakers().add(speaker);
            }
            String[] conferenceNames = StringUtils.split(talkInput[2], ",");
            for (String conferenceInput : conferenceNames) {
                Conference conference = conferenceRepository.findByName(conferenceInput);
                talk.getConferences().add(conference);
            }


            talkRepository.save(talk);
        }


    }
}