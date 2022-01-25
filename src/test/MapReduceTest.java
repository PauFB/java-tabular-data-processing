package test;

import composite.DirectoryData;
import composite.FileData;
import factory.Data;
import factory.DataFrame;
import mapreduce.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapReduceTest {

    static FileData csv;
    static FileData json;
    static FileData txt;

    static DirectoryData dir1;
    static DirectoryData dir2;

    static List<DataFrame> list;

    @BeforeAll
    static void Initialize() {
        csv = new FileData("resources/ages.csv");
        json = new FileData("resources/cities.json");
        txt = new FileData("resources/example.txt");

        dir1 = new DirectoryData("resources/dir1");
        dir2 = new DirectoryData("resources/dir2");

        list = Arrays.asList(csv, json, txt, dir1, dir2);
    }

    @BeforeEach
    public void Separate() {
        System.out.println("\n");
    }

    @Test
    public void MapReduceSize() {
        Integer sumSize = MapReduce.mapreduce(list, new Size(), (x,y)->x+y);
        System.out.println("sum of size(): " + sumSize);

        assertEquals(686, sumSize);
    }

    @Test
    public void MapReduceColumns() {
        Integer sumColumn = MapReduce.mapreduce(list, new Columns(), (x,y)->x+y);
        System.out.println("sum of columns(): " + sumColumn);

        assertEquals(29, sumColumn);
    }

    @Test
    public void MapReduceQueries() {
        Data querySum = MapReduce.mapreduce(list, new Query("Code", x -> Integer.parseInt(x) > 888), new QueryAccumulate());
        System.out.println("Result of Code > 888:\n" + querySum);

        Data querySum2 = MapReduce.mapreduce(list, new Query("Code", x -> Integer.parseInt(x) < 0), new QueryAccumulate());
        System.out.println("Result of Code < 0:\n" + querySum2);

        assertAll(() -> assertNotNull(querySum),
                () -> assertNotNull(querySum2));
    }

}
