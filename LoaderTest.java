// write a classloader that delegates A to super loader and attempts to load B itself
// Can we force a constraint violation then?

import java.io.*;
import java.nio.*;
import java.nio.file.*;

public class LoaderTest extends ClassLoader {

static String[] ARGS;


public static void main(String[] args) throws Throwable {
	ARGS = args;

	Class<?> c = new LoaderTest().loadClass(args[0], false);
	c.newInstance();
	System.out.println(""+c+"::loader="+c.getClassLoader());

}

public Class<?> loadClass(String name) throws ClassNotFoundException {
  System.out.println("1-loadClass:" + name);
  return loadClass(name, false);
}
protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
  System.out.println("2-loadClass:" + name + ":::"+resolve);

  if (name.equals(ARGS[0])) {
    System.out.println("2-loadClass:arg0:" + name + ":::"+resolve);
    // load locally first
    try {
    byte[] bytes = Files.readAllBytes(Paths.get(name+".class"));
    return defineClass(name, bytes, 0, bytes.length);
    } catch (IOException e) { throw new RuntimeException(e); }
  }

  return super.loadClass(name, resolve);
}

}
