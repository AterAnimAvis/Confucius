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

package org.trendafilov.confucius;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The <tt>Configurable</tt> interface defines the contracts which are exposed
 * publicly via the API to all clients of the framework.
 * 
 * @author Ivan Trendafilov
 * @since  1.0
 */
public interface Configurable {

	/**
	 * Returns a {@link Set} view of the keys contained in the configuration
	 * map.
	 * 
	 * @return a set view of the keys contained in the configuration properties
	 */
	@NotNull Set<@NotNull String> keySet();

	/**
	 * Returns as a {@code boolean} the configuration value to which the
	 * specified key is mapped, or throws an unchecked
	 * {@code ConfigurationException} if the specified key is missing in the
	 * configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as a boolean
	 */
	boolean getBooleanValue(@NotNull String key);

	/**
	 * Returns as a {@code boolean} the configuration value to which the
	 * specified key is mapped, or returns the provided default value argument
	 * if the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as a boolean, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	boolean getBooleanValue(@NotNull String key, boolean defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of boolean values, as separated on the <b>separator</b>
	 *         regular expression
	 */
	@NotNull List<@NotNull Boolean> getBooleanList(@NotNull String key, @NotNull String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of boolean values, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	@NotNull List<@NotNull Boolean> getBooleanList(@NotNull String key);

	/**
	 * Returns as a {@code byte} the configuration value to which the specified
	 * key is mapped, or throws an unchecked {@code ConfigurationException} if
	 * the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as a byte
	 */
	byte getByteValue(@NotNull String key);

	/**
	 * Returns as a {@code byte} the configuration value to which the specified
	 * key is mapped, or returns the provided default value argument if the
	 * specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as a byte, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	byte getByteValue(@NotNull String key, byte defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of byte values, as separated on the <b>separator</b>
	 *         regular expression
	 */
	@NotNull List<@NotNull Byte> getByteList(@NotNull String key, @NotNull String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of byte values, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	@NotNull List<@NotNull Byte> getByteList(@NotNull String key);

	/**
	 * Returns as a {@code char} the configuration value to which the specified
	 * key is mapped, or throws an unchecked {@code ConfigurationException} if
	 * the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as a char
	 */
	char getCharValue(@NotNull String key);

	/**
	 * Returns as a {@code char} the configuration value to which the specified
	 * key is mapped, or returns the provided default value argument if the
	 * specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as a char, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	char getCharValue(@NotNull String key, char defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of char values, as separated on the <b>separator</b>
	 *         regular expression
	 */
	@NotNull List<@NotNull Character> getCharList(@NotNull String key, @NotNull String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of char values, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	@NotNull List<@NotNull Character> getCharList(@NotNull String key);

	/**
	 * Returns as a {@code double} the configuration value to which the
	 * specified key is mapped, or throws an unchecked
	 * {@code ConfigurationException} if the specified key is missing in the
	 * configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as a double
	 */
	double getDoubleValue(@NotNull String key);

	/**
	 * Returns as a {@code double} the configuration value to which the
	 * specified key is mapped, or returns the provided default value argument
	 * if the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as a double, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	double getDoubleValue(@NotNull String key, double defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of double values, as separated on the <b>separator</b>
	 *         regular expression
	 */
	@NotNull List<@NotNull Double> getDoubleList(@NotNull String key, @NotNull String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of double values, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	@NotNull List<@NotNull Double> getDoubleList(@NotNull String key);

	/**
	 * Returns as a {@code float} the configuration value to which the specified
	 * key is mapped, or throws an unchecked {@code ConfigurationException} if
	 * the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as a float
	 */
	float getFloatValue(@NotNull String key);

	/**
	 * Returns as a {@code float} the configuration value to which the specified
	 * key is mapped, or returns the provided default value argument if the
	 * specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as a float, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	float getFloatValue(@NotNull String key, float defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of float values, as separated on the <b>separator</b>
	 *         regular expression
	 */
	@NotNull List<@NotNull Float> getFloatList(@NotNull String key, @NotNull String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of float values, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	@NotNull List<@NotNull Float> getFloatList(@NotNull String key);

	/**
	 * Returns as an {@code int} the configuration value to which the specified
	 * key is mapped, or throws an unchecked {@code ConfigurationException} if
	 * the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as an int
	 */
	int getIntValue(@NotNull String key);

	/**
	 * Returns as an {@code int} the configuration value to which the specified
	 * key is mapped, or returns the provided default value argument if the
	 * specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as an int, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	int getIntValue(@NotNull String key, int defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of int values, as separated on the <b>separator</b>
	 *         regular expression
	 */
	@NotNull List<@NotNull Integer> getIntList(@NotNull String key, @NotNull String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of int values, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	@NotNull List<@NotNull Integer> getIntList(@NotNull String key);

	/**
	 * Returns as a {@code long} the configuration value to which the specified
	 * key is mapped, or throws an unchecked {@code ConfigurationException} if
	 * the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as a long
	 */
	long getLongValue(@NotNull String key);

	/**
	 * Returns as a {@code long} the configuration value to which the specified
	 * key is mapped, or returns the provided default value argument if the
	 * specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as a long, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	long getLongValue(@NotNull String key, long defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of long values, as separated on the <b>separator</b>
	 *         regular expression
	 */
	@NotNull List<@NotNull Long> getLongList(@NotNull String key, @NotNull String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of long values, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	@NotNull List<@NotNull Long> getLongList(@NotNull String key);

	/**
	 * Returns as a {@code short} the configuration value to which the specified
	 * key is mapped, or throws an unchecked {@code ConfigurationException} if
	 * the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as a short
	 */
	short getShortValue(@NotNull String key);

	/**
	 * Returns as a {@code short} the configuration value to which the specified
	 * key is mapped, or returns the provided default value argument if the
	 * specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as a short, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	short getShortValue(@NotNull String key, short defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of short values, as separated on the <b>separator</b>
	 *         regular expression
	 */
	@NotNull List<@NotNull Short> getShortList(@NotNull String key, @NotNull String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of short values, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	@NotNull List<@NotNull Short> getShortList(@NotNull String key);

	/**
	 * Returns as a {@link String} the configuration value to which the
	 * specified key is mapped, or throws an unchecked
	 * {@code ConfigurationException} if the specified key is missing in the
	 * configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return associated value as a String
	 */
	@NotNull String getStringValue(@NotNull String key);

	/**
	 * Returns as a {@link String} the configuration value to which the
	 * specified key is mapped, or returns the provided default value argument
	 * if the specified key is missing in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param defaultValue
	 *            is returned if the map does not contain the passed <b>key</b>
	 *            parameter
	 * @return associated value as a String, or <b>defaultValue</b> if the map
	 *         does not contain the <b>key</b>
	 */
	@Contract("_, null -> null; _, !null -> !null")
	@Nullable String getStringValue(@NotNull String key, @Nullable String defaultValue);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties.
	 * 
	 * @param key
	 *            of the configuration property
	 * @param separator
	 *            regular expression
	 * @return a list of strings, as separated on the <b>separator</b> regular
	 *         expression
	 */
	@NotNull List<@NotNull String> getStringList(@NotNull String key, @NotNull String separator);

	/**
	 * Returns a List of values mapped to the specified key, or throws an
	 * unchecked {@code ConfigurationException} if the specified key is missing
	 * in the configuration properties. Uses the comma character ("<b>,</b>") as
	 * the regular expression for separation of items.
	 * 
	 * @param key
	 *            of the configuration property
	 * @return a list of strings, as separated on the comma character
	 *         ("<b>,</b>")
	 */
	@NotNull List<@NotNull String> getStringList(@NotNull String key);

	/**
	 * Returns the current configuration properties.
	 * 
	 * @return properties
	 * 
	 * @see java.lang.System#getProperties()
	 * @see java.util.Properties
	 */
	@NotNull Properties getProperties();

	/**
	 * Sets the configuration property indicated by the specified key.
	 * 
	 * <p>
	 * Where applicable, the value argument must override its {@code toString}
	 * method to provide the desired textually representative value to store as
	 * a Configuration property.
	 * </p>
	 * 
	 * @param key
	 *            the name of the configuration property
	 * @param value
	 *            the value of the configuration property
	 */
	<T> void setProperty(@NotNull String key, @NotNull T value);

	/**
	 * Sets the configuration properties as indicated by the specified map.
	 * 
	 * @param properties
	 *            a map of configuration properties
	 * @see #setProperty(String, T)
	 */
	<T> void setProperties(@NotNull Map<String, T> properties);

	/**
	 * Sets the configuration properties to the <code>Properties</code>
	 * argument.
	 * 
	 * @param properties
	 *            the new system properties
	 * @see java.util.Properties
	 * @see java.lang.System#setProperties(Properties)
	 */
	void setProperties(@NotNull Properties properties);

	/**
	 * Removes the configuration property indicated by the specified key.
	 * 
	 * @param key
	 *            of the configuration property
	 */
	void clearProperty(@NotNull String key);

	/**
	 * Resets all intermediate state held in the configuration properties.
	 * 
	 * <p>
	 * It is advisable not to call this method during normal program flow.
	 * Instead, use it to reset the state of a <tt>Configurable</tt> instance
	 * between unit tests.
	 * </p>
	 */
	@ApiStatus.Internal
	void reset();
}
