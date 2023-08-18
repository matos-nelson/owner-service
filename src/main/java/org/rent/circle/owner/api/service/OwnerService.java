package org.rent.circle.owner.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.owner.api.dto.OwnerDto;
import org.rent.circle.owner.api.dto.SaveOwnerInfoDto;
import org.rent.circle.owner.api.dto.UpdateOwnerInfoDto;
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
    public Long saveInfo(SaveOwnerInfoDto ownerInfo) {
        Owner owner = ownerMapper.toModel(ownerInfo);
        ownerRepository.persist(owner);
        return owner.getId();
    }

    public OwnerDto getOwnerInfo(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId);
        return ownerMapper.toDto(owner);
    }

    @Transactional
    public void updateOwnerInfo(Long ownerId, UpdateOwnerInfoDto updateOwnerInfo) {
        Owner owner = ownerRepository.findById(ownerId);
        if (owner == null) {
            return;
        }

        ownerMapper.update(updateOwnerInfo, owner);
        ownerRepository.persist(owner);
    }
}
