package events;

import main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Objects;

public class BanCommand extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Info.prefix + "ban")) {
            if (!Objects.requireNonNull(event.getMember()).hasPermission(Permission.BAN_MEMBERS)) {
                EmbedBuilder noPerm = new EmbedBuilder();
                noPerm.setColor(Color.RED);
                noPerm.setTitle("\uD83D\uDD34 No Permission!");
                noPerm.setDescription("You do not have permission to do this");
                event.getChannel().sendMessage(noPerm.build()).queue();
            } else if (event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
                if (args.length != 3) {
                    EmbedBuilder usage = new EmbedBuilder();
                    usage.setColor(Color.RED);
                    usage.setTitle("\uD83D\uDD34 Invalid Usage");
                    usage.setDescription("Usage: `" + Info.prefix + "ban [@name] [duration]`");
                    event.getChannel().sendMessage(usage.build()).queue();
                } else {
                    int duration = Integer.parseInt(args[2]);
                    if (duration > 7 || duration < 0) {
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(Color.RED);
                        error.setTitle("\uD83D\uDD34 Error!");
                        error.setDescription("The duration must be less than 7 days and greater than 0");
                        event.getChannel().sendMessage(String.valueOf(duration)).queue();
                        event.getChannel().sendMessage(error.build()).queue();
                    } else {
                        Member target = event.getMessage().getMentionedMembers().get(0);
                        String realName = target.getUser().getName();

                        target.ban(duration).queue();

                        EmbedBuilder successBan = new EmbedBuilder();
                        successBan.setColor(Color.GREEN);
                        successBan.setTitle(":white_check_mark: Successfully banned " + realName + " for " + duration + " day(s)");
                        event.getChannel().sendMessage(successBan.build()).queue();
                    }
                }
            }
        }
    }
}
