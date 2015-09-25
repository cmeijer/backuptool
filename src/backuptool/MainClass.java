package backuptool;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class MainClass {

	public static void main(String[] args) {
		GuiceModule guiceModule = new GuiceModule("/home/chris/development/backuptool");
		Injector injector = Guice.createInjector(guiceModule);
		MainClass app = injector.getInstance(MainClass.class);
	}
}
