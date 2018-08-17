package ofouro.code.graphql.demo.service;

import ofouro.code.graphql.demo.model.Talk;
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
public class TalkServiceImpl implements TalkService {

    private TalkRepository talkRepository;

    public TalkServiceImpl(TalkRepository talkRepository) {
        this.talkRepository = talkRepository;
    }

    public List<Talk> find(final Talk talk) {
        if (talk == null) {
            return talkRepository.findAll();
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        return StreamSupport.stream(talkRepository.findAll(Example.of(talk, matcher)).spliterator(), false)
                .collect(Collectors.toList());
    }


    public Optional<Talk> findById(final Long id) {
        return talkRepository.findById(id);
    }


}