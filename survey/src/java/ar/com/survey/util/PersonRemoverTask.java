package ar.com.survey.util;

import java.util.TimerTask;

import org.apache.log4j.Logger;

public class PersonRemoverTask extends TimerTask {

	private static Logger logger = Logger.getLogger(PersonRemoverTask.class);
	
	/**
	 * task to run, overrides default run method
	 */
	public void run() {
		logger.info("Ejecucion del limpiador: " + System.currentTimeMillis());
	}

}
