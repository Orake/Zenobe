package evChargingManager;

import java.time.LocalTime;

    public class EVChargingManager {

        public enum EVType {
            SCHOOL_BUS,
            COMMUTER_BUS,
        }

        public int getChargeDifferential(EVType evType, int currentCharge, LocalTime time) {
            int targetCharge = 0;

            if (currentCharge < 50) {
                targetCharge = 80;
            } else if (currentCharge >= 50 && currentCharge <= 60) {
                targetCharge = 70;
            } else if (currentCharge > 60) {
                targetCharge = 50;
            }

            if (evType == EVType.SCHOOL_BUS) {
                if (time.isBefore(LocalTime.of(8, 0))) {
                    targetCharge = 90;
                } else if (time.isAfter(LocalTime.of(8, 0)) && time.isBefore(LocalTime.of(11, 0))) {
                    targetCharge = 50;
                } else if (time.isAfter(LocalTime.of(18, 0)) && time.isBefore(LocalTime.of(0, 0))) {
                    targetCharge = 30;
                }
            } else if (evType == EVType.COMMUTER_BUS) {
                if (time.isAfter(LocalTime.of(3, 0)) && time.isBefore(LocalTime.of(7, 0))) {
                    targetCharge = 95;
                } else if (time.isAfter(LocalTime.of(23, 15))) {
                    targetCharge = 30;
                }
            }

            return targetCharge - currentCharge;
        }
    }


