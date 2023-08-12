package org.rent.circle.owner.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.owner.api.dto.OwnerInfoDto;
import org.rent.circle.owner.api.persistence.model.Owner;
import org.rent.circle.owner.api.persistence.repository.OwnerRepository;
import org.rent.circle.owner.api.service.mapper.OwnerMapper;

@AllArgsConstructor
@ApplicationScoped
@Slf4j
public class OwnerService {

    private final OwnerMapper ownerMapper;
    private final OwnerRepository ownerRepository;

    @Transactional
    public Long saveInfo(OwnerInfoDto ownerInfo) {
        Owner owner = ownerMapper.toModel(ownerInfo);
        ownerRepository.persist(owner);
        return owner.getId();
    }
}