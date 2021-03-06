package course.spring.webmvc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Document(collection = "articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Article {
    @Id
    @Pattern(regexp = "[0-9a-f]{24}")
    private String id;

    @NonNull
    @NotNull
    @Size(min = 2, max = 60, message = "Title must be between 2 and 60 characters long.")
    private String title;

    @NonNull
    @NotNull
    @Size(min = 10, max = 2048, message = "Content must be between 10 and 2048 characters long.")
    private String content;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime created = LocalDateTime.now();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime modified = LocalDateTime.now();

    @Pattern(regexp = "[0-9a-f]{24}")
    private String authorId;

    @Transient
    private String authorName;
}
