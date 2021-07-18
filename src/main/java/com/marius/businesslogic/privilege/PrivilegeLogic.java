package com.marius.businesslogic.privilege;

import com.marius.converter.privilege.PrivilegeConverter;
import com.marius.dto.privilege.PrivilegeDTO;
import com.marius.model.domain.privilege.Privilege;
import com.marius.model.domain.privilege.PrivilegeEnum;
import com.marius.model.repository.privilege.PrivilegeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class PrivilegeLogic {

    private static final Logger logger = LoggerFactory.getLogger(PrivilegeLogic.class);

    private final PrivilegeRepository privilegeRepository;
    private final PrivilegeConverter privilegeConverter;

    @Autowired
    public PrivilegeLogic(PrivilegeRepository privilegeRepository, PrivilegeConverter privilegeConverter) {
        this.privilegeRepository = privilegeRepository;
        this.privilegeConverter = privilegeConverter;
    }

    public PrivilegeDTO createPrivilege(PrivilegeDTO dto) {
        logger.info("Creating new privilege with name {}.", dto.getName());

        Privilege privilege = privilegeConverter.dtoToEntity(dto);
        privilege = privilegeRepository.insert(privilege);

        logger.info("Successfully created new privilege {} with id {}.", privilege.getName(), privilege.get_id());
        return privilegeConverter.entityToDto(privilege);
    }

    public PrivilegeDTO getPrivilege(PrivilegeEnum name) {
        logger.info("Getting {} from Database", name);

        Optional<Privilege> foundPrivilege = privilegeRepository.findPrivilegeByName(name);

        if (foundPrivilege.isPresent()) {
            logger.info("Successfully got {} from Database", name);
            return privilegeConverter.entityToDto(foundPrivilege .get());
        }

        throw new NoSuchElementException(String.format("Could not find element with name %s.", name));

    }

    public PrivilegeDTO deletePrivilege(PrivilegeEnum name) {

        logger.info("Deleting {} from Database", name);
        Optional<Privilege> deletedPrivilege= privilegeRepository.deletePrivilegeByName(name);
        if (deletedPrivilege.isPresent()) {
            logger.info("Successfully deleted {} from Database", name);
            return privilegeConverter.entityToDto(deletedPrivilege.get());
        }

        throw new NoSuchElementException(String.format("Could not find element with name %s.", name));
    }
}
