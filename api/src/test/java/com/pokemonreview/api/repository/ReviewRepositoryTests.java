package com.pokemonreview.api.repository;

import com.pokemonreview.api.models.Review;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReviewRepositoryTests {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewRepositoryTests(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Test
    public void ReviewRepository_saveAll_ReturnSavedReview() {
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(6)
                .build();
        Review savedReview = reviewRepository.save(review);
        Assertions.assertThat(savedReview).isNotNull();
        Assertions.assertThat(savedReview.getId()).isGreaterThan(0);
    }

    @Test
    public void ReviewRepository_GetAll_ReturnMoreThanOneReview() {
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(6)
                .build();
        Review review2 = Review.builder()
                .title("title")
                .content("content")
                .stars(6)
                .build();

        reviewRepository.save(review);
        reviewRepository.save(review2);

        List<Review> reviewList = reviewRepository.findAll();
        Assertions.assertThat(reviewList).isNotNull();
        Assertions.assertThat(reviewList.size()).isEqualTo(2);
    }

    @Test
    public void ReviewRepository_FindById_ReturnSavedReview() {
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(6)
                .build();
        reviewRepository.save(review);
        Review reviewReturn = reviewRepository.findById(review.getId()).get();
        Assertions.assertThat(reviewReturn).isNotNull();
    }

    @Test
    public void ReviewRepository_updateReview_ReturnReview() {
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(6)
                .build();
        reviewRepository.save(review);
        Review reviewSave = reviewRepository.findById(review.getId()).get();
        reviewSave.setTitle("title");
        reviewSave.setContent("Raichu");

        Review updatePokemon = reviewRepository.save(reviewSave);
        Assertions.assertThat(updatePokemon.getTitle()).isNotNull();
        Assertions.assertThat(updatePokemon.getContent()).isNotNull();
    }

    @Test
    public void ReviewRepository_ReviewDelete_ReturnReviewEmpty() {
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(6)
                .build();
        reviewRepository.save(review);
        reviewRepository.deleteById(review.getId());
        Optional<Review> reviewReturn = reviewRepository.findById(review.getId());

        Assertions.assertThat(reviewReturn).isEmpty();
    }

}
