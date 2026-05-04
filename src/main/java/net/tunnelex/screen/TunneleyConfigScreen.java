package net.tunnelex.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.tunnelex.config.TunneleyConfig;

public class TunneleyConfigScreen extends Screen {

	private final Screen parent;

	public TunneleyConfigScreen(Screen parent) {
		super(Text.literal("Tunnelex Configuration"));
		this.parent = parent;
	}

	@Override
	protected void init() {
		super.init();

		// Add buttons for toggling features
		int buttonWidth = 150;
		int buttonHeight = 20;
		int x = this.width / 2 - buttonWidth / 2;
		int y = 60;
		int spacing = 25;

		// FPS Boost Toggle
		this.addDrawableChild(ButtonWidget.builder(
				Text.literal("FPS Boost: " + (TunneleyConfig.fpsBoostEnabled ? "ON" : "OFF")),
				button -> {
					TunneleyConfig.fpsBoostEnabled = !TunneleyConfig.fpsBoostEnabled;
					button.setMessage(Text.literal("FPS Boost: " + (TunneleyConfig.fpsBoostEnabled ? "ON" : "OFF")));
					TunneleyConfig.save();
				}
		)
		.dimensions(x, y, buttonWidth, buttonHeight)
		.build());

		// AFK Detection Toggle
		this.addDrawableChild(ButtonWidget.builder(
				Text.literal("AFK Detection: " + (TunneleyConfig.afkDetectionEnabled ? "ON" : "OFF")),
				button -> {
					TunneleyConfig.afkDetectionEnabled = !TunneleyConfig.afkDetectionEnabled;
					button.setMessage(Text.literal("AFK Detection: " + (TunneleyConfig.afkDetectionEnabled ? "ON" : "OFF")));
					TunneleyConfig.save();
				}
		)
		.dimensions(x, y + spacing, buttonWidth, buttonHeight)
		.build());

		// Combat Boost Toggle
		this.addDrawableChild(ButtonWidget.builder(
				Text.literal("Combat Boost: " + (TunneleyConfig.combatBoostEnabled ? "ON" : "OFF")),
				button -> {
					TunneleyConfig.combatBoostEnabled = !TunneleyConfig.combatBoostEnabled;
					button.setMessage(Text.literal("Combat Boost: " + (TunneleyConfig.combatBoostEnabled ? "ON" : "OFF")));
					TunneleyConfig.save();
				}
		)
		.dimensions(x, y + spacing * 2, buttonWidth, buttonHeight)
		.build());

		// Double AFK Detection Toggle
		this.addDrawableChild(ButtonWidget.builder(
				Text.literal("Double AFK: " + (TunneleyConfig.doubleAFKDetectionEnabled ? "ON" : "OFF")),
				button -> {
					TunneleyConfig.doubleAFKDetectionEnabled = !TunneleyConfig.doubleAFKDetectionEnabled;
					button.setMessage(Text.literal("Double AFK: " + (TunneleyConfig.doubleAFKDetectionEnabled ? "ON" : "OFF")));
					TunneleyConfig.save();
				}
		)
		.dimensions(x, y + spacing * 3, buttonWidth, buttonHeight)
		.build());

		// Back Button
		this.addDrawableChild(ButtonWidget.builder(
				Text.literal("Back"),
				button -> this.close()
		)
		.dimensions(x, this.height - 30, buttonWidth, buttonHeight)
		.build());
	}

	@Override
	public void render(net.minecraft.client.gui.DrawContext context, int mouseX, int mouseY, float delta) {
		this.renderBackground(context);
		context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
		super.render(context, mouseX, mouseY, delta);
	}

	@Override
	public void close() {
		this.client.setScreen(this.parent);
	}

	@Override
	public boolean shouldCloseOnEsc() {
		return true;
	}
}