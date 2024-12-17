package libraryservice.domain;

import java.time.LocalDate;
import java.util.*;
import libraryservice.domain.*;
import libraryservice.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class LoanReturned extends AbstractEvent {

    private Long loanId;
    private Member member;
    private LoanPeriod loanPeriod;
    private Date loanDate;
    private Date returnDueDate;
    private LoanStatus status;
    private BookId bookId;

    public LoanReturned(Loan aggregate) {
        super(aggregate);
    }

    public LoanReturned() {
        super();
    }
}
//>>> DDD / Domain Event
