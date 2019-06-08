package pl.akolata.trainingtracker.authorization

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import pl.akolata.trainingtracker.BaseSpecification
import pl.akolata.trainingtracker.user.Role
import pl.akolata.trainingtracker.user.RoleName
import pl.akolata.trainingtracker.user.RoleRepository
import pl.akolata.trainingtracker.user.UserRepository

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class AuthorizationControllerSpec extends BaseSpecification {

    static final SIGN_UP_REQUEST = '/api/auth/sign-up'

    @Autowired
    UserRepository userRepository

    @Autowired
    RoleRepository roleRepository

    def setup() {
        roleRepository.deleteAll()

        def role = new Role()
        role.setName(RoleName.ROLE_USER)
        roleRepository.saveAndFlush(role)
    }

    def '/sign-up should register user'() {
        given: 'sign up request'
        def req = buildSignUpRequest()

        when: 'a sign up request will be executed'
        def result = mvc
                .perform(post(SIGN_UP_REQUEST)
                        .content(toJson(req))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
        def response = result.response

        then: 'response status will be 201'
        response.status == 201

        and: 'user will be created in the database'
        userRepository.findByUsernameOrEmail(req.username, req.email).isPresent()
    }

    def cleanup() {
        userRepository.deleteAll()
        roleRepository.deleteAll()
    }

    private static buildSignUpRequest() {
        return new SignUpRequest(
                email: 'user@email.com',
                firstName: 'First',
                lastName: 'Last',
                username: 'username',
                password: 'password'
        )
    }
}
