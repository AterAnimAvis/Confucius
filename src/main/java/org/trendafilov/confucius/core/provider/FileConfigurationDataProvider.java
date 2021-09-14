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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class FileConfigurationDataProvider implements ConfigurationDataProvider {
	private final @Nullable File file;

	public FileConfigurationDataProvider(@Nullable File file) {
		this.file = file;
	}

	public FileConfigurationDataProvider(@Nullable String filename) {
		this.file = filename == null ? null : new File(filename);
	}

	public @NotNull List<String> getAllLines() throws IOException {
		return file == null ? new ArrayList<>() : Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
	}

	public @Nullable InputStream getInputStream() throws IOException {
		return file == null ? null : new FileInputStream(file);
	}
}
