package pl.akolata.trainingtracker.shared.service;

import pl.akolata.trainingtracker.shared.BaseEntity;
import pl.akolata.trainingtracker.shared.dto.BaseApiDto;
import pl.akolata.trainingtracker.shared.dto.BaseDto;

public interface BaseEntityMapper<T extends BaseEntity, S extends BaseDto, V extends BaseApiDto> {
    S toDto(T entity);

    V toApiDto(T entity);

    V toApiDto(S dto);
}
