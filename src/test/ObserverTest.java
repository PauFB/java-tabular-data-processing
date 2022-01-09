package test;

import factory.CSVData;
import factory.DataFrame;
import observer.Interceptor;
import observer.LogObserver;
import observer.QueryObserver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ObserverTest {

    static Interceptor interceptor;

    static LogObserver logObserver;
    static QueryObserver queryObserver;

    static DataFrame df;

    @BeforeAll
    static void Initialize() {
        interceptor = new Interceptor(new CSVData("resources/ages.csv"));
        logObserver = new LogObserver();
        queryObserver = new QueryObserver();
        interceptor.addObserver(logObserver);
        interceptor.addObserver(queryObserver);
        df = (DataFrame) Proxy.newProxyInstance(DataFrame.class.getClassLoader(),
                new Class<?>[]{DataFrame.class},
                interceptor);

        df.columns();
        df.size();
        df.iat(0, 0);
        df.query("Code", x -> Integer.parseInt(x) > 888);
        df.max("Code");
        df.query("SortCode", x -> Integer.parseInt(x) < 0);
    }

    @BeforeEach
    public void Separate() {
        System.out.println("\n");
    }

    @Test
    public void LogObserver() {
        System.out.println("df log of method executions = " + logObserver);
        assertNotNull(logObserver);
    }

    @Test
    public void QueryObserver() {
        System.out.println("df log of query method executions = " + queryObserver);
        assertNotNull(queryObserver);
    }

}
