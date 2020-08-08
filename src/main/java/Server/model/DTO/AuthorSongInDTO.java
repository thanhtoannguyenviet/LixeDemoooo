package Server.model.DTO;

import Server.model.DB.AuthorSongEntity;

public class AuthorSongInDTO {
    private String apiToken = null;
    private AuthorSongEntity authorSongEntity = new AuthorSongEntity();

    public AuthorSongInDTO() {
    }

    public AuthorSongInDTO(String apiToken, AuthorSongEntity authorSongEntity) {
        this.apiToken = apiToken;
        this.authorSongEntity = authorSongEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public AuthorSongEntity getAuthorSongEntity() {
        return authorSongEntity;
    }

    public void setAuthorSongEntity(AuthorSongEntity authorSongEntity) {
        this.authorSongEntity = authorSongEntity;
    }
}
