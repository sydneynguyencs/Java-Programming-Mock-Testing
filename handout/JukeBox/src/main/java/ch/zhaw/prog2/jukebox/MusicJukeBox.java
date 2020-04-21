package ch.zhaw.prog2.jukebox;

import java.util.List;

public class MusicJukeBox implements JukeBox {
    private java.util.Hashtable<String, Song> playlist = new java.util.Hashtable<String, Song>();
    private Song actualSong;

    @Override
    public Song getActualSong() {
        return actualSong;
    }

    @Override
    public void addSong(Song song) {
        playlist.put(song.getTitle(), song);
    }

    @Override
    public void playTitle(String songTitle) throws JukeBoxException {
        if (playlist.containsKey(songTitle)) {
            actualSong = playlist.get(songTitle);
            actualSong.start();
        } else {
            throw new JukeBoxException("No song found with title '" + songTitle + "'");
        }
    }

    @Override
    public List<Song> getPlayList() {
        return List.copyOf(playlist.values());
    }
}
