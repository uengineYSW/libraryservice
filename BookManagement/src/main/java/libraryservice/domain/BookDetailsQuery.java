package libraryservice.domain;

import java.util.Date;
import lombok.Data;

@Data
public class BookDetailsQuery {

    private String bookId;
    private String title;
    private ISBN isbn;
    private String author;
    private String publisher;
    private BookCategory category;
    private BookStatus status;
}
