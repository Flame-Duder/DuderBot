package events;

import main.Info;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class UptimeCommand extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(Info.prefix + "uptime")) {

            RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
            long uptime = runtimeMXBean.getUptime();
            long uptimeInSeconds = uptime / 1000;
            long numberOfHours = uptimeInSeconds / (60 * 60);
            long numberOfMinutes = (uptimeInSeconds / 60) - (numberOfHours * 60);
            long numberOfSeconds = uptimeInSeconds % 60;

            event.getChannel().sendMessageFormat("My uptime is `%s hours, %s minutes, %s seconds`", numberOfHours, numberOfMinutes, numberOfSeconds).queue();
        }
    }
}
