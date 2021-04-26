package events;

import main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

public class TicketCommand extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        String user = event.getAuthor().getName();
        Guild guild = event.getGuild();

        if (args[0].equalsIgnoreCase(Info.prefix + "ticket")) {
            if (!guild.getTextChannelsByName("ticket-" + user, true).isEmpty()) {
                EmbedBuilder error = new EmbedBuilder();
                error.setColor(Color.RED);
                error.setTitle("\uD83D\uDD34 You already have an opened ticket");
                event.getChannel().sendMessage(error.build()).queue();
            }
            else if (guild.getTextChannelsByName("ticket-" + user, true).isEmpty()){
                event.getChannel().sendMessage("Successfully created new ticket!").queue();
                guild.createTextChannel("ticket-" + user).addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null).addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL)).queue();
                guild.getTextChannelsByName("ticket-" + user, true).get(0).sendMessage("test").queueAfter(3, TimeUnit.SECONDS);
            }
        }
    }
}
