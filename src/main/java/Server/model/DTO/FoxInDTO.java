package Server.model.DTO;

public class FoxInDTO {
    private String apiToken = null;
    private FoxDTO foxObj = new FoxDTO();

    public FoxInDTO() {
    }

    public FoxInDTO(String apiToken, FoxDTO foxObj) {
        this.apiToken = apiToken;
        this.foxObj = foxObj;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public FoxDTO getFoxObj() {
        return foxObj;
    }

    public void setFoxObj(FoxDTO foxObj) {
        this.foxObj = foxObj;
    }
}
