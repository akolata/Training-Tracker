package pl.akolata.trainingtracker.authorization

import org.springframework.beans.factory.annotation.Autowired
import pl.akolata.trainingtracker.BaseSpecification

class AuthorizationFacadeSpec extends BaseSpecification {

    @Autowired
    AuthorizationFacade authorizationFacade

    def "signIn should throw NPE if command is null"() {
        given: "null command object"
        SignInCommand command = null

        when: "sign in will be executed"
        authorizationFacade.signIn(command)

        then: "NPE will be thrown"
        thrown(NullPointerException)
    }
}
