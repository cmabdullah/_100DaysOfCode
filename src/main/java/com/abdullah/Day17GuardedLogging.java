package com.abdullah;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Day17GuardedLogging {
	private static final Logger LOGGER = Logger.getLogger(Day17GuardedLogging.class.getName());
	public static void main(String[] args) {
		LOGGER.setLevel(Level.INFO);
		int id = 20;
		LOGGER.log(Level.FINE, "Product "+id+" has been selected");

		if (LOGGER.isLoggable(Level.FINER)){
			LOGGER.log(Level.FINER, "Product "+id+" has been selected");
		}

		LOGGER.log(Level.FINE, "Product {0} has been selected", id);
		LOGGER.log(Level.INFO, "Product {0} has been selected", id);
	}
}
