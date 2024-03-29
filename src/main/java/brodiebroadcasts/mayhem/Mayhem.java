package brodiebroadcasts.mayhem;

import brodiebroadcasts.mayhem.init.ModEnchantments;
import brodiebroadcasts.mayhem.init.ModEntities;
import brodiebroadcasts.mayhem.init.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mayhem implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("mayhem");
	public static final String MOD_ID = "mayhem";

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		ModItems.initialize();
		ModEntities.initialize();
		ModEnchantments.initialize();
	}
}