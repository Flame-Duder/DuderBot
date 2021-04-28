package events;

import main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.EnumSet;

public class TicketCommand extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        String user = event.getAuthor().getName();
        Guild guild = event.getGuild();
        TextChannel channel = event.getChannel();

        if (args[0].equalsIgnoreCase(Info.prefix + "ticket")) {
            if (!guild.getTextChannelsByName("ticket-" + user, true).isEmpty()) {
                EmbedBuilder error = new EmbedBuilder();
                error.setColor(Color.RED);
                error.setTitle("\uD83D\uDD34 You already have an opened ticket");
                channel.sendMessage(error.build()).queue();
            } else if (guild.getTextChannelsByName("ticket-" + user, true).isEmpty()) {

                TextChannel ticketChannel = guild.createTextChannel("ticket-" + user).addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null).addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL)).complete();

                EmbedBuilder ticketEmbed = new EmbedBuilder();
                ticketEmbed.setColor(Color.CYAN);
                ticketEmbed.setDescription("Hello " + event.getMember().getUser().getAsMention() + " a staff member will be with you shortly");


                channel.sendMessage("Successfully created new ticket!").queue();
                ticketChannel.sendMessage(ticketEmbed.build()).complete().addReaction("\uD83D\uDD12").queue();
            }
        }
    }

    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {

        if (!event.getUser().isBot()) {
            if (event.getReaction().getTextChannel().getName().startsWith("ticket-")) {
                if (event.getReaction().getReactionEmote().getAsReactionCode().equals("\uD83D\uDD12")) {
                    if (event.getMember().hasPermission(Permission.MANAGE_CHANNEL)) {
                        event.getChannel().delete().queue();
                    }
                    else {
                        EmbedBuilder noPerm = new EmbedBuilder();
                        noPerm.setColor(Color.RED);
                        noPerm.setTitle("\uD83D\uDD34 No Permission!");
                        noPerm.setDescription("You do not have permission to do this");
                        event.getChannel().sendMessage(noPerm.build()).queue();
                    }
                }
            }
        }
    }
}