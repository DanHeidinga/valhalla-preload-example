public class Test2 extends A {
  public static void main(String[] args) {
    System.out.println("test2");
  }
}

class Test3 extends B {
  public static void main(String[] args) {
    System.out.println("test3");
  }
}

class Test4 {
  public static void main(String[] args) {
    System.out.println("test4");
    B.main(args);
  }
}
