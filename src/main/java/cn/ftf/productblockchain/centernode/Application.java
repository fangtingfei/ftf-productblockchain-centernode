package cn.ftf.productblockchain.centernode;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Scanner;

@SpringBootApplication
public class Application {

    public static Integer websocketPort;

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Application Port:");
        String port=scanner.nextLine();
        System.out.println("Websocket Port:");
        websocketPort=Integer.parseInt(scanner.nextLine());
        new SpringApplicationBuilder(Application.class).properties("server.port="+port).run(args);
    }

}
