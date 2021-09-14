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

import com.google.common.collect.Lists;
import com.google.common.jimfs.Jimfs;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PathConfigurationDataProviderTest {
	private static FileSystem JIM_FS;
	private static Path FILENAME;

	@BeforeAll
	public static void setup() throws IOException {
		JIM_FS = Jimfs.newFileSystem();
		FILENAME = JIM_FS.getPath("path_provider_test");
		Files.writeString(FILENAME, "a\nb\r\nc\n");
	}

	@AfterAll
	public static void tearDown() throws IOException {
		JIM_FS.close();
	}

	@Test
	public void testReturnInputStream() throws IOException {
		PathConfigurationDataProvider provider = new PathConfigurationDataProvider(FILENAME);
		assertEquals("a\nb\r\nc\n", Utils.streamToString(provider.getInputStream()));
	}

	@Test
	public void testReturnEmptyListWhenFileNameIsNull() throws IOException {
		PathConfigurationDataProvider provider = new PathConfigurationDataProvider(null);
		List<String> lines = provider.getAllLines();
		assertNotNull(lines);
		assertEquals(0, lines.size());
	}

	@Test
	public void testReturnLines() throws IOException {
		PathConfigurationDataProvider provider = new PathConfigurationDataProvider(FILENAME);
		List<String> lines = provider.getAllLines();
		assertEquals(3, lines.size());
		assertEquals("a", lines.get(0));
		assertEquals("b", lines.get(1));
		assertEquals("c", lines.get(2));
	}
}