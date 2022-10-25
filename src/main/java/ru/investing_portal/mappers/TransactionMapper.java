package ru.investing_portal.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.investing_portal.dto.TransactionCreateDto;
import ru.investing_portal.dto.TransactionReadDto;
import ru.investing_portal.models.domain.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionReadDto toDto(Transaction transaction);

    Transaction toTransaction(TransactionCreateDto transactionCreateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTransactionFromDto(TransactionCreateDto transactionCreateDto, @MappingTarget Transaction entity);
}
