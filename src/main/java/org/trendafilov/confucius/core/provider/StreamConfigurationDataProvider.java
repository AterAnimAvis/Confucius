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

package org.trendafilov.confucius.core.provider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class StreamConfigurationDataProvider implements ConfigurationDataProvider {

	private @Nullable InputStream inputStream;

	public StreamConfigurationDataProvider(@Nullable InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public @NotNull List<String> getAllLines() throws IOException {
		if (inputStream == null)
			return new ArrayList<>();
		String configurationString = Utils.streamToString(inputStream);
		this.inputStream = new ByteArrayInputStream(configurationString.getBytes(StandardCharsets.UTF_8));
		return new ArrayList<>(Arrays.asList(configurationString.split("\\r?\\n")));
	}

	public @Nullable InputStream getInputStream() {
		return inputStream;
	}
}
