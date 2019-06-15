package com.sicaga.finchbot.event;

import com.sicaga.finchbot.FinchBot;
import com.sicaga.finchbot.util.RoleEmotePair;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MessageReactionRemoveListener extends ListenerAdapter {
    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
        Guild sicaga = FinchBot.jda.getGuildById(464369469595058176L);

        HashMap<String, ArrayList<RoleEmotePair>> trackedMessages = FinchBot.config.getTrackedMessages();
        Set<String> keys = trackedMessages.keySet();

        if (keys.contains(event.getMessageId())) {
            // The message is one that we're tracking
            ArrayList<RoleEmotePair> reps = trackedMessages.get(event.getMessageId());
            for (RoleEmotePair pair : reps) {
                if (event.getReactionEmote().getId() == null) {
                    if (event.getReactionEmote().getName().equals(pair.getEmote()) && !pair.isShouldRemoveEmoteAferAdding()) {
                        Role role = pair.getRole();
                        GuildController gc = new GuildController(sicaga);
                        gc.removeSingleRoleFromMember(event.getMember(), role).queue(); // Remove the role
                    }
                } else {
                    if (event.getReactionEmote().getId().equals(pair.getEmote()) && !pair.isShouldRemoveEmoteAferAdding()) {
                        Role role = pair.getRole();
                        GuildController gc = new GuildController(sicaga);
                        gc.removeSingleRoleFromMember(event.getMember(), role).queue(); // Remove the role

                    }
                }
            }
        }
        // Not a tracked message, just return
    }
}
