package ru.investing_portal.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.investing_portal.dto.TransactionDto;
import ru.investing_portal.models.domain.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionUpdateMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTransactionFromDto(TransactionDto transactionDto, @MappingTarget Transaction entity);
}
