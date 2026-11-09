package pl.carbclient.module;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/**
 * Test module for private worlds/servers. Use only where you have permission.
 */
public final class KillAuraModule {
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    public static boolean enabled = false;
    public static double range = 3.5;
    public static boolean targetPlayers = true;
    public static boolean targetMobs = true;
    public static boolean rotate = true;

    private static LivingEntity currentTarget;

    private KillAuraModule() {}

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!enabled || client.player == null || client.world == null
                    || client.interactionManager == null) {
                currentTarget = null;
                return;
            }

            currentTarget = findTarget();
            if (currentTarget == null) return;

            if (rotate) faceTarget(currentTarget);

            if (client.player.getAttackCooldownProgress(0.5F) >= 1.0F) {
                client.interactionManager.attackEntity(client.player, currentTarget);
                client.player.swingHand(Hand.MAIN_HAND);
            }
        });
    }

    private static LivingEntity findTarget() {
        if (MC.player == null || MC.world == null) return null;

        return MC.world.getEntitiesByClass(
                LivingEntity.class,
                MC.player.getBoundingBox().expand(range),
                entity -> entity != MC.player
                        && entity.isAlive()
                        && !entity.isRemoved()
                        && MC.player.distanceTo(entity) <= range
                        && isAllowed(entity)
        ).stream().min((a, b) -> Double.compare(
                MC.player.squaredDistanceTo(a),
                MC.player.squaredDistanceTo(b)
        )).orElse(null);
    }

    private static boolean isAllowed(LivingEntity entity) {
        if (entity instanceof PlayerEntity) return targetPlayers;
        return targetMobs;
    }

    private static void faceTarget(LivingEntity target) {
        if (MC.player == null) return;

        Vec3d from = MC.player.getEyePos();
        Vec3d to = target.getBoundingBox().getCenter();
        double dx = to.x - from.x;
        double dy = to.y - from.y;
        double dz = to.z - from.z;
        double horizontal = Math.sqrt(dx * dx + dz * dz);

        float yaw = (float) (Math.toDegrees(Math.atan2(dz, dx)) - 90.0);
        float pitch = (float) -Math.toDegrees(Math.atan2(dy, horizontal));

        MC.player.setYaw(MC.player.getYaw()
                + MathHelper.clamp(MathHelper.wrapDegrees(yaw - MC.player.getYaw()), -25.0F, 25.0F));
        MC.player.setPitch(MC.player.getPitch()
                + MathHelper.clamp(pitch - MC.player.getPitch(), -20.0F, 20.0F));
    }

    public static void setRange(double value) {
        range = MathHelper.clamp(value, 1.0, 6.0);
    }

    public static LivingEntity getCurrentTarget() {
        return currentTarget;
    }
}
