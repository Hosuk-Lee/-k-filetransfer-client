package k.filetransfer.agent;

import k.filetransfer.agent.service.FileTransferService;
import k.filetransfer.agent.util.ZipUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EnableFeignClients
@SpringBootApplication
public class ClientApp {
    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class, args);
    }

    @Bean
    public ApplicationRunner run(FileTransferService service) {
        return args -> {

//            ZipUtil.pack();
            //service.run();

            System.out.println(args);
            System.out.println("NonOption Arguments : " + args.getNonOptionArgs());
            System.out.println("Option Arguments Names : " + args.getOptionNames());
            System.out.println("ID   : " + args.getOptionValues("id").get(0));
            System.out.println("PATH : " + args.getOptionValues("path"));

            Path srcPath = Paths.get(
                    /* "C:"+ */
                    args.getOptionValues("path").get(0).trim()
            );

            System.out.println("@@"+srcPath.getFileName());
            System.out.println("@@"+srcPath.getParent());
            System.out.println("@@"
                    + srcPath.getParent() + File.separator
                    + srcPath.getFileName() +"_"
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                    + ".zip"
            );
            Path desPath = Paths.get(
                    srcPath.getParent() + File.separator
                            + srcPath.getFileName() +"_"
                            + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                            + ".zip");

            ZipUtil.pack(srcPath,desPath);
        };
    }
}
