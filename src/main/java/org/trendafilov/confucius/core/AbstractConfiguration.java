/* 
 * Copyright 2013-2014 Ivan Trendafilov and contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.trendafilov.confucius.core;

import java.nio.file.Path;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trendafilov.confucius.Configurable;

import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;
import org.trendafilov.confucius.ConfigurationException;
import org.trendafilov.confucius.core.provider.ConfigurationDataProvider;

public abstract class AbstractConfiguration implements Configurable {
	private final static Logger LOG = LoggerFactory.getLogger(AbstractConfiguration.class);

	private final static String ITEM_SEPARATOR = ",";
	protected static String FILE_PARAM = "conf.properties";
	protected static String CONTEXT_PARAM = "conf.context";

	private final @NotNull  ConfigurationDataProvider configurationDataProvider;
	private final @Nullable String context;
	private final @NotNull  Map<String, String> initialState;

	public AbstractConfiguration() {
		this.configurationDataProvider = ConfigurationDataProvider.of(System.getProperty(FILE_PARAM));
		this.context = System.getProperty(CONTEXT_PARAM);
		this.initialState = Collections.unmodifiableMap(Utils.propertiesToMap(System.getProperties()));
		init();
	}

	public AbstractConfiguration(@NotNull String filePath, @Nullable String context) {
		//noinspection ConstantConditions
		if (filePath == null)
			throw new ConfigurationException("filePath cannot be null. Use no arg constructor instead.");
		if (context != null)
			setProperty(CONTEXT_PARAM, context);
		setProperty(FILE_PARAM, filePath);
		this.configurationDataProvider = ConfigurationDataProvider.of(filePath);
		this.context = context;
		this.initialState = Collections.unmodifiableMap(Utils.propertiesToMap(System.getProperties()));
		init();
	}

	public AbstractConfiguration(@NotNull Path path, @Nullable String context) {
		if (context != null)
			setProperty(CONTEXT_PARAM, context);
		setProperty(FILE_PARAM, path);
		this.configurationDataProvider = ConfigurationDataProvider.of(path);
		this.context = context;
		this.initialState = Collections.unmodifiableMap(Utils.propertiesToMap(System.getProperties()));
		init();
	}

	public AbstractConfiguration(@NotNull InputStream inputStream, @Nullable String context) {
		this.configurationDataProvider = ConfigurationDataProvider.of(inputStream);
		this.context = context;
		this.initialState = Collections.unmodifiableMap(Utils.propertiesToMap(System.getProperties()));
		init();
	}

	private void init() {
		LOG.info("Initializing configuration...");
		setProperties(initialState);
		setProperties(new Parser(configurationDataProvider, context).getConfiguration());
	}

	public synchronized @NotNull Set<String> keySet() {
		return Utils.propertiesToMap(getProperties()).keySet();
	}

	public boolean getBooleanValue(@NotNull String key) {
		return Boolean.parseBoolean(getKey(key));
	}

	public synchronized boolean getBooleanValue(@NotNull String key, boolean defaultValue) {
		String value = System.getProperty(key);
		return value == null ? defaultValue : Boolean.parseBoolean(value);
	}

	public @NotNull List<@NotNull Boolean> getBooleanList(@NotNull String key, @NotNull String separator) {
		List<Boolean> parts = new ArrayList<>();
		for (String value : getKey(key).split(separator))
			parts.add(Boolean.parseBoolean(value.trim()));
		return parts;
	}

	public @NotNull List<@NotNull Boolean> getBooleanList(@NotNull String key) {
		return getBooleanList(key, ITEM_SEPARATOR);
	}

	public byte getByteValue(@NotNull String key) {
		try {
			return Byte.parseByte(getKey(key));
		} catch (NumberFormatException e) {
			throw new NumberFormatException(String.format("Configuration value [%s] is not a parsable byte", key));
		}
	}

	public synchronized byte getByteValue(@NotNull String key, byte defaultValue) {
		String value = System.getProperty(key);
		try {
			return value == null ? defaultValue : Byte.parseByte(value);
		} catch (NumberFormatException e) {
			throw new NumberFormatException(String.format("Configuration value [%s] is not a parsable byte", key));
		}
	}

	public @NotNull List<@NotNull Byte> getByteList(@NotNull String key, @NotNull String separator) {
		List<Byte> parts = new ArrayList<>();
		try {
			for (String value : getKey(key).split(separator))
				parts.add(Byte.parseByte(value.trim()));
			return parts;
		} catch (NumberFormatException e) {
			throw new NumberFormatException(String.format("Configuration value [%s] is not a parsable byte", key));
		}
	}

	public @NotNull List<@NotNull Byte> getByteList(@NotNull String key) {
		return getByteList(key, ITEM_SEPARATOR);
	}

	public char getCharValue(@NotNull String key) {
		return getKey(key).charAt(0);
	}

	public synchronized char getCharValue(@NotNull String key, char defaultValue) {
		String value = System.getProperty(key);
		return value == null ? defaultValue : value.charAt(0);
	}

	public @NotNull List<@NotNull Character> getCharList(@NotNull String key, @NotNull String separator) {
		List<Character> parts = new ArrayList<>();
		for (String value : getKey(key).split(separator))
			parts.add(value.trim().charAt(0));
		return parts;
	}

	public @NotNull List<@NotNull Character> getCharList(@NotNull String key) {
		return getCharList(key, ITEM_SEPARATOR);
	}

	public double getDoubleValue(@NotNull String key) {
		return Double.parseDouble(getKey(key));
	}

	public synchronized double getDoubleValue(@NotNull String key, double defaultValue) {
		String value = System.getProperty(key);
		return value == null ? defaultValue : Double.parseDouble(value);
	}

	public @NotNull List<@NotNull Double> getDoubleList(@NotNull String key, @NotNull String separator) {
		List<Double> parts = new ArrayList<>();
		for (String value : getKey(key).split(separator))
			parts.add(Double.parseDouble(value.trim()));
		return parts;
	}

	public @NotNull List<@NotNull Double> getDoubleList(@NotNull String key) {
		return getDoubleList(key, ITEM_SEPARATOR);
	}

	public float getFloatValue(@NotNull String key) {
		return Float.parseFloat(getKey(key));
	}

	public synchronized float getFloatValue(@NotNull String key, float defaultValue) {
		String value = System.getProperty(key);
		return value == null ? defaultValue : Float.parseFloat(value);
	}

	public @NotNull List<@NotNull Float> getFloatList(@NotNull String key, @NotNull String separator) {
		List<Float> parts = new ArrayList<>();
		for (String value : getKey(key).split(separator))
			parts.add(Float.parseFloat(value.trim()));
		return parts;
	}

	public @NotNull List<@NotNull Float> getFloatList(@NotNull String key) {
		return getFloatList(key, ITEM_SEPARATOR);
	}

	public int getIntValue(@NotNull String key) {
		return Integer.parseInt(getKey(key));
	}

	public synchronized int getIntValue(@NotNull String key, int defaultValue) {
		String value = System.getProperty(key);
		return value == null ? defaultValue : Integer.parseInt(value);
	}

	public @NotNull List<@NotNull Integer> getIntList(@NotNull String key, @NotNull String separator) {
		List<Integer> parts = new ArrayList<>();
		for (String value : getKey(key).split(separator))
			parts.add(Integer.parseInt(value.trim()));
		return parts;
	}

	public @NotNull List<@NotNull Integer> getIntList(@NotNull String key) {
		return getIntList(key, ITEM_SEPARATOR);
	}

	public long getLongValue(@NotNull String key) {
		try {
			return Long.parseLong(getKey(key));
		} catch (NumberFormatException e) {
		        throw new NumberFormatException(String.format("Configuration value [%s] is not a parsable long", key));
		}
	}

	public synchronized long getLongValue(@NotNull String key, long defaultValue) {
		String value = System.getProperty(key);
		try {
			return value == null ? defaultValue : Long.parseLong(value);
		} catch (NumberFormatException e) {
			throw new NumberFormatException(String.format("Configuration value [%s] is not a parsable long", key));
		}
	}

	public @NotNull List<@NotNull Long> getLongList(@NotNull String key, @NotNull String separator) {
		List<Long> parts = new ArrayList<>();
		try {
			for (String value : getKey(key).split(separator))
				parts.add(Long.parseLong(value.trim()));
			return parts;
		} catch (NumberFormatException e) {
			throw new NumberFormatException(String.format("Configuration value [%s] is not a parsable long", key));
		}
	}

	public @NotNull List<@NotNull Long> getLongList(@NotNull String key) {
		return getLongList(key, ITEM_SEPARATOR);
	}

	public short getShortValue(@NotNull String key) {
		try {
			return Short.parseShort(getKey(key));
		} catch (NumberFormatException e) {
			throw new NumberFormatException(String.format("Configuration value [%s] is not a parsable short", key));
		}
	}

	public synchronized short getShortValue(@NotNull String key, short defaultValue) {
		String value = System.getProperty(key);
		try {
			return value == null ? defaultValue : Short.parseShort(value);
		} catch (NumberFormatException e) {
			throw new NumberFormatException(String.format("Configuration value [%s] is not a parsable short", key));
		}
	}

	public @NotNull List<@NotNull Short> getShortList(@NotNull String key, @NotNull String separator) {
		List<Short> parts = new ArrayList<>();
		try {
			for (String value : getKey(key).split(separator))
				parts.add(Short.parseShort(value.trim()));
			return parts;
		} catch (NumberFormatException e) {
			throw new NumberFormatException(String.format("Configuration value [%s] is not a parsable short", key));
		}
	}

	public @NotNull List<@NotNull Short> getShortList(@NotNull String key) {
		return getShortList(key, ITEM_SEPARATOR);
	}

	public @NotNull String getStringValue(@NotNull String key) {
		return getKey(key);
	}

	@Contract("_, null -> null; _, !null -> !null")
	public synchronized @Nullable String getStringValue(@NotNull String key, @Nullable String defaultValue) {
		String value = System.getProperty(key);
		return value == null ? defaultValue : value;
	}

	public @NotNull List<@NotNull String> getStringList(@NotNull String key, @NotNull String separator) {
		List<String> parts = new ArrayList<>();
		for (String value : getKey(key).split(separator))
			parts.add(value.trim());
		return parts;
	}

	public @NotNull List<@NotNull String> getStringList(@NotNull String key) {
		return getStringList(key, ITEM_SEPARATOR);
	}

	public synchronized @NotNull Properties getProperties() {
		return System.getProperties();
	}

	public synchronized <T> void setProperty(@NotNull String key, @NotNull T value) {
		String item = value.toString();
		System.setProperty(key, item);
		LOG.info("Set configuration property: [{}] => [{}]", key, item);
	}

	public synchronized <T> void setProperties(@NotNull Map<String, T> properties) {
		for (Entry<String, T> entry : properties.entrySet())
			setProperty(entry.getKey(), entry.getValue());
	}

	public synchronized void setProperties(@NotNull Properties properties) {
		for (Object e : properties.keySet())
			setProperty((String) e, properties.getProperty((String) e));
	}

	public synchronized void clearProperty(@NotNull String key) {
		System.clearProperty(key);
		LOG.info("Unset configuration property: [{}]", key);
	}

	/**
	 * {@inheritDoc}
	 * <p/>
	 * <p>
	 * The reset procedure restores configuration properties to their initial
	 * values at the time of creation of the <tt>Configurable</tt> instance.
	 * Configuration properties specified via a file are re-processed.
	 * </p>
	 */
	public synchronized void reset() {
		for (String key : Utils.propertiesToMap(getProperties()).keySet())
			clearProperty(key);
		init();
		LOG.info("Configuration properties have been reset");
	}

	private synchronized @NotNull String getKey(@NotNull String key) {
		String value = System.getProperty(key);
		if (value == null)
			throw new ConfigurationException(String.format("Unable to find configuration value for key [%s]", key));
		return value;
	}
}
