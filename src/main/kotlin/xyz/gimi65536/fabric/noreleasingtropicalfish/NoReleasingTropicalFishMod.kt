package xyz.gimi65536.fabric.noreleasingtropicalfish

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object NoReleasingTropicalFishMod : ModInitializer {
    private val logger = LoggerFactory.getLogger("no-releasing-tropical-fish")

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("No tropical fishes get released!")
	}
}