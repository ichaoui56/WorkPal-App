package org.workPall.services.interfaces;

import java.time.LocalDateTime;

public interface ReservationServiceInter {
    boolean reserveSpace( int spaceId,LocalDateTime startTime, LocalDateTime endTime, int userId);
}
