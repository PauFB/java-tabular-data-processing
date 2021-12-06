package composite;

public class Main {

    public static void main(String[] args) {
        DirectoryCOMP dir1 = new DirectoryCOMP("dir1");
        DirectoryCOMP dir2 = new DirectoryCOMP("dir2");
        FileCOMP f1 = new FileCOMP("DimenLookupAge8277.csv");
        FileCOMP f2 = new FileCOMP("cities.json");
        FileCOMP f3 = new FileCOMP("example.txt");
        dir1.addChild(f1);
        dir2.addChild(f2);
        dir2.addChild(f3);

        System.out.println("Dir1 Size: "+dir1.size());
        System.out.println("Dir1 Columns: "+dir1.columns());
        System.out.println("Dir2 Size: "+dir2.size());
        System.out.println("Dir2 Columns: "+dir2.columns());
    }
}
