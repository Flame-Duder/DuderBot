package events;

import main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.List;
import java.util.Objects;


public class ClearMessage extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String args[] = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(Info.prefix + "clear")) {
            if (Objects.requireNonNull(event.getMember()).hasPermission(Permission.MESSAGE_MANAGE)) {
                if (args.length < 2) {
                    EmbedBuilder usage = new EmbedBuilder();
                    usage.setColor(Color.RED);
                    usage.setTitle("Invalid Usage");
                    usage.setDescription("Usage: `" + Info.prefix + "clear [# of messages]`");
                    event.getChannel().sendMessage(usage.build()).queue();
                } else {
                    try {
                        List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                        event.getChannel().deleteMessages(messages).queue();

                        EmbedBuilder success = new EmbedBuilder();
                        success.setColor(Color.GREEN);
                        success.setTitle(":white_check_mark: Successfully deleted " + args[1] + " messages");
                        event.getChannel().sendMessage(success.build()).queue();
                    } catch (IllegalArgumentException e) {
                        if (e.toString().startsWith("java.lang..IllegalArgumentException: Message retrieval")) {
                            EmbedBuilder error = new EmbedBuilder();
                            error.setColor(Color.RED);
                            error.setTitle("\uD83D\uDD34 Too Many Messages Selected");
                            error.setDescription("Between 1-100 messages can be deleted at one time");
                            event.getChannel().sendMessage(error.build()).queue();
                        } else if (args.length > 2) {
                            EmbedBuilder error = new EmbedBuilder();
                            error.setColor(Color.RED);
                            error.setTitle("\uD83D\uDD34 Invalid Usage");
                            error.setDescription("Usage: `" + "-clear [# of messages]`");
                            event.getChannel().sendMessage(error.build()).queue();
                        }
                    }
                }
            } else if (!Objects.requireNonNull(event.getMember()).hasPermission(Permission.MESSAGE_MANAGE)){
                EmbedBuilder noPerm = new EmbedBuilder();
                noPerm.setColor(Color.RED);
                noPerm.setTitle("\uD83D\uDD34 No Permission!");
                noPerm.setDescription("You do not have permission to do this");
                event.getChannel().sendMessage(noPerm.build()).queue();
            }
        }
    }
}
