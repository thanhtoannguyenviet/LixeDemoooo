package Server.model.DTO;

import Server.model.DB.CategoryfilmEntity;

public class CategoryfilmInDTO {
    private String apiToken = null;
    private CategoryfilmEntity categoryfilmEntity = new CategoryfilmEntity();

    public CategoryfilmInDTO() {
    }

    public CategoryfilmInDTO(String apiToken, CategoryfilmEntity categoryfilmEntity) {
        this.apiToken = apiToken;
        this.categoryfilmEntity = categoryfilmEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public CategoryfilmEntity getCategoryfilmEntity() {
        return categoryfilmEntity;
    }

    public void setCategoryfilmEntity(CategoryfilmEntity categoryfilmEntity) {
        this.categoryfilmEntity = categoryfilmEntity;
    }
}
