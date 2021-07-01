package com.abdullah;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.*;
import java.time.Instant;
import java.util.Set;
/***
 * set last modified time
 * set permissions
 * look up users and groups using FileSystem UserPrincipalLookupService
 * Set owner and group
 */
public class Day14FilePermission {
	public static void main(String[] args) throws IOException {
		Path p2 = Path.of("./resourcesForFile/docs/abdullah.txt");
		Files.setLastModifiedTime(p2, FileTime.from(Instant.now()));
		Set<PosixFilePermission> perams = PosixFilePermissions.fromString("rw-rw-r--");
		Files.setPosixFilePermissions(p2, perams);
		FileSystem fs = p2.getFileSystem();
		UserPrincipalLookupService userPrincipalLookupService = fs.getUserPrincipalLookupService();
		UserPrincipal user = userPrincipalLookupService.lookupPrincipalByName("cmabdullahkhan");
		GroupPrincipal groupPrincipal = userPrincipalLookupService.lookupPrincipalByGroupName("staff");
		Files.setOwner(p2, user);
		Files.getFileAttributeView(p2, PosixFileAttributeView.class).setGroup(groupPrincipal);
	}
}
