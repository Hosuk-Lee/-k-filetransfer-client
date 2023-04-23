package k.filetransfer.agent.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    public static void main(String[] args) throws IOException, URISyntaxException {

//        URI uri = new URI("C:\\Users\\hosuk\\cop\\k-filetransfer-agent\\asdf.zip");
//        Paths.get(uri);
//
//        String zPath = "C:\\Users\\hosuk\\cop\\k-filetransfer-agent\\asdf.zip";
//        String path = "C:\\Users\\hosuk\\cop\\k-filetransfer-agent\\target";

        Path srcPath = Paths.get("C:"+"/Users/hosuk/cop/k-filetransfer-server/target/classes");

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

         pack(srcPath,desPath);
    }

    public static void pack(Path sourceDirPath, Path zipFilePath) throws IOException {
        Path pp = sourceDirPath;

        Path p = Files.createFile(zipFilePath);

//        BufferedOutputStream outputStream = new BufferedOutputStream()
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p));
             Stream<Path> paths = Files.walk(pp)) {
            paths.filter(path -> !Files.isDirectory(path))
                .forEach(path -> {
                    System.out.println(pp.relativize(path).toString());
                    ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                    try {
                        zs.putNextEntry(zipEntry);
                        Files.copy(path, zs);
                        zs.closeEntry();
                    } catch (IOException e) {
                        System.err.println(e);
                    }
            });
            zs.flush();
        }
    }
}
