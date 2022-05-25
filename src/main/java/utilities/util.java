package utilities;

import com.spring.dynamodb.controller.SongResponse;
import com.spring.dynamodb.controller.model.Song;
import com.spring.dynamodb.entity.SongEntity;

public class util {

    public static Song convertFromSongEntityToSong(SongEntity songFrom){

        Song song = new Song();
        song.setAlbumTitle(songFrom.getAlbumTitle());
        song.setArtist(songFrom.getArtist());
        song.setAwards(songFrom.getAwards());
        song.setSongTitle(songFrom.getSongTitle());

        return song;

    }

    public static SongResponse convertFromSongToSongReponse(Song songFrom){

        SongResponse song = new SongResponse();
        song.setAlbumTitle(songFrom.getAlbumTitle());
        song.setArtist(songFrom.getArtist());
        song.setAwards(songFrom.getAwards());
        song.setSongTitle(songFrom.getSongTitle());
        return song;
    }

}
