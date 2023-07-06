package xyz.gimi65536.fabric.noreleasingtropicalfish

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.fabricmc.fabric.api.event.player.UseEntityCallback
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.entity.passive.AxolotlEntity
import net.minecraft.item.Items
import net.minecraft.util.ActionResult

object NoReleasingTropicalFishModClient : ClientModInitializer {
	private val bypassModifier =
        KeyBinding(
                "no-releasing-tropical-fish.key.bypass",
                InputUtil.UNKNOWN_KEY.code,
                "no-releasing-tropical-fish.key.category"
        )

	override fun onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		KeyBindingHelper.registerKeyBinding(bypassModifier)

		UseBlockCallback.EVENT.register(
			@Suppress("UNUSED_ANONYMOUS_PARAMETER")
			fun(player, world, hand, hitResult): ActionResult{
				if(player.isSpectator()){
					return ActionResult.PASS
				}
				if(player.getStackInHand(hand).isOf(Items.TROPICAL_FISH_BUCKET)){
					return if(bypassModifier.isPressed) ActionResult.PASS else ActionResult.FAIL
				}
				return ActionResult.PASS
			}
		)
		UseEntityCallback.EVENT.register(
			@Suppress("UNUSED_ANONYMOUS_PARAMETER")
			fun(player, world, hand, entity, hitResult): ActionResult{
				if(player.isSpectator()){
					return ActionResult.PASS
				}
				if(player.getStackInHand(hand).isOf(Items.TROPICAL_FISH_BUCKET) and (entity !is AxolotlEntity)){
					return if(bypassModifier.isPressed) ActionResult.PASS else ActionResult.FAIL
				}
				return ActionResult.PASS
			}
		)
	}
}