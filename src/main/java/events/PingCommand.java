package events;

import main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class PingCommand extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date date = new Date();
        if (args[0].equalsIgnoreCase(Info.prefix + "ping")) {
            long ping = event.getJDA().getGatewayPing();
            EmbedBuilder pingEmbed = new EmbedBuilder();

            pingEmbed.setColor(Color.CYAN);
            pingEmbed.addField(":ping_pong: Pong! Current websocket ping: " + ping + "ms :ping_pong:", "", false);
            pingEmbed.setFooter("Pinged: " + Objects.requireNonNull(event.getMember()).getUser().getAsTag() + " • " + formatter.format(date));
            event.getChannel().sendMessage(pingEmbed.build()).queue();
        }
    }
}
