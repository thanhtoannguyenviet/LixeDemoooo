package Server.model.DTO;

import Server.model.DB.AuthorEntity;

public class AuthorInDTO {
    private String apiToken = null;
    private AuthorEntity authorEntity = new AuthorEntity();

    public AuthorInDTO() {
    }

    public AuthorInDTO(String apiToken, AuthorEntity authorEntity) {
        this.apiToken = apiToken;
        this.authorEntity = authorEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public AuthorEntity getAuthorEntity() {
        return authorEntity;
    }

    public void setAuthorEntity(AuthorEntity authorEntity) {
        this.authorEntity = authorEntity;
    }
}
