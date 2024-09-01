package org.beaoh.food.domain.generic.time;

import jakarta.persistence.Embeddable;
import java.time.LocalTime;
import lombok.ToString;
import org.beaoh.base.domain.ValueObject;

@Embeddable
@ToString
public class TimePeriod extends ValueObject<TimePeriod> {
    private LocalTime startTime;
    private LocalTime endTime;

    protected TimePeriod() {}
    public TimePeriod(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean contains(LocalTime dateTime) {
        return (dateTime.isAfter(startTime) || dateTime.equals(startTime)) &&
                (dateTime.isBefore(endTime) || dateTime.equals(endTime));
    }

    public TimePeriod putOffHours(long hours) {
        return new TimePeriod(startTime.plusHours(hours), endTime.plusHours(hours));
    }
}
