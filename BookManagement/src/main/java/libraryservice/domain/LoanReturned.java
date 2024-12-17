package libraryservice.domain;

import java.util.*;
import libraryservice.domain.*;
import libraryservice.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class LoanReturned extends AbstractEvent {

    private Long loanId;
    private Object member;
    private Object loanPeriod;
    private Date loanDate;
    private Date returnDueDate;
    private Object status;
    private Object bookId;
}
