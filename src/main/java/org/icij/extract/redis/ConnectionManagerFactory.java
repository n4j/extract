package org.icij.extract.redis;

import org.icij.extract.core.Report;

import org.redisson.Config;
import org.redisson.SingleServerConfig;
import org.redisson.connection.ConnectionManager;
import org.redisson.connection.SingleConnectionManager;

/**
 * Factory methods for creating a Redis client.
 *
 * @author Matthew Caruana Galizia <mcaruana@icij.org>
 * @since 1.0.0-beta
 */
public class ConnectionManagerFactory {

	public static ConnectionManager createConnectionManager(final Object config) {
		final ConnectionManager connectionManager;

		if (config instanceof SingleServerConfig) {
			connectionManager = new SingleConnectionManager((SingleServerConfig) config, new Config());
		} else {
			throw new IllegalArgumentException("Server(s) address(es) not defined!");
		}

		return connectionManager;
	}
}