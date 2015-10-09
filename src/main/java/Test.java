import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Test - Short description of the class
 *
 * @author Camille
 *         Last: 05/10/2015 22:19
 * @version $Id$
 */
public class Test {

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\errorist\\workspace\\javatest1\\bin\\");
        URL[] urls = new URL[] { file.toURI().toURL() };
        ClassLoader loader = new URLClassLoader(urls);
        Class c = loader.loadClass("src.SomeClass1");

        c.invokeMethod("main", new String[] { "Hello", "World!" });
    }

}
