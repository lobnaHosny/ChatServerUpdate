package ChatServer.src.Server;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

@TestInstance(LifeCycle.PER_CLASS)
class ServerTest {

    public Server server;
    @BeforeAll
    void runServer(){
        server = new Server(9000);
    }

    //@AfterEach
    //void quitServer() throws IOException {
       // server.finalize();
    //}
    
   @Test
   void anotherOne(){
       System.out.println("This is another one");
       System.out.printf("%b", server.c.isRunning());
       //assertTrue(server.c.isRunning());
   }
    @Test
    void test2(){
        System.out.println("Thi is test 2");
    }



}
