package com.hjc.java_common_tools.apache;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Assert;
import org.junit.Test;

/**
 * Apache Lang3 中的时间操作工具类
 * 
 */
public class DateUtil_Study {

	/**
	 * FastDateFormat is a fast and thread-safe version of SimpleDateFormat.
	 * 
	 */
	@Test
	public void testFastDateFormatBaisc() {
		long time = 1400001070527L;// 表示时间：2014-05-14 02:11:10（东八区时间）
		FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss",
				TimeZone.getTimeZone("GMT+8:00"));
		Assert.assertEquals("2014-05-14 01:11:10", fdf.format(time));
	}

	/**
	 * DateFormatUtils可以用于快速格式化时间字符串。
	 */
	@Test
	public void testDateFormatUtilsBaisc() {
		long time = 1400001070527L;// 表示时间：2014-05-14 02:11:10（东八区时间）

		Assert.assertEquals(
				"2014-05-14 01:11:10",
				DateFormatUtils.format(time, "yyyy-MM-dd HH:mm:ss",
						TimeZone.getTimeZone("GMT+8:00")));

		// 测试东九区 TimeZone
		Assert.assertEquals(
				"2014-05-14 02:11:10",
				DateFormatUtils.format(time, "yyyy-MM-dd HH:mm:ss",
						TimeZone.getTimeZone("GMT+9:00")));

	}

	/**
	 * 
	 * DateUtils 可以方便的根据时间字符串和其对应的时间格式得到Date类型。
	 * 此外还能方便的计算两个时间格式之间的差值（不同粒度的衡量单位），以及比较两个时间是否相等。
	 * 
	 * <pre>
	 * character	duration element
	 * y			years
	 * M			months
	 * d			days
	 * H			hours
	 * m			minutes
	 * s			seconds
	 * S			milliseconds
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDateUtilsBaisc() throws Exception {
		String startDateStr = "2014-02-28 23:00:00";
		String endDateStr = "2014-03-01 08:00:00";
		Date startDate = DateUtils.parseDate(startDateStr,
				new String[] { "yyyy-MM-dd HH:mm:ss" });
		Date endDate = DateUtils.parseDate(endDateStr,
				new String[] { "yyyy-MM-dd HH:mm:ss" });
		// 计算两个时间的时间差（以分钟计算）
		String durationStrInMimutes = DurationFormatUtils.formatDuration(
				endDate.getTime() - startDate.getTime(), "m");

		Assert.assertEquals("540", durationStrInMimutes);

		// 计算两个时间的时间差（以天计算）
		String durationStrInDays = DurationFormatUtils.formatDuration(
				startDate.getTime() - endDate.getTime(), "d");

		// 注意这里由于时间差<24小时，所以值为0
		Assert.assertEquals("0", durationStrInDays);

		// 表示截止到分钟，两个时间是否相等
		boolean retBoolean = DateUtils.truncatedEquals(startDate, endDate,
				Calendar.MINUTE);
		Assert.assertFalse(retBoolean);

		// 表示截止到月份，两个时间是否相等
		retBoolean = DateUtils.truncatedEquals(startDate, endDate,
				Calendar.MONTH);
		Assert.assertFalse(retBoolean);

		// 表示截止到年份，两个时间是否相等。显然此处是相等的
		retBoolean = DateUtils.truncatedEquals(startDate, endDate,
				Calendar.YEAR);
		Assert.assertTrue(retBoolean);
	}
}
