package fr.agregio.energy.core.models;

import fr.agregio.energy.core.exceptions.InvalidDataException;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OfferTest {

    @Test
    void shouldThrowInvalidDataExceptionWhenOfferTimeBlockIsNullOrEmpty(){
        PowerStation powerStation = new PowerStation(PowerStationType.SOLAR, 8000);

        InvalidDataException expectedException = assertThrows(InvalidDataException.class,
                () -> new Offer(MarketType.PRIMARY_RESERVE, null,  List.of(powerStation)));

        assertEquals("Offer time blocks cannot be null or empty", expectedException.getMessage());
    }

    @Test
    void shouldThrowInvalidDataExceptionWhenOfferPowerStationsIsNullOrEmpty(){
        TimeBlock timeBlock = new TimeBlock(2000, 200d);

        InvalidDataException expectedException = assertThrows(InvalidDataException.class,
                () -> new Offer(MarketType.PRIMARY_RESERVE, List.of(timeBlock), Collections.emptyList()));

        assertEquals("Offer power stations cannot be null or empty", expectedException.getMessage());
    }

}
