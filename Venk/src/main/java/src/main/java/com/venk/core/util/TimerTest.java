package src.main.java.com.venk.core.util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

/**
 * Java定时任务
 * schedule和scheduleAtFixedRate
 * @author Venk
 * time: 2015-11-23
 * origin by vincent
 */
public class TimerTest {

	public static void main(String[] args) {
		Timer t = new Timer();

		// 在5秒之后执行TimerTask的任务
		t.schedule(new TimerTask() {
			public void run() {
				System.out.println("this is task you do1");
			}
		}, 5*1000);

		// 在Date指定的特定时刻之后执行TimerTask的任务
		Date d1 = new Date(System.currentTimeMillis() + 1000);
		t.schedule(new TimerTask() {
			public void run() {
				System.out.println("this is task you do2");
			}
		}, d1);

		// 在Date指定的特定时刻之后,每隔1秒执行TimerTask的任务一次
		Date d2 = new Date(System.currentTimeMillis() + 1000);
		t.schedule(new TimerTask() {
			public void run() {
				System.out.println("this is task you do3");
			}
		}, d2, 1*1000);

		// 在3秒之后,每隔1秒执行TimerTask的任务一次
		t.schedule(new TimerTask() {
			public void run() {
				System.out.println("this is task you do4");
			}
		}, 3*1000, 1*1000);

		// 在3秒之后,绝对每隔2秒执行TimerTask的任务一次

		t.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println("this is task you do6");
			}
		}, 3*1000, 2*1000);
	}
}

/*

schedule和scheduleAtFixedRate的区别在于，如果指定开始执行的时间在当前系统运行时间之前，
scheduleAtFixedRate会把已经过去的时间也作为周期执行，而schedule不会把过去的时间算上。

比如

SimpleDateFormat fTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
   Date d1 = fTime.parse("2005/12/30 14:10:00");
  
   t.scheduleAtFixedRate(new TimerTask(){
    public void run()
    {
        System.out.println("this is task you do6");
    }
   },d1,3*60*1000);

间隔时间是3分钟，指定开始时间是2005/12/30 14:10:00，如果我在14:17:00分执行这个程序，那么会立刻打印3次

this is task you do6      //14:10
this is task you do6      //14:13
this is task you do6      //14:16

并且注意，下一次执行是在14:19 而不是 14:20。就是说是从指定的开始时间开始计时，而不是从执行时间开始计时。

但是上面如果用schedule方法，间隔时间是3分钟，指定开始时间是2005/12/30 14:10:00，
那么在14:17:00分执行这个程序，则立即执行程序一次,
并且下一次的执行时间是 14:20，而不是从14:10开始算的周期(14:19)。

*/