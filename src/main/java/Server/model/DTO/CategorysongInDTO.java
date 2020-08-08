package Server.model.DTO;

import Server.model.DB.CategorysongEntity;

public class CategorysongInDTO {
    private String apiToken = null;
    private CategorysongEntity categorysongEntity = new CategorysongEntity();

    public CategorysongInDTO() {
    }

    public CategorysongInDTO(String apiToken, CategorysongEntity categorysongEntity) {
        this.apiToken = apiToken;
        this.categorysongEntity = categorysongEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public CategorysongEntity getCategorysongEntity() {
        return categorysongEntity;
    }

    public void setCategorysongEntity(CategorysongEntity categorysongEntity) {
        this.categorysongEntity = categorysongEntity;
    }
}
