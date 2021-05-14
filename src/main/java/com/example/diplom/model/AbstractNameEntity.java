package com.example.diplom.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractNameEntity extends AbstractIdEntity{

    @Column(name = "name", nullable = false)
    @Size(max = 30)
    protected String name;

    public AbstractNameEntity() {
    }

    public AbstractNameEntity(Integer id, @Size(max = 30) String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
