package pl.carbclient.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import pl.carbclient.module.KillAuraModule;

public class CarbClickGui extends Screen {
    private static final int PURPLE = 0xFF9B4DFF;
    private static final int PANEL = 0xF0181422;
    private static final int CARD = 0xFF241D30;
    private static final int MUTED = 0xFFB8AFC4;

    private int panelX, panelY, panelW = 430, panelH = 270;
    private final String[] categories = {
            "COMBAT", "MOVEMENT", "RENDER", "PLAYER", "WORLD",
            "EXPLOITS", "HUD", "UTILITY", "MISC", "VISUALS"
    };
    private int selectedCategory = 0;

    public CarbClickGui() {
        super(Text.literal("CARB CLIENT"));
    }

    @Override
    protected void init() {
        panelX = (width - panelW) / 2;
        panelY = (height - panelH) / 2;
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        renderBackground(ctx, mouseX, mouseY, delta);
        ctx.fill(panelX, panelY, panelX + panelW, panelY + panelH, PANEL);
        ctx.fill(panelX, panelY, panelX + panelW, panelY + 34, 0xFF17111F);
        ctx.fill(panelX, panelY + 33, panelX + panelW, panelY + 35, PURPLE);
        ctx.drawTextWithShadow(textRenderer, "CARB CLIENT", panelX + 14, panelY + 12, 0xFFFFFFFF);
        ctx.drawTextWithShadow(textRenderer, "1.21.4 | FABRIC", panelX + panelW - 105, panelY + 13, MUTED);

        int tabY = panelY + 48;
        for (int i = 0; i < categories.length; i++) {
            int col = i % 5;
            int row = i / 5;
            int x = panelX + 14 + col * 80;
            int y = tabY + row * 27;
            boolean active = i == selectedCategory;
            ctx.fill(x, y, x + 74, y + 23, active ? 0xFF563078 : CARD);
            ctx.drawCenteredTextWithShadow(textRenderer, categories[i], x + 37, y + 8,
                    active ? 0xFFFFFFFF : MUTED);
        }

        int listX = panelX + 14;
        int listY = panelY + 108;
        if (selectedCategory == 0) {
            drawModule(ctx, listX, listY, "Kill Aura",
                    "Automatyczny atak | PPM: ustawienia", KillAuraModule.enabled);
        } else {
            ctx.drawTextWithShadow(textRenderer,
                    "Moduly tej kategorii sa przygotowane jako lista do rozbudowy.",
                    listX + 4, listY + 10, MUTED);
        }

        ctx.drawTextWithShadow(textRenderer, "LPM: wlacz/wylacz   PPM: ustawienia   K: otworz GUI",
                panelX + 14, panelY + panelH - 20, MUTED);
        super.render(ctx, mouseX, mouseY, delta);
    }

    private void drawModule(DrawContext ctx, int x, int y, String name,
                            String description, boolean enabled) {
        ctx.fill(x, y, x + 390, y + 48, CARD);
        ctx.fill(x, y, x + 3, y + 48, enabled ? PURPLE : 0xFF51485B);
        ctx.drawTextWithShadow(textRenderer, name, x + 12, y + 9, 0xFFFFFFFF);
        ctx.drawTextWithShadow(textRenderer, description, x + 12, y + 25, MUTED);
        ctx.fill(x + 340, y + 14, x + 378, y + 34,
                enabled ? 0xFF7540A6 : 0xFF51485B);
        ctx.drawCenteredTextWithShadow(textRenderer, enabled ? "ON" : "OFF",
                x + 359, y + 20, 0xFFFFFFFF);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int tabY = panelY + 48;
        for (int i = 0; i < categories.length; i++) {
            int col = i % 5;
            int row = i / 5;
            int x = panelX + 14 + col * 80;
            int y = tabY + row * 27;
            if (mouseX >= x && mouseX <= x + 74 && mouseY >= y && mouseY <= y + 23) {
                selectedCategory = i;
                return true;
            }
        }

        int moduleX = panelX + 14;
        int moduleY = panelY + 108;
        if (selectedCategory == 0
                && mouseX >= moduleX && mouseX <= moduleX + 390
                && mouseY >= moduleY && mouseY <= moduleY + 48) {
            if (button == 0) {
                KillAuraModule.enabled = !KillAuraModule.enabled;
                return true;
            }
            if (button == 1) {
                client.setScreen(new KillAuraSettingsScreen(this));
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
