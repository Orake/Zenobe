package evChargingManager;

import org.testng.annotations.Test;

import java.time.LocalTime;

import static org.testng.Assert.assertEquals;

public class EVChargingManagerTest {

    @Test
    public void testGetChargeDifferential() {
        EVChargingManager chargingManager = new EVChargingManager();

        // Test school bus charging before 8am
        int chargeDiff = chargingManager.getChargeDifferential(EVChargingManager.EVType.SCHOOL_BUS, 30, LocalTime.of(7, 0));
        assertEquals(chargeDiff, 60);

        // Test school bus discharging between 8am and 11am
        chargeDiff = chargingManager.getChargeDifferential(EVChargingManager.EVType.SCHOOL_BUS, 55, LocalTime.of(9, 0));
        assertEquals(chargeDiff, -5);

        // Test school bus discharging between 6pm and 12am
        chargeDiff = chargingManager.getChargeDifferential(EVChargingManager.EVType.SCHOOL_BUS, 70, LocalTime.of(22, 0));
        assertEquals(chargeDiff, -20);
        // Test commuter bus charging between 3am and 7am
        chargeDiff = chargingManager.getChargeDifferential(EVChargingManager.EVType.COMMUTER_BUS, 50, LocalTime.of(5, 0));
        assertEquals(chargeDiff, 45);

        // Test commuter bus discharging after 11:15pm
        chargeDiff = chargingManager.getChargeDifferential(EVChargingManager.EVType.COMMUTER_BUS, 60, LocalTime.of(23, 30));
        assertEquals(chargeDiff, -30);

    }
}

