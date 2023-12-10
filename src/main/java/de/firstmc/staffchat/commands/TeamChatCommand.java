package de.firstmc.staffchat.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.AllArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

@AllArgsConstructor
public final class TeamChatCommand implements SimpleCommand {

	private final ProxyServer server;

	@Override
	public void execute(Invocation invocation) {
		CommandSource source = invocation.source();
		String[] args = invocation.arguments();

		if (args.length == 0) {
			source.sendMessage(Component.text("Please provide a message.", NamedTextColor.RED));
			return;
		}

		StringBuilder message = new StringBuilder();
		for (int i = 1; i <= args.length; i++) {
			message.append(args[i]).append(" ");
		}

		for (Player player : this.server.getAllPlayers()) {
			if (!player.hasPermission("Teamchat.FirstMC")) continue;

			player.sendMessage(MiniMessage.miniMessage().deserialize(message.toString(), TagResolver.standard()));
		}
	}

	@Override
	public boolean hasPermission(Invocation invocation) {
		return invocation.source().hasPermission("Teamchat.FirstMC");
	}
}
