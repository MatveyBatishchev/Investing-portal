package ru.investing_portal.models.domain;

/**
 * Тип транзакции в портфолио пользователя
 */
public enum TransactionType {

    BUY(0),
    SELL(1),
    TRANSFER_IN(2),
    TRANSFER_OUT(3);

    private final int code;

    TransactionType(int code) {
        this.code = code;
    }

    /**
     * @return цифровой код типа транзакции
     */
    public int getCode() {
        return code;
    }

}
