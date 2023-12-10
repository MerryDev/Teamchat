package de.firstmc.staffchat.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.AllArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public final class StaffChatCommand implements SimpleCommand {

	private final ProxyServer server;

	private static final String PERMISSION = "Staffchat.FirstMC";

	@Override
	public void execute(final Invocation invocation) {
		CommandSource source = invocation.source();
		String[] args = invocation.arguments();

		if (!(source instanceof Player player)) {
			source.sendMessage(Component.text("Do do this, you have to be a player.", NamedTextColor.RED));
			return;
		}

		if (!player.hasPermission(PERMISSION)) {
			player.sendMessage(Component.text("You don't have permission to use this command.", NamedTextColor.RED));
			return;
		}

		if (args.length == 0) {
			player.sendMessage(Component.text("Please use /staffchat <Message>!", NamedTextColor.RED));
			return;
		}

		final String message = String.join(" ", args);
		for (Player team : this.server.getAllPlayers()) {
			if (!team.hasPermission(PERMISSION)) continue;
			team.sendMessage(Component.text("[StaffChat] " + player.getUsername() + ": " + message, NamedTextColor.BLUE));
		}
	}

	@Override
	public CompletableFuture<List<String>> suggestAsync(Invocation invocation) {
		return CompletableFuture.supplyAsync(Collections::emptyList);
	}
}

