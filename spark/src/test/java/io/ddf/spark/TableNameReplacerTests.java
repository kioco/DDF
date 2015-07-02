package io.ddf.spark;

import io.ddf.DDF;
import io.ddf.DDFManager;
import io.ddf.exception.DDFException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Created by jing on 6/30/15.
 */
public class TableNameReplacerTests {

    @Test
    public void testRun() throws DDFException {
        createTableAirline();
        DDF ddf = manager.sql2ddf("select year, month, dayofweek, deptime, arrtime,origin, distance, arrdelay, "
                + "depdelay, carrierdelay, weatherdelay, nasdelay, securitydelay, lateaircraftdelay from airline");

        manager.setDDFName(ddf, "myddf");
        Assert.assertEquals("ddf://adatao/" + ddf.getName(), ddf.getUri());

        manager.addDDF(ddf);
        Assert.assertEquals(ddf, manager.getDDF(ddf.getUUID()));

        // ddf.getSummary() check what's this
    }

    public static DDFManager manager;

    // static Logger LOG;


    @BeforeClass
    public static void startServer() throws Exception {
        Thread.sleep(1000);
        // LOG = LoggerFactory.getLogger(BaseTest.class);
        manager = DDFManager.get("spark");
    }

    @AfterClass
    public static void stopServer() throws Exception {

        manager.shutdown();
    }

    public void createTableAirline() throws DDFException {
        manager.sql("drop table if exists airline");

        manager.sql("create table airline (Year int,Month int,DayofMonth int,"
                + "DayOfWeek int,DepTime int,CRSDepTime int,ArrTime int,"
                + "CRSArrTime int,UniqueCarrier string, FlightNum int, "
                + "TailNum string, ActualElapsedTime int, CRSElapsedTime int, "
                + "AirTime int, ArrDelay int, DepDelay int, Origin string, "
                + "Dest string, Distance int, TaxiIn int, TaxiOut int, Cancelled int, "
                + "CancellationCode string, Diverted string, CarrierDelay int, "
                + "WeatherDelay int, NASDelay int, SecurityDelay int, LateAircraftDelay int ) "
                + "ROW FORMAT DELIMITED FIELDS TERMINATED BY ','");

        manager.sql("load data local inpath '../resources/test/airline.csv' into table airline");

    }

    public void createTableSmiths2() throws DDFException {
        manager.sql("drop table if exists smiths2");

        manager.sql("create table smiths2 (subject string, variable string, value double) "
                + "ROW FORMAT DELIMITED FIELDS TERMINATED BY ','");

        manager.sql("load data local inpath '../resources/test/smiths2' into table smiths2");

    }

    public void createTableAirlineWithNA() throws DDFException {
        manager.sql("drop table if exists airline");

        manager.sql("create table airline (Year int,Month int,DayofMonth int,"
                + "DayOfWeek int,DepTime int,CRSDepTime int,ArrTime int,"
                + "CRSArrTime int,UniqueCarrier string, FlightNum int, "
                + "TailNum string, ActualElapsedTime int, CRSElapsedTime int, "
                + "AirTime int, ArrDelay int, DepDelay int, Origin string, "
                + "Dest string, Distance int, TaxiIn int, TaxiOut int, Cancelled int, "
                + "CancellationCode string, Diverted string, CarrierDelay int, "
                + "WeatherDelay int, NASDelay int, SecurityDelay int, LateAircraftDelay int ) "
                + "ROW FORMAT DELIMITED FIELDS TERMINATED BY ','");

        manager.sql("load data local inpath '../resources/test/airlineWithNA.csv' into table airline");
    }

    public void createTableMovie() throws DDFException {
        manager.sql("drop table if exists movie");

        manager.sql("create table movie (" +
                "idx string,title string,year int,length int,budget double,rating double,votes int," +
                "r1 double,r2 double,r3 double,r4 double,r5 double,r6 double,r7 double,r8 double,r9 double,r10 double," +
                "mpaa string,Action int,Animation int,Comedy int,Drama int,Documentary int,Romance int,Short int" +
                ") ROW FORMAT DELIMITED FIELDS TERMINATED BY ','");

        manager.sql("load data local inpath '../resources/test/movies.csv' into table movie");
    }

    public void createTableRatings() throws DDFException {
        manager.sql("drop table if exists ratings");

        manager.sql("create table ratings (userid int,movieid int,score double ) "
                + "ROW FORMAT DELIMITED FIELDS TERMINATED BY ','");

        manager.sql("load data local inpath '../resources/test/ratings.data' into table ratings");
    }

    public void createTableMtcars() throws DDFException {
        manager.sql("drop table if exists mtcars");

        manager.sql("CREATE TABLE mtcars ("
                + "mpg double, cyl int, disp double, hp int, drat double, wt double, qsec double, vs int, am int, gear int, carb int"
                + ") ROW FORMAT DELIMITED FIELDS TERMINATED BY ' '");

        manager.sql("load data local inpath '../resources/test/mtcars' into table mtcars");
    }

}