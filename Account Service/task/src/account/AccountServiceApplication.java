package account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

//    @Bean
//    public Server h2Server() throws SQLException {
//        return Server.createTcpServer(".tcp", ".tcpAllOthers", ".tcpPort", "9092");
//    }
}