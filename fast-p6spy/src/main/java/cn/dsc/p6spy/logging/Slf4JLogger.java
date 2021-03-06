package cn.dsc.p6spy.logging;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.FormattedLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 *
 * @author dingshichen
 */
public class Slf4JLogger extends FormattedLogger {

    private Logger log;

    final static Pattern p = Pattern.compile("(\\r?\n(\\s*\\r?\\n)+)");

    public Slf4JLogger() {
        log = LoggerFactory.getLogger("cn.dsc.tools.sqlLog");
    }

    @Override
    public void logException(Exception e) {
        log.error("", e);
    }

    @Override
    public void logText(String s) {
        log.info(s);
    }

    @Override
    public void logSQL(int connectionId, String now, long elapsed, Category category, String prepared, String sql, String url) {
        sql = p.matcher(sql).replaceAll("\\\r\\\n");
        if(sql.length() == 0 || sql.trim().length() == 0) {
            return ;
        }
        final String msg = strategy.formatMessage(connectionId, now, elapsed, category.toString(), prepared, sql, url);

        if (Category.ERROR.equals(category) || Category.OUTAGE.equals(category)) {
            log.error(msg);
        } else if (Category.WARN.equals(category)) {
            log.warn(msg);
        } else if (Category.DEBUG.equals(category)) {
            log.debug(msg);
        } else {
            log.info(msg);
        }
    }

    @Override
    public boolean isCategoryEnabled(Category category) {
        if (Category.ERROR.equals(category)) {
            return log.isErrorEnabled();
        } else if (Category.WARN.equals(category)) {
            return log.isWarnEnabled();
        } else if (Category.DEBUG.equals(category)) {
            return log.isDebugEnabled();
        } else {
            return log.isInfoEnabled();
        }
    }
}
