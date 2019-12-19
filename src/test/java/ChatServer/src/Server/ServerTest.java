package ChatServer.src.Server;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

class ServerTest {

    public volatile Server server;
    //@BeforeAll
    //@Test
    void runServer(){
        server = new Server(9000);
    }
    
    runServer();

    //@AfterEach
    //void quitServer() throws IOException {
       // server.finalize();
    //}
    
   @Test
   void anotherOne(){
       System.out.printf("%d", server.getNumberOfUsers());
       System.out.println("This is another one");
       
       //assertTrue(server.c.isRunning());
   }
    @Test
    void test2(){
        System.out.println("Thi is test 2");
    }



}
