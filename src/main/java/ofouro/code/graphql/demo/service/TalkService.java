package ofouro.code.graphql.demo.service;

import ofouro.code.graphql.demo.model.Talk;

import java.util.List;
import java.util.Optional;

public interface TalkService {


    List<Talk> find(Talk talk);

    Optional<Talk> findById(Long id);
}