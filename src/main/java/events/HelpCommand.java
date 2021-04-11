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
            helpEmbed.addField("Suggestion: ", "-suggest [suggestion]", false);
            helpEmbed.addField("User Information: ", "-user [name]", false);
            helpEmbed.addField("Server Information: ", "-info", false);
            helpEmbed.addField("Online Members: ", "-online", false);
            helpEmbed.addField("Ping: ", "-ping", false);
            helpEmbed.addField("Uptime: ", "-uptime", false);
            helpEmbed.setColor(Color.CYAN);
            event.getChannel().sendMessage(helpEmbed.build()).queue();
        }
    }
}
