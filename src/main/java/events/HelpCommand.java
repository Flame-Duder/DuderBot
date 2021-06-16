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
            helpEmbed.setTitle("Help Menu");
            helpEmbed.addField(":video_game: Fun ", Info.prefix + "help fun", true);
            helpEmbed.addField(":information_source: Info ", Info.prefix + "help info", true);
            helpEmbed.addField(":tools: Tools ", Info.prefix + "help tools", true);
            helpEmbed.setColor(Color.CYAN);
            event.getChannel().sendMessage(helpEmbed.build()).queue();
        }
        if (args[0].equalsIgnoreCase(Info.prefix + "help") && args[1].equalsIgnoreCase("fun")) {
            EmbedBuilder helpEmbed = new EmbedBuilder();
            helpEmbed.setTitle("List Of Commands");
            helpEmbed.addField("Calculator: ", Info.prefix + "calc(ulator) [number] [operator] [number]", false);
            helpEmbed.addField("8ball: ", Info.prefix + "8ball [message]", false);
            helpEmbed.addField("Coinflip: ", Info.prefix + "coinflip or " + Info.prefix + "cf", false);
            helpEmbed.setColor(Color.CYAN);
            event.getChannel().sendMessage(helpEmbed.build()).queue();
        }
        if (args[0].equalsIgnoreCase(Info.prefix + "help") && args[1].equalsIgnoreCase("info")) {
            EmbedBuilder helpEmbed = new EmbedBuilder();
            helpEmbed.setTitle("List Of Commands");
            helpEmbed.addField("User Information: ", Info.prefix + "user [name]", false);
            helpEmbed.addField("Server Information: ", Info.prefix + "info", false);
            helpEmbed.addField("Online Members: ", Info.prefix + "online", false);
            helpEmbed.addField("Uptime: ", Info.prefix + "uptime", false);
            helpEmbed.setColor(Color.CYAN);
            event.getChannel().sendMessage(helpEmbed.build()).queue();
        }
        if (args[0].equalsIgnoreCase(Info.prefix + "help") && args[1].equalsIgnoreCase("tools")) {
            EmbedBuilder helpEmbed = new EmbedBuilder();
            helpEmbed.setTitle("List Of Commands");
            helpEmbed.addField("Suggestion: ", Info.prefix + "suggest [suggestion]", false);
            helpEmbed.addField("Ping: ", Info.prefix + "ping", false);
            helpEmbed.addField("Ticket: ", Info.prefix + "ticket", false);
            helpEmbed.setColor(Color.CYAN);
            event.getChannel().sendMessage(helpEmbed.build()).queue();
        }
    }
}
