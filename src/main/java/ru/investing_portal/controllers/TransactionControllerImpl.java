package ru.investing_portal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.TransactionCreateDto;
import ru.investing_portal.dto.TransactionGroupDto;
import ru.investing_portal.dto.TransactionReadDto;
import ru.investing_portal.services.TransactionService;

import java.util.List;

@RestController
@AllArgsConstructor
public class TransactionControllerImpl implements TransactionController {

    private final TransactionService transactionService;

    // Transactions
    @Override
    public void create(TransactionCreateDto transactionCreateDto) {
        transactionService.createTransaction(transactionCreateDto);
    }

    @Override
    public TransactionReadDto read(int id) {
        return transactionService.findTransactionById(id);
    }

    @Override
    public void update(int id, TransactionCreateDto transactionCreateDto) {
        transactionService.updateTransaction(id, transactionCreateDto);
    }

    @Override
    public void delete(int id) {
        transactionService.deleteTransactionById(id);
    }

    @Override
    public List<TransactionReadDto> readByGroup(int groupId) {
        return transactionService.findTransactionsByGroupId(groupId);
    }


    // Transaction Groups
    @Override
    public TransactionGroupDto readGroup(int groupId) {
        return transactionService.findTransactionGroupById(groupId);
    }

    @Override
    public void deleteGroup(int groupId) {
        transactionService.deleteTransactionGroupById(groupId);
    }

    @Override
    public List<TransactionGroupDto> readGroupsByPortfolioId(int portfolioId) {
        return transactionService.findTransactionGroupsByPortfolioId(portfolioId);
    }

}
