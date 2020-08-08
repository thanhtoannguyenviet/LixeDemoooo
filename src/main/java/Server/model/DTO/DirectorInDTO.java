package Server.model.DTO;

import Server.model.DB.DirectorEntity;

public class DirectorInDTO {
    private String apiToken = null;
    private DirectorEntity directorEntity = new DirectorEntity();

    public DirectorInDTO() {
    }

    public DirectorInDTO(String apiToken, DirectorEntity directorEntity) {
        this.apiToken = apiToken;
        this.directorEntity = directorEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public DirectorEntity getDirectorEntity() {
        return directorEntity;
    }

    public void setDirectorEntity(DirectorEntity directorEntity) {
        this.directorEntity = directorEntity;
    }
}
