package ru.investing_portal.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.investing_portal.dto.TransactionCreateDto;
import ru.investing_portal.dto.TransactionReadDto;
import ru.investing_portal.mappers.TransactionMapper;
import ru.investing_portal.models.domain.Transaction;
import ru.investing_portal.repos.TransactionRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    private Transaction getTransactionById(int id) {
        return transactionRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Транзакция с id " + id + " не была найдена!"));
    }

    public void createTransaction(TransactionCreateDto transactionCreateDto) {
        transactionRepository.save(transactionMapper.toTransaction(transactionCreateDto));
    }

    public TransactionReadDto findTransactionById(int id) {
        return transactionMapper.toReadDto(getTransactionById(id));
    }

    public void updateTransaction(int id, TransactionCreateDto transactionCreateDto) {
        Transaction dbTransaction = getTransactionById(id);
        transactionCreateDto.setId(id);
        transactionMapper.updateTransactionFromDto(transactionCreateDto, dbTransaction);
        transactionRepository.save(dbTransaction);
    }

    public void deleteTransactionById(int id) {
        transactionRepository.deleteById(id);
    }

    public List<TransactionReadDto> findAllTransactions(Integer pageNum, Integer perPage) {
        List<Transaction> transactions = transactionRepository.findAll(PageRequest.of(pageNum, perPage)).getContent();
        return transactionMapper.map(transactions);
    }

}
