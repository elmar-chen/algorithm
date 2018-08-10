package elmar.algorithm.leetcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SubmissionGenerator {
	public static void main(String[] args) {

		File root = new File(args.length > 0 ? args[0] : ".", "src");
		String sourcePath = SubmissionGenerator.class.getPackage().getName();
		File dir = new File(root, sourcePath.replace('.', '/'));
		if (!dir.isDirectory())
			throw new RuntimeException("cannot find source folder:" + dir.getAbsolutePath());

		File[] sources = dir.listFiles((f, n) -> n.matches("P[0-9]+\\.java+"));
		Set<String> imports = new HashSet<>();
		StringBuffer sbContent = new StringBuffer();
		Arrays.asList(sources).forEach(file -> {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (line.startsWith("import")) {

					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

	}

	public static void generateSubmission(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("import")) {

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
