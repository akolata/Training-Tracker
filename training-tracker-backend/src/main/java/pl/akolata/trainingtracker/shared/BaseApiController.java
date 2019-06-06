package pl.akolata.trainingtracker.shared;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = BaseApiController.API_URL)
public class BaseApiController {

    protected static final String API_URL = "/api";

    protected BaseApiController() {

    }
}
