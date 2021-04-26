package events;

import main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class HelpCommand extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(Info.prefix + "help")) {
            EmbedBuilder helpEmbed = new EmbedBuilder();
            helpEmbed.setTitle("List Of Commands");
            helpEmbed.addField("Suggestion: ", Info.prefix + "suggest [suggestion]", false);
            helpEmbed.addField("User Information: ", Info.prefix + "user [name]", false);
            helpEmbed.addField("Server Information: ", Info.prefix + "info", false);
            helpEmbed.addField("Online Members: ", Info.prefix + "online", false);
            helpEmbed.addField("Ping: ", Info.prefix + "ping", false);
            helpEmbed.addField("Uptime: ", "-uptime", false);
            helpEmbed.addField("Ticket: ", "-ticket", false);
            helpEmbed.addField("Calculator: ", Info.prefix +"calculator [number] [operator] [number] or " + Info.prefix + "calc [number] [operator] [number]", false);
            helpEmbed.setColor(Color.CYAN);
            event.getChannel().sendMessage(helpEmbed.build()).queue();
        }
    }
}
