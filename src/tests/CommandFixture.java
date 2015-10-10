package tests;

import commands.Command;

public class CommandFixture implements Command {

	private boolean wasCalled = false;

	@Override
	public void execute() {
		wasCalled = true;
	}

	public boolean wasCalled() {
		return wasCalled;
	}

}
