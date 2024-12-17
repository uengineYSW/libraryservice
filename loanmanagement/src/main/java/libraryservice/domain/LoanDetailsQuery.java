package libraryservice.domain;

import java.util.Date;
import lombok.Data;

@Data
public class LoanDetailsQuery {

    private Long loanId;
    private Member member;
    private LoanPeriod loanPeriod;
    private Date loanDate;
    private Date returnDueDate;
    private LoanStatus status;
}
