package Server.model.DTO;

import Server.model.DB.ImageEntity;

public class ImageInDTO {
    private String apiToken = null;
    private ImageEntity imageEntity = new ImageEntity();

    public ImageInDTO() {
    }

    public ImageInDTO(String apiToken, ImageEntity imageEntity) {
        this.apiToken = apiToken;
        this.imageEntity = imageEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
    }
}
