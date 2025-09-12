package entities;

import java.time.LocalDateTime;

public class SeatLock {
    private Show show;
    private Seat seat;
    private LocalDateTime lockTime;
    private Integer timeoutInSeconds;
    private User user;

    public SeatLock(Show show, Seat seat, User user, LocalDateTime lockTime, Integer timeoutInSeconds) {
        this.show = show;
        this.seat = seat;
        this.user = user;
        this.lockTime = lockTime;
        this.timeoutInSeconds = timeoutInSeconds;
    }

    public boolean isLockExpired() {
        return LocalDateTime.now().isAfter(lockTime.plusSeconds(timeoutInSeconds));
    }
}
