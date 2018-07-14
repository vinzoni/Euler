package euler;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 *
 * @author eanvinz
 * You are given the following information, but you may prefer to do some research for yourself.
 * 1 Jan 1900 was a Monday.
 * Thirty days has September,
 * April, June and November.
 * All the rest have thirty-one,
 * Saving February alone,
 * Which has twenty-eight, rain or shine.
 * And on leap years, twenty-nine.
 * A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 */
class Euler_19 extends EulerBase {
    
    Euler_19() { super("Counting Sundays", "171"); }
    
    public void run() {
        
        final int MAX_DAYS_IN_A_YEAR = 366;
        final EulerDate START_FROM = new EulerDate(1, 1, 1900, 1);
        final int YEARS_TO_GO = 101;
        
        DateGenerator dateGenerator = new DateGenerator(START_FROM);
        long sum = Stream.generate(dateGenerator)
                .limit(MAX_DAYS_IN_A_YEAR * YEARS_TO_GO)
                .filter(date -> (date.year > 1900) && (date.year < 2001))
                .filter(date -> date.sunday())
                .filter(date -> date.firstOfTheMonth())
                .count();
        
        printout(sum);
    }
}

class EulerDate {
    public int day;
    public int month;
    public int year;
    public int weekday; // 0 == sunday
    
    EulerDate(int day, int month, int year, int weekday) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.weekday = weekday;
    }
    
    @Override
    public String toString() {
        return String.format("%02d-%02d-%04d (%d)", day, month, year, weekday);
    }
    
    public boolean sunday() { return weekday == 0; }
    public boolean firstOfTheMonth() { return day == 1; }    
}

class DateGenerator implements Supplier<EulerDate> {
        EulerDate startEpoch;
        EulerDate last = null;
        
        public DateGenerator(EulerDate startFrom) {
            startEpoch = startFrom;
        }
        
        public EulerDate get() {
            if (last == null)
            {
                last = startEpoch;
            }
            else
            {    
                last = new EulerDate(nextDay(), nextMonth(), nextYear(), nextWeekday());
            }
            return last;            
        }
        private int nextWeekday() { return (last.weekday+1)%7; }
        private int nextYear() { return (last.day == 31) && (last.month == 12) ? last.year+1 : last.year; }
        private int nextMonth() { return  last.day == lastDayOfThisMonth() ? (last.month%12)+1 : last.month; }        
        private int nextDay() { return (last.day % lastDayOfThisMonth()) + 1; }
        
        private int lastDayOfThisMonth() {
            switch (last.month) {
                case 2: // february
                    return isLeapYear() ? 29 : 28;
                case 4: // april
                case 6: // june
                case 9: // september
                case 11: // november
                    return 30;
                default: return 31;
            }
        }
        
        private boolean isLeapYear() {
            if (last.year % 400 == 0)
                return true;
            if (last.year % 100 == 0)
                return false;
            if (last.year % 4 == 0)
                return true;
            
            return false;
        }
}