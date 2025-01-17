package com.gamerforea.eventhelper.inject;

import com.gamerforea.eventhelper.util.InjectionUtils;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import lombok.SneakyThrows;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

public final class WGInjection
{
    @SneakyThrows
    public static PluginInjection getInjection()
	{
        Class<?> clazz = InjectionUtils.injectClass("WorldGuard", WGInjection.class);
        if(clazz == null) {
            throw new RuntimeException("something wrong while worldguard injection");
        }

        return  (PluginInjection) clazz.newInstance();
    }

    public static final class Inj implements PluginInjection
    {
        public boolean isInPrivate(World world, int x, int y, int z) {
            for (ProtectedRegion region : WorldGuardPlugin.inst().getRegionManager(world).getApplicableRegions(new Vector(x, y, z))) {
                if (!region.getId().equals(ProtectedRegion.GLOBAL_REGION))
                    return true;
            }
            return false;
        }

        public boolean isPrivateMember(Player player, int x, int y, int z) {
            WorldGuardPlugin wg = WorldGuardPlugin.inst();
            return wg.getRegionManager(player.getWorld()).getApplicableRegions(new Vector(x, y, z)).isMemberOfAll(wg.wrapPlayer(player, true));
        }

        public boolean isPrivateOwner(Player player, int x, int y, int z) {
            WorldGuardPlugin wg = WorldGuardPlugin.inst();
            return wg.getRegionManager(player.getWorld()).getApplicableRegions(new Vector(x, y, z)).isOwnerOfAll(wg.wrapPlayer(player, true));
        }

        public List<String> getRegions(World world, int x, int y, int z) {
            WorldGuardPlugin wg = WorldGuardPlugin.inst();
            return wg.getRegionManager(world).getApplicableRegionsIDs(new Vector(x, y, z));
        }
    }
}