package backuptool;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import commands.UpdateListCommand;

public class MainClass {
	private UpdateListCommand updateListCommand;

	@Inject
	public MainClass(UpdateListCommand updateListCommand) {
		this.updateListCommand = updateListCommand;
	}

	public static void main(String[] args) {
		GuiceModule guiceModule = new GuiceModule("/home/chris/development/backuptool");
		Injector injector = Guice.createInjector(guiceModule);
		MainClass app = injector.getInstance(MainClass.class);
		app.run();
	}

	private void run() {
		updateListCommand.execute();
	}
}
