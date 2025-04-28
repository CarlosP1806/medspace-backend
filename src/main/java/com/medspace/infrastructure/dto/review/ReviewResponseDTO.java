package com.medspace.infrastructure.dto.review;

import com.medspace.domain.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {
    private Long id;
    private String type;
    private Integer rating;
    private String comment;
    private Instant createdAt;
    private Long authorId;

    public static ReviewResponseDTO fromReview(Review review) {
        return new ReviewResponseDTO(
            review.getId(),
            review.getType().name(),
            review.getRating(),
            review.getComment(),
            review.getCreatedAt(),
            review.getAuthor() != null ? review.getAuthor().getId() : null
        );
    }
}
