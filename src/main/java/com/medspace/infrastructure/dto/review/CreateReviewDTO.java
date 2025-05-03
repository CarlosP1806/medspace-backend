package com.medspace.infrastructure.dto.review;

import com.medspace.domain.model.Review;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewDTO {

    @NotNull
    private Review.Type type;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    @NotBlank
    private String comment;

    private Long clinicId;

    public Review toReview() {
        Review review = new Review();
        review.setType(type);
        review.setRating(rating);
        review.setComment(comment);
        return review;
    }
}

