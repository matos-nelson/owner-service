package org.rent.circle.owner.api.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.rent.circle.owner.api.dto.OwnerDto;
import org.rent.circle.owner.api.dto.SaveOwnerInfoDto;
import org.rent.circle.owner.api.dto.UpdateOwnerInfoDto;
import org.rent.circle.owner.api.persistence.model.Owner;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface OwnerMapper {

    Owner toModel(SaveOwnerInfoDto ownerInfo);

    OwnerDto toDto(Owner owner);

    void update(UpdateOwnerInfoDto updateOwnerInfo, @MappingTarget Owner owner);
}
