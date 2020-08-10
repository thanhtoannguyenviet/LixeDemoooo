package Server.model.DTO;

import Server.model.DB.SeriCategoryfilmEntity;

public class SeriCategoryfilmInDTO {
    private String apiToken = null;
    private SeriCategoryfilmEntity seriCategoryfilmEntity = new SeriCategoryfilmEntity();

    public SeriCategoryfilmInDTO() {
    }

    public SeriCategoryfilmInDTO(String apiToken, SeriCategoryfilmEntity seriCategoryfilmEntity) {
        this.apiToken = apiToken;
        this.seriCategoryfilmEntity = seriCategoryfilmEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public SeriCategoryfilmEntity getSeriCategoryfilmEntity() {
        return seriCategoryfilmEntity;
    }

    public void setSeriCategoryfilmEntity(SeriCategoryfilmEntity seriCategoryfilmEntity) {
        this.seriCategoryfilmEntity = seriCategoryfilmEntity;
    }
}
