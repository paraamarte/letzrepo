package com.sivillage.common.util;

import net.sf.log4jdbc.Slf4jSpyLogDelegator;
import net.sf.log4jdbc.Spy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SQLFomatter extends Slf4jSpyLogDelegator {
	private Logger log = LoggerFactory.getLogger(getClass());

	public SQLFomatter() {
	}
	
	@Override
	public void sqlTimingOccured(Spy spy, long execTime, String methodCall, String sql) {		
		if (log.isDebugEnabled()) {
			StringBuffer SQL = new StringBuffer();
			
			String[] temp = sql.split("\n");
			
			for (String str : temp) {
				if ("".equals(str.trim())) {
					continue;
				}
				
				SQL.append(str+"\r\n");
			}
			
			log.debug(" {SQL executed in " + execTime + " msec}\n" + SQL.toString());
		}
	}
}
