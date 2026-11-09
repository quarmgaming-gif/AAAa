package pl.carbclient.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import pl.carbclient.module.KillAuraModule;

public class KillAuraSettingsScreen extends Screen {
    private final Screen parent;
    private boolean draggingRange;
    private final int panelW = 300;
    private final int panelH = 150;
    private int panelX, panelY;

    public KillAuraSettingsScreen(Screen parent) {
        super(Text.literal("CARB CLIENT - Kill Aura"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        panelX = (width - panelW) / 2;
        panelY = (height - panelH) / 2;
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        renderBackground(ctx, mouseX, mouseY, delta);
        ctx.fill(panelX, panelY, panelX + panelW, panelY + panelH, 0xF0181422);
        ctx.fill(panelX, panelY, panelX + panelW, panelY + 32, 0xFF17111F);
        ctx.fill(panelX, panelY + 31, panelX + panelW, panelY + 33, 0xFF9B4DFF);
        ctx.drawTextWithShadow(textRenderer, "KILL AURA - USTAWIENIA",
                panelX + 12, panelY + 12, 0xFFFFFFFF);
        ctx.drawTextWithShadow(textRenderer,
                "Range: " + String.format(java.util.Locale.ROOT, "%.1f", KillAuraModule.range) + " blokow",
                panelX + 16, panelY + 58, 0xFFFFFFFF);

        int sx = panelX + 18;
        int sy = panelY + 91;
        int sw = panelW - 36;
        ctx.fill(sx, sy, sx + sw, sy + 6, 0xFF51485B);
        int knobX = sx + (int) ((KillAuraModule.range - 1.0) / 5.0 * sw);
        ctx.fill(knobX - 4, sy - 5, knobX + 5, sy + 11, 0xFFB66BFF);
        ctx.drawTextWithShadow(textRenderer, "1.0", sx, sy + 18, 0xFFB8AFC4);
        ctx.drawTextWithShadow(textRenderer, "6.0", sx + sw - 18, sy + 18, 0xFFB8AFC4);
        ctx.drawCenteredTextWithShadow(textRenderer, "Przeciagnij suwak | ESC: powrot",
                width / 2, panelY + 127, 0xFFB8AFC4);
        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int sx = panelX + 18, sy = panelY + 91, sw = panelW - 36;
        if (button == 0 && mouseY >= sy - 8 && mouseY <= sy + 14
                && mouseX >= sx && mouseX <= sx + sw) {
            draggingRange = true;
            updateSlider(mouseX);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button,
                                double deltaX, double deltaY) {
        if (draggingRange && button == 0) {
            updateSlider(mouseX);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) draggingRange = false;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    private void updateSlider(double mouseX) {
        int sx = panelX + 18, sw = panelW - 36;
        double amount = Math.max(0.0, Math.min(1.0, (mouseX - sx) / sw));
        KillAuraModule.setRange(1.0 + amount * 5.0);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            client.setScreen(parent);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
