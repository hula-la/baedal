package com.baedal.monolithic.domain.review.api;

import com.baedal.monolithic.domain.Constants;
import com.baedal.monolithic.domain.DatabaseCleaner;
import com.baedal.monolithic.domain.account.entity.Account;
import com.baedal.monolithic.domain.account.entity.Role;
import com.baedal.monolithic.domain.account.repository.AccountRepository;
import com.baedal.monolithic.domain.auth.util.JwtProvider;
import com.baedal.monolithic.domain.order.repository.OrderMenuRepository;
import com.baedal.monolithic.domain.order.repository.OrderRepository;
import com.baedal.monolithic.domain.review.dto.ReviewDto;
import com.baedal.monolithic.domain.review.entity.Review;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ReviewControllerTest {

    protected static String accessToken;

    @LocalServerPort
    int port;

    protected static final String SOCIAL_ID = "sdfadsf";

    private static final String AUTHORIZATION_PREFIX = "Bearer ";
    private static final Long storeId = 1L;
    private static ReviewDto.Info review;
    private static ReviewDto.Info review2;

    private static Long preReviewId;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private DatabaseCleaner databaseCleaner;


    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        Account account = saveAccount();

        savePreReviews(account);
    }


    @AfterEach
    void deleteAll() {
        databaseCleaner.execute();
    }


    @Test
    @DisplayName("리뷰를 저장한다")
    void 리뷰_등록() {
        // given
        ReviewDto.PostReq newReviewPostReq = new ReviewDto.PostReq(
                1L,
                Constants.REVIEW_RATING,
                Constants.REVIEW_CONTENT
        );

        // when
        ExtractableResponse<Response> response = saveReview(newReviewPostReq);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("가게에 등록된 리뷰를 조회한다")
    void 리뷰_리스트_조회() {
        // given, when
        ExtractableResponse<Response> response = findAllReviews(storeId);
        ReviewDto.GetRes actualResponse = response.as(ReviewDto.GetRes.class);

        List<ReviewDto.Info> expectedReviews = Arrays.asList(
                review2,
                review
        );

        ReviewDto.GetRes expectedResponse = new ReviewDto.GetRes(expectedReviews);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(actualResponse).usingRecursiveComparison()
                .ignoringActualNullFields()
                .ignoringExpectedNullFields()
                .isEqualTo(expectedResponse);
    }

    @Test
    @DisplayName("가게에 등록된 리뷰를 수정한다")
    void 리뷰_수정() {
        // given
        ReviewDto.PostReq updatedReviewPostReq = new ReviewDto.PostReq(
                1L,
                Constants.UPDATE_REVIEW_RATING,
                Constants.UPDATE_REVIEW_CONTENT
        );

        // when
        ExtractableResponse<Response> response = updateReview(review.getId(), updatedReviewPostReq);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("작성한 리뷰를 삭제한다")
    void 리뷰_삭제() {
        // given, when
        ExtractableResponse<Response> response = deleteReview(preReviewId);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    static ExtractableResponse<Response> saveReview(final ReviewDto.PostReq reviewPostReq) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", AUTHORIZATION_PREFIX + accessToken)
                .body(reviewPostReq)
                .when().post("/reviews/stores/"+storeId)
                .then().log().all().extract();
    }

    static ExtractableResponse<Response> findAllReviews(final Long storeId) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", AUTHORIZATION_PREFIX + accessToken)
                .when().get("/reviews/stores/"+storeId)
                .then().log().all().extract();
    }

    static ExtractableResponse<Response> updateReview(final Long reviewId, ReviewDto.PostReq reviewPostReq) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", AUTHORIZATION_PREFIX + accessToken)
                .body(reviewPostReq)
                .when().put("/reviews/"+reviewId)
                .then().log().all().extract();
    }

    static ExtractableResponse<Response> deleteReview(final Long reviewId) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", AUTHORIZATION_PREFIX + accessToken)
                .when().delete("/reviews/"+reviewId)
                .then().log().all().extract();
    }


    private Account saveAccount() {
        Account save = accountRepository.save(Account.builder()
                .socialId(SOCIAL_ID)
                .role(Role.USER)
                .build());

        accessToken = jwtProvider.createAccessToken(String.valueOf(save.getId()));
        return save;
    }


    public static Long getReviewIdAfterSave(
            final ReviewDto.PostReq reviewReq) {
        return Long.valueOf(
                saveReview(reviewReq)
                        .header("location")
                        .split("/")[6]);
    }

    private void savePreReviews(Account account) {
        ReviewDto.PostReq reviewReq1 = new ReviewDto.PostReq(
                Constants.REVIEW_ORDER_ID_1,
                Constants.REVIEW_RATING,
                Constants.REVIEW_CONTENT
        );
        ReviewDto.PostReq reviewReq2 = new ReviewDto.PostReq(
                Constants.REVIEW_ORDER_ID_2,
                Constants.REVIEW_RATING,
                Constants.REVIEW_CONTENT
        );


        preReviewId = getReviewIdAfterSave(reviewReq1);
        review = ReviewDto.Info.builder()
                .id(preReviewId)
                .nickName(account.getNickname())
                .menus(null)
                .rating(Constants.REVIEW_RATING)
                .content(Constants.REVIEW_CONTENT)
                .build();

        review2 = ReviewDto.Info.builder()
                .id(getReviewIdAfterSave(reviewReq2))
                .nickName(account.getNickname())
                .menus(null)
                .rating(Constants.REVIEW_RATING)
                .content(Constants.REVIEW_CONTENT)
                .build();

    }


}