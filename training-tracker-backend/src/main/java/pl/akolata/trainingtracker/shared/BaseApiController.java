package pl.akolata.trainingtracker.shared;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping(value = BaseApiController.API_URL)
public class BaseApiController {

    protected static final String API_URL = "/api";

    protected BaseApiController() {
    }

    protected Pageable getPageable(Pageable pageable) {
        return pageable != null ? pageable : getDefaultPageable();
    }

    protected Pageable getDefaultPageable() {
        return PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
    }

    protected URI getResourceLocation(String pathWithoutApiPrefix, Object... uriVariables) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(API_URL + pathWithoutApiPrefix)
                .buildAndExpand(uriVariables)
                .toUri();
    }
}
