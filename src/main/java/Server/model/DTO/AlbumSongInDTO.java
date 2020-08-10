package Server.model.DTO;

import Server.model.DB.AlbumSongEntity;

public class AlbumSongInDTO {
    private String apiToken = null;
    private AlbumSongEntity albumSongEntity = new AlbumSongEntity();

    public AlbumSongInDTO() {
    }

    public AlbumSongInDTO(String apiToken, AlbumSongEntity albumSongEntity) {
        this.apiToken = apiToken;
        this.albumSongEntity = albumSongEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public AlbumSongEntity getAlbumSongEntity() {
        return albumSongEntity;
    }

    public void setAlbumSongEntity(AlbumSongEntity albumSongEntity) {
        this.albumSongEntity = albumSongEntity;
    }
}
