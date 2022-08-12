package com.gamerforea.eventhelper.util.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IEventUtilClientImpl implements IEventUtil
{
    @Override
    public boolean cantBreak(@Nonnull EntityPlayer player, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean cantBreak(@Nonnull EntityPlayer player, double x, double y, double z) {
        return false;
    }

    @Override
    public boolean cantDamage(@Nonnull Entity attacker, @Nonnull Entity victim) {
        return false;
    }

    @Override
    public boolean cantInteract(@Nonnull EntityPlayer player, @Nullable ItemStack stack, int x, int y, int z, @Nonnull ForgeDirection side) {
        return false;
    }

    @Override
    public boolean cantFromTo(@Nonnull World world, int fromX, int fromY, int fromZ, @Nonnull ForgeDirection direction) {
        return false;
    }

    @Override
    public boolean cantTeleport(EntityPlayer player, World toWorld, double toX, double toY, double toZ) {
        return false;
    }

    @Override
    public boolean cantTeleport(EntityPlayer player, World fromWorld, double fromX, double fromY, double fromZ, World toWorld, double toX, double toY, double toZ) {
        return false;
    }

    @Override
    public boolean cantTeleport(Entity entity, World toWorld, double toX, double toY, double toZ) {
        return false;
    }

    @Override
    public boolean cantTeleport(Entity entity, World fromWorld, double fromX, double fromY, double fromZ, World toWorld, double toX, double toY, double toZ) {
        return false;
    }

    @Override
    public List<String> getRegions(World world, int x, int y, int z) {
        return new ArrayList<>();
    }

    @Override
    public boolean isInPrivate(@Nonnull World world, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean isPrivateMember(@Nonnull EntityPlayer player, double x, double y, double z) {
        return true;
    }

    @Override
    public boolean isPrivateMember(@Nonnull EntityPlayer player, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean isPrivateOwner(@Nonnull EntityPlayer player, double x, double y, double z) {
        return true;
    }

    @Override
    public boolean isPrivateOwner(@Nonnull EntityPlayer player, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean isInPrivate(@Nonnull Entity entity) {
        return false;
    }

    @Override
    public boolean hasPermission(@Nullable EntityPlayer player, @Nonnull String permission) {
        return true;
    }

    @Override
    public boolean hasPermission(@Nullable UUID playerId, @Nonnull String permission) {
        return true;
    }

    @Override
    public boolean hasPermission(@Nullable String playerName, @Nonnull String permission) {
        return true;
    }
}
