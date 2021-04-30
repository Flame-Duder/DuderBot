package events;

import main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Objects;

public class CoinFlip extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Info.prefix + "coinflip") || args[0].equalsIgnoreCase(Info.prefix + "cf")) {

            double flipResult = Math.round(Math.random());
            String flipResultString = "";
            String thumbnail = "";

            String userName = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getUser().getName());
            User user = event.getGuild().getMembersByName(userName, true).get(0).getUser();
            String avatar = user.getAvatarUrl();

            if (flipResult == 0.0) {
                flipResultString = "Tails";
                thumbnail = "https://cdn.discordapp.com/attachments/637105382631669760/675097748747452431/tails.png";
            }
            else if (flipResult == 1.0){
                flipResultString = "Heads";
                thumbnail = "https://cdn.discordapp.com/attachments/637105382631669760/675097745295540244/heads.png";
            }

            EmbedBuilder flipEmbed = new EmbedBuilder();
            flipEmbed.setTitle("Coin Flipped");
            flipEmbed.setThumbnail(thumbnail);
            flipEmbed.appendDescription("Result: " + flipResultString);
            flipEmbed.setFooter("Flipped By: " + event.getMember().getUser().getAsTag(), avatar);
            flipEmbed.setColor(Color.CYAN);
            event.getChannel().sendMessage(flipEmbed.build()).queue();
        }
    }
}
