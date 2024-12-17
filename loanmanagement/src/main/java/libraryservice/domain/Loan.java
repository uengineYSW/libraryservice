package libraryservice.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import libraryservice.LoanmanagementApplication;
import libraryservice.domain.LoanCreated;
import libraryservice.domain.LoanReturned;
import lombok.Data;

@Entity
@Table(name = "Loan_table")
@Data
//<<< DDD / Aggregate Root
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loanId;

    @Embedded
    private Member member;

    @Embedded
    private LoanPeriod loanPeriod;

    private Date loanDate;

    private Date returnDueDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    @Embedded
    private BookId bookId;

    @PostPersist
    public void onPostPersist() {
        LoanCreated loanCreated = new LoanCreated(this);
        loanCreated.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        LoanReturned loanReturned = new LoanReturned(this);
        loanReturned.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static LoanRepository repository() {
        LoanRepository loanRepository = LoanmanagementApplication.applicationContext.getBean(
            LoanRepository.class
        );
        return loanRepository;
    }
}
//>>> DDD / Aggregate Root
