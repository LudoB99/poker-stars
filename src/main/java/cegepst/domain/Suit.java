package cegepst.domain;

public enum Suit {
    HEARTS,
    DIAMONDS,
    CLUBS,
    SPADES;

    public String toEmoji() {
        return switch (this) {
            case HEARTS -> "♥️";
            case DIAMONDS -> "♦️";
            case CLUBS -> "♣️";
            case SPADES -> "♠️";
        };
    }
}