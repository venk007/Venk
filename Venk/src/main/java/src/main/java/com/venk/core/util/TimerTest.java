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
