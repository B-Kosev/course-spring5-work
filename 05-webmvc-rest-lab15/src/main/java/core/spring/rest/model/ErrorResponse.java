package core.spring.rest.model;

import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    @NonNull
    private int status;
    @NonNull
    private String message;
    private String stacktrace;
}
