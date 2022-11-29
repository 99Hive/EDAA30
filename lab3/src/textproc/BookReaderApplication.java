package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookReaderApplication {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner s = new Scanner(new File("nilsholg.txt"), "UTF-8");
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		Scanner sw = new Scanner(new File("undantagsord.txt"), "UTF-8");
		sw.useDelimiter(" ");

		Set<String> words = new HashSet<String>();

		while (sw.hasNext()) {
			String word = sw.next().toLowerCase();
			words.add(word);
		}

		GeneralWordCounter wc = new GeneralWordCounter(words);

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			wc.process(word);
		}

		s.close();
		sw.close();

		BookReaderController bc = new BookReaderController(wc);

	}
}
