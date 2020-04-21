package ch.zhaw.prog2.jukebox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MusicJukeBoxTest {

    private JukeBox jukeBox = null;

    @BeforeEach
    public void setUp() throws Exception {
        jukeBox = new MusicJukeBox();
    }

    @Test
    public void testPlayOfNoneExistingSong() {
        // test empty music juke box
        try {
            jukeBox.playTitle("NoneExistingTitle");
            fail("should not be executed");
        } catch (JukeBoxException e) {

        }
    }

    @Test
    public void testGetPlayList() {
        jukeBox.addSong(new SongStub(""));

        assertEquals(1, jukeBox.getPlayList().size());
    }

    @Test
    public void testPlay() {
        jukeBox.addSong(new SongStub("d"));
        jukeBox.playTitle("d");

        Song song = jukeBox.getActualSong();
        assertEquals("d", song.getTitle());

        assertTrue(song.isPlaying());
    }

    @Test
    public void testPlayOfAlreadyPlayingSong() {
        jukeBox.addSong(new SongStub("d"));
        jukeBox.playTitle("d");
        assertThrows(JukeBoxException.class, () -> { jukeBox.playTitle("d"); });
    }
}
