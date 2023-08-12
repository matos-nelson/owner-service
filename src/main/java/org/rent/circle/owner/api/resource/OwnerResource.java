package org.rent.circle.owner.api.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.owner.api.dto.OwnerInfoDto;
import org.rent.circle.owner.api.service.OwnerService;

@AllArgsConstructor
@Path("/owner")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class OwnerResource {

    private final OwnerService ownerService;

    @POST
    public Long saveOwnerInfo(@Valid OwnerInfoDto ownerInfo) {
        return ownerService.saveInfo(ownerInfo);
    }
}
