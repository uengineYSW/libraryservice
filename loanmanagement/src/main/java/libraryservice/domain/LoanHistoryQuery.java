package libraryservice.domain;

import java.util.Date;
import lombok.Data;

@Data
public class LoanHistoryQuery {

    private Long loanId;
    private String memberId;
    private String bookId;
    private Date loanDate;
    private Date returnDueDate;
    private LoanStatus status;
}
