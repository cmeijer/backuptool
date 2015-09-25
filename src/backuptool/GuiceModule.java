package backuptool;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class GuiceModule extends AbstractModule implements Module {
	private final String configDir;

	public GuiceModule(String configDir) {
		this.configDir = configDir;
	}

	@Override
	protected void configure() {
		bind(FileCopier.class).to(IoFileCopier.class);

		FileReader fileReader = null;
		try {
			Properties properties = new Properties();
			fileReader = new FileReader(configDir + File.separator + "settings.properties");
			properties.load(fileReader);
			Names.bindProperties(binder(), properties);
		} catch (IOException ex) {
			throw new RuntimeException("Error reading settings file.", ex);
		} finally {
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
				}
			}
		}
	}

}