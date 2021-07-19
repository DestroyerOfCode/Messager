package com.marius.service.common;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marius.config.TwilioInitializer;
import com.marius.dto.common.BaseDTO;
import com.marius.model.domain.common.BaseEntity;
import com.marius.model.repository.common.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractService<Entity extends BaseEntity, DTO extends BaseDTO> {

    private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInitializer.class);

    private final ObjectMapper mapper;

    private final BaseRepository<Entity, String> baseRepository;

    private final Class<DTO> dtoClass;

    private final Class<Entity> entityCLass;

    @Autowired
    public AbstractService(ObjectMapper mapper, BaseRepository<Entity, String> baseRepository){
        this.mapper = mapper;
        this.baseRepository = baseRepository;
        entityCLass = (Class<Entity>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        dtoClass = (Class<DTO>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public DTO patch(String id, Map<String, Object> patchValues){
        LOGGER.info("Getting entity of type {} with id: {}", entityCLass.toString(), id);
        Optional<Entity> entity = baseRepository.findById(id);
        Entity updatedEntity = null;

        try {
            if (entity.isPresent()) {
                updatedEntity = entity.get();
                mapper.updateValue(updatedEntity, patchValues);
                baseRepository.save(updatedEntity);
                LOGGER.info("Patched entity of type {} with id: {}", entityCLass.toString(), id);
            }
        } catch (JsonMappingException e) {
            LOGGER.error("Error patching entity of type {} with id: {}", entityCLass, id);
        }

        return mapper.convertValue(updatedEntity, dtoClass);
    }

    public DTO delete(String id) {
        LOGGER.info("getting entity of type {} with id: {}", entityCLass.toString(), id);
        Optional<Entity> entity = baseRepository.findById(id);
        Entity deletedEntity = null;
        if (entity.isPresent()) {
            deletedEntity = entity.get();
            baseRepository.delete(deletedEntity);
            LOGGER.info("deleted entity of type {} with id: {}", entityCLass.toString(), id);
            return mapper.convertValue(deletedEntity, dtoClass);
        }

        LOGGER.error("Entity {} exists NOT", id);
        throw new RuntimeException(String.format("Entity with id %s does NOT exist", id));
    }
}
