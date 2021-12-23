package utils;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author By-Lin
 */
public class DateUtilTest extends TestCase {

    @Test
    public void testFormatStr() {
    }

    public void testToDayStartHour() {
    }

    public void testAddDateMinutes() {
    }

    public void testAddDateHour() {
    }

    @Test
    public void testGetDateMenus() {
        List<Date> dateMenus = DateUtil.getDateMenus();
        System.out.println(dateMenus);
    }

    public void testGetDates() {
    }

    @Test
    public void testData2str() {
    }

    @Test
    public void testDemo() {
        int x = 10;
        x+=3.5;
        System.out.println(x); // 13 int
        double y = 10D;
        y+=x;
        System.out.println(y); // 23 double
        long b = 10L;
        float c = 10F;
        float v = b + c;
    }
}