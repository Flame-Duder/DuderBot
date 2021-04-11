package events;

import main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Objects;

public class ModHelp extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(Info.prefix + "mod") && args[1].equalsIgnoreCase("help")) {
            if (!Objects.requireNonNull(event.getMember()).hasPermission(Permission.MESSAGE_MANAGE)) {
                EmbedBuilder noPerm = new EmbedBuilder();
                noPerm.setColor(Color.RED);
                noPerm.setTitle("\uD83D\uDD34 No Permission!");
                noPerm.setDescription("You do not have permission to do this");
                event.getChannel().sendMessage(noPerm.build()).queue();
            }
            else if (Objects.requireNonNull(event.getMember()).hasPermission(Permission.MESSAGE_MANAGE)) {
                EmbedBuilder modHelpEmbed = new EmbedBuilder();
                modHelpEmbed.setTitle("List Of Commands");
                modHelpEmbed.addField("Clear: ", "`-clear [# of messages]`", false);
                modHelpEmbed.addField("kick: ", "`-kick [@name]`", false);
                modHelpEmbed.setColor(Color.CYAN);
                event.getChannel().sendMessage(modHelpEmbed.build()).queue();
            }
        }
    }
}
