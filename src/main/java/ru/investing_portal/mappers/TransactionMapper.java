package ru.investing_portal.mappers;

import org.mapstruct.*;
import ru.investing_portal.dto.TransactionCreateDto;
import ru.investing_portal.dto.TransactionGroupDto;
import ru.investing_portal.dto.TransactionReadDto;
import ru.investing_portal.models.domain.Transaction;
import ru.investing_portal.models.domain.TransactionGroup;
import ru.investing_portal.repos.CoinRepository;
import ru.investing_portal.repos.PortfolioRepository;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class,
        uses = {CoinRepository.class, CoinMapper.class, PortfolioRepository.class})
public interface TransactionMapper {

    // Transactions
    @Mapping(target="id", ignore=true)
    Transaction toTransaction(TransactionCreateDto transactionCreateDto);

    @Named("toTransactionReadDto")
    @Mapping(target="transactionGroupId", expression = "java(transaction.getTransactionGroup().getId())")
    TransactionReadDto toReadDto(Transaction transaction);

    @Mapping(target="transactionGroup", ignore=true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTransactionFromDto(TransactionCreateDto transactionDto, @MappingTarget Transaction entity);

    // Transaction Groups
    @Mapping(target="id", ignore=true)
    @Mapping(target="coin", source="coinId", qualifiedByName = "getCoinReferenceById")
    @Mapping(target="portfolio", source="portfolioId", qualifiedByName = "getPortfolioReferenceById")
    @Mapping(target="holdings", source="amount")
    @Mapping(target="holdingsValue", source="sum")
    @Mapping(target="avgPrice", source="pricePerCoin")
    @Mapping(target="priceChange", constant = "0.0")
    @Mapping(target="priceChangePercentage", constant = "0.0")
    TransactionGroup toTransactionGroup(TransactionCreateDto transactionCreateDto);

    @Named("toTransactionGroupShortDto")
    @Mapping(target="portfolioId", expression = "java(transactionGroup.getPortfolio().getId())")
    @Mapping(target="coinShortDto", source="coin", qualifiedByName = "toCoinShortDto")
    TransactionGroupDto toGroupDto(TransactionGroup transactionGroup);

    @IterableMapping(qualifiedByName = "toTransactionGroupShortDto")
    @Named("mapToTransactionGroupDtoList")
    List<TransactionGroupDto> mapGroups(Collection<TransactionGroup> transactionGroups);

    @IterableMapping(qualifiedByName = "toTransactionReadDto")
    List<TransactionReadDto> mapTransactions(List<Transaction> transactions);

}
