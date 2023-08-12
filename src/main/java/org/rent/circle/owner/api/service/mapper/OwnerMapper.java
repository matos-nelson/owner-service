package org.rent.circle.owner.api.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.rent.circle.owner.api.dto.OwnerDto;
import org.rent.circle.owner.api.dto.OwnerInfoDto;
import org.rent.circle.owner.api.persistence.model.Owner;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface OwnerMapper {

    Owner toModel(OwnerInfoDto ownerInfo);

    OwnerDto toDto(Owner owner);
}
