package pl.akolata.trainingtracker.gym

import org.springframework.beans.factory.annotation.Autowired
import pl.akolata.trainingtracker.BaseSpecification

class GymDatabaseServiceSpec extends BaseSpecification {

    @Autowired
    GymDatabaseService gymService

    @Autowired
    GymRepository gymRepository

    def setup() {
        gymRepository.deleteAll()
    }

    def "should not create gym if name it's not unique"() {
        given: "gym with name 'City Fit' saved in the database"
        def gymName = 'City Fit'
        gymRepository.saveAndFlush(new Gym(name: gymName))

        when: "someone will attempt to save a new gym with the same name"
        gymService.createGym(new CreateGymCommand(gymName))

        then: "an exception should be thrown"
        thrown GymCreationFailureException
    }

    def "should create and save a new gym in the database"() {
        given: "crete gym command with a unique name"
        def gymName = 'City Fit'
        def createGymCommand = new CreateGymCommand(gymName)

        when: "createGym will be called"
        Gym gym = gymService.createGym(createGymCommand)

        then: "it will be saved in the database"
        gym != null
        gym.name == gymName
        gymRepository.count() == 1
    }

    def "should find a gym in the database"() {
        given: "gym already present in the database"
        Gym gym = gymRepository.saveAndFlush(new Gym(name: 'City Fit'))

        when: ""
        GymDto foundGym = gymService.findGymById(gym.id)

        then: "gym will be found"
        foundGym != null
        gym.id == foundGym.id
        gym.name == foundGym.name
    }

    def cleanup() {
        gymRepository.deleteAll()
    }
}
