package com.sicaga.finchbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sicaga.finchbot.FinchBot;
import com.sicaga.finchbot.util.CustomEmbedBuilder;

public class RoadmapCommand extends Command {
    public RoadmapCommand() {
        this.name = "roadmap";
        this.help = "Provides a link to FinchBot's roadmap of coming features";
        this.arguments = "";
        this.guildOnly = false;
        this.ownerCommand = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        CustomEmbedBuilder builder = new CustomEmbedBuilder();
        builder.addField("FinchBot Roadmap", "", false);

        event.reply(builder.build());
    }
}