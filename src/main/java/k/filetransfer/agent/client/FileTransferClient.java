package k.filetransfer.agent.client;

import k.filetransfer.agent.config.FeignSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file", url = "http://localhost:18080",
        configuration = FeignSupportConfig.class)
public interface FileTransferClient {


    @GetMapping(value = "/hello", produces = "application/json", consumes = "application/json")
    String hello(
//            @RequestHeader("Ocp-Apim-Subscription-Key") String key,
//            @RequestHeader("Ocp-Apim-Subscription-Region") String region,
//            @RequestParam("api-version") String version,
//            @RequestParam("from") Locale from,
//            @RequestParam("to") Locale to,
//            @RequestBody String content
    );

    // headers = "Content-Type=multipart/form-data")
//    @PostMapping(value = "/uploadFile", produces = "multipart/form-data")
//    String upload(@RequestPart("file") MultipartFile file);
    @PostMapping(
            value = "/uploadFile"
            , consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    String fileUpload(@RequestPart(value = "file", required = false) MultipartFile file);
}
