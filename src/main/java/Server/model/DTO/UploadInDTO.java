package Server.model.DTO;

import Server.model.DB.UploadEntity;

public class UploadInDTO {
    private String apiToken = null;
    private UploadEntity uploadEntity = new UploadEntity();

    public UploadInDTO() {
    }

    public UploadInDTO(String apiToken, UploadEntity uploadEntity) {
        this.apiToken = apiToken;
        this.uploadEntity = uploadEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public UploadEntity getUploadEntity() {
        return uploadEntity;
    }

    public void setUploadEntity(UploadEntity uploadEntity) {
        this.uploadEntity = uploadEntity;
    }
}
