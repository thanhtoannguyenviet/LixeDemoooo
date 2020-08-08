package Server.model.DTO;

import Server.model.DB.RatingEntity;

public class RatingInDTO {
    private String apiToken = null;
    private RatingEntity ratingEntity = new RatingEntity();

    public RatingInDTO() {
    }

    public RatingInDTO(String apiToken, RatingEntity ratingEntity) {
        this.apiToken = apiToken;
        this.ratingEntity = ratingEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public RatingEntity getRatingEntity() {
        return ratingEntity;
    }

    public void setRatingEntity(RatingEntity ratingEntity) {
        this.ratingEntity = ratingEntity;
    }
}
