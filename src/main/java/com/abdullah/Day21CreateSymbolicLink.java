package com.abdullah;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day21CreateSymbolicLink {
	public static void main(String[] args) throws IOException {
		String base = "/Users/cmabdullahkhan/Documents/workspace/_100DaysOfCode/";
		Path resourcesDirectory = Path.of(base + "resourcesForFile");
		Path path1 = Path.of(base + "resourcesForFile/docs/originalFile.txt");

		Path path2 = Path.of("./resourcesForFile/docsV2/symbolicLink.txt");

		if (Files.notExists(path2)) {
			Files.createSymbolicLink(path2, path1);
		}

		Files.list(resourcesDirectory).forEach(n -> System.out.println(n.toString()));
		Files.walk(resourcesDirectory).map(Path::toString).forEach(System.out::println);
		System.out.println("isSymbolicLink " + Files.isSymbolicLink(path2));

	}
}
