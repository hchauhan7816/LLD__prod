package temp.seatLockProvider.providers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import entities.Seat;
import entities.SeatLock;
import entities.Show;
import entities.User;
import temp.seatLockProvider.ISeatLockProvider;

public class SeatLockProvider implements ISeatLockProvider {

    private final Integer timeoutInSeconds;

    private final Map<Show, Map<Seat, SeatLock>> seatLocks;

    public SeatLockProvider(Integer timeoutInSeconds) {
        this.timeoutInSeconds = timeoutInSeconds;
        this.seatLocks = new ConcurrentHashMap<>();
    }

    @Override
    public void lockSeats(Show show, List<Seat> seats, User user) {
        Map<Seat, SeatLock> seatLockMap = seatLocks.computeIfAbsent(show, s -> new ConcurrentHashMap<>());

        synchronized (seatLockMap) {

            for (Seat seat : seats) {
                if (seatLockMap.containsKey(seat) && !seatLockMap.get(seat).isLockExpired()) {
                    throw new RuntimeException("Seat is already locked");
                }
            }

            LocalDateTime now = LocalDateTime.now();

            for (Seat seat : seats) {
                SeatLock seatLock = new SeatLock(show, seat, user, now, timeoutInSeconds);
                seatLockMap.put(seat, seatLock);
            }
        }

    }

    @Override
    public void unlockSeats(Show show, List<Seat> seats) {
        Map<Seat, SeatLock> seatLockMap = seatLocks.get(show);

        synchronized (seatLockMap) {
            for (Seat seat : seats) {
                seatLockMap.remove(seat);
            }
        }
    }

    @Override
    public boolean validateSeatLock(Show show, Seat seat, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateSeatLock'");
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        Map<Seat, SeatLock> seatLockMap = seatLocks.get(show);

        synchronized (seatLockMap) {
            return seatLockMap.entrySet().stream().collect(Collectors.toList());
        }
    }

}
