package events;

import main.Info;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class CalculatorCommand extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Info.prefix + "calculate") || args[0].equalsIgnoreCase(Info.prefix + "calc")) {
            if (args.length < 4) {
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(Color.RED);
                usage.setTitle("\uD83D\uDD34 Invalid Usage");
                usage.setDescription("Usage: `" + Info.prefix + "calculate [number] [operator] [number]`");
                event.getChannel().sendMessage(usage.build()).queue();
            }
            if (args.length == 4) {
                if (!args[2].equalsIgnoreCase("+") && args[2].equalsIgnoreCase("-") && args[2].equalsIgnoreCase("*") && args[2].equalsIgnoreCase("/")) {
                    EmbedBuilder usage = new EmbedBuilder();
                    usage.setColor(Color.RED);
                    usage.setTitle("\uD83D\uDD34 Invalid Usage");
                    usage.setDescription("`Please Use: + for addition, - for subtraction, * for multiplication, or / for division, ^ for exponents`");
                    event.getChannel().sendMessage(usage.build()).queue();
                } else {
                    double firstNumber = Double.parseDouble(args[1]);
                    double secondNumber = Double.parseDouble(args[3]);

                    if (args[2].equalsIgnoreCase("+")) {
                        double solution = firstNumber + secondNumber;
                        event.getChannel().sendMessage("Solution: " + solution).queue();
                    }
                    if (args[2].equalsIgnoreCase("-")) {
                        double solution = firstNumber - secondNumber;
                        event.getChannel().sendMessage("Solution: " + solution).queue();
                    }
                    if (args[2].equalsIgnoreCase("*")) {
                        double solution = firstNumber * secondNumber;
                        event.getChannel().sendMessage("Solution: " + solution).queue();
                    }
                    if (args[2].equalsIgnoreCase("/")) {
                        double solution = firstNumber / secondNumber;
                        event.getChannel().sendMessage("Solution: " + solution).queue();
                    }
                    if (args[2].equalsIgnoreCase("^")) {
                        double solution = Math.pow(firstNumber, secondNumber);
                        event.getChannel().sendMessage("Solution: " + solution).queue();
                    }
                }
            }
        }
    }
}
