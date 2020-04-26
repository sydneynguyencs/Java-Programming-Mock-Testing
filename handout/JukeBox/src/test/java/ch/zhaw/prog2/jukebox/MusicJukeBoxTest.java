package ch.zhaw.prog2.jukebox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class MusicJukeBoxTest {

    private JukeBox jukeBox;
    @Mock
    private Song mock;

    @BeforeEach
    public void setUp() throws Exception {
        jukeBox = new MusicJukeBox();
        MockitoAnnotations.initMocks(this);
        when(mock.getTitle()).thenReturn("d");
        when(mock.isPlaying()).thenReturn(true);
        doNothing().when(mock).start();
    }

    @Test
    public void testPlayOfNoneExistingSong() {
        // test empty music juke box
        /*try {
            jukeBox.playTitle("NoneExistingTitle");
            fail("should not be executed");
        } catch (JukeBoxException e) {
        }*/

        assertThrows(JukeBoxException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                jukeBox.playTitle("NoneExistingTitle");
            }
        });

        //lamba expression:
        //assertThrows(Exception.class, () -> jukeBox.playTitle("NoneExistingTitle"));

    }

    @Test
    public void testGetPlayList() {
        /*jukeBox.addSong(new SongStub(""));
        assertEquals(1, jukeBox.getPlayList().size());
        */

        // Prüfen Sie, ob die Funktion getTitle aufgerufen wurde.
        // Wie oft wurde diese aufgerufen?
        Song song1 = mock(Song.class);
        Song song2 = mock(Song.class);
        when(song1.getTitle()).thenReturn("Song1");
        when(song2.getTitle()).thenReturn("Song2");

        jukeBox.addSong(song1);
        jukeBox.addSong(song2);
        //jukeBox.addSong(song2);

        List<Song> list = jukeBox.getPlayList();
        assertNotNull(list);
        assertEquals(2, list.size());
        verify(song1).getTitle();
        assertEquals("Song1", song1.getTitle());
        verify(song2).getTitle();
        assertEquals("Song2", song2.getTitle());
    }

    @Test
    public void testPlay() {
        jukeBox.addSong(new SongStub("d"));
        jukeBox.playTitle("d");

        Song song = jukeBox.getActualSong();
        assertEquals("d", song.getTitle());

        assertTrue(song.isPlaying());
    }

    // testen, ob die erwarteten Aufrufe beim Hinzufügen und Abspielen eines Songs auch tatsächlich stattfinden.
    // Prüfen Sie auch die Reihenfolge der Aufrufe.
    // Verwenden Sie dazu die InOrder Funktionalität.
    @Test
    public void testPlayMock() {
        jukeBox.addSong(mock);
        jukeBox.playTitle("d");//check set up

        InOrder inOrder = inOrder(mock);
        inOrder.verify(mock).getTitle();
        inOrder.verify(mock).start();
    }

    @Test
    public void testIsPlayingBasedOnJukeboxState(){
        jukeBox.addSong(mock);
        jukeBox.playTitle("d");
        //assertTrue(mock.isPlaying());
        Answer<Boolean> answer = invocation -> true;
        when(mock.isPlaying()).thenAnswer(answer);
    }


    @Test
    public void testPlayOfAlreadyPlayingSong() {
        //jukeBox.addSong(new SongStub("d"));
        //jukeBox.playTitle("d");
        //assertThrows(JukeBoxException.class, () -> { jukeBox.playTitle("d"); });
        Song song = mock(Song.class);
        //Song song1 = mock(Song.class);
        jukeBox.addSong(song);
        //jukeBox.addSong(song1);
        when(song.getTitle()).thenReturn("firstValue");
        jukeBox.playTitle("firstValue");

        doThrow(new JukeBoxException("")).when(song).start();
        verify(song).start();
        verify(song).getTitle();


    }
}
