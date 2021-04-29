package events;

import main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Region;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ServerInfo extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(Info.prefix + "info")) {

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

            EmbedBuilder inviteEmbed = new EmbedBuilder();

            String guildName = event.getGuild().getName();
            int guildMembers = event.getGuild().getMembers().size();
            String avatar = event.getGuild().getIconUrl();
            Region region = event.getGuild().getRegion();
            String guildOwner = Objects.requireNonNull(event.getGuild().getOwner()).getUser().getName();
            String creationDate = event.getGuild().getTimeCreated().format(fmt);

            inviteEmbed.setTitle(guildName + "'s discord server!");
            inviteEmbed.setThumbnail(avatar);
            inviteEmbed.setColor(Color.CYAN);
            inviteEmbed.addField("Owner:", guildOwner, false);
            inviteEmbed.addField("Member Count:", String.valueOf(guildMembers), false);
            inviteEmbed.addField("Region:", String.valueOf(region), false);
            inviteEmbed.addField("Creation Date:", creationDate, false);
            inviteEmbed.addField("Invite Link: ", event.getChannel().createInvite().setMaxAge(36000).complete().getUrl(), false);
            event.getChannel().sendMessage(inviteEmbed.build()).queue();
        }
    }
}
