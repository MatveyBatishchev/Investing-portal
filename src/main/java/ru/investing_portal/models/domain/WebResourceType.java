package ru.investing_portal.models.domain;

/**
 * Тип веб-ресруса, связанного с монетой
 */
public enum WebResourceType {

    WEB_SITE(0),
    SOCIAL_NETWORK(1),
    EXPLORER(2),
    WALLET(3);

    private final int code;

    WebResourceType(int code) {
        this.code = code;
    }

    /**
     * @return код типа веб ресурса в виде цифрового кода
     */
    public int getCode() {
        return code;
    }

}
