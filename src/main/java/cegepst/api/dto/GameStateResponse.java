package cegepst.api.dto;

import java.util.List;

public record GameStateResponse(String phase, String phaseDisplay, List<CardDto> humanCards, List<CardDto> botCards,
        List<CardDto> communityCards, int pot, int humanChips, int botChips, String humanHandName, String botHandName,
        String result, boolean gameOver, List<String> availableActions) {
}
