package events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.List;

public class WelcomeMessage extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        if (event.getGuild().getTextChannelsByName("welcome", true).isEmpty()) {
            event.getGuild().createTextChannel("welcome").queue();
        } else {

            final List<TextChannel> channelList = event.getGuild().getTextChannelsByName("welcome", true);

            final TextChannel channel = channelList.get(0);

            String name = event.getMember().getAsMention();
            String guildName = event.getGuild().getName();

            EmbedBuilder welcomeEmbed = new EmbedBuilder();
            welcomeEmbed.setTitle("Welcome! " + event.getMember().getUser().getAsTag());
            welcomeEmbed.addField("", "Welcome, " + name + " to, " + guildName + "!", true);
            welcomeEmbed.setThumbnail(event.getMember().getUser().getAvatarUrl());
            welcomeEmbed.setColor(Color.CYAN);
            channel.sendMessage(welcomeEmbed.build()).queue();
        }
    }
}
