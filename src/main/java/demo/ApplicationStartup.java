package demo;

import demo.model.Person;
import demo.model.Talk;
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

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        String[][] speakersInput = {{"Niek","npalm","https://code040.github.io"},
                {"Josh","joshlong","https://spring.io/team/jlong"},
                {"Maarten","",""},
                {"Gert","aval",""},
                {"Arjen","poutsma",""}};
        String[][] talksInput = {{"Niek", "Code the Next Build"},
                {"Niek","GraphQL - The Next API Language"},
                {"Arjen,Josh","Reactive Spring"},
                {"Maarten,Gert","Clojure: alien technology coming to a runtime near you"}};

        for(String[] speaker : speakersInput) {
            Person person = new Person();
            person.setName(speaker[0]);
            person.setGithubAccount(speaker[1]);
            person.setBlog(speaker[2]);
            personRepository.save(person);
        }

        for(String[] talkInput : talksInput ) {
            Talk talk = new Talk();
            talk.setTitle(talkInput[1]);
            String[] speakers = StringUtils.split(talkInput[0], ",");
            for(String speakerInput : speakers) {
                Person speaker = personRepository.findByName(speakerInput);
                talk.getSpeakers().add(speaker);
            }

            talkRepository.save(talk);

        }
    }
}