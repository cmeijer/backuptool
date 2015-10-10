package backuptool;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import commands.CopyUnitCommand;
import commands.UpdateListCommand;

public class MainClass {
	private UpdateListCommand updateListCommand;
	private CopyUnitCommand copyUnitCommand;

	@Inject
	public MainClass(UpdateListCommand updateListCommand, CopyUnitCommand copyUnitCommand) {
		this.updateListCommand = updateListCommand;
		this.copyUnitCommand = copyUnitCommand;
	}

	public static void main(String[] args) {
		GuiceModule guiceModule = new GuiceModule("/home/chris/development/backuptool");
		Injector injector = Guice.createInjector(guiceModule);
		MainClass app = injector.getInstance(MainClass.class);
		app.run(args);
	}

	public void run(String[] args) {
		if (args.length == 0) {
			return;
		}
		if ("list".equals(args[0])) {
			updateListCommand.execute();
		}
		if ("copy".equals(args[0])) {
			copyUnitCommand.execute();
		}
	}
}
