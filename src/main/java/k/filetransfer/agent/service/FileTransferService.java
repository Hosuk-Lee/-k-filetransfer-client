package k.filetransfer.agent.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

import k.filetransfer.agent.client.FileTransferClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileTransferService {

    private final FileTransferClient fileTransferClient;

    public void run(Path path) throws IOException {


        // TODO Local File 체크
        ;
        File file = new File(path.toAbsolutePath().toString());
                //new File(path + File.separator + name);
        log.debug("File.pathSeparator : {}", File.separator);

        // TODO 접속정보 알아보기.
        // FileTransferServer.getPeer

        // TODO Local File To Multipart File
        // TODO File Send
        CustomMultipartFile customMultipartFile = new CustomMultipartFile(file.getName(), Files.readAllBytes(file.toPath()));
        log.debug("customMultipartFile.getName() {}", customMultipartFile.getName());
        log.debug("customMultipartFile.getContentType() {}", customMultipartFile.getContentType());
        log.debug("customMultipartFile.getSize() {}", customMultipartFile.getSize());
//        log.debug("upload : {}", customMultipartFile.getBytes());

        String upload = fileTransferClient.fileUpload(customMultipartFile);
        log.debug("upload : {}", upload);
    }

    public void run(MultipartFile file) throws IOException {

        String upload = fileTransferClient.fileUpload(file);
        log.debug("upload : {}", upload);
    }

}
