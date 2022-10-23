package ru.investing_portal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.TransactionDto;
import ru.investing_portal.mappers.TransactionDtoMapper;
import ru.investing_portal.mappers.TransactionUpdateMapper;
import ru.investing_portal.models.domain.Transaction;
import ru.investing_portal.repos.TransactionRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class TransactionControllerImpl implements TransactionController {

    private final TransactionRepository transactionRepository;

    private final TransactionDtoMapper transactionDtoMapper;

    private final TransactionUpdateMapper transactionUpdateMapper;

    @Override
    public void create(TransactionDto transactionDto) {
        transactionRepository.save(transactionDtoMapper.toTransaction(transactionDto));
    }

    @Override
    public TransactionDto read(int id) {
        return transactionDtoMapper.toDto(transactionRepository.findById(id).get());
    }

    // CHECKME: mapper ✓ or modifying query update
    @Override
    public void update(int id, TransactionDto transactionDto) {
        Transaction dbTransaction = transactionRepository.findById(id).get();
        transactionDto.setId(id);
        transactionUpdateMapper.updateTransactionFromDto(transactionDto, dbTransaction);
        transactionRepository.save(dbTransaction);
    }

    @Override
    public void delete(int id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public List<TransactionDto> readAll(Integer pageNum, Integer perPage) {
        if (perPage == null) perPage = 25; // Default page size
        if (pageNum == null) pageNum = 0;  // Default page number
        List<Transaction> transactions = transactionRepository.findAll(PageRequest.of(pageNum, perPage)).getContent();
        return transactions.stream().map(transactionDtoMapper::toDto).collect(Collectors.toList());
    }

}
