package com.booking.entities;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDates {
    @SerializedName("checkin")
    private String checkIn;
    @SerializedName("checkout")
    private String checkOut;
}
