package Server.model.DTO;

import Server.model.DB.ActorEntity;

public class ActorInDTO {
    private String apiToken = null;
    private ActorEntity actorEntity = new ActorEntity();

    public ActorInDTO() {
    }

    public ActorInDTO(String apiToken, ActorEntity actorEntity) {
        this.apiToken = apiToken;
        this.actorEntity = actorEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public ActorEntity getActorEntity() {
        return actorEntity;
    }

    public void setActorEntity(ActorEntity actorEntity) {
        this.actorEntity = actorEntity;
    }
}
