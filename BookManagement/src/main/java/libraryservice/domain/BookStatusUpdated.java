package libraryservice.domain;

import java.time.LocalDate;
import java.util.*;
import libraryservice.domain.*;
import libraryservice.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class BookStatusUpdated extends AbstractEvent {

    private String bookId;
    private BookStatus status;

    public BookStatusUpdated(Book aggregate) {
        super(aggregate);
    }

    public BookStatusUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
