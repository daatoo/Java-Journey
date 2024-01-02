package filetree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class FileUtil {

	public static File toFileRepresentation(Path path) throws IOException {
		if (Files.isRegularFile(path)) {
			return new RegularFile(path);
		}
		try (Stream<Path> stream = Files.list(path)) {
			Iterator<Path> dirContents = stream.iterator();
			List<File> contents = new ArrayList<File>();
			while (dirContents.hasNext()) {
				Path p = dirContents.next();
				if (Files.isRegularFile(p)) {
					contents.add(new RegularFile(p));
				} else {
					contents.add(toFileRepresentation(p));
				}
			}
			return new Directory(path, contents);
		}
	}

}
