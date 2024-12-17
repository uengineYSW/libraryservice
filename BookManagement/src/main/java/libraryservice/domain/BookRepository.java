package libraryservice.domain;

import java.util.Date;
import java.util.List;
import libraryservice.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepository
    extends PagingAndSortingRepository<Book, String> {
    @Query(
        value = "select book " +
        "from Book book " +
        "where(:bookId is null or book.bookId like %:bookId%) and (:title is null or book.title like %:title%) and (:isbn is null or book.isbn = :isbn) and (:author is null or book.author like %:author%) and (:publisher is null or book.publisher like %:publisher%) and (:category is null or book.category = :category) and (:status is null or book.status = :status)"
    )
    List<Book> bookList(
        String bookId,
        String title,
        ISBN isbn,
        String author,
        String publisher,
        BookCategory category,
        BookStatus status,
        Pageable pageable
    );

    @Query(
        value = "select book " +
        "from Book book " +
        "where(:bookId is null or book.bookId like %:bookId%) and (:title is null or book.title like %:title%) and (:isbn is null or book.isbn = :isbn) and (:author is null or book.author like %:author%) and (:publisher is null or book.publisher like %:publisher%) and (:category is null or book.category = :category) and (:status is null or book.status = :status)"
    )
    Book bookDetails(
        String bookId,
        String title,
        ISBN isbn,
        String author,
        String publisher,
        BookCategory category,
        BookStatus status
    );
}
