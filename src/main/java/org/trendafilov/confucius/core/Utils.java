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
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class Utils {
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	private Utils() {
	}

	static @NotNull Map<String, String> propertiesToMap(@NotNull Properties props) {
		Map<String, String> properties = new HashMap<>();
		for (Object e : props.keySet()) {
			String key = (String) e;
			String value = props.getProperty(key);
			properties.put(key, value);
		}
		return properties;
	}

	static @NotNull String streamToString(@Nullable InputStream input) throws IOException {
		if (input == null) return "";

		StringWriter output = new StringWriter();
		InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);

		char[] buffer = new char[DEFAULT_BUFFER_SIZE];
		for (int n = reader.read(buffer); n != -1; n = reader.read(buffer)) {
			output.write(buffer, 0, n);
		}
		return output.toString();
	}

}
