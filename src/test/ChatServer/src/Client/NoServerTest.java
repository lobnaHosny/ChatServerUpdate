package ChatServer.src.Client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoServerTest {


    @Test
    void noServerCLI(){ //initializing client without running server
        Client client = new Client(8080);
        assertNull(client.s); //socket should be null, since no connection will be established
    }

    @Test
    void noServerGUI(){ //initializing client gui without running server
        ClientGUI clientGUI = new ClientGUI(8080);
        assertNull(clientGUI.s); //socket should be null, since no connection will be established
    }

}