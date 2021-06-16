package events;

import main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class UserEmbedCreator extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date date = new Date();

        if (args[0].equalsIgnoreCase(Info.prefix + "embed")) {
            if (args.length == 1) {
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(Color.RED);
                usage.setTitle("\uD83D\uDD34 Please specify the message you want to be embedded");
                usage.setDescription("Usage: `" + Info.prefix + "embed [message]`");
                event.getChannel().sendMessage(usage.build()).queue();
            } else {
                String message = event.getMessage().getContentRaw().replaceFirst("-embed", "");
                String userName = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getUser().getName());
                User user = event.getGuild().getMembersByName(userName, true).get(0).getUser();
                String avatar = user.getAvatarUrl();

                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.addField(message, "", false);
                embedBuilder.setColor(Color.CYAN);
                embedBuilder.setFooter("From: " + Objects.requireNonNull(event.getMember()).getUser().getAsTag() + " • " + formatter.format(date), avatar);
                event.getChannel().sendMessage(embedBuilder.build()).queue();
            }
        }
    }
}
