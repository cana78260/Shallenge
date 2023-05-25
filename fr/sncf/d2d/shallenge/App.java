
package fr.sncf.d2d.shallenge;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HexFormat;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {
    // public static void main( String[] args ) throws IOException
    // {
    // System.out.println("Entrez un nom de fichier: ");
    // java.util.Scanner in = new Scanner(System.in);
    // String file = in.next();

    // Path path = Paths.get(file);
    // FileSystem fs = path.getFileSystem();
    // System.out.println("fsTostring: " + fs);
    // System.out.println("pathIs absolute: " + path.isAbsolute());
    // System.out.println("path getfileName: " + path.getFileName());
    // System.out.println("path To absolute path . to string: " +
    // path.toAbsolutePath().toString());
    // System.out.println("get root: " + path.getRoot());
    // System.out.println("get parent: "+ path.getParent());
    // System.out.println("get nameCount: " + path.getNameCount());
    // System.out.println("get name: " + path.getName(0));
    // System.out.println("subPath: " + path.subpath(0, 2));
    // System.out.println("path toString: "+ path.toString());
    // System.out.println("path get nameCount: " + path.getNameCount());
    // Path realPath = path.toRealPath(LinkOption.NOFOLLOW_LINKS);
    // System.out.println("real path to string: " + realPath.toString());

    // String originalPath =
    // "C:\\Users\\7502233B\\Documents\\Stage\\exoNataniel\\exo1Nataniel-id\\cible.txt";
    // String originalPath2 = originalPath.replace(":", "_");
    // Path path1 = Paths.get( originalPath2);
    // Path path2 = path1.normalize();
    // System.out.println("path 2 = " + path2);
    // System.out.println("path path: " + path);
    // Path absolute = path.toAbsolutePath();
    // System.out.println("absolute path: "+ absolute);
    // }

    public static void main(String[] args) throws Exception {

        // System.out.println(HashManager.toHexHash(data, Hash.SHA256));

        // System.out.println("Entrez un nom de fichier: ");
        // java.util.Scanner in = new Scanner(System.in);
        // String file = in.next();
        // Builder request = HttpRequest.newBuilder();
        // System.out.println(args[1]);
        // String body = "mot de passe à POST";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://shallenge.onrender.com/challenges"))
                // .headers("content-Type","application/json;charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response : "+ response.body());

        String regex = "\"[^\"]+\"\\s*:\\s*\"([^\"]*)\"";
        Pattern pattern = Pattern.compile(regex);
        String parse = response.body();
        Matcher matcher = pattern.matcher(parse);

        int counter = 0;
        var hashValue = "";
        var saltValue = "";
        var idValue = "";
        while (matcher.find()) {
            String value = matcher.group(1);
            // System.out.println("value: "+ value);
            counter++;
            if (counter == 1) {
                idValue = value;
            }
            if (counter == 2) {
                hashValue = value;
                System.out.println("hashValue: " + hashValue);
            }
            if (counter == 3) {
                saltValue = value;
                System.out.println("saltValue: " + saltValue);
            }

            // String[] valueTab = value.split("");
            // String valueTest = valueTab[0];
            // System.out.println( "valueTab: "+ valueTab);
        }

        final var hash = HexFormat.of().parseHex(hashValue);
        final var salt = HexFormat.of().parseHex(saltValue);
        var generator = new PasswordsGenerator();
        // final var data = generator.generate();

        while (!generator.isEmpty()) {
            final var password = generator.generate();
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
            // System.out.println("passwordBytes: "+ passwordBytes);
            final var computedHash = HashManager.hashPassword(salt, passwordBytes);
            if (!Arrays.equals(computedHash, hash)) {
                continue;
            }
           
            HttpClient finalClient = HttpClient.newHttpClient();
            HttpRequest finalRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://shallenge.onrender.com/challenges/" + idValue + "/answer"))
                    .headers("content-Type", "application/json;charset=UTF-8")
                    .POST(HttpRequest.BodyPublishers.ofString("\""+password+"\""))
                    .build();

            HttpResponse<String> finalResponse = finalClient.send(finalRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(finalResponse.body());
            System.exit(0);

        }

        System.exit(1);

    }
}
