package ru.investing_portal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.TransactionCreateDto;
import ru.investing_portal.dto.TransactionReadDto;
import ru.investing_portal.mappers.TransactionMapper;
import ru.investing_portal.models.domain.Transaction;
import ru.investing_portal.repos.TransactionRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class TransactionControllerImpl implements TransactionController {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    @Override
    public void create(TransactionCreateDto transactionCreateDto) {
        transactionRepository.save(transactionMapper.toTransaction(transactionCreateDto));
    }

    @Override
    public TransactionReadDto read(int id) {
        return transactionMapper.toReadDto(transactionRepository.findById(id).get());
    }

    @Override
    public void update(int id, TransactionCreateDto transactionCreateDto) {
        Transaction dbTransaction = transactionRepository.findById(id).get();
        transactionCreateDto.setId(id);
        transactionMapper.updateTransactionFromDto(transactionCreateDto, dbTransaction);
        transactionRepository.save(dbTransaction);
    }

    @Override
    public void delete(int id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public List<TransactionReadDto> readAll(Integer pageNum, Integer perPage) {
        List<Transaction> transactions = transactionRepository.findAll(PageRequest.of(pageNum, perPage)).getContent();
        return transactions.stream().map(transactionMapper::toReadDto).collect(Collectors.toList());
    }

}
