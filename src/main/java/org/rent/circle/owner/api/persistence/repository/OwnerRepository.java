package org.rent.circle.owner.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.rent.circle.owner.api.persistence.model.Owner;

@ApplicationScoped
public class OwnerRepository implements PanacheRepository<Owner> {

}
