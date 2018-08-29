package ofouro.code.graphql.demo.service;

import ofouro.code.graphql.demo.model.Conference;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ConferenceServiceImpl implements ConferenceService {

    private ConferenceRepository conferenceRepository;

    public ConferenceServiceImpl(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public List<Conference> find(final Conference filter) {
        if (filter == null) {
            return conferenceRepository.findAll();
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        return StreamSupport.stream(conferenceRepository.findAll(Example.of(filter, matcher)).spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<Conference> findById(final Long id) {
        return conferenceRepository.findById(id);
    }

    public Conference save(Conference conference) {
        return this.conferenceRepository.save(conference);
    }
}