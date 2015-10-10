package tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Before;
import org.junit.Test;

import backuptool.MainClass;
import commands.CopyUnitCommand;
import commands.UpdateListCommand;

public class MainClassTest {
	private UpdateListCommand updateListCommand;
	private CopyUnitCommand copyUnitCommand;
	private MainClass app;

	@Test
	public void run_list_listCommandWasCalled() {
		app.run(new String[] { "list" });
		verify(updateListCommand).execute();
	}

	@Test
	public void run_list_copyCommandWasNotCalled() {
		app.run(new String[] { "list" });
		verifyZeroInteractions(copyUnitCommand);
	}

	@Test
	public void run_copy_copyCommandWasCalled() {
		app.run(new String[] { "copy" });
		verify(copyUnitCommand).execute();
	}

	@Test
	public void run_copy_listCommandWasNotCalled() {
		app.run(new String[] { "copy" });
		verifyZeroInteractions(updateListCommand);
	}

	@Test
	public void run_none_copyCommandWasNotCalled() {
		app.run(new String[] {});
		verifyZeroInteractions(copyUnitCommand);
	}

	@Test
	public void run_none_listCommandWasNotCalled() {
		app.run(new String[] {});
		verifyZeroInteractions(updateListCommand);
	}

	@Before
	public void setUp() {
		updateListCommand = mock(UpdateListCommand.class);
		copyUnitCommand = mock(CopyUnitCommand.class);
		app = new MainClass(updateListCommand, copyUnitCommand);
	}
}
