package com.gamerforea.eventhelper.util.event;

import com.gamerforea.eventhelper.EventHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public interface IEventUtil
{
    IEventUtil INSTANCE = EventHelper.SERVER_START ? new IEventUtilServerImpl() : new IEventUtilClientImpl();

    boolean cantBreak(@Nonnull EntityPlayer player, int x, int y, int z);

    boolean cantBreak(@Nonnull EntityPlayer player, double x, double y, double z);

    boolean cantDamage(@Nonnull Entity attacker, @Nonnull Entity victim);

    boolean cantInteract(@Nonnull EntityPlayer player, @Nullable ItemStack stack, int x, int y, int z, @Nonnull ForgeDirection side);

    boolean cantFromTo(@Nonnull World world, int fromX, int fromY, int fromZ, @Nonnull ForgeDirection direction);

    boolean cantTeleport(EntityPlayer player, World toWorld, double toX, double toY, double toZ);

    boolean cantTeleport(EntityPlayer player, World fromWorld, double fromX, double fromY, double fromZ, World toWorld, double toX, double toY, double toZ);

    boolean cantTeleport(Entity entity, World toWorld, double toX, double toY, double toZ);

    boolean cantTeleport(Entity entity, World fromWorld, double fromX, double fromY, double fromZ, World toWorld, double toX, double toY, double toZ);

    List<String> getRegions(World world, int x, int y, int z);

    boolean isInPrivate(@Nonnull World world, int x, int y, int z);

    boolean isPrivateMember(@Nonnull EntityPlayer player, double x, double y, double z);

    boolean isPrivateMember(@Nonnull EntityPlayer player, int x, int y, int z);

    boolean isPrivateOwner(@Nonnull EntityPlayer player, double x, double y, double z);

    boolean isPrivateOwner(@Nonnull EntityPlayer player, int x, int y, int z);

    boolean isInPrivate(@Nonnull Entity entity);

    boolean hasPermission(@Nullable EntityPlayer player, @Nonnull String permission);

    boolean hasPermission(@Nullable UUID playerId, @Nonnull String permission);

    boolean hasPermission(@Nullable String playerName, @Nonnull String permission);
}
