package com.nexoscredibanco.Service;

import com.nexoscredibanco.Entity.Card;
import com.nexoscredibanco.Exception.CardNotFoundException;
import com.nexoscredibanco.Repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    public CardServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testActivateCard_Success() {
        Card card = new Card();
        card.setCardNumber(123456789L);
        card.setActive(false);

        when(cardRepository.findByCardNumber(123456789L)).thenReturn(Optional.of(card));

        Card activatedCard = cardService.activateCard(123456789L);

        assertEquals(null, activatedCard);
    }

    @Test
    public void testActivateCard_CardNotFound() {
        when(cardRepository.findByCardNumber(123456789L)).thenReturn(Optional.empty());

        assertThrows(CardNotFoundException.class, () -> cardService.activateCard(123456789L));
    }
}
