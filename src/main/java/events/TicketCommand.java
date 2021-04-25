package events;

import main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class TicketCommand extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        String user = event.getAuthor().toString();

        if (args[0].equalsIgnoreCase(Info.prefix + "ticket")) {
            if (!event.getGuild().getTextChannelsByName("ticket-" + user, true).isEmpty()) {
                EmbedBuilder error = new EmbedBuilder();
                error.setColor(Color.RED);
                error.setTitle("\uD83D\uDD34 You already have an opened ticket");
                event.getChannel().sendMessage(error.build()).queue();
            }
            else {
                event.getChannel().sendMessage("Successfully created new ticket!").queue();
                event.getGuild().createTextChannel("ticket-" + user).queue();
            }
        }
    }
}
