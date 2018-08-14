package demo.repository;

import demo.model.Person;
import demo.service.PersonRepository;
import demo.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    private Person johnDoe = new Person("John Doe", "github", "www", new ArrayList<>());

    @Test
    public void shouldFindPersonLikeNameJohn() throws Exception {
        checkFindPerson("John");
    }

    @Test
    public void shouldFindPersonLikeNameOhn() throws Exception {
        checkFindPerson("Ohn");
    }

    @Test
    public void shouldFindPersonLikeNameHN_DO() throws Exception {
        checkFindPerson("HN DO");
    }

    @Test
    public void shouldNotFindJHND() throws Exception {
        checkNotFindPerson("JHND");
    }


    private Iterable<Person> setupAndFind(String name) {
        // Arrange
        personRepository.save(johnDoe);

        // Act
        return personService.findPerson(new Person(name));
    }

    private void checkFindPerson(String name) {
        // Arrange && Act
        Iterable<Person> findPerson = setupAndFind(name);

        // Assert
        assertThat(johnDoe, equalTo(findPerson.iterator().next()));
    }

    private void checkNotFindPerson(String name) {
        // Arrange && Act
        Iterable<Person> findPerson = setupAndFind(name);

        // Assert
        assertThat(Boolean.FALSE, equalTo(findPerson.iterator().hasNext()));
    }

}