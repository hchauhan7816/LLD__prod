package temp.seatLockProvider;

import java.util.List;

import entities.Seat;
import entities.Show;
import entities.User;

public interface ISeatLockProvider {
    void lockSeats(Show show, List<Seat> seats, User user);

    void unlockSeats(Show show, List<Seat> seats);

    boolean validateSeatLock(Show show, Seat seat, User user);

    List<Seat> getLockedSeats(Show show);
}
