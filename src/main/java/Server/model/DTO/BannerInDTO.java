package Server.model.DTO;

import Server.model.DB.BannerEntity;

public class BannerInDTO {
    private String apiToken = null;
    private BannerEntity bannerEntity = new BannerEntity();

    public BannerInDTO() {
    }

    public BannerInDTO(String apiToken, BannerEntity bannerEntity) {
        this.apiToken = apiToken;
        this.bannerEntity = bannerEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public BannerEntity getBannerEntity() {
        return bannerEntity;
    }

    public void setBannerEntity(BannerEntity bannerEntity) {
        this.bannerEntity = bannerEntity;
    }
}
