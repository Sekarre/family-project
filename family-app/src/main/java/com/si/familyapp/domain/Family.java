package com.si.familyapp.domain;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
public class Family {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @NotEmpty
    private String familyName;

    private int nrOfAdults;
    private int nrOfChildren;
    private int nrOfInfants;

    @PrePersist
    public void setIdIfEmpty() {
        if (StringUtils.isBlank(this.id.toString())) {
            this.id = UUID.randomUUID();
        }
    }
}
