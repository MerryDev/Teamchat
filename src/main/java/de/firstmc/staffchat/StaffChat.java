package de.firstmc.staffchat;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;

@Plugin(id = "TeamChat", name = "TeamChat", version = "0.1.0-SNAPSHOT",
        url = "https://firstmc.de", description = "Velocity TeamChat", authors = {"MerryChristmas,ExceptionThread"})
public record StaffChat(ProxyServer proxyServer) {
    private static StaffChat instance;

    @Inject
    public StaffChat {
        instance = this;
    }

    @Subscribe
    public void handle(ProxyInitializeEvent event) {
        this.proxyServer.getCommandManager().register("tc", new TeamChatCommand());
        this.proxyServer.getCommandManager().register("tc", new StaffChatCommand());
    }

    public static StaffChat getInstance() {
        return instance;
    }
}
