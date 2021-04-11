package events;

import main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Objects;

public class KickCommand extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(Info.prefix + "kick")) {
            if (Objects.requireNonNull(event.getMember()).hasPermission(Permission.KICK_MEMBERS)) {
                if (args.length < 2) {
                    EmbedBuilder usage = new EmbedBuilder();
                    usage.setColor(Color.RED);
                    usage.setTitle("\uD83D\uDD34 Invalid Usage");
                    usage.setDescription("Usage: `" + Info.prefix + "kick [@name]`");
                    event.getChannel().sendMessage(usage.build()).queue();
                }
                else if (args.length == 2 && args[0].equalsIgnoreCase(Info.prefix + "kick")) {

                    Member name = event.getMessage().getMentionedMembers().get(0);
                    String realName = name.getUser().getName();

                    EmbedBuilder successKick = new EmbedBuilder();
                    successKick.setColor(Color.GREEN);
                    successKick.setTitle(":white_check_mark: Successfully kicked " + realName);
                    event.getGuild().kick(name, "You have been kicked!").queue();
                    event.getChannel().sendMessage(successKick.build()).queue();
                }
            }
            else if (!event.getMember().hasPermission(Permission.KICK_MEMBERS)) {
                EmbedBuilder noPerm = new EmbedBuilder();
                noPerm.setColor(Color.RED);
                noPerm.setTitle("\uD83D\uDD34 No Permission!");
                noPerm.setDescription("You do not have permission to do this");
                event.getChannel().sendMessage(noPerm.build()).queue();
            }
        }
    }
}
