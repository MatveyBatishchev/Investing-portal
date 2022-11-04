package ru.investing_portal.mappers;

import org.mapstruct.*;
import ru.investing_portal.dto.TransactionCreateDto;
import ru.investing_portal.dto.TransactionReadDto;
import ru.investing_portal.models.domain.Transaction;
import ru.investing_portal.repos.CoinRepository;
import ru.investing_portal.repos.PortfolioRepository;

import java.util.List;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class,
        uses = {CoinRepository.class, PortfolioRepository.class, CoinMapper.class})
public interface TransactionMapper {

    @Mapping(target="coinShortDto", source = "coin", qualifiedByName = "toCoinShortDto")
    @Mapping(target="portfolioId", expression = "java(transaction.getPortfolio().getId())")
    @Named("toTransactionReadDto")
    TransactionReadDto toReadDto(Transaction transaction);

    @Mapping(target = "id", ignore = true) // during creating id will generate automatically
    @Mapping(target="coin", source = "coinId", qualifiedByName = "getCoinReferenceById")
    @Mapping(target="portfolio", source = "portfolioId", qualifiedByName = "getPortfolioReferenceById")
    Transaction toTransaction(TransactionCreateDto transactionCreateDto);

    @Mapping(target="coin", ignore = true)
    @Mapping(target="portfolio", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTransactionFromDto(TransactionCreateDto transactionCreateDto, @MappingTarget Transaction entity);

    @IterableMapping(qualifiedByName = "toTransactionReadDto")
    List<TransactionReadDto> map(List<Transaction> transactions);
}
