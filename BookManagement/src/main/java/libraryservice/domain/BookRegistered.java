package libraryservice.domain;

import java.time.LocalDate;
import java.util.*;
import libraryservice.domain.*;
import libraryservice.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class BookRegistered extends AbstractEvent {

    private String bookId;
    private String title;
    private String author;
    private String publisher;
    private BookCategory category;
    private BookStatus status;
    private Isbn isbn;

    public BookRegistered(Book aggregate) {
        super(aggregate);
    }

    public BookRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
