package events;

import main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class InfoEmbed extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date date = new Date();
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args.length == 1 && args[0].equalsIgnoreCase(Info.prefix + "user")) {
            EmbedBuilder usage = new EmbedBuilder();
            usage.setColor(Color.RED);
            usage.setTitle("\uD83D\uDD34 Please specify a member");
            usage.setDescription("Usage: `" + Info.prefix + "user [name]`");
            event.getChannel().sendMessage(usage.build()).queue();
        } else if (args.length == 2 && args[0].equalsIgnoreCase(Info.prefix + "user")) {

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

            String userName = args[1];
            User user = event.getGuild().getMembersByName(userName, true).get(0).getUser();
            String avatar = user.getAvatarUrl();
            EmbedBuilder avatarEmbed = new EmbedBuilder();

            String nameString;
            nameString = event.getGuild().getMembersByName(userName, true).get(0).getTimeJoined().format(fmt);

            avatarEmbed.setTitle(userName + "'s info");
            avatarEmbed.setThumbnail(avatar);
            avatarEmbed.setColor(Color.CYAN);
            avatarEmbed.addField("Name:", user.getName(), false);
            avatarEmbed.addField("Online Status: ", event.getGuild().getMembersByName(userName, true).get(0).getOnlineStatus().toString(), false);
            avatarEmbed.addField("First Joined:", nameString, false);
            avatarEmbed.setFooter("Request was made @ " + formatter.format(date), event.getGuild().getIconUrl());
            event.getChannel().sendMessage(avatarEmbed.build()).queue();
        }
    }
}
