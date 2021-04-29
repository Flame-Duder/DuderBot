package events;

import main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class EightBallFortune extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Info.prefix + "8ball")) {
            if (args.length == 1) {
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(Color.RED);
                usage.setTitle("\uD83D\uDD34 Invalid Usage");
                usage.setDescription("Usage: `" + Info.prefix + "8ball [message]`");
                event.getChannel().sendMessage(usage.build()).queue();
            }
            if (args.length > 1) {
                String answer;

                double solutionInt = Math.round((Math.random() + 1) * (Math.random() + 1) * (Math.random() + 1));                // 1, 2, 4, 6, 8

                switch ((int) solutionInt) {
                    case 1: answer = "Concentrate and ask again";
                        event.getMessage().reply(answer).queue();
                        break;
                    case 2: answer = "Yes";
                        event.getMessage().reply(answer).queue();
                        break;
                    case 3: answer = "Maybe";
                        event.getMessage().reply(answer).queue();
                        break;
                    case 4: answer = "No";
                        event.getMessage().reply(answer).queue();
                        break;
                    case 5: answer = "Ask again later";
                        event.getMessage().reply(answer).queue();
                        break;
                    case 6: answer = "Don't count on it";
                        event.getMessage().reply(answer).queue();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value please report: " + solutionInt);
                }
            }
        }
    }
}
