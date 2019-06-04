package pl.akolata.trainingtracker.gym

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import pl.akolata.trainingtracker.BaseSpecification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class GymControllerSpec extends BaseSpecification {

    static final GYM_URL = "/api/gym"

    @Autowired
    GymRepository gymRepository

    def "should return a 401 status if user is not authenticated"() {
        given: "create gym request"
        def createGymReq = new CreateGymRequest(name: 'City Fit')

        when: "a create gym request will be executed"
        MvcResult result = mvc
                .perform(post(GYM_URL)
                        .content(toJson(createGymReq))
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()

        then:
        result.response.status == HttpStatus.UNAUTHORIZED.value()
    }

    @WithMockUser
    def "should return a 400 status if gym's name is too short"() {
        given: "create gym request"
        def createGymReq = new CreateGymRequest(name: 'City')

        when: "a create gym request will be executed"
        MvcResult result = mvc
                .perform(post(GYM_URL)
                        .content(toJson(createGymReq))
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()

        then:
        result.response.status == HttpStatus.BAD_REQUEST.value()
    }

    @WithMockUser
    def "should return a 400 status if gym's name is not unique"() {
        given: "gym with name 'City Fit' already saved in the database"
        def gymName = 'CityFit'
        gymRepository.saveAndFlush(new Gym(name: gymName))

        and: "request with the same gym's name"
        def createGymReq = new CreateGymRequest(name: gymName)

        when: "a create gym request will be executed"
        MvcResult result = mvc
                .perform(post(GYM_URL)
                        .content(toJson(createGymReq))
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()

        then: "server will return 400 status"
        result.response.status == HttpStatus.BAD_REQUEST.value()
    }

    @WithMockUser
    def "should return a 201 status after successful gym creation"() {
        given: "request with the same gym's name"
        def createGymReq = new CreateGymRequest(name: 'City Fit')

        when: "a create gym request will be executed"
        MvcResult result = mvc
                .perform(post(GYM_URL)
                        .content(toJson(createGymReq))
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()

        then: "server will return 201 status"
        result.response.status == HttpStatus.CREATED.value()

        and: "location header will be present"
        def header = result.response.getHeader(HttpHeaders.LOCATION)
        header != null
    }
}
