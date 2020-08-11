package Server.model.DTO;

public class APIAccountDTO {
    private String apiToken = null;

    public APIAccountDTO() {
    }

    public APIAccountDTO(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }
}
