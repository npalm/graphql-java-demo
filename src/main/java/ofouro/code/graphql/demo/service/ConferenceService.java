package ofouro.code.graphql.demo.service;

import ofouro.code.graphql.demo.model.Conference;

import java.util.List;
import java.util.Optional;

public interface ConferenceService {


    List<Conference> find(Conference conference);

    Optional<Conference> findById(Long id);
}