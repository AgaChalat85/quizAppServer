package pl.agnieszkachalat.quizapp.enums;

import java.util.Arrays;

public enum HttpStatusEnum {
    OK(200, "OK"),
    UNAUTHENTICATED(401, "Unauthenticated"),
    NOT_FOUND(404, "No question found"),
    TOO_MANY_REQUESTS(429, "Too many requests"),
    UNEXPECTED(999, "An unexcepted status");
    
    private final int statusCode;
    private final String description;
    
    private HttpStatusEnum(int statusCode, String description) {
        this.statusCode = statusCode;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }
    
    public static HttpStatusEnum findStatusByCode(int statusCode) {
        return Arrays.asList(values()).stream()
                                      .filter(status -> status.getStatusCode() == statusCode)
                                      .findFirst()
                                      .orElse(UNEXPECTED);
    }
}
