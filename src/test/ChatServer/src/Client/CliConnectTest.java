package ChatServer.src.Client;

import ChatServer.src.Server.Server;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CliConnectTest {

    static Server server;
    Client client;
    static Thread thread;

    @BeforeAll
   static void runServer(){
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                server = new Server(8080);
                server.listenClient();
            }
        });
        thread.start();

    }


    @Test
    void connectClient() throws IOException {
        //runServer();
        client = new Client(8080);
        assertEquals(8080, client.s.getPort());
        //server.server.close();

    }

    @Test
    void wrongPortClient(){
        client = new Client (5000);
        assertNull(client.s);
    }


}