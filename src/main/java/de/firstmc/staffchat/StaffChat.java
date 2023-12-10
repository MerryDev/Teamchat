package de.firstmc.staffchat;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import de.firstmc.staffchat.commands.TeamChatCommand;

import java.util.logging.Logger;

@Plugin(id = "TeamChat", name = "TeamChat", version = "0.1.0-SNAPSHOT",
		url = "https://firstmc.de", description = "Velocity TeamChat", authors = {"MerryChristmas,ExceptionThread"})
public class StaffChat {

	private final ProxyServer proxyServer;

	@Inject
	public StaffChat(ProxyServer proxyServer, Logger logger) {
		this.proxyServer = proxyServer;

		logger.info("Plugin started successfully.");
	}

	@Subscribe
	public void handle(ProxyInitializeEvent event) {
		this.proxyServer.getCommandManager().register("tc", new TeamChatCommand(proxyServer));
	}

	// sd

}
