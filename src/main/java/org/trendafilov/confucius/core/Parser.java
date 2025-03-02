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

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.trendafilov.confucius.ConfigurationException;
import org.trendafilov.confucius.core.provider.ConfigurationDataProvider;

class Parser {
	private final static String DEFAULT_CONTEXT = "Default";
	private final static String COMMENT = "#";
	private final static String IDENTITY = "=";
	private final static String LEFT_CONTEXT = "[";
	private final static String RIGHT_CONTEXT = "]";
	private final static String LEFT_SUBSTITUTION = "${";
	private final static String RIGHT_SUBSTITUTION = "}";

	private final Map<String, String> configuration = new HashMap<>();

	public Parser(@NotNull ConfigurationDataProvider configurationDataProvider, @Nullable String context) {
		try {
			Collection<String> lines = configurationDataProvider.getAllLines();
			if (!lines.isEmpty() && isStandardProps(lines)) {
				loadStandardProps(configurationDataProvider);
			} else {
				parseContext(lines, DEFAULT_CONTEXT);
				parseContext(lines, context);
			}
			parseVariables();
		} catch (IOException e) {
			throw new ConfigurationException("Unable to read configuration", e);
		}
	}

	public @NotNull Map<String, String> getConfiguration() {
		return configuration;
	}

	private @NotNull Map<String, String> parseLine(@NotNull String line) {
		Map<String, String> pair = new HashMap<>();
		String newLine = line.trim();
		if (line.contains(COMMENT))
			newLine = newLine.substring(0, line.indexOf(COMMENT)).trim();
		if (newLine.isEmpty())
			return pair;
		if (newLine.contains(IDENTITY)) {
			String key = newLine.substring(0, newLine.indexOf(IDENTITY)).trim();
			String value = newLine.substring(newLine.indexOf(IDENTITY) + 1).trim();
			pair.put(key, value);
			return pair;
		} else {
			throw new ConfigurationException(String.format("Unparsable line: [%s]", line));
		}
	}

	private boolean isStandardProps(@NotNull Collection<String> lines) {
		for (String line : lines)
			if (isContext(line))
				return false;
		return true;
	}

	private void loadStandardProps(@NotNull ConfigurationDataProvider provider) throws IOException {
		Properties props = new Properties();
		InputStream stream = provider.getInputStream();
		if (stream != null) props.load(stream);
		configuration.putAll(Utils.propertiesToMap(props));
	}

	private void parseContext(@NotNull Collection<String> lines, @Nullable String context) {
		boolean insideContext = false;
		for (String line : lines) {
			if (isNamedContext(line, context))
				insideContext = true;
			else if (insideContext && isContext(line))
				insideContext = false;
			else if (insideContext)
				configuration.putAll(parseLine(line));
		}
	}

	private void parseVariables() {
		int previousSize = 0;
		Map<String, String> unresolved = new HashMap<>();
		for (Entry<String, String> entry : configuration.entrySet())
			if (isSubstitution(entry.getValue()))
				unresolved.put(entry.getKey(), getSubstitution(entry.getValue()));
		while (previousSize != unresolved.size()) {
			previousSize = unresolved.size();
			List<String> resolved = new ArrayList<>();
			for (Entry<String, String> entry : unresolved.entrySet())
				if (configuration.containsKey(entry.getValue()) && !unresolved.containsKey(entry.getValue())) {
					resolved.add(entry.getKey());
					configuration.put(entry.getKey(), configuration.get(entry.getValue()));
				}
			for (String item : resolved)
				unresolved.remove(item);
		}
	}

	private boolean isContext(@NotNull String line) {
		line = line.trim();
		return line.startsWith(LEFT_CONTEXT) && line.endsWith(RIGHT_CONTEXT);
	}

	private boolean isNamedContext(@NotNull String line, @Nullable String context) {
		return context != null && line.trim().equalsIgnoreCase(LEFT_CONTEXT + context + RIGHT_CONTEXT);
	}

	private boolean isSubstitution(@NotNull String value) {
		return value.startsWith(LEFT_SUBSTITUTION) && value.endsWith(RIGHT_SUBSTITUTION);
	}

	private @NotNull String getSubstitution(@NotNull String value) {
		return value.substring(2, value.length() - 1);
	}
}
