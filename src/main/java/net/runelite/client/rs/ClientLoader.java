/*
 * Decompiled with CFR 0.150.
 */
package net.runelite.client.rs;

import com.ferox.Client;
import net.runelite.client.ui.SplashScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.applet.Applet;
import java.awt.*;
import java.io.IOException;

import static java.lang.System.out;

@Singleton
public class ClientLoader {
	private static final Logger log = LoggerFactory.getLogger(ClientLoader.class);

	@Inject
	private ClientLoader(@Named(value="updateCheckMode") ClientUpdateCheckMode updateCheckMode, ClientConfigLoader clientConfigLoader) {
	}

	private static Canvas loadVanilla() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<Client> clientClass = Client.class;
		return ClientLoader.loadFromClass(clientClass);
	}

	private static Canvas loadFromClass(Class<?> clientClass) throws IllegalAccessException, InstantiationException {
		return (Canvas)clientClass.newInstance();
	}

	public Canvas load() {
		try {

			SplashScreen.stage(.465, "Starting", "Starting Ferox");

            Canvas rs = loadVanilla();

			SplashScreen.stage(.5, null, "Starting core classes");
			return rs;
		}
		catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			if (e instanceof ClassNotFoundException) {
                out.println("Unable to load client - class not found. This means you are not running RuneLite with Maven as the injected client is not in your classpath.");
			}
			log.error("Error loading RS!", e);
			System.exit(-1);
			return null;
		}
	}
}

