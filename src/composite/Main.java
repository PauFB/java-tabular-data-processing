package composite;

public class Main {

    public static void main(String[] args) {

        DirectoryCOMP dir = new DirectoryCOMP("dir1");
        System.out.println("Dir1 columns " + dir.columns());
        System.out.println("Dir1 size " + dir.size());
        System.out.println("Dir1 Code >= 888:\n" + (dir.query("Code", x -> Integer.parseInt(x) >= 888)));

        DirectoryCOMP dir2 = new DirectoryCOMP("dir2");
        System.out.println("Dir2 columns " + dir2.columns());
        System.out.println("Dir2 size " + dir2.size());
        System.out.println("Dir2 LatD >= 50:\n" + (dir2.query("LatD", x -> Integer.parseInt(x) >= 50)));
    }
}
