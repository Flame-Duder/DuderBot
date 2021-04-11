package events;

import main.Info;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnlineCount extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Info.prefix + "online")) {
            int online = 0;
            for (int i = 0; i < event.getGuild().getMembers().size(); i++) {
                if (event.getGuild().getMembers().get(i).getOnlineStatus() == OnlineStatus.ONLINE || event.getGuild().getMembers().get(i).getOnlineStatus() == OnlineStatus.DO_NOT_DISTURB) {
                    online++;
                }
            }
            event.getChannel().sendMessage("There are currently " + online + "/" + event.getGuild().getMembers().size() + " members online").queue();
        }
    }
}
