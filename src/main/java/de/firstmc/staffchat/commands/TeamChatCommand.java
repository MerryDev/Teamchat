package de.firstmc.staffchat.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.RawCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.AllArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

@AllArgsConstructor
public final class TeamChatCommand implements RawCommand {

	private final ProxyServer server;

	@Override
	public void execute(final Invocation invocation) {
		CommandSource source = invocation.source();

		if (!(source instanceof Player)) {
			source.sendMessage(Component.text("To do this, you have to be a Player.", NamedTextColor.RED));
			return;
		}

		for (Player player : this.server.getAllPlayers()) {
			if (!player.hasPermission("Teamchat.FirstMC")) continue;
			player.sendMessage(MiniMessage.miniMessage().deserialize(invocation.arguments(), TagResolver.standard())); // Allow the player to use tags to colorize the message
		}
	}

	@Override
	public boolean hasPermission(final Invocation invocation) {
		return invocation.source().hasPermission("Teamchat.FirstMC");
	}
}

