package ChatServer.src.Client;

import ChatServer.src.Server.Server;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.*;

class CliRegisterTest {

    static Server server;
    Client client;
    static Thread thread;
    private ExecutorService es;

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

    //@Test
   synchronized Client registerClient(){

    client = new Client(8080);
        //String input = "IDEN c1" + username;
        String input = "IDEN c1";
        //.concat(username);
        InputStream i = new ByteArrayInputStream(input.getBytes());
        System.setIn(i);
        Scanner scn = new Scanner(System.in);
        client.sendMessage(scn);
        //client.readMessage();
        return client;
        //assertTrue(server.doesUserExist("c1"));
    }

   // @Test
    void testRegister(){
        String[] usernameList = {"abcd", "c1d2e3", "3658", "CLIclient", "ABCD", "NAME", "$home!", "B@r>?e^%"};

        for (int i=0; i<usernameList.length; i++){
           // registerClient(usernameList[i]);
            int finalI = i;
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    assertTrue(server.doesUserExist(usernameList[finalI]));
                }
            });

            try {
                th.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            th.start();


        }

        //assertEquals(usernameList,server.getUserList());

    }


    //@Test
    synchronized void usedNameTest(){
        Client client1, client2;

        client1 = registerClient();
        client2 = registerClient();
        //registerClient("myName");


       /* Thread t = new Thread(new Runnable() {
        @Override
        public void run() {

            registerClient("myName");
        }
    });
        try {
            t.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.start();*/


        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                String m = client2.readMessage();
                System.out.println("This is final message: " + m);
                assertEquals("BAD username is already taken", m);
                //registerClient("myName");
                //assertTrue(server.doesUserExist(usernameList[finalI]));
            }
        });

        try {
            th.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        th.start();



    }



    @Test
    synchronized void usedNames(){
        int j;
        String[] userNames = {"myName","oooo"};
        for (j = 0; j<userNames.length; j++){
            int finalJ = j;
            System.out.println(finalJ);
            //System.out.println(userNames[finalJ]);
            Client client = registerClient();


        }


    }
}