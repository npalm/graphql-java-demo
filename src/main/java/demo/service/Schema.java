/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 oEmbedler Inc. and Contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 *  rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 *  persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package demo.service;

import com.oembedler.moon.graphql.engine.stereotype.*;
import demo.model.Person;
import demo.model.Talk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@GraphQLSchema
public class Schema {

    @GraphQLIgnore
    @Autowired
    private PersonRepository personRepository;

    @GraphQLSchemaQuery
    private QueryType queryType;

    @GraphQLObject
    public static class QueryType {

        @GraphQLIgnore
        @Autowired
        private PersonRepository personRepository;

        @GraphQLIgnore
        @Autowired
        private TalkRepository talkRepository;

        @GraphQLField("persons")
        public List<Person> persons() {
            return personRepository.findAll(new PageRequest(0, 1000)).getContent();
        }

        @GraphQLField("talks")
        public List<Talk> talks() {
            return talkRepository.findAll(new PageRequest(0, 1000)).getContent();
        }
    }

    @GraphQLMutation
    public String mandatoryMudation(@GraphQLIn("mandatoryMudation") String someText) {
        return someText;
    }

    @GraphQLMutation
    public Person addPerson(@GraphQLIn("name") String name) {
        return personRepository.save(new Person(name));
    }

}
