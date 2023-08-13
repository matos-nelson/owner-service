package org.rent.circle.owner.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rent.circle.owner.api.enums.Suffix;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {

    private Long addressId;
    private String firstName;
    private String lastName;
    private String middleName;
    private Suffix suffix;
    private String email;
    private String phone;
}
