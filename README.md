*Update*: Resolved.  Hotspot doesn't link the class when creating a j.l.Class instance.  That occurs later.
When forcing the class to be linked, the preload occurs.
 
Playing with the preload classfile attribute and seeing some strange behaviour.

1. Build a JDK from the valhalla repo and the `lworld` branch
1. Compile A.java and B.java.value to get a preload attribute in A.class
1. Compile B.java to convert it to a non-value class
1. Run test case `Test1` and see the preload attribute in use:
```
$ ../build/macosx-x86_64-server-release/jdk/bin/java  -Xlog:class+preload=debug:stdout -XX:+EnableValhalla  Test1

==== Preloading classes during linking of class A because of its Preload[1] attribute
Preloading class B during linking of class A because of its Preload attribute
[0.191s][info][class,preload] Preloading class B during linking of class A because of its Preload attribute
main
```
1. Run `LoaderTest` to see preload on a user-defined classloader
```
$ ../build/macosx-x86_64-server-release/jdk/bin/java  -Xlog:class+preload=debug:stdout -XX:+EnableValhalla  LoaderTest A
2-loadClass:A:::false
2-loadClass:arg0:A:::false
1-loadClass:java.lang.Object
2-loadClass:java.lang.Object:::false
class A::loader=LoaderTest@4cb2c100
```

Neither my debugging printfs or the `Xlog` output shows the preload attribute is being used with the user-defined classloader
 
