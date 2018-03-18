package com.github.sununiq;

import com.sun.deploy.net.HttpRequest;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(value = "/*")
@Slf4j
public class FileServlet extends HttpServlet {

    private static final String title = "<title>Directory listing for /%s</title>\n";

    private static final String head = "<h1>Directory listing for /%s</h1>\n";

    private static final String preDir = "<li><a style=\"font-size:150%;\" href=\"..\">..</a></li>\n";
    private static final String liFile = "<li><a href=\"%s\">%s</a></li>\n";
    private static final String liDir = "<li><a href=\"%s/\">%s/</a></li>\n";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        log.info("request path:{}", requestURI);

        requestURI = requestURI.substring(1);

        if(Files.isDirectory(Paths.get(requestURI))) {
            listDirFiles(response, requestURI);
        } else if(Files.isRegularFile(Paths.get(requestURI))) {
            writeFile(response, Paths.get(requestURI));
        } else {
            response.setHeader(HttpRequest.CONTENT_TYPE, "text/html");
            PrintWriter writer = response.getWriter();
            writer.write("Failed to find target file or dir.");
        }
    }

    private void writeFile(HttpServletResponse response, Path filePath) throws IOException {
        response.setHeader(HttpRequest.CONTENT_TYPE, "application/octet-stream");
        try (FileChannel fileChannel = FileChannel.open(filePath);
             OutputStream outputStream = response.getOutputStream();
             WritableByteChannel writableByteChannel = Channels.newChannel(outputStream)
        ) {
            fileChannel.transferTo(0, Files.size(filePath), writableByteChannel);
        }
    }

    private static void listDirFiles(HttpServletResponse response, String dir) throws IOException {
        response.setHeader(HttpRequest.CONTENT_TYPE, "text/html");
        PrintWriter writer = response.getWriter();
        writer.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n");
        writer.write("<html>\n");

        writer.write("<head>\n");
        writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n");
        writer.write(String.format(title, dir));
        writer.write("</head>\n");

        writer.write("<body>\n");
        writer.write(String.format(head, dir));
        writer.write("<hr>\n");
        writer.write("<ul>\n");
        if(!dir.equals("")) {
            writer.write(preDir);
        }
        printFileDetails(writer, dir);
        writer.write("</ul>\n");
        writer.write("<hr>\n");
        writer.write("</body>\n");
        writer.write("</html>");
    }

    private static void printFileDetails(PrintWriter writer, String rootDir) throws IOException {
        Files.list(Paths.get(rootDir)).forEach(
                path -> {
                    if(path.toFile().isDirectory()) {
                        writer.write(String.format(liDir, path.getFileName(), path.getFileName()));
                    } else if(path.toFile().isFile()) {
                        writer.write(String.format(liFile, path.getFileName(), path.getFileName()));
                    }
                }
        );
    }

}
