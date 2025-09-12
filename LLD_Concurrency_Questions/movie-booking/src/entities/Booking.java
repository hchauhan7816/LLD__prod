package entities;

import java.util.List;

import enums.BookingStatusEnum;

public class Booking {
    private Integer id;
    private User user;
    private Show show;
    private List<Seat> seats;
    private BookingStatusEnum status;
}
