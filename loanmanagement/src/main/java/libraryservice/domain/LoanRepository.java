package libraryservice.domain;

import java.util.Date;
import java.util.List;
import libraryservice.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "loans", path = "loans")
public interface LoanRepository extends PagingAndSortingRepository<Loan, Long> {
    @Query(
        value = "select loan " +
        "from Loan loan " +
        "where(:loanId is null or loan.loanId = :loanId) and (:memberId is null or loan.memberId like %:memberId%) and (:bookId is null or loan.bookId like %:bookId%) and (:loanDate is null or loan.loanDate = :loanDate) and (:returnDueDate is null or loan.returnDueDate = :returnDueDate) and (:status is null or loan.status = :status)"
    )
    List<Loan> loanHistory(
        Long loanId,
        String memberId,
        String bookId,
        Date loanDate,
        Date returnDueDate,
        LoanStatus status,
        Pageable pageable
    );

    @Query(
        value = "select loan " +
        "from Loan loan " +
        "where(:loanId is null or loan.loanId = :loanId) and (:member is null or loan.member = :member) and (:loanPeriod is null or loan.loanPeriod = :loanPeriod) and (:loanDate is null or loan.loanDate = :loanDate) and (:returnDueDate is null or loan.returnDueDate = :returnDueDate) and (:status is null or loan.status = :status)"
    )
    Loan loanDetails(
        Long loanId,
        Member member,
        LoanPeriod loanPeriod,
        Date loanDate,
        Date returnDueDate,
        LoanStatus status
    );
}
