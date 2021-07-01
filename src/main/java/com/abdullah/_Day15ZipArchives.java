package com.abdullah;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class _Day15ZipArchives {
	public static void main(String[] args) throws IOException {
		Path relativePath = Path.of(".");
		Path basePath = Path.of("./resourcesForFile/");
		final Path zipFileName = Path.of("./abu"+ LocalTime.now() +".zip");
		Files.createFile(zipFileName);
		try (ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(zipFileName))) {
			out.setLevel(Deflater.BEST_COMPRESSION);
			Files.walk(basePath).filter(filePath -> !Files.isDirectory(filePath)).forEach(filePath -> {
				String filePathOfCurrentDirectory = relativePath.relativize(filePath).toString();
				ZipEntry zipEntry = new ZipEntry(filePathOfCurrentDirectory);
				try {
					out.putNextEntry(zipEntry);
					out.write(Files.readAllBytes(filePath));
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			});
			System.out.println("Zip Archive created");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
