package com.gamerforea.eventhelper;

import com.gamerforea.eventhelper.command.CommandReloadAllConfigs;
import com.gamerforea.eventhelper.config.ConfigUtils;
import com.gamerforea.eventhelper.util.EventUtils;
import com.gamerforea.eventhelper.util.RuntimeUtils;
import com.google.common.collect.Lists;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.FormattedMessage;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;

import java.io.File;
import java.util.List;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

@SuppressWarnings({"unchecked", "rawtypes"})
@Mod(modid = "EventHelper", name = "EventHelper", version = "@VERSION@", acceptableRemoteVersions = "*")
public final class EventHelper
{
	public static final boolean SERVER_START = RuntimeUtils.detectBukkit() && !RuntimeUtils.detectIdea();
	public static final Logger LOGGER = LogManager.getLogger("EventHelper");
	public static final File cfgDir = new File(Loader.instance().getConfigDir(), "Events");
	public static final List listeners = Lists.newArrayList();
	public static String craftPackage = "org.bukkit.craftbukkit.v1_7_R4";
	public static boolean explosions = true;
	public static boolean debug = true;

	@EventHandler
	public void onServerStart(FMLServerStartingEvent event)
	{
		if(SERVER_START) {
			onServerStartSage(event);
		}
	}

	@EventHandler
	public void serverStarted(FMLServerStartedEvent event)
	{
		if(SERVER_START) {
			serverStartedSafe(event);
		}
	}

	private void onServerStartSage(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandReloadAllConfigs());
	}

	private void serverStartedSafe(FMLServerStartedEvent event)
	{
		Configuration cfg = ConfigUtils.getConfig("EventHelper");
		String c = CATEGORY_GENERAL;
		craftPackage = cfg.getString("craftPackage", c, craftPackage, "CraftBukkit package (for reflection)");
		explosions = cfg.getBoolean("explosions", c, explosions, "Explosions enabled");
		debug = cfg.getBoolean("debug", c, debug, "Debugging enabled");
		cfg.save();

		Plugin wg = Bukkit.getPluginManager().getPlugin("WorldGuard");
		if(wg == null) {
			throw new RuntimeException("WorldGuard plugin not found");
		}
		listeners.addAll(HandlerList.getRegisteredListeners(wg));
		EventUtils.init();
	}

	public static void callEvent(Object event)
	{
		for (RegisteredListener listener : (List<RegisteredListener>)listeners)
		{
			try
			{
				listener.callEvent((Event) event);
			}
			catch (Throwable throwable)
			{
				if (debug)
					LOGGER.error("Failed event call", throwable);
			}
		}
	}

	public static void error(Throwable throwable, String message, Object... args)
	{
		if (debug)
			LOGGER.error(new FormattedMessage(message, args), throwable);
		else
			LOGGER.error(message, args);
	}
}
