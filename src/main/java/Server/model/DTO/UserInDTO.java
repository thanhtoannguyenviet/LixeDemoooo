package Server.model.DTO;

import Server.model.DB.UserEntity;

public class UserInDTO {
    private String apiToken = null;
    private UserEntity userEntity = new UserEntity();

    public UserInDTO() {
    }

    public UserInDTO(String apiToken, UserEntity userEntity) {
        this.apiToken = apiToken;
        this.userEntity = userEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
