package Server.model.DTO;

import Server.model.DB.CommentEntity;

public class CommentInDTO {
    private String apiToken = null;
    private CommentEntity commentEntity = new CommentEntity();

    public CommentInDTO() {
    }

    public CommentInDTO(String apiToken, CommentEntity commentEntity) {
        this.apiToken = apiToken;
        this.commentEntity = commentEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public CommentEntity getCommentEntity() {
        return commentEntity;
    }

    public void setCommentEntity(CommentEntity commentEntity) {
        this.commentEntity = commentEntity;
    }
}
