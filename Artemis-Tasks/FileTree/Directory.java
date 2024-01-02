package filetree;

import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Directory extends File {

	private final List<File> files;

	public Directory(Path path, List<File> files) {
		super(path);
		this.files = files;
	}

	@Override
	public Iterator<File> iterator() {
		/**
		 * Liefert einen Iterator über das Verzeichnis selbst, sowie alle rekursiv enthaltenen Elemente.
		 */
		return new Iterator<File>() {
			int index = -1;
			Iterator<File> subIterator;

			@Override
			public boolean hasNext() {
				if (files == null || index == files.size()) {
					return false;
				}
				return true;
			}
			@Override
			public File next() {
				if (index == -1) {
					index++;
					return Directory.this;
				} else if (index == files.size()) {
					throw new NoSuchElementException();
				}
				if (subIterator == null) {
					subIterator = files.get(index).iterator();
				}
				File ret = subIterator.next();
				if (!subIterator.hasNext()) {
					subIterator = null;
					index++;
				}
				return ret;
			}
		};
	}

	@Override
	public int getHeight() {
		int maxDepth = 0;
		// Blatt hat Höhe 0
		if (files.isEmpty()) {
			return 0;
		}
		for (File file : files) {
			int depth = 0;
			if ((depth = file.getHeight()) > maxDepth) {
				maxDepth = depth;
			}
		}
		return maxDepth + 1;
	}

	@Override
	public boolean isRegularFile() {
		return false;
	}

	public List<File> getFiles() {
		return files;
	}

}
