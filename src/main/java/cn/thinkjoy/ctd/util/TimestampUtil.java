package cn.thinkjoy.ctd.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * The class TimestampUtil for generate a local timestamp.
 */
public final class TimestampUtil {
	/** The Constant UTC. */
	private static final String UTC = "UTC";

	/** The Constant UTC_FORMAT. */
	private static final String UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	/**
	 * Instantiates a new wsse util.
	 */
	private TimestampUtil() {
	}

	/**
	 * Gets the local utc timestamp. Get a local timestamp and the timezone is
	 * UTC.
	 * 
	 * @return the local utc timestamp
	 */
	public static String getLocalUtcTimestamp() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(UTC_FORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone(UTC));
		return sdf.format(calendar.getTime());
	}
}
