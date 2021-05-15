package io.github.slimjar.example.spigot;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public final class ChatListener implements Listener {

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent chatEvent) {
        HttpResponse<JsonNode> response = Unirest.post("http://httpbin.org/post")
                .header("accept", "application/json")
                .queryString("apiKey", "123")
                .field("parameter", "value")
                .field("firstname", "Gary")
                .asJson();
        for (Player recipient : chatEvent.getRecipients()) {
            recipient.sendMessage(response.getBody().toPrettyString());
        }
    }

}
