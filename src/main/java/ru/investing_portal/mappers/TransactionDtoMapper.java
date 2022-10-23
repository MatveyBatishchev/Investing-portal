package ru.investing_portal.mappers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.investing_portal.dto.TransactionDto;
import ru.investing_portal.models.domain.Transaction;

@Component
@AllArgsConstructor
public class TransactionDtoMapper {

    private final ModelMapper modelMapper;

    public TransactionDto toDto(Transaction transaction) {
        return modelMapper.map(transaction, TransactionDto.class);
    }

    public Transaction toTransaction(TransactionDto transactionDto) {
        return modelMapper.map(transactionDto, Transaction.class);
    }

}
