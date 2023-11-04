import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hi");

        var file = new File("sandbox/build.gradle");
        System.out.println(file.exists());
    }
}
