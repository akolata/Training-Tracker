package pl.akolata.trainingtracker.user

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import pl.akolata.trainingtracker.BaseSpecification
import pl.akolata.trainingtracker.security.SecurityFacade
import pl.akolata.trainingtracker.security.UserPrincipal
import pl.akolata.trainingtracker.training.CreateTrainingCommand
import pl.akolata.trainingtracker.training.Training
import pl.akolata.trainingtracker.training.TrainingsFacade

import java.time.LocalDate

class UserFacadeSpec extends BaseSpecification {

    @Autowired
    UserFacade userFacade

    @Autowired
    UserRepository userRepository

    @Autowired
    RoleRepository roleRepository

    @Autowired
    TrainingsFacade trainingsFacade

    @SpringBean
    SecurityFacade securityFacade = Mock()

    def setup() {
        deleteAllDataFromRepositories()
        roleRepository.saveAndFlush(new Role(RoleName.ROLE_USER))
    }

    def "SignUp"() {
        given: "command"
        SignUpCommand command = new SignUpCommand("John", "Doe", "username", "user@mail.com", "password")

        when: "signUp will be called on facade"
        User user = userFacade.signUp(command)

        then: "created user will be returned"
        user != null

        and: "it will has the same properties as in the command"
        user.firstName == command.firstName
        user.lastName == command.lastName
        user.username == command.username
        user.email == command.email

        and: "it will be saved in the database"
        user.id != null
        user.updatedAt != null
        user.createdAt != null
        user.version != null

        and: "it will be able to find in the database"
        Optional<User> userOpt = userRepository.findById(user.id)
        userOpt != null
        userOpt.get() == user
    }

    def "getUserById should throw exception if id is null"() {
        given: "null id"
        Long id = null

        when: "getUserById will be called"
        userFacade.getUserById(id)

        then: "NPE will be thrown"
        thrown(NullPointerException)
    }

    def "getUserById should return null if user is not in the database"() {
        given: "id of an not existing user"
        Long id = 500L

        when: "getUserById will be called with an id not present in the database"
        User user = userFacade.getUserById(id)

        then: "null will be returned"
        user == null
    }

    def "getUserById should return the user from the database"() {
        given: "user saved in the database"
        User user = saveUserInTheDatabase()

        when: "getUserById will be called with his id"
        User foundUser = userFacade.getUserById(user.id)

        then: "facade will returned the same user"
        user == foundUser
    }

    def "addTrainingToUser should throw an exception if training is null"() {
        given: "null training"
        Training training = null

        when: "addTrainingToUser will be called"
        userFacade.addTrainingToUser(training)

        then: "NPE will be thrown"
        thrown(NullPointerException)
    }

    def "addTrainingToUser should add training to principal trainings collection"() {
        given: "user saved in the database"
        User user = saveUserInTheDatabase()

        and: "mocked security facade which returns principal id of an user in the database"
        securityFacade.getUserPrincipal() >> UserPrincipal.create(user)

        and: "training saved in the database"
        Training training = trainingsFacade.createTraining(new CreateTrainingCommand(
                LocalDate.now(),
                null,
                user.id,
                'additional info',
                'training name'
        ))

        when: "addTrainingToUser will be called"
        user = userFacade.addTrainingToUser(training)

        then: "training will be added to user's trainings collection"
        user.trainings.size()
        Training userTraining = user.trainings.iterator().next()
        training == userTraining
        training.user == user
    }

    def cleanup() {
        deleteAllDataFromRepositories()
    }

    def deleteAllDataFromRepositories() {
        userRepository.deleteAll()
        roleRepository.deleteAll()
    }

    def saveUserInTheDatabase() {
        User user = new User("John", "Doe", "username", "user@email.com", "password")
        Optional<Role> userRole = roleRepository.findByName(RoleName.ROLE_USER)
        user.roles.add(userRole.get())
        user.userAccountDetails
        return userRepository.saveAndFlush(user)
    }
}
