package de.firstmc.staffchat;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import de.firstmc.staffchat.commands.TeamChatCommand;

import java.util.logging.Logger;

@Plugin(id = "teamchat", name = "teamchat", version = "0.1.0-SNAPSHOT",
		url = "https://firstmc.de", description = "Velocity TeamChat", authors = {"MerryChristmas", "ExceptionThread"})
public class StaffChat {

	private final ProxyServer proxyServer;
	private final Logger logger;

	@Inject
	public StaffChat(ProxyServer proxyServer, Logger logger) {
		this.proxyServer = proxyServer;
		this.logger = logger;

		logger.info("Plugin started successfully.");
	}

	@Subscribe
	public void onProxyInitialize(ProxyInitializeEvent event) {
		final CommandManager commandManager = this.proxyServer.getCommandManager();

		CommandMeta teamChatCommandMeta = commandManager.metaBuilder("teamchat")
				.aliases("tc")
				.plugin(this)
				.build();

		commandManager.register(teamChatCommandMeta, new TeamChatCommand(this.proxyServer));

		logger.info("Plugin was initialized");
	}
}
