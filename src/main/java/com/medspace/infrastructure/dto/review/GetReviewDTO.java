package com.medspace.infrastructure.dto.review;

import java.time.Instant;
import com.medspace.domain.model.Review;
import com.medspace.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetReviewDTO {
    private Long id;
    private String userName;
    private String userPfpPath;
    private Integer rating;
    private String body;
    private Instant createdAt;

    public GetReviewDTO(Review review, User author) {
        this.id = review.getId();
        this.userName = author.getFullName();
        this.userPfpPath = author.getPfpPath();
        this.rating = review.getRating();
        this.body = review.getComment();
        this.createdAt = review.getCreatedAt();
    }
}
