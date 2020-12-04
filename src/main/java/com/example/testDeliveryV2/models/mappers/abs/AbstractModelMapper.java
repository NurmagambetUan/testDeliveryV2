package com.example.testDeliveryV2.models.mappers.abs;

import com.example.testDeliveryV2.models.audit.AuditModel;
import com.example.testDeliveryV2.models.DTO.MealDTO;

import java.util.List;

public abstract class AbstractModelMapper<E extends AuditModel,D> {

    public abstract D toDto (E e);
    public abstract E toEntity(D d);
    public abstract List<D> toDtoList(List<E> eList);
    public abstract List<E> toEntityList(List<D> dList);

}
