package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();
		Set<String> words = new HashSet<String>();
		ArrayList<TextProcessor> list = new ArrayList<TextProcessor>();

		Scanner s = new Scanner(new File("./lab2/nilsholg.txt"), "UTF-8");
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		Scanner sw = new Scanner(new File("./lab2/undantagsord.txt"), "UTF-8");
		sw.useDelimiter(" ");
		


		list.add(new SingleWordCounter("nils"));
		list.add(new SingleWordCounter("norge"));
		list.add(new MultiWordCounter(REGIONS));
		list.add(new GeneralWordCounter(words));


		while(sw.hasNext()){
			String word = sw.next().toLowerCase();
			words.add(word);
		}

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			for(TextProcessor a : list){
				a.process(word);
			}

		}

		s.close();
		sw.close();

		for(TextProcessor a : list){
			a.report(); 
		}

		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");

	}
}